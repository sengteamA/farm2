package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farm.src.main.animal.*;
import farm.src.main.crops.*;
import farm.src.main.items.*;

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
	
	@Test //completed 30/04/2020
	void animal_list_test() {
		Sheep blackie = new Sheep();
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		testFarm.addAnimals(blackie);
		testFarm.addAnimals(moomoo);
		testFarm.addAnimals(crash);
		assertEquals(3, testFarm.showAnimals().size());
		assertTrue(testFarm.showAnimals().contains(blackie));
		testFarm.delAnimals(moomoo);
		assertFalse(testFarm.showAnimals().contains(moomoo));
	}
	
	@Test //completed 30/04/2020
	void crops_list_test() {
		Carrot carrot = new Carrot();
		Hipotke slime = new Hipotke();
		Mushroom magic = new Mushroom();
		Tomacco tomacco = new Tomacco();
		Wasabi volcano = new Wasabi();
		Wheat mccain = new Wheat();
		testFarm.addCrops(carrot);
		testFarm.addCrops(magic);
		testFarm.addCrops(volcano);
		assertEquals(3, testFarm.showCrops().size());
		assertTrue(testFarm.showCrops().contains(magic));
		assertFalse(testFarm.showCrops().contains(slime));
		assertFalse(testFarm.showCrops().contains(tomacco));
		assertFalse(testFarm.showCrops().contains(mccain));
		testFarm.delCrops(magic);;	
		assertFalse(testFarm.showCrops().contains(magic));
	}
	
	@Test //completed 30/04/2020
	void items_list_test() {
		ChemicalSpray bleach = new ChemicalSpray();
		Compost compost = new Compost();
		InstantGroLite ig_lite = new InstantGroLite();
		InstantGroPro ig_pro = new InstantGroPro();
		PandaGummy po = new PandaGummy();
		Stockfeed stockfeed = new Stockfeed();
		testFarm.addItems(bleach);
		testFarm.addItems(compost);
		testFarm.addItems(ig_lite);
		assertEquals(3, testFarm.showItems().size());
		assertTrue(testFarm.showItems().contains(bleach));
		assertFalse(testFarm.showItems().contains(ig_pro));
		assertFalse(testFarm.showItems().contains(po));
		assertFalse(testFarm.showItems().contains(stockfeed));
		testFarm.delItems(bleach);
		assertFalse(testFarm.showItems().contains(bleach));
	}
	
	@Test //tests completed 02/05/2020
	void animal_bonus_test() {
		Sheep blackie = new Sheep();
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		testFarm.addAnimals(blackie);
		assertEquals(40, testFarm.getDailyBonusMoney());
		blackie.updateHappiness(5);
		blackie.updateHealth(2);
		assertEquals(68, testFarm.getDailyBonusMoney());
		testFarm.addAnimals(crash);
		assertEquals(228, testFarm.getDailyBonusMoney());
		testFarm.delAnimals(crash);
		assertEquals(68, testFarm.getDailyBonusMoney());
		testFarm.addAnimals(moomoo);
		assertEquals(164, testFarm.getDailyBonusMoney());
	}
}
