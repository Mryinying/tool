package com.it.rabbitmq.base.api;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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
		channel.confirmSelect();
		
		String exchange = "test_confirm_exchange";
		String routing = "confirm.save";
		
		String msg = "confirm  ==========msg ,don't reply!!";
		
		channel.basicPublish(exchange, routing, null, msg.getBytes());
		
		channel.addConfirmListener(new ConfirmListener() {
			
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				// TODO Auto-generated method stub
				System.err.println("=========no ack");
			}
			
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("=============ack");
			}
		});
	}

}

