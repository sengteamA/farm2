package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TomaccoLandTest {

	@Test //test passed 16/04/2020
	void growth_speed_test() {
		TomaccoLand homer = new TomaccoLand();
		float days = 10 * homer.cropGrowth;
		assertEquals(days, 12);
	}

}
