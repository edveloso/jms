package br.com.caelum.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagem implements MessageListener{

	@Override
	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.print("Tratando a mensagem recebida Assinanaturas: ");
			System.out.println(textMessage.getText());
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	
	
}
