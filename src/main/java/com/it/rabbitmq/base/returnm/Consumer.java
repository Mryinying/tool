package com.it.rabbitmq.base.returnm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer {
	
	public static void main(String[] args) throws Exception {
		
		//创建工厂
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("192.168.203.133");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		//超时重连 3s
		factory.setAutomaticRecoveryEnabled(true);
		factory.setNetworkRecoveryInterval(3000);
		
		
		//获取连接
		Connection connection = factory.newConnection();
		//创建channel
		Channel channel = connection.createChannel();
		
		String exchangeName = "test_confirm_exchange";
		String routingKey = "confirm.*";
		String exchangeType = "topic";
		String queuename = "test_confirm_queue";
		//声明交换机
		channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
		//声明queue
		channel.queueDeclare(queuename,false,false,false,null);
		//绑定 指定路由key
		channel.queueBind(queuename, exchangeName, routingKey);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queuename, true, consumer);
		
		while (true) {
			Delivery delivery = consumer.nextDelivery();
			String string = new String(delivery.getBody());
			System.err.println("consumer========"+string);
		}
	}

}
