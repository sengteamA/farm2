package main.crops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CropTest {
	private Crop myCrop;

	@BeforeEach
	void setUp() throws Exception {
		myCrop = new Crop("Bacon", 100, 2000, 150);
	}

	@Test
	void testName() {
		assertEquals("Bacon", myCrop.getName());
	}

	@Test
	void testDaysToHarvest() {
		assertEquals(2000, myCrop.getDaysToHarvest());
	}

	@Test
	void testDaysElapsed() {
		assertEquals(0, myCrop.getDaysElapsed());
	}

	@Test
	void testPurchasePrice() {
		assertEquals(100, myCrop.getPurchasePrice());
	}

	@Test // done 03/05/2020
	void daysTillHarvestTest() {
		myCrop.updateDaysElapsed(500);
		assertEquals(1500, myCrop.getDaysLeft());
		myCrop.updateDaysElapsed(2000);
		assertEquals(0, myCrop.getDaysLeft());
	}

	@Test
	void testSellingPrice() {
		assertEquals(150, myCrop.getPrice());
	}

}
