package org.demo.books.service;

import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.demo.books.utils.Loggable;
import org.demo.books.utils.ThirteenDigits;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

	// ======================================
	// = Attributes =
	// ======================================

	@Inject
	private Logger logger;

	// ======================================
	// = Business methods =
	// ======================================

	@Loggable
	public String generateNumber() {
		String isbn = "13-84356-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISBN : " + isbn);
		return isbn;
	}
}