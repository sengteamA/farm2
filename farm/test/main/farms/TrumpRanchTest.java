package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrumpRanchTest {

	public TrumpRanch Don;
	// Initialise an instance of Trump Ranch
	@BeforeEach
	public void init() {
		Don = new TrumpRanch();
	}
	
	/**
	 * Tests that TrumpRanch has a starting
	 * cash advantage.
	 */
	@Test
	void bankBalanceTest() {
		Don.updateBankBalance(-100);
		Don.updateBankBalance(200);
		assertEquals(1300, Don.getBankBalance());
	}

}
