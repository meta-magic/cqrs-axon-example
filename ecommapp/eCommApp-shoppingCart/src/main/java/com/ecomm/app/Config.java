package com.ecomm.app;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
import org.axonframework.eventhandling.SubscribingEventProcessor;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.DefaultParameterResolverFactory;
import org.axonframework.messaging.annotation.MultiParameterResolverFactory;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;
import org.axonframework.spring.config.annotation.SpringBeanParameterResolverFactory;
import org.axonframework.spring.messaging.OutboundEventMessageChannelAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.ecomm.app.aggregate.ShoppingCart;
import com.ecomm.app.events.OrderPlacedEvent;
import com.ecomm.app.messaging.channel.SourceChannel;
import com.mongodb.MongoClient;

@Configuration
@EnableBinding(SourceChannel.class)
public class Config {

	@Value("${spring.data.mongodb.database}")
    private String database;
	
	@Value("${mongodb.events.collection.name}")
	private String mongoEventCollectionName;
	
	@Value("${mongodb.events.snapshot.collection.name}")
	private String mongoEventSnapshotCollectionName;
	
	@Autowired 
	private MongoClient mongoClient;
	
	@Autowired
	private EventStore eventStore;
	
	@Autowired
	@Qualifier(SourceChannel.OUT_PUT_CHANNEL)
	private MessageChannel messageChannel;
	
	@Bean
	public MongoTemplate axonMongoTemplate(){
		return new DefaultMongoTemplate(mongoClient, database, mongoEventCollectionName, mongoEventSnapshotCollectionName);
	}
	
	@Bean
	public AggregateFactory<ShoppingCart> shoppingCartAggregateFactory(){
		return new GenericAggregateFactory<ShoppingCart>(ShoppingCart.class);
	}
	
	@Bean
	public EventStorageEngine eventStorageEngine(){
		return new MongoEventStorageEngine(axonMongoTemplate());
	}
	
	@Bean
	public EventSourcingRepository<ShoppingCart> shoppingCartRepository(){
		return new EventSourcingRepository<ShoppingCart>(shoppingCartAggregateFactory(), eventStore, multiParameterResolverFactory(), snapshotTriggerDefinition());
	}
	
	/* SNAPSHOTING CONFIG */
	@Bean
	public Snapshotter snapshotter(){
		List<AggregateFactory<?>> aggregateList = new ArrayList<>();
		aggregateList.add(shoppingCartAggregateFactory());
		return new AggregateSnapshotter(eventStore, aggregateList, multiParameterResolverFactory());
	}
	
	@Bean
	public EventCountSnapshotTriggerDefinition snapshotTriggerDefinition(){
		return new EventCountSnapshotTriggerDefinition(snapshotter(), 5);
	}
	
	@Autowired
	public void configure(EventHandlingConfiguration eventHandlingConfiguration){
		eventHandlingConfiguration.registerSubscribingEventProcessor("testprocessor", c -> eventStore);
	}
	
	/* SPRING BEAN INJECTION CONFIG */
	
	@Bean
	public SpringBeanParameterResolverFactory springBeanParameterResolverFactory(){
		return new SpringBeanParameterResolverFactory();
	}
	
	@Bean
	public DefaultParameterResolverFactory defaultParameterResolverFactory(){
		return new DefaultParameterResolverFactory();
	}
	
	@Bean
	public MultiParameterResolverFactory multiParameterResolverFactory(){
		List<ParameterResolverFactory> delegates = new ArrayList<>();
		delegates.add(defaultParameterResolverFactory());
		delegates.add(springBeanParameterResolverFactory());
		MultiParameterResolverFactory multiParameterResolverFactory = new MultiParameterResolverFactory(delegates);
		return multiParameterResolverFactory;
	}
	
	/* AXON MESSAGING CONFIG */
	
	@Bean
	public OutboundEventMessageChannelAdapter outboundEventMessageChannelAdapter(){
		return new OutboundEventMessageChannelAdapter(eventStore, messageChannel, s -> s.getPayloadType().equals(OrderPlacedEvent.class));
	}
	
}
