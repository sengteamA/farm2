package main.animal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnimalTest {
	
	public Animal pig;
	//animal name, current health, current happiness, max health, max happiness, price
	
	@BeforeEach
	public void init() {
		pig = new Animal("Napoleon", 10, 10, 20, 20, 200);
	}

	@Test // test passed 17/04/2020
	void name_test() { 
		assertEquals("Napoleon", pig.getName());
	}
	
	@Test // test passed 19/04/2020
	void health_test() {
		assertEquals(10, pig.getHealth());
	}
	
	@Test // test passed 17/04/2020
	void price_test() {
		assertEquals(200, pig.getPrice());
	}
	
	@Test // test passed 18/04/2020
	void animal_bonus_test() {
		assertEquals(80, pig.bonus());
	}
	
	@Test // test passed 19/04/2020
	void get_healthy_test() {
		pig.updateHealth(5);
		pig.updateHealth(-3);
		assertEquals(12, pig.getHealth());
	}
	
	@Test // test passed 20/04/2020
	void get_happy_test() {
		pig.updateHappiness(5);
		assertEquals(15, pig.getHappiness());
	}
	
	@Test // test passed 20/04/2020
	void health_limit() {
		pig.updateHealth(20);
		assertEquals(20, pig.getHealth());
	}
}
