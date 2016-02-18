package org.demo.jboss.ticket.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.demo.jboss.ticket.model.Seat;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class TheatreBookerService implements Serializable {
	private static final Logger logger = Logger.getLogger(TheatreBookerService.class);

	int money;
	@Inject
	TheatreBox theatreBox;

	@PostConstruct
	public void createCustomer() {
		this.money = 100;
	}

	public void bookSeat(int seatId) {
		FacesContext fc = FacesContext.getCurrentInstance();

		logger.info("Booking seat " + seatId);
		Seat seat = theatreBox.getSeatList().get(seatId - 1);

		if (seat.getPrice() > money) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not enough Money!",
					"Registration successful");
			fc.addMessage(null, m);
			return;
		}

		theatreBox.buyTicket(seatId - 1);

		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Booked!", "Booking successful");
		fc.addMessage(null, m);
		logger.info("Seat booked.");

		money = money - seat.getPrice();
	}

	public int getMoney() {
		return money;
	}
}
