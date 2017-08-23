/**
 * 
 */
package com.metamagic.desire.issue;

import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;

/**
 * @author Mahesh Pardeshi
 *
 */
@Configuration
public class AxonConfiguration {

	@Bean
	public SpringAMQPMessageSource issueEvents(Serializer serializer) {
		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

			@RabbitListener(queues = "IssueEvents")

			@Override
			public void onMessage(Message arg0, Channel arg1) throws Exception {
				super.onMessage(arg0, arg1);
			}
		};
	}
}
