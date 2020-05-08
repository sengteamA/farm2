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
	void happiness_test() {
		assertEquals(5, babe.getHappiness());
	}
	
	@Test // test passed 19/04/2020
	void health_test() { 
		assertEquals(5, babe.getHealth());
	}
	
	@Test // test passed 19/04/2020
	void health_upper_bound() {
		babe.updateHealth(11);
		assertEquals(10, babe.getHealth());
	}
	
	@Test
	void health_up() {
		babe.updateHealth(5);
		assertEquals(15, babe.getHealth());
	}
	
	@Test //??? why can subclass pass while parent fails???
	void default_get_happy() {
		babe.updateHappiness(5);
		assertEquals(10, babe.getHappiness());
	}

}
