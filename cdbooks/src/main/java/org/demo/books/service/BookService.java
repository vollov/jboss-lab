package org.demo.books.service;

import javax.inject.Inject;

import org.demo.books.domain.Book;
import org.demo.books.utils.Loggable;
import org.demo.books.utils.ThirteenDigits;


@Loggable
public class BookService {

	// ======================================
	// = Attributes =
	// ======================================

	@Inject
	@ThirteenDigits
	private NumberGenerator numberGenerator;

	// ======================================
	// = Business methods =
	// ======================================

	public Book createBook(String title, Float price, String description) {
		Book book = new Book(title, price, description);
		book.setNumber(numberGenerator.generateNumber());
		return book;
	}
}
