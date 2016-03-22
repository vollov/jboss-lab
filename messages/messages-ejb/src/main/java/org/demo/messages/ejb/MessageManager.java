package org.demo.messages.ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageManager {
	public List<Message> getMessageList();
	
	public void findMessages();

	public void select();

	public void delete();

	public void destroy();
}