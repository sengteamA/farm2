package main.animals;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimalTest {
	// An object created for each animal subclass
	public Sheep babe;
	public Cow moomoo;
	public Fox crash;

	@BeforeEach
	public void init() {
		babe = new Sheep();
		moomoo = new Cow();
		crash = new Fox();
	}

	/**
	 * Check if health of sheep is bounded by maxHealth.
	 */
	@Test
	void sheepHealthTest() {
		babe.updateHealth(5);
		assertEquals(55, babe.getHealth());
	}

	/**
	 * Check if happiness of sheep is bounded by maxHappiness.
	 */
	@Test
	void sheepHappyTest() {
		babe.updateHappiness(5);
		assertEquals(55, babe.getHappiness());
	}

	/**
	 * Check if happiness of cow is bounded by maxHappiness.
	 */
	@Test
	void cowHappyBoundTest() {
		moomoo.updateHappiness(300);
		assertEquals(250, moomoo.getHappiness());
	}

	/**
	 * Verify price of a fox.
	 */
	@Test
	void foxPriceTest() {
		assertEquals(400, crash.getPurchasePrice());
	}

	/**
	 * Test whether the toString() method works.
	 */
	@Test
	void toStringTest() {
		assertEquals("SHEEP health 50 happiness 50", babe.toString());
	}

	/**
	 * Test if daily bonus for an animal incrases with a higher health level.
	 */
	@Test
	void bonusTest() {
		assertEquals(100, moomoo.bonus());
		moomoo.updateHealth(10);
		assertEquals(104, moomoo.bonus());
	}
}