package com.it.rabbitmq.base.hello;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		//创建工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.203.133");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		
		//获取连接
		Connection connection = factory.newConnection();
		
		//创建channel
		Channel channel = connection.createChannel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		AMQP.BasicProperties properties =new AMQP.BasicProperties.Builder()
				.deliveryMode(2)
				.contentEncoding("UTF-8")
				.expiration("10000")
				.headers(map )
				.build();
		
		String exchangeName = "test_topic_exchange";
		String routingKey1 = "user.save";
		String routingKey2 = "user.update";
		String routingKey3 = "user.del.id";
		
		channel.basicPublish(exchangeName, routingKey1, null, "hello11".getBytes());
		channel.basicPublish(exchangeName, routingKey2, null, "hello22".getBytes());
		channel.basicPublish(exchangeName, routingKey3, null, "hello33".getBytes());
		//发送
//		for (int i = 0; i < 5; i++) {
//			channel.basicPublish("", "test001", null, "hello".getBytes());
//		}
//		
		channel.close();
		
		connection.close();
	}

}
