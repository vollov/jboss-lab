package org.demo.messages.ejb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
@Remote(MessageManager.class)
public class MessageManagerBean implements Serializable, MessageManager {

	private static final long serialVersionUID = -1L;

	private List<Message> messageList;

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	@Inject
	private Message message;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public void findMessages() {
		messageList = (List<Message>) em.createQuery("select msg from Message msg order by msg.datetime desc")
				.getResultList();
	}

	public void select() {
		if (message != null)
			message.setRead(true);
	}

	public void delete() {
		if (message != null) {
			messageList.remove(message);
			em.remove(message);
			message = null;
		}
	}

	@PreDestroy
	public void exit() {
		// items list into the database.
		System.out.println("Saved items list into database");
	}

	@Remove
	public void destroy() {
		// The method body can be empty.
		System.out.println("From destroy method with @Remove annotation");
	}

}
