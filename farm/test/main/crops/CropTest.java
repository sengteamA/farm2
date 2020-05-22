package main.crops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CropTest {
	private Crop myCrop;

	// custom crop created before each test
	@BeforeEach
	void setUp() throws Exception {
		myCrop = new Crop("Bacon", 100, 2000, 150, 5);
	}

	// tests name getter
	@Test
	void testName() {
		assertEquals("Bacon", myCrop.getName());
	}

	// tests daysToHarvest getter
	@Test
	void testDaysToHarvest() {
		assertEquals(2000, myCrop.getDaysToHarvest());
	}

	// tests daysElapsed getter
	@Test
	void testDaysElapsed() {
		assertEquals(0, myCrop.getDaysElapsed());
	}

	// tests purchasePrice getter
	@Test
	void testPurchasePrice() {
		assertEquals(100, myCrop.getPurchasePrice());
	}

	// tests if days left <= 0, which it shouldn't
	@Test
	void daysTillHarvestTest() {
		myCrop.updateDaysElapsed(500);
		assertEquals(1500, myCrop.getDaysLeft());
		myCrop.updateDaysElapsed(2000);
		assertEquals(0, myCrop.getDaysLeft());
		myCrop.updateDaysElapsed(100);
		assertEquals(0, myCrop.getDaysLeft());
	}

	// selling price getter test
	@Test
	void testSellingPrice() {
		assertEquals(150, myCrop.getSellingPrice());
	}

	// crop toString test
	@Test
	void toStringTest() {
		assertEquals("Bacon: planted day 5, 2000 days left",
				myCrop.toString());
	}

	// crop getInfoTest
	@Test
	void getInfoTest() {
		assertEquals("Bacon - 100 (2000 days)", myCrop.getInfo());
	}

	// test all aspects of Carrot
	@Test
	void carrotTest() {
		Carrot carrot = new Carrot(5);
		assertEquals("Carrot", carrot.getName());
		assertEquals(20, carrot.getPurchasePrice());
		assertEquals(6, carrot.getDaysToHarvest());
		assertEquals(24, carrot.getSellingPrice());
	}

	// test Hipotke attributes
	@Test
	void hipTest() {
		Hipotke hip = new Hipotke(5);
		assertEquals("Hipotke Grass", hip.getName());
		assertEquals(200, hip.getPurchasePrice());
		assertEquals(18, hip.getDaysToHarvest());
		assertEquals(300, hip.getSellingPrice());
	}

	// test mushroom attributes
	@Test
	void mushTest() {
		Mushroom mush = new Mushroom(5);
		assertEquals("Mushroom", mush.getName());
		assertEquals(30, mush.getPurchasePrice());
		assertEquals(7, mush.getDaysToHarvest());
		assertEquals(39, mush.getSellingPrice());
	}

	// test tomacco attributes
	@Test
	void tomcaccoTest() {
		Tomacco tomacco = new Tomacco(5);
		assertEquals("Tomacco", tomacco.getName());
		assertEquals(50, tomacco.getPurchasePrice());
		assertEquals(9, tomacco.getDaysToHarvest());
		assertEquals(65, tomacco.getSellingPrice());
	}

	// test wasabi attributes
	@Test
	void wasabiTest() {
		Wasabi wasabi = new Wasabi(5);
		assertEquals("Wasabi", wasabi.getName());
		assertEquals(100, wasabi.getPurchasePrice());
		assertEquals(15, wasabi.getDaysToHarvest());
		assertEquals(150, wasabi.getSellingPrice());
	}

	// test wheat attributes
	@Test
	void wheatTest() {
		Wheat wheat = new Wheat(5);
		assertEquals("Wheat", wheat.getName());
		assertEquals(10, wheat.getPurchasePrice());
		assertEquals(4, wheat.getDaysToHarvest());
		assertEquals(12, wheat.getSellingPrice());
	}

}
