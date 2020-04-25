package main.crops;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.crops;

import main.crops.Crop;

class CropTest {
	private Crop my_crop;

	@BeforeEach
	void setUp() throws Exception {
		my_crop = new Crop("Bacon", 100, 2000);
	}

	@Test
	void testName() {
		assertEquals("Bacon", my_crop.getName());
	}
	
	@Test
	void testDaysToHarvest() {
		assertEquals(2000, my_crop.getDaysToHarvest());
	}
	
	@Test
	void testDaysElapsed() {
		assertEquals(0, my_crop.getDaysElapsed());
	}

	@Test
	void testPurchasePrice() {
		assertEquals(100, my_crop.getPurchasePrice());
	}

}
