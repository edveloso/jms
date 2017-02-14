package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class RegistraFinanceiroNoTopico {
	
	
	public static void main(String[] args) throws Exception {
		
		InitialContext ctx =new InitialContext();
		ConnectionFactory fac = (ConnectionFactory) 
				ctx.lookup("jms/RemoteConnectionFactory");
		Topic topic = (Topic) ctx.lookup("jms/TOPICO.LIVRARIA");
		
		
		try(JMSContext context = fac.createContext("jms","jms2")){
			
			context.setClientID("Financeiro");

			JMSConsumer consumer = context
					.createDurableConsumer(topic, "Assinanaturas");
			consumer.setMessageListener(new TratadorDeMensagem());
			
			JMSConsumer consumerNf = context
					.createDurableConsumer(topic, "NF");
			consumerNf.setMessageListener(new TratadorDeMensagemTopico());
			
			context.start();
			Scanner scan = new Scanner(System.in);
			System.out.println("Startado servidor");
			scan.nextLine();
			scan.close();
			context.close();
			
		}
		
		
		
	}

}
