package com.it.rabbitmq.base.myConsumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MyConsumer extends DefaultConsumer {

	public MyConsumer(Channel channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		System.out.println(consumerTag);
		System.out.println(envelope);
		System.out.println(properties);
		System.out.println(new String(body));
	}

}
