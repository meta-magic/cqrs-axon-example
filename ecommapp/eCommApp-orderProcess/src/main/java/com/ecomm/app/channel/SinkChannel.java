package com.ecomm.app.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SinkChannel {

	String CHANNEL = "outputchannel";
	
	@Input(CHANNEL)
	SubscribableChannel messageChannel();
}
