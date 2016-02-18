package org.demo.jboss.ticket.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.demo.jboss.ticket.model.Seat;

@Model
public class TheatreInfoService {

	@Inject
	TheatreBox box;

	private List<Seat> seats;

	@Produces
	@Named
	public List<Seat> getSeats() {
		return seats;
	}

	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
		retrieveAllSeatsOrderedByName();
	}

	@PostConstruct
	public void retrieveAllSeatsOrderedByName() {
		seats = box.getSeatList();
	}
}
