package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnimalFarmTest {
	
	public AnimalFarm snowball;
	
	@BeforeEach
	public void init() {
		snowball = new AnimalFarm();
	}
	
	@Test //passed 16/04/2020
	void health_bonus_test() {
		float health = 10 * snowball.animal_bonus;
		assertEquals(2, health);
	}
	
	@Test //passed 16/04/2020
	void happy_bonus_test() {
		float happy = 10 * snowball.animal_bonus;
		assertEquals(2, happy);
	}
	
	@Test // passed 16/04/2020
	void flavour_test() {
		String s= snowball.flavour;
		assertEquals("All Animals are equal, but some are more equal than others.\n"
				+ "All animals start 20% happier and healthier on purchase\n"
				+ "Feeding and playing with animals is 20% more effective\n", s);
	}
	
	@Test // passed 02/05/2020
	void class_test() {
		assertTrue(snowball instanceof AnimalFarm);
		assertTrue(snowball instanceof Farm);
	}
	

}
