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
		Don.updateBankbalance(-100);
		Don.updateBankbalance(200);
		assertEquals(1300, Don.getBankBalance());
	}

	@Test //test passed 16/04/2020
	void discountTest() {
		float purchase = 100 * Don.discount;
		assertEquals(90, purchase);
	}

	@Test //test passed 16/04/2020
	void bonusTest() {
		float revenue = 100 * Don.bonus;
		assertEquals(110, revenue);
	}

}
