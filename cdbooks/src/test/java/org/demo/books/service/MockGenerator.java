package org.demo.books.service;

import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.demo.books.utils.Loggable;
import org.demo.books.utils.ThirteenDigits;

@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator {

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
		String mock = "MOCK-" + Math.abs(new Random().nextInt());
		logger.info("Generated Mock : " + mock);
		return mock;
	}
}
