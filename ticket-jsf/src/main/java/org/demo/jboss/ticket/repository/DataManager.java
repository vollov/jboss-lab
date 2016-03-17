/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.demo.jboss.ticket.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.demo.jboss.ticket.model.Seat;
import org.demo.jboss.ticket.model.SeatType;

@ApplicationScoped
public class DataManager {

	@Inject
	private EntityManager em;

	public Seat findSeatById(Long id) {
		return em.find(Seat.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<SeatType> findAllSeatTypes() {
		return em.createQuery("from SeatType seat").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Seat> findAllSeats() {
		return em.createQuery("from Seat seat").getResultList();
	}
	
	public void deleteAllData() {
		em.createQuery("delete from Seat").executeUpdate();
		em.createQuery("delete from SeatType").executeUpdate();
	}
}
