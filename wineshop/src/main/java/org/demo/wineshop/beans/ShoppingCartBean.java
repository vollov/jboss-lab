package org.demo.wineshop.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful(name = "ShoppingCart")
public class ShoppingCartBean {
	public ArrayList<String> cartItems;

	public ShoppingCartBean() {
	}

	public void addWineItem(String wine) {
		cartItems.add(wine);
	}

	public void removeWineItem(String wine) {
		cartItems.remove(wine);
	}

	public void setCartItems(ArrayList<String> cartItems) {
		this.cartItems = cartItems;
	}

	public ArrayList<String> getCartItems() {
		return cartItems;
	}

	@PostConstruct
	public void initialize() {
		cartItems = new ArrayList<String>();
	}

	@PreDestroy
	public void exit() {
		System.out.println("Saved items list into database");
	}

	@Remove
	public void stopSession() {
		// The method body can be empty.
		System.out.println("From stopSession method with @Remove annotation");
	}
}
