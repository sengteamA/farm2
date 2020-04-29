package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		testFarm.setName(sc);
		/* first name will be less than 3 char long
		 * second name will be more than 15 char long
		 * 3rd name will contain symbols
		 * finally, enter abc, which will be a legal name
		 */
		assertEquals("abc", testFarm.getName());
		sc.close();
	}

	@Test
	void valid_names_test() {
		assertTrue(testFarm.isValidName("Rimuru Tempest"));
		assertTrue(testFarm.isValidName("abc"));
		assertTrue(testFarm.isValidName("D V Dyke"));
		assertTrue(testFarm.isValidName("MiXeD cApS nAmE"));
		assertTrue(testFarm.isValidName("NICK LEE"));
	}

	@Test
	void invalid_names_test() {
		assertFalse(testFarm.isValidName(""));
		assertFalse(testFarm.isValidName("a"));
		assertFalse(testFarm.isValidName("ab"));
		assertFalse(testFarm.isValidName("My 1st Name"));
		assertFalse(testFarm.isValidName("abcdefghijklmnop"));
		assertFalse(testFarm.isValidName("tHis name Is tOo long"));
		assertFalse(testFarm.isValidName("Ke$ha"));
		assertFalse(testFarm.isValidName("Two  spaces h"));
	}

	@Test //
	void farm_type_test() {
		testFarm.setType("Moomoo Farm");
		assertEquals("Moomoo Farm", testFarm.getType());
	}
}
