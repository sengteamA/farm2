package main.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.Sheep;
import main.farms.Farm;

class SheepTest {

	public Farm farm;
	public Sheep babe;

	@BeforeEach
	public void init() {
		farm = new Farm("", "");
		babe = new Sheep();

	}
	@Test // test passed 19/04/2020
	void happinessTest() {
		assertEquals(50, babe.getHappiness());
	}

	@Test // test passed 19/04/2020
	void healthTest() {
		assertEquals(50, babe.getHealth());
	}

	@Test // test passed 19/04/2020
	void healthUpperBound() {
		babe.updateHealth(51);
		assertEquals(100, babe.getHealth());
	}

	@Test
	void healthUp() {
		int currentHealth = babe.getHealth();
		int toAdd = 5;
		babe.updateHealth(toAdd);
		assertEquals(currentHealth + toAdd, babe.getHealth());
	}

	@Test //??? why can subclass pass while parent fails???
	void defaultGetHappy() {
		int currentHappiness = babe.getHappiness();
		int toAdd = 5;
		babe.updateHappiness(toAdd);
		assertEquals(currentHappiness + toAdd, babe.getHappiness());
	}

}
