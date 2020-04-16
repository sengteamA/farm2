package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//animals, crops and items not yet implemented
class FarmTest {
	
	public Farm testFarm;
	
	@BeforeEach
	public void init() {
		testFarm = new Farm();
	}

	@Test //passed on 15/04/2020 - Nick
	void bank_balance_test() {
		assertEquals(1000, testFarm.getBankBalance());
	}
	
	@Test //passed on 15/04/2020 - Nick
	void AP_test() {
		testFarm.updateAP();
		assertEquals(1, testFarm.getActionsLeft());
	}
	
	@Test //passed on 15/04/2020 - Nick
	void name_lenght_test() {
		testFarm.setName();
		/* first name will be less than 3 char long
		 * second name will be more than 15 char long
		 * 3rd name will contain symbols
		 * finally, enter abc, which will be a legal name
		 */
		assertEquals("abc", testFarm.getName());
	}

}
