package org.demo.jboss.ticket.service;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.demo.jboss.ticket.model.Seat;
import org.demo.jboss.ticket.model.SeatType;
import org.demo.jboss.ticket.producer.SeatProducer;
import org.demo.jboss.ticket.repository.DataManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.MutableBoundRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TicketTest {

	@Inject BoundConversationContext conversationContext;

	@Before
	public void init() {
		conversationContext.associate(
				new MutableBoundRequest(new HashMap<String, Object>(),
						new HashMap<String, Object>()));
		conversationContext.activate();
	}
	@Deployment
	public static Archive<?> createTestArchive() {


		return ShrinkWrap.create(WebArchive.class, "ticket-jsf.war")
				.addPackage(SeatProducer.class.getPackage())
				.addPackage(Seat.class.getPackage())
				.addPackage(TicketService.class.getPackage())
				.addPackage(DataManager.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				;
	}

	@Inject
	TicketService ticketService;

	@Inject
	BookerService bookerService;

	@Inject
	Logger log;

	@Test
	public void testTicketAgency() throws Exception {

		SeatType seatType = new SeatType();
		seatType.setDescription("Balcony");
		seatType.setPrice(11);
		seatType.setQuantity(5);

		ticketService.createSeatType(seatType);
		log.info("Created Seat Type "+seatType.getDescription());
		assertNotNull(seatType.getId());
		
		List listSeats = new ArrayList();
		listSeats.add(seatType);
		ticketService.createTheatre(listSeats);

		log.info("Created Theatre");

		log.info(seatType.getDescription() + " was persisted with id " + seatType.getId());

		bookerService.bookSeat(new Long(seatType.getId()), seatType.getPrice());
		log.info("Created Theatre");
		log.info("Money left: " +bookerService.getMoney());
		assertTrue(bookerService.getMoney() <100); 
	}

}
