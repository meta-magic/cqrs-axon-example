package com.banking.app;

import javax.persistence.EntityManager;

import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.EventHandlerInvoker;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.banking.app.aggregate.Account;
import com.banking.app.eventHandlers.AccountReplayEventHandler;

@Configuration
public class AppConfiguration {

	@Autowired
	private EventStore eventStore;
	
	@Bean
	public AggregateFactory<Account> aggregateFactory(){
		return new GenericAggregateFactory<Account>(Account.class);
	}
	
	@Bean
	public EventCountSnapshotTriggerDefinition countSnapshotTriggerDefinition(){
		return new EventCountSnapshotTriggerDefinition(snapShotter(), 3);
	}
	
	@Bean
	public Snapshotter snapShotter(){
		return new AggregateSnapshotter(eventStore, aggregateFactory());
	}
	
	@Bean
	public EventSourcingRepository<Account> accountRepository(){
		return new EventSourcingRepository<>(aggregateFactory(), eventStore, countSnapshotTriggerDefinition());
	}
	
	/* EVENT REPLAY CONFIG */
	
	/* AMQP QUEUE, EXCHANGE AND BINDING CONFIG */
	
	@Bean
	public Queue queue(){
		return QueueBuilder.durable("accountEvents").build();
	}
	
	@Bean
	public Exchange exchange(){
		return ExchangeBuilder.fanoutExchange("accountEvents").build();
	}
	
	@Bean
	public Binding binding(){
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}
	
	@Autowired
	private void configure(AmqpAdmin amqpAdmin){
		amqpAdmin.declareQueue(queue());
		amqpAdmin.declareExchange(exchange());
		amqpAdmin.declareBinding(binding());
	}
}
