package com.it.rabbitmq.base.qos;

import java.io.IOException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MyConsumer extends DefaultConsumer {

	private Channel channel;
	
	public MyConsumer(Channel channel) {
		super(channel);
		this.channel = channel;
	}
	
	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		System.out.println(consumerTag);
		System.out.println(envelope);
		System.out.println(properties);
		System.out.println(new String(body));
		
		channel.basicAck(envelope.getDeliveryTag(), false);
	}

}
