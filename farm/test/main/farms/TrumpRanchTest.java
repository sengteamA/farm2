package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrumpRanchTest {

	public TrumpRanch Don;

	@BeforeEach
	public void init() {
		Don = new TrumpRanch();
	}

	@Test //test passed 16/04/2020
	void bankBalanceTest() {
		Don.updateBankBalance(-100);
		Don.updateBankBalance(200);
		assertEquals(1300, Don.getBankBalance());
	}

}
