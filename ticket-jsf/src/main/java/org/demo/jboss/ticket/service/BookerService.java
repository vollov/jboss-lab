package org.demo.jboss.ticket.service;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class BookerService implements Serializable {
	@Inject
	private Logger logger;

	int money;
	@Inject TicketService ticketService;
	
	@PostConstruct
	public void createCustomer() {
		this.money=100;

	}

	public void bookSeat(Long seatId, int price)   {
		FacesContext fc = FacesContext.getCurrentInstance(); 

		if (price  > money) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not enough Money!", "Registration successful");
			fc.addMessage(null, m);		  
			return;
		}
		logger.info("Booking seat "+seatId);
		ticketService.bookSeat(seatId);
		money = money - price;
        				 
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
		if (fc != null)
		fc.addMessage(null, m);	
		logger.info("Seat booked.");
	}
	
	public int getMoney() {
		return money;
	}
}
