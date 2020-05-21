package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AssetTest {
	
	// Tests custom asset is displayed correctly.
	@Test
	void getInfoTest() {
		Asset car = new Asset("Car", 10000);
		assertEquals("Car - 10000", car.getInfo());
	}
	
	// Tests the to String method
	@Test
	void toStringTest() {
		Asset car = new Asset("Car", 10000);
		assertEquals("Car: 10000", car.toString());
	}

}
