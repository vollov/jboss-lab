package org.demo.it;

import java.io.PrintStream;

/**
 * A component for creating personal greetings.
 */
public class Greater {
    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
