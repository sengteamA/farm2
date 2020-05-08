package main.animals;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimalTest {

	//an object created for each animal subclass
	public Sheep babe;
	public Cow moomoo;
	public Fox crash;

	@BeforeEach
	public void init() {
		babe = new Sheep();
		moomoo = new Cow();
		crash = new Fox();
	}

	@Test // sheep health update test passed 21/04/2020
	void sheepHealthTest() {
		babe.updateHealth(5);
		assertEquals(10, babe.getHealth());
	}

	@Test // sheep happiness update test passed 21/04/2020
	void sheepHappyTest() {
		babe.updateHappiness(5);
		assertEquals(10, babe.getHappiness());
	}

	@Test // cow max happiness test passed 21/04/2020
	void cowHappyBoundTest() {
		moomoo.updateHappiness(50);
		assertEquals(25, moomoo.getHappiness());
	}

	@Test // fox price test passed 21/04/2020
	void foxPriceTest() {
		assertEquals(400, crash.getPurchasePrice());
	}

	@Test // passed 30/04/2020
	void toStringTest() {
		assertEquals("SHEEP health 5 happiness 5", babe.toString());
	}

	@Test // passed 02/05/2020
	void bonusTest() {
		assertEquals(100, moomoo.bonus());
		moomoo.updateHealth(10);
		assertEquals(104, moomoo.bonus());
	}
}
