package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnimalFarmTest {

	public AnimalFarm snowball;

	@BeforeEach
	public void init() {
		snowball = new AnimalFarm();
	}

	@Test // passed 16/04/2020
	void flavourTest() {
		String s = snowball.getFlavour();
		assertEquals("All Animals are equal, but some are more equal than others.\n"
				+ "All animals start 20% happier and healthier on purchase.\n"
				+ "Feeding and playing with animals is 20% more effective.\n", s);
	}

	@Test // passed 02/05/2020
	void class_test() {
		assertTrue(snowball instanceof AnimalFarm);
		assertTrue(snowball instanceof Farm);
	}


}
