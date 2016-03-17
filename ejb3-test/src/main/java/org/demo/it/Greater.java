package org.demo.it;

import java.io.PrintStream;

import javax.inject.Inject;

/**
 * A component for creating personal greetings.
 */
public class Greater {
	// public void greet(PrintStream to, String name) {
	// to.println(createGreeting(name));
	// }
	//
	// public String createGreeting(String name) {
	// return "Hello, " + name + "!";
	// }

	private PhraseBuilder phraseBuilder;

	@Inject
    public Greater(PhraseBuilder phraseBuilder) {
        this.phraseBuilder = phraseBuilder;
    }

	public void greet(PrintStream to, String name) {
		to.println(createGreeting(name));
	}

	public String createGreeting(String name) {
		return phraseBuilder.buildPhrase("hello", name);
	}
}
