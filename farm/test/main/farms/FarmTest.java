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

	@Test //test completed 13/05/2020
	void farmCapTests() {
		assertTrue(testFarm.hasSpace());
		Carrot carrot = new Carrot();
		for (int i=0; i <=9; i++) {
			testFarm.addCrop(carrot);
		}
		assertFalse(testFarm.hasSpace());
	}

	@Test//test completed 17/05/2020
	void itemFilterTest() {
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(stock);
		testFarm.addItem(stock);
		testFarm.addItem(compost);
		assertEquals(1, testFarm.getItemType("Crop").size());
		assertEquals(2, testFarm.getItemType("Animal").size());
	}
}
