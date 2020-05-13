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
		assertEquals("Sheep", testFarm.showAnimals().get(0).getName());
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
	}

	@Test //test completed 08/05/2020
	void hasAnimalTest() {
		assertFalse(testFarm.hasAnimals());
		Sheep blackie = new Sheep();
		testFarm.addAnimal(blackie);
		assertTrue(testFarm.hasAnimals());
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
	}

	@Test // test completed 08/05/2020
	void hasCropsTest() {
		assertFalse(testFarm.hasCrops());
		Tomacco marl = new Tomacco();
		assertFalse(testFarm.hasCrops());
		testFarm.addCrop(marl);
		assertTrue(testFarm.hasCrops());
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
	}
	
	@Test // test completed 13/05/2020
	void inStockTests() {
		Carrot carrot = new Carrot();
		assertFalse(testFarm.plantInStock(carrot));
		testFarm.addCrop(carrot);
		assertTrue(testFarm.plantInStock(carrot));
	}
}
