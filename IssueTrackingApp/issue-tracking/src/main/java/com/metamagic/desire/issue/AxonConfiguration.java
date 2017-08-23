/**
 * 
 */
package com.metamagic.desire.issue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mahesh Pardeshi
 *
 */
@Configuration
public class AxonConfiguration {

	@Value("${axon.amqp.exchange}")
	private String exchange;

	private final static Logger logger = LoggerFactory.getLogger(AxonConfiguration.class);

	@Bean
	public Exchange exchange() {
		logger.info(exchange + " AMQP Exchange Registering ");
		return ExchangeBuilder.fanoutExchange(exchange).build();
	}

	@Bean
	public Queue queue() {
		return QueueBuilder.durable(exchange).build();
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}

	@Autowired
	public void configure(AmqpAdmin amqpAdmin) {
		amqpAdmin.declareExchange(exchange());
		amqpAdmin.declareQueue(queue());
		amqpAdmin.declareBinding(binding());
	}
}
