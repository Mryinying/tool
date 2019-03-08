package com.it.rabbitmq.base.returnm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.AMQP.BasicProperties;


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
		String routingerror = "abc.save";
		
		String msg = "confirm  ==========msg ,don't reply!!";
		//mandatory true 可以return不可达路由
		channel.basicPublish(exchange, routingerror,true, null, msg.getBytes());
		
		//confirm机制
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
		
		//return不可达路由handler
		channel.addReturnListener(new ReturnListener() {
			
			@Override
			public void handleReturn(int arg0, String arg1, String arg2, String arg3, BasicProperties arg4, byte[] arg5)
					throws IOException {
				// TODO Auto-generated method stub
				System.err.println("=========no ack" +arg0 );
				System.err.println("=========no ack" +arg1 );
				System.err.println("=========no ack" +arg2 );
				System.err.println("=========no ack" +arg3 );
				System.err.println("=========no ack" +arg4 );
				System.err.println("=========no ack" +new String(arg5) );
			}
		});
		
	}

}

