package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;

public class EnviadorParaFila {

	public static void main(String[] args) throws Exception {

		InitialContext ic = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		try(JMSContext context = factory.createContext("jms", "jms2")){
			JMSProducer producer = context.createProducer();
			Scanner sc = new Scanner(System.in);
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				producer.send(queue, line);
			}
			sc.close();
		}
		
	}

}
