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
	void name_length_test() {
		testFarm.setName();
		/* first name will be less than 3 char long
		 * second name will be more than 15 char long
		 * 3rd name will contain symbols
		 * finally, enter abc, which will be a legal name
		 */
		assertEquals("abc", testFarm.getName());
	}

	@Test
	void valid_names_test() {
		assertEquals(true, testFarm.isValidName("Rimuru Tempest"));
		assertEquals(true, testFarm.isValidName("abc"));
		assertEquals(true, testFarm.isValidName("D V Dyke"));
		assertEquals(true, testFarm.isValidName("MiXeD cApS nAmE"));
		assertEquals(true, testFarm.isValidName("NICK LEE"));
	}
	
	@Test
	void invalid_names_test() {
		assertEquals(false, testFarm.isValidName(""));
		assertEquals(false, testFarm.isValidName("a"));
		assertEquals(false, testFarm.isValidName("ab"));
		assertEquals(false, testFarm.isValidName("My 1st Name"));
		assertEquals(false, testFarm.isValidName("abcdefghijklmnop"));
		assertEquals(false, testFarm.isValidName("tHis name Is tOo long"));
		assertEquals(false, testFarm.isValidName("Ke$ha"));
		assertEquals(false, testFarm.isValidName("Two  spaces h"));
	}
}
