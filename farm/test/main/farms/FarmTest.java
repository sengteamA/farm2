package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.*;
import main.crops.*;
import main.items.*;

class FarmTest {

	public Farm testFarm;

	@BeforeEach
	public void init() {
		testFarm = new Farm("My new farm", "Flavour text");
	}

	@Test
	void bankBalanceTest() {
		assertEquals(1000, testFarm.getBankBalance());
	}

	@Test
	void APTest() {
		testFarm.updateAP();
		assertEquals(1, testFarm.getActionsLeft());
	}

	@Test
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

	@Test
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

	@Test
	void hasAnimalTest() {
		assertFalse(testFarm.hasAnimals());
		Sheep blackie = new Sheep();
		testFarm.addAnimal(blackie);
		assertTrue(testFarm.hasAnimals());
	}

	@Test
	void hasFoodItems() {
		assertFalse(testFarm.hasFoodItems());
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(compost);
		assertFalse(testFarm.hasFoodItems());
		testFarm.addItem(stock);
		assertTrue(testFarm.hasFoodItems());
	}

	@Test
	void hasCropsTest() {
		assertFalse(testFarm.hasCrops());
		Tomacco marl = new Tomacco();
		assertFalse(testFarm.hasCrops());
		testFarm.addCrop(marl);
		assertTrue(testFarm.hasCrops());
	}

	@Test
	void hasPlantItems() {
		assertFalse(testFarm.hasPlantItems());
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(stock);
		assertFalse(testFarm.hasPlantItems());
		testFarm.addItem(compost);
		assertTrue(testFarm.hasPlantItems());
	}

	@Test
	void inStockTests() {
		Carrot carrot = new Carrot();
		assertFalse(testFarm.plantInStock(carrot));
		testFarm.addCrop(carrot);
		assertTrue(testFarm.plantInStock(carrot));
	}

	@Test
	void farmCapTests() {
		assertTrue(testFarm.hasSpace());
		Carrot carrot = new Carrot();
		for (int i=0; i <=9; i++) {
			testFarm.addCrop(carrot);
		}
		assertFalse(testFarm.hasSpace());
	}

	@Test
	void itemFilterTest() {
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		Stockfeed stock2 = new Stockfeed();
		testFarm.addItem(stock);
		testFarm.addItem(stock2);
		testFarm.addItem(compost);
		assertEquals(1, testFarm.getItemType("Crop").size());
		assertEquals(2, testFarm.getItemType("Animal").size());
	}

	@Test
	void getCropTypeTest() {
		testFarm.addCrop(new Wheat());
		testFarm.addCrop(new Mushroom());
		testFarm.addCrop(new Wasabi());
		testFarm.addCrop(new Hipotke());
		testFarm.addCrop(new Carrot());
		testFarm.addCrop(new Tomacco());
		testFarm.addCrop(new Tomacco());
		testFarm.addCrop(new Tomacco());
		ArrayList<String> types = testFarm.getCropType();
		// testing an array for equality, the lazy way
		assertEquals("Carrot", types.get(0));
		assertEquals("Hipotke Grass", types.get(1));
		assertEquals("Mushroom", types.get(2));
		assertEquals("Tomacco", types.get(3));
		assertEquals("Wasabi", types.get(4));
		assertEquals("Wheat", types.get(5));
	}

	@Test
	void getCropTypeEmptyTest() {
		assertEquals(true, testFarm.getCropType().isEmpty());
	}
}
