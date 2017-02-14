package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;

public class RegistraTratadorMensagem {

	public static void main(String[] args) throws Exception {

		InitialContext ic = new InitialContext();
		
		
		ConnectionFactory fac = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		try(JMSContext context = fac.createContext("jms", "jms2")){
			
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new TratadorDeMensagem());
			context.start();
			Scanner scan = new Scanner(System.in);
			System.out.println(" Tratador esperando");
			scan.nextLine();
			scan.close();
			context.stop();
			
		}
		
	}

}
