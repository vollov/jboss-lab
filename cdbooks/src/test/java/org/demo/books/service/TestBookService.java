package org.demo.books.service;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.demo.books.domain.Book;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * @author dtz
 *
 */
@RunWith(Arquillian.class)
public class TestBookService {

//	@Inject
//	BoundConversationContext conversationContext;
//
//	@Before
//	public void init() {
//		conversationContext
//				.associate(new MutableBoundRequest(new HashMap<String, Object>(), new HashMap<String, Object>()));
//		conversationContext.activate();
//	}

	/**
	 * deploy book service as a jar
	 * 
	 * @return
	 */
//	@Deployment
//	public static Archive<?> createTestArchive() {
//
//		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "books.jar").addPackage(Book.class.getPackage())
//				.addPackage(BookService.class.getPackage()).addPackage(IsbnGenerator.class.getPackage())
//				.addPackage(NumberGenerator.class.getPackage()).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//		System.out.println(jar.toString(true));
//	    return jar;
//	}

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Book.class.getPackage())
				.addPackage(BookService.class.getPackage()).addPackage(IsbnGenerator.class.getPackage())
				.addPackage(NumberGenerator.class.getPackage()).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
    }

	@Inject
	BookService bookService;

	@Test
	public void testTicketAgency() throws Exception {
		Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
		assertTrue(book.getNumber().startsWith("MOCK"));
	}

}

// import static org.junit.Assert.assertTrue;
//
// import org.demo.books.domain.Book;
// import org.jboss.weld.Weld;
// import org.jboss.weld.environment.se.WeldContainer;
// import org.junit.Test;
//
// public class BookServiceIT {
//
// // ======================================
// // = Methods =
// // ======================================
//
// @Test
// public void shouldCheckNumberIsMock() {
//
// Weld weld = new Weld();
// WeldContainer container = weld.initialize();
//
// BookService bookService =
// container.instance().select(BookService.class).get();
//
// Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
//
// assertTrue(book.getNumber().startsWith("MOCK"));
//
// weld.shutdown();
// }
// }