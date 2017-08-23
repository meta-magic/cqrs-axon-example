package com.ecomm.app;


import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.eventhandling.saga.repository.DefaultMongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.spring.messaging.InboundEventMessageChannelAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecomm.app.aggregate.OrderAggregate;
import com.ecomm.app.aggregate.PaymentAggregate;
import com.ecomm.app.aggregate.ShippingAggregate;
import com.ecomm.app.channel.SinkChannel;
import com.ecomm.app.sagas.OrderProcessingSaga;
import com.mongodb.MongoClient;

@Configuration
@EnableBinding(SinkChannel.class)
public class Config {

	@Autowired
	private SinkChannel sinkChannel;

	@Autowired
	private MongoClient mongoClient;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${mongodb.sagas.collection.name}")
	private String sagasCollectionName;

	@Value("${mongodb.events.collection.name}")
	private String mongoEventCollectionName;

	@Value("${mongodb.events.snapshot.collection.name}")
	private String mongoEventSnapshotCollectionName;
	
	@Bean
	public InboundEventMessageChannelAdapter inboundEventMessageChannelAdapter() {
		InboundEventMessageChannelAdapter inboundEventMessageChannelAdapter = new InboundEventMessageChannelAdapter();
		sinkChannel.messageChannel().subscribe(inboundEventMessageChannelAdapter);
		return inboundEventMessageChannelAdapter;
	}
	
	@Bean
	public EventBus eventBus() {
		return eventStore();
	}

	@Bean
	public MongoTemplate sagaMongoTemplate() {
		return new DefaultMongoTemplate(mongoClient, database, sagasCollectionName);
	}

	@Bean
	public SagaStore<Object> mongoSagaStore() {
		return new MongoSagaStore(sagaMongoTemplate());
	}
	
	@Bean
	public SagaConfiguration<OrderProcessingSaga> orderProcessingSagaSagaConfiguration() {
		return SagaConfiguration.subscribingSagaManager(OrderProcessingSaga.class, c -> inboundEventMessageChannelAdapter());
	}

	@Bean
	public org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate aggregateMongoTemplate() {
		return new org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate(mongoClient, database,
				mongoEventCollectionName, mongoEventSnapshotCollectionName);
	}

	@Bean
	public EventStore eventStore() {
		return new EmbeddedEventStore(mongoEventStorageEngine());
	}

	@Bean
	public EventStorageEngine mongoEventStorageEngine() {
		return new MongoEventStorageEngine(aggregateMongoTemplate());
	}

	@Bean
	public EventSourcingRepository<OrderAggregate> orderAggregateEventSourcingRepository() {
		return new EventSourcingRepository<OrderAggregate>(OrderAggregate.class, eventStore());
	}

	@Bean
	public EventSourcingRepository<ShippingAggregate> shippingAggregateEventSourcingRepository() {
		return new EventSourcingRepository<ShippingAggregate>(ShippingAggregate.class, eventStore());
	}

	@Bean
	public EventSourcingRepository<PaymentAggregate> paymentAggregateEventSourcingRepository() {
		return new EventSourcingRepository<PaymentAggregate>(PaymentAggregate.class, eventStore());
	}

}
