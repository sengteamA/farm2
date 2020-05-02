package main.animal;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimalTest {
	
	//an object created for each animal subclass
	public Sheep Babe;
	public Cow Moomoo;
	public Fox Crash;
	
	@BeforeEach
	public void init() {
		Babe = new Sheep();
		Moomoo = new Cow();
		Crash = new Fox();
	}
	
	@Test // sheep health update test passed 21/04/2020
	void sheep_health() {
		Babe.updateHealth(5);
		assertEquals(10, Babe.getHealth());
	}
	
	@Test // sheep happiness update test passed 21/04/2020
	void sheep_happy() {
		Babe.updateHappiness(5);
		assertEquals(10, Babe.getHappiness());
	}
	
	@Test // cow max happiness test passed 21/04/2020
	void cow_happy_bound_test() {
		Moomoo.updateHappiness(50);
		assertEquals(25, Moomoo.getHappiness());
	}
	
	@Test // fox price test passed 21/04/2020
	void fox_price_test() {
		assertEquals(400, Crash.getPurchasePrice());
	}
	
	@Test // passed 30/04/2020
	void toString_test() {
		assertEquals("SHEEP health 5 happiness 5", Babe.toString());
	}
	
	@Test // passed 02/05/2020
	void bonus_tests() {
		assertEquals(100, Moomoo.bonus());
		Moomoo.updateHealth(10);
		assertEquals(104, Moomoo.bonus());
	}
}
