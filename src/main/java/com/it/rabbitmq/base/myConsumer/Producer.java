package com.it.rabbitmq.base.myConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Producer {
	
	private static ConnectionFactory factory = new ConnectionFactory();
	
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		factory.setHost("192.168.203.133");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		String exchange = "test_consumer_exchange";
		String routing = "consumer.save";
		String msg = "confirm  ==========msg ,don't reply!!";
		for (int i = 0; i < 5; i++) {
			channel.basicPublish(exchange, routing,true, null, msg.getBytes());
		}
		
	}

}

