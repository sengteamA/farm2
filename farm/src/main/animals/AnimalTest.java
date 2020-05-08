package main.animals;

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
	void sheepHealthTest() {
		Babe.updateHealth(5);
		assertEquals(10, Babe.getHealth());
	}

	@Test // sheep happiness update test passed 21/04/2020
	void sheepHappyTest() {
		Babe.updateHappiness(5);
		assertEquals(10, Babe.getHappiness());
	}

	@Test // cow max happiness test passed 21/04/2020
	void cowHappyBoundTest() {
		Moomoo.updateHappiness(50);
		assertEquals(25, Moomoo.getHappiness());
	}

	@Test // fox price test passed 21/04/2020
	void foxPriceTest() {
		assertEquals(400, Crash.getPurchasePrice());
	}

	@Test // passed 30/04/2020
	void toStringTest() {
		assertEquals("SHEEP health 5 happiness 5", Babe.toString());
	}

	@Test // passed 02/05/2020
	void bonusTest() {
		assertEquals(100, Moomoo.bonus());
		Moomoo.updateHealth(10);
		assertEquals(104, Moomoo.bonus());
	}
}
