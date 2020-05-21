package main.crops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CropTest {
	private Crop myCrop;
	
	//custom crop created before each test
	@BeforeEach
	void setUp() throws Exception {
		myCrop = new Crop("Bacon", 100, 2000, 150);
	}
	
	//tests name getter
	@Test
	void testName() {
		assertEquals("Bacon", myCrop.getName());
	}
	
	//tests daysToHarvest getter
	@Test
	void testDaysToHarvest() {
		assertEquals(2000, myCrop.getDaysToHarvest());
	}
	
	//tests daysElapsed getter
	@Test
	void testDaysElapsed() {
		assertEquals(0, myCrop.getDaysElapsed());
	}

	//tests purchasePrice getter
	@Test
	void testPurchasePrice() {
		assertEquals(100, myCrop.getPurchasePrice());
	}
	
	//tests if days left <= 0, which it shouldn't
	@Test 
	void daysTillHarvestTest() {
		myCrop.updateDaysElapsed(500);
		assertEquals(1500, myCrop.getDaysLeft());
		myCrop.updateDaysElapsed(2000);
		assertEquals(0, myCrop.getDaysLeft());
		myCrop.updateDaysElapsed(100);
		assertEquals(0, myCrop.getDaysLeft());
	}
	
	//selling price getter test
	@Test
	void testSellingPrice() {
		assertEquals(150, myCrop.getPrice());
	}
	
	//crop toString test
	@Test
	void toStringTest() {
		assertEquals("Bacon: days left 2000", myCrop.toString());
	}
	//crop getInfoTest
	@Test
	void getInfoTest() {
		assertEquals("Bacon - 100 (2000 days)", myCrop.getInfo());
	}
	
	//test all aspects of Carrot
	@Test
	void carrotTest() {
		Carrot carrot = new Carrot();
		assertEquals("Carrot", carrot.getName());
		assertEquals(20, carrot.getPurchasePrice());
		assertEquals(6, carrot.getDaysToHarvest());
		assertEquals(24, carrot.getPrice());
	}
	
	//test Hipotke attributes
	@Test
	void hipTest() {
		Hipotke hip = new Hipotke();
		assertEquals("Hipotke Grass", hip.getName());
		assertEquals(200, hip.getPurchasePrice());
		assertEquals(18, hip.getDaysToHarvest());
		assertEquals(300, hip.getPrice());
	}
	
	//test mushroom attributes
	@Test
	void mushTest() {
		Mushroom mush = new Mushroom();
		assertEquals("Mushroom", mush.getName());
		assertEquals(30, mush.getPurchasePrice());
		assertEquals(7, mush.getDaysToHarvest());
		assertEquals(39, mush.getPrice());
	}
	
	//test tomacco attributes
	@Test
	void tomcaccoTest() {
		Tomacco tomacco = new Tomacco();
		assertEquals("Tomacco", tomacco.getName());
		assertEquals(50, tomacco.getPurchasePrice());
		assertEquals(9, tomacco.getDaysToHarvest());
		assertEquals(65, tomacco.getPrice());
	}
	
	//test wasabi attributes
	@Test
	void wasabiTest() {
		Wasabi wasabi = new Wasabi();
		assertEquals("Wasabi", wasabi.getName());
		assertEquals(100, wasabi.getPurchasePrice());
		assertEquals(15, wasabi.getDaysToHarvest());
		assertEquals(150, wasabi.getPrice());
	}
	
	//test wheat attributes
	@Test
	void wheatTest() {
		Wheat wheat = new Wheat();
		assertEquals("Wheat", wheat.getName());
		assertEquals(10, wheat.getPurchasePrice());
		assertEquals(4, wheat.getDaysToHarvest());
		assertEquals(12, wheat.getPrice());
	}

}
