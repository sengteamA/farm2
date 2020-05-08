package main.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.Animal;
import main.farms.Farm;

class AnimalTest {

	public Animal pig;
	public Farm farm;
	//animal name, current health, current happiness, max health, max happiness, price

	@BeforeEach
	public void init() {
		pig = new Animal("Napoleon", 10, 10, 20, 20, 200);
		farm = new Farm("My New Farm", "Empty flavour text");
	}

	@Test // test passed 17/04/2020
	void nameTest() {
		assertEquals("Napoleon", pig.getName());
	}

	@Test // test passed 19/04/2020
	void healthTest() {
		assertEquals(10, pig.getHealth());
	}

	@Test // test passed 17/04/2020
	void priceTest() {
		assertEquals(200, pig.getPurchasePrice());
	}

	@Test // test passed 18/04/2020
	void animalBonusTest() {
		assertEquals(80, pig.bonus());
	}

	@Test // test passed 19/04/2020
	void getHealthyTest() {
		pig.updateHealth(5);
		pig.updateHealth(-3);
		assertEquals(12, pig.getHealth());
	}

	@Test // test passed 20/04/2020
	void getHappyTest() {
		pig.updateHappiness(5);
		assertEquals(15, pig.getHappiness());
	}

	@Test // test passed 20/04/2020
	void healthLimit() {
		pig.updateHealth(20);
		assertEquals(20, pig.getHealth());
	}
}
