package com.ecomm.app.messaging.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SourceChannel {
	
	String OUT_PUT_CHANNEL = "outputchannel";

	@Output(OUT_PUT_CHANNEL)
	MessageChannel messageChannel();
	
}
