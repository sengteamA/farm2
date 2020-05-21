package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

	/**
	 * Checks getName and setName are working properly.
	 */
	@Test
	void nameTest() {
		String myName = "bacon and eggs";
		testFarm.setName(myName);
		assertEquals(myName, testFarm.getName());
	}

	/**
	 * Checks if bank balance is working.
	 */
	@Test
	void bankBalanceTest() {
		assertEquals(1000, testFarm.getBankBalance());
	}

	/**
	 * Animal bonus should be 0 unless it's an Animal Farm, in which case
	 * it is 20% (0.2).
	 */
	@Test
	void animalBonusTest() {
		assertEquals(0, testFarm.getAnimalBonus());
		AnimalFarm animalFarm = new AnimalFarm();
		assertEquals((float)0.2, animalFarm.getAnimalBonus());

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

	/**
	 * Checks if a crop is on the farm.
	 */
	@Test
	void inStockTests() {
		Carrot carrot = new Carrot();
		Wasabi wasabi = new Wasabi();
		assertFalse(testFarm.plantInStock(carrot));
		testFarm.addCrop(carrot);
		assertTrue(testFarm.plantInStock(carrot));
		assertFalse(testFarm.plantInStock(wasabi));
	}

	/**
	 * Check if we can reach the farm cap of ten.
	 */
	@Test
	void farmCapTests() {
		assertTrue(testFarm.hasSpace());
		for (int i=0; i <=9; i++) {
			testFarm.addCrop(new Carrot());
		}
		assertFalse(testFarm.hasSpace());
	}

	/**
	 * Checks if getItemType works.
	 */
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

	/**
	 * Test that adding every type of crop works.
	 */
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

	/**
	 * Checks if an empty farm has no crop types.
	 */
	@Test
	void getCropTypeEmptyTest() {
		assertEquals(true, testFarm.getCropType().isEmpty());
	}

	/**
	 * Make sure the user can't add the same animal instance twice.
	 * (A clone is fine though.)
	 */
	@Test
	void animalDupeTest() {
		Cow cow = new Cow();
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> {
					testFarm.addAnimal(cow);
					testFarm.addAnimal(cow);
				}, "Expected adding two of the same animal instance to " +
					"fail, it did not");
		assertTrue(thrown.getMessage().contentEquals(
				"Cannot add two identical instances (duplicates) of an animal"
		));
	}

	/**
	 * Checks that adding the same crop instance twice doesn't work.
	 */
	@Test
	void cropDupeTest() {
		Carrot carrot = new Carrot();
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> {
					testFarm.addCrop(carrot);
					testFarm.addCrop(carrot);
				}, "Expected adding two of the same crop instance to fail, " +
				    "it did not");
		assertTrue(thrown.getMessage().contentEquals(
				"Cannot add two identical instances (duplicates) of a crop"
		));
	}

	/**
	 * Checks that adding the same item instance twice doesn't work.
	 */
	@Test
	void itemDupeTest() {
		Stockfeed stockfeed = new Stockfeed();
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> {
					testFarm.addItem(stockfeed);
					testFarm.addItem(stockfeed);
				}, "Expected adding two of the same item instance to fail, " +
				    "it did not");
		assertTrue(thrown.getMessage().contentEquals(
				"Cannot add two identical instances (duplicates) of an item"
		));
	}
	
	@Test 
	void MoomooFarmTest() {
		Cow cow = new Cow();
		Sheep sheep = new Sheep();
		MoomooFarm moo = new MoomooFarm();
		moo.addAnimal(sheep);
		assertFalse(moo.hasCow());
		moo.addAnimal(cow);
		assertTrue(moo.hasCow());
	}
	
	@Test 
	void TomaccoLandTest() {
		TomaccoLand Homer = new TomaccoLand();
		assertEquals("The dangers of nuclear waste meets the genius of genetic engineering\n"
				+ "tending to crops makes them mature 1 day faster than others", Homer.getFlavour());
	}
	
	@Test
	void genericFarmTest() {
		Farm farm2 = new Farm("Trump Ranch", "I make the best deals!", 1500);
		assertEquals(1500, farm2.getBankBalance());
		farm2.setName("Donald");
		assertEquals("Donald", farm2.getName());
		assertEquals(0, farm2.getAnimalBonus());
		farm2.updateBankBalance(-100);
		assertEquals(1400, farm2.getBankBalance());
		assertEquals("Trump Ranch", farm2.getType());
		farm2.updateAP();
		farm2.refreshAP();
		assertEquals(2, farm2.getActionsLeft());
	}
	
	@Test 
	void dailyBonusTest() {
		Fox fox = new Fox();
		testFarm.addAnimal(fox);
		assertEquals(160, testFarm.getDailyBonusMoney());
	}
	
	@Test
	void itemInHandTest() {
		Compost compost = new Compost();
		Stockfeed stock = new Stockfeed();
		testFarm.addItem(stock);
		assertFalse(testFarm.itemInHand(compost));
		assertTrue(testFarm.itemInHand(stock));
		testFarm.delItem(stock);
		assertFalse(testFarm.itemInHand(stock));
	}
	
	@Test
	void scoreTest() {
		assertEquals(1000, testFarm.getScore());
		Fox crash = new Fox();
		testFarm.addAnimal(crash);
		assertEquals(1010, testFarm.getScore());
	}
	
	@Test
	void cropDeleteTest() {
		Wasabi volcano = new Wasabi();
		testFarm.addCrop(volcano);
		assertEquals(1, testFarm.showCrops().size());
		testFarm.delCrop(volcano);
		assertTrue(testFarm.showCrops().isEmpty());
	}
	
	@Test
	void animalDeleteTest() {
		Cow seacow = new Cow();
		testFarm.addAnimal(seacow);
		assertEquals(1, testFarm.showAnimals().size());
		testFarm.delAnimal(seacow);
		assertTrue(testFarm.showAnimals().isEmpty());
	}
	
	@Test
	void liquidityTest() {
		testFarm.updateBankBalance(-700);
		Fox fox = new Fox();
		Sheep sheep = new Sheep();
		assertFalse(testFarm.hasEnoughMoney(fox.getPurchasePrice()));
		assertTrue(testFarm.hasEnoughMoney(sheep.getPurchasePrice()));
	}
}