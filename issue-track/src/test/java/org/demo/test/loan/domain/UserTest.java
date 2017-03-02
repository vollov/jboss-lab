package org.demo.test.loan.domain;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.demo.loan.domain.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class UserTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(User.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUsers() throws Exception {
		utx.begin();
		em.joinTransaction();
		
		String query = "select u from User u order by u.id";

		System.out.println("Selecting (using JPQL)...");
		List<User> users = em.createQuery(query)
				.getResultList();

		utx.commit();
		// clear the persistence context (first-level cache)
		em.clear();
		
		// then
		System.out.println("Found " + users.size() + " games (using JPQL):");
		
		for (User u : users) {
			System.out.println("* " + u.getFirstName());
		}
	}
}
