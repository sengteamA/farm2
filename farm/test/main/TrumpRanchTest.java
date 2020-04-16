package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrumpRanchTest {
	
	public TrumpRanch Don;
	
	@BeforeEach
	public void init() {
		Don = new TrumpRanch();
	}

	@Test //test passed 16/04/2020
	void bank_balance_test() {
		Don.updateBankbalance(-100);
		Don.updateBankbalance(200);
		assertEquals(1300, Don.getBankBalance());
	}
	
	@Test //test passed 16/04/2020
	void discount_test() {
		float purchase = 100 * Don.discount;
		assertEquals(90, purchase);
	}
	
	@Test //test passed 16/04/2020
	void bonus_test() {
		float revenue = 100 * Don.bonus;
		assertEquals(110, revenue);
	}

}
