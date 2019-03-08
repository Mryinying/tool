package com.it.rabbitmq.base.dlx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Producer {
	
	private static ConnectionFactory factory = new ConnectionFactory();
	
	
	@Autowired
	public static void main(String[] args) throws IOException, TimeoutException {
		
		factory.setHost("192.168.203.133");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		String exchange = "test_dlx_exchange";
		String routing = "dlx.save";
		String msg = "dlx  ==========msg ,don't reply!!";
		for (int i = 0; i < 4; i++) {
//			Map<String, Object>  map = new HashMap<String, Object>();
//			map.put("n", i);
//			AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().deliveryMode(2).contentEncoding("UTF-8").headers(map).build();
			
			channel.basicPublish(exchange, routing,true, null, msg.getBytes());
		}
		
	}

}

