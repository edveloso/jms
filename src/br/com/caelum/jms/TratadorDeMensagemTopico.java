package br.com.caelum.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagemTopico implements MessageListener{

	@Override
	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.print("Tratando a mensagem recebida no NF: ");
			System.out.println(textMessage.getText());
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	
	
}
