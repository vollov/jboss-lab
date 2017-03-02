package org.demo.loan.service;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.demo.loan.domain.User;

@Named
@SessionScoped
public class UserService implements Serializable{
	
	private static final long serialVersionUID = -1L;

	@Inject
	private Logger logger;
	
	@Inject
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<User> findUsers() {
		logger.info("entering findUsers().....");
		return (List<User>) em.createQuery("select u from User u order by u.id")
				.getResultList();
	}
	
	
	public String hello(){
		logger.info("entering hello().....");
		return "Hello!";
	}
}
