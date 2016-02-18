package org.demo.jboss.ticket.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;

import org.demo.jboss.ticket.exception.NotEnoughMoneyException;
import org.demo.jboss.ticket.exception.SeatBookedException;

public interface TheatreBooker {
	public String bookSeat(int seatId) throws SeatBookedException, NotEnoughMoneyException;

	@Asynchronous
	public Future<String> bookSeatAsync(int seatId);
}