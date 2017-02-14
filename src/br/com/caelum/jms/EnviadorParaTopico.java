package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class EnviadorParaTopico {

	public static void main(String[] args) throws Exception {

		InitialContext ic = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topic = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		
		try(JMSContext context = factory.createContext("jms", "jms2")){
			JMSProducer producer = context.createProducer();
			Scanner sc = new Scanner(System.in);
			
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				if("veloso".equals(line))
					producer.setProperty("formato",	"ebook");
				else
					producer.setProperty("formato",	"");
				producer.send(topic, line);
			}
			sc.close();
		}
		
	}

}
