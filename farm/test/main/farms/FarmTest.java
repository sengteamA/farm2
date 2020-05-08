package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.*;
import main.crops.*;
import main.items.*;

//animals, crops and items not yet implemented
class FarmTest {

	public Farm testFarm;

	@BeforeEach
	public void init() {
		testFarm = new Farm("My new farm", "Flavour text");
	}

	@Test //passed on 15/04/2020 - Nick
	void bankBalanceTest() {
		assertEquals(1000, testFarm.getBankBalance());
	}

	@Test //passed on 15/04/2020 - Nick
	void APTest() {
		testFarm.updateAP();
		assertEquals(1, testFarm.getActionsLeft());
	}

	@Test //passed on 15/04/2020 - Nick
	void nameLengthTest() {
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
	void validNamesTest() {
		assertTrue(testFarm.isValidName("Rimuru Tempest"));
		assertTrue(testFarm.isValidName("abc"));
		assertTrue(testFarm.isValidName("D V Dyke"));
		assertTrue(testFarm.isValidName("MiXeD cApS nAmE"));
		assertTrue(testFarm.isValidName("NICK LEE"));
	}

	@Test
	void invalidNamesTest() {
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
	void farmTypeTest() {
		testFarm.setType("Moomoo Farm");
		assertEquals("Moomoo Farm", testFarm.getType());
	}

	@Test //completed 30/04/2020
	void animalListTest() {
		Sheep blackie = new Sheep();
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		testFarm.addAnimal(blackie);
		testFarm.addAnimal(moomoo);
		testFarm.addAnimal(crash);
		assertEquals(3, testFarm.showAnimals().size());
		assertTrue(testFarm.showAnimals().contains(blackie));
		testFarm.delAnimal(moomoo);
		assertFalse(testFarm.showAnimals().contains(moomoo));
	}

	@Test //completed 30/04/2020
	void cropsListTest() {
		Carrot carrot = new Carrot();
		Hipotke slime = new Hipotke();
		Mushroom magic = new Mushroom();
		Tomacco tomacco = new Tomacco();
		Wasabi volcano = new Wasabi();
		Wheat mccain = new Wheat();
		testFarm.addCrop(carrot);
		testFarm.addCrop(magic);
		testFarm.addCrop(volcano);
		assertEquals(3, testFarm.showCrops().size());
		assertTrue(testFarm.showCrops().contains(magic));
		assertFalse(testFarm.showCrops().contains(slime));
		assertFalse(testFarm.showCrops().contains(tomacco));
		assertFalse(testFarm.showCrops().contains(mccain));
		testFarm.delCrop(magic);
		assertFalse(testFarm.showCrops().contains(magic));
	}

	@Test //completed 30/04/2020
	void itemsListTest() {
		ChemicalSpray bleach = new ChemicalSpray();
		Compost compost = new Compost();
		InstantGroLite igLite = new InstantGroLite();
		InstantGroPro igPro = new InstantGroPro();
		PandaGummy po = new PandaGummy();
		Stockfeed stockfeed = new Stockfeed();
		testFarm.addItem(bleach);
		testFarm.addItem(compost);
		testFarm.addItem(igLite);
		assertEquals(3, testFarm.showItems().size());
		assertTrue(testFarm.showItems().contains(bleach));
		assertFalse(testFarm.showItems().contains(igPro));
		assertFalse(testFarm.showItems().contains(po));
		assertFalse(testFarm.showItems().contains(stockfeed));
		testFarm.delItem(bleach);
		assertFalse(testFarm.showItems().contains(bleach));
	}

	@Test //tests completed 02/05/2020
	void animalBonusTest() {
		Sheep blackie = new Sheep();
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		testFarm.addAnimal(blackie);
		assertEquals(40, testFarm.getDailyBonusMoney());
		blackie.updateHappiness(50);
		blackie.updateHealth(20);
		assertEquals(68, testFarm.getDailyBonusMoney());
		testFarm.addAnimal(crash);
		assertEquals(228, testFarm.getDailyBonusMoney());
		testFarm.delAnimal(crash);
		assertEquals(68, testFarm.getDailyBonusMoney());
		testFarm.addAnimal(moomoo);
		assertEquals(168, testFarm.getDailyBonusMoney());
	}

	@Test //test completed 08/05/2020
	void hasAnimalTest() {
		assertFalse(testFarm.hasAnimals());
		Sheep blackie = new Sheep();
		testFarm.addAnimal(blackie);
		assertTrue(testFarm.hasAnimals());
		testFarm.delAnimal(blackie);
		assertFalse(testFarm.hasAnimals());
	}

	@Test // test completed 08/05/2020
	void hasFoodItems() {
		assertFalse(testFarm.hasFoodItems());
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(compost);
		assertFalse(testFarm.hasFoodItems());
		testFarm.addItem(stock);
		assertTrue(testFarm.hasFoodItems());
		testFarm.delItem(compost);
		assertTrue(testFarm.hasFoodItems());
		testFarm.delItem(stock);
		assertFalse(testFarm.hasFoodItems());
	}

	@Test // test completed 08/05/2020
	void hasCropsTest() {
		assertFalse(testFarm.hasCrops());
		Tomacco marl = new Tomacco();
		testFarm.addCrop(marl);
		assertTrue(testFarm.hasCrops());
		testFarm.delCrop(marl);
		assertFalse(testFarm.hasCrops());
	}

	@Test // test completed 08/05/2020
	void hasPlantItems() {
		assertFalse(testFarm.hasPlantItems());
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(stock);
		assertFalse(testFarm.hasPlantItems());
		testFarm.addItem(compost);
		assertTrue(testFarm.hasPlantItems());
		testFarm.delItem(compost);
		assertFalse(testFarm.hasPlantItems());
	}
}
