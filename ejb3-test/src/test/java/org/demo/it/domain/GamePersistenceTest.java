package org.demo.it.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class GamePersistenceTest {
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Game.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private static final String[] GAME_TITLES = { "Super Mario Brothers",
			"Mario Kart", "F-Zero" };

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;

	// tests go here
	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		insertData();
		startTransaction();
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from Game").executeUpdate();
		utx.commit();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Inserting records...");
		for (String title : GAME_TITLES) {
			Game game = new Game(title);
			em.persist(game);
		}
		utx.commit();
		// clear the persistence context (first-level cache)
		em.clear();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	@Test
	public void shouldFindAllGamesUsingJpqlQuery() throws Exception {
		// given
		String fetchingAllGamesInJpql = "select g from Game g order by g.id";

		// when
		System.out.println("Selecting (using JPQL)...");
		List<Game> games = em.createQuery(fetchingAllGamesInJpql, Game.class)
				.getResultList();

		// then
		System.out.println("Found " + games.size() + " games (using JPQL):");
		assertContainsAllGames(games);
	}

	private static void assertContainsAllGames(Collection<Game> retrievedGames) {
		Assert.assertEquals(GAME_TITLES.length, retrievedGames.size());
		final Set<String> retrievedGameTitles = new HashSet<String>();
		for (Game game : retrievedGames) {
			System.out.println("* " + game);
			retrievedGameTitles.add(game.getTitle());
		}
		Assert.assertTrue(retrievedGameTitles.containsAll(Arrays
				.asList(GAME_TITLES)));
	}
	
	@Test
	public void shouldFindAllGamesUsingCriteriaApi() throws Exception {
	    // given
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Game> criteria = builder.createQuery(Game.class);
	    		
	    Root<Game> game = criteria.from(Game.class);
	    criteria.select(game);
	    // TIP: If you don't want to use the JPA 2 Metamodel,
	    // you can change the get() method call to get("id")
	    criteria.orderBy(builder.asc(game.get("id")));
	    // No WHERE clause, which implies select all

	    // when
	    System.out.println("Selecting (using Criteria)...");
	    List<Game> games = em.createQuery(criteria).getResultList();

	    // then
	    System.out.println("Found " + games.size() + " games (using Criteria):");
	    assertContainsAllGames(games);
	}
}