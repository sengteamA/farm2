package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import main.Farmer;
import main.animals.*;
import main.crops.*;
import main.items.*;
import main.farms.*;
import org.junit.jupiter.api.Test;

class FarmerTest {
	public Farm farm;
	public Farmer brown;
	// Initialises a farm and farmer before each test.
	@BeforeEach
	public void init() {
		farm = new Farm("farm", "test");
		brown = new Farmer(farm);
	}

	/**
	 * Tests if tend to land daily action
	 * is functioning correctly.
	 */
	@Test
	void tendLandTest() {
		Sheep blackie = new Sheep();
		farm.addAnimal(blackie);
		assertEquals(50, blackie.getHappiness());
		assertEquals(10, farm.getFarmCap());
		brown.tendToLand();
		assertEquals(70, farm.showAnimals().get(0).getHappiness());
		assertEquals(12, farm.getFarmCap());
		assertEquals(1, farm.getActionsLeft());
	}

	/**
	 * Tests that animalBonus is corrected
	 * implemented for tendToLand daily action.
	 */
	@Test
	void animalFarmLandTest() {
		Sheep blackie = new Sheep();
		AnimalFarm snowball = new AnimalFarm();
		Farmer duncan = new Farmer(snowball);
		snowball.addAnimal(blackie);
		duncan.tendToLand();
		assertEquals("Sheep", snowball.showAnimals().get(0).getName());
		assertEquals(74, snowball.showAnimals().get(0).getHappiness());
	}

	/**
	 * Tests that the harvest crop option
	 * is correctly accounting revenue
	 * and removing the crops from list.
	 */
	@Test
	void harvestTest() {
		Tomacco tomacco = new Tomacco(0);
		Tomacco tomacco2 = new Tomacco(0);
		farm.addCrop(tomacco);
		farm.addCrop(tomacco2);
		assertEquals(2, farm.showCrops().size());
		InstantGrowPro igp = new InstantGrowPro();
		farm.addItem(igp);
		brown.tendToCrops("use item", tomacco, igp);
		brown.harvestCrops();
		assertEquals(0, farm.showCrops().size());
		assertEquals(1130, farm.getBankBalance());
	}

	/**
	 * Tests that when harvesting crops that
	 * TrumpRanch gets 10% bonus revenue.
	 */
	@Test
	void trumpHarvestTest() {
		Carrot carrot = new Carrot(0);
		Wheat wheat = new Wheat(0);
		TrumpRanch tower = new TrumpRanch();
		Farmer Donald = new Farmer(tower);
		tower.addCrop(wheat);
		tower.addCrop(carrot);
		assertEquals(2, tower.showCrops().size());
		InstantGrowPro igp = new InstantGrowPro();
		Donald.tendToCrops("use item", carrot, igp);
		assertEquals(0, tower.showCrops().get(1).getDaysLeft());
		Donald.harvestCrops();
		assertEquals(1, tower.showCrops().size());
		assertEquals(1226, tower.getBankBalance());
	}
	/**
	 * Tests that playing with animals
	 * yields the right amount of bonuses.
	 */
	@Test
	void playAnimalsTest() {
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		farm.addAnimal(moomoo);
		farm.addAnimal(crash);
		brown.playWithAnimals();
		assertEquals(155, farm.showAnimals().get(0).getHappiness());
		assertEquals(230, farm.showAnimals().get(1).getHappiness());
		assertEquals(1, farm.getActionsLeft());
	}

	/**
	 * Tests that when playing with animals,
	 * the animalFarm bonus is included.
	 */
	@Test
	void animalFarmPlayTest() {
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		AnimalFarm pigsy = new AnimalFarm();
		Farmer napoleon = new Farmer(pigsy);
		pigsy.addAnimal(moomoo);
		pigsy.addAnimal(crash);
		napoleon.playWithAnimals();
		assertEquals(161, pigsy.showAnimals().get(0).getHappiness());
		assertEquals(236, pigsy.showAnimals().get(1).getHappiness());
	}

	/**
	 * Checks that feeding animals gives the right
	 * benefits.
	 */
	@Test
	void animalFeedingItemsTest() {
		Stockfeed stockfeed = new Stockfeed();
		PandaGummy panda = new PandaGummy();
		Cow moomoo = new Cow();
		Cow bubu = new Cow();
		farm.addItem(stockfeed);
		farm.addAnimal(moomoo);
		brown.feedAnimals(stockfeed);
		assertFalse(farm.hasFoodItems());
		assertEquals(175, farm.showAnimals().get(0).getHealth());
		farm.addItem(panda);
		farm.addAnimal(bubu);
		brown.feedAnimals(panda);
		assertEquals(250, farm.showAnimals().get(0).getHealth());
		assertEquals(225, farm.showAnimals().get(1).getHealth());
	}

	/**
	 * Tests that animal farm bonus is
	 * correctly applied when feeding
	 * animals. Due to code, unable to test
	 * both branches for item selection.
	 */
	@Test
	void animalFarmFeedingTests() {
		PandaGummy panda = new PandaGummy();
		PandaGummy panda2 = new PandaGummy();
		Cow moomoo = new Cow();
		AnimalFarm farm1 = new AnimalFarm();
		Farmer Duncan = new Farmer(farm1);
		farm1.addAnimal(moomoo);
		farm1.addItem(panda);
		Duncan.feedAnimals(panda2);
		assertEquals(245, farm1.showAnimals().get(0).getHealth());
	}
	/**
	 * Tests that tending to crops
	 * is working as intended.
	 */
	@Test
	void cropTendingTest() {
		Carrot carrot = new Carrot(0);
		Hipotke hip = new Hipotke(0);
		ChemicalSpray spray = new ChemicalSpray();
		InstantGrowLite igl = new InstantGrowLite();
		farm.addCrop(carrot);
		farm.addCrop(hip);
		farm.addItem(igl);
		farm.addItem(spray);
		brown.tendToCrops("watering plants", carrot, null);
		assertEquals(5, farm.showCrops().get(0).getDaysLeft());
		brown.tendToCrops("use item", hip, spray);
		assertEquals(14, farm.showCrops().get(1).getDaysLeft());
	}

	/**
	 * Tests that when tending to crops,
	 * TomaccoLand bonus correctly applies.
	 */
	@Test
	void TomaccoCropTest() {
		Tomacco cig = new Tomacco(0);
		Tomacco cig2 = new Tomacco(0);
		Tomacco cig3 = new Tomacco(0);
		TomaccoLand spring = new TomaccoLand();
		Farmer homer = new Farmer(spring);
		InstantGrowLite igl = new InstantGrowLite();
		spring.addCrop(cig);
		spring.addCrop(cig2);
		spring.addCrop(cig3);
		spring.addItem(igl);
		homer.tendToCrops("use item", cig, igl);
		assertFalse(spring.hasPlantItems());
		assertEquals(4, spring.showCrops().get(0).getDaysLeft());
		assertEquals(4, spring.showCrops().get(1).getDaysLeft());
		assertEquals(4, spring.showCrops().get(2).getDaysLeft());
		homer.tendToCrops("watering plants", cig, null);
		assertEquals(2, spring.showCrops().get(0).getDaysLeft());
		assertEquals(2, spring.showCrops().get(1).getDaysLeft());
		assertEquals(2, spring.showCrops().get(2).getDaysLeft());
	}

	/**
	 * Additional test of items
	 * being applied to crops.
	 */
	@Test
	void moreCropItemsTests() {
		InstantGrowPro igp = new InstantGrowPro();
		Carrot carrot = new Carrot(0);
		Hipotke hip = new Hipotke(0);
		farm.addItem(igp);
		farm.addCrop(hip);
		farm.addCrop(carrot);
		brown.tendToCrops("use item", carrot, igp);
		assertEquals(0, farm.showCrops().get(1).getDaysLeft());
		assertEquals(18, farm.showCrops().get(0).getDaysLeft());
	}

	/**
	 * Tests that MoomooFarm bonus
	 * correctly applies when a cow is
	 * in play.
	 */
	@Test
	void moomooFarmCropTests() {
		MoomooFarm seacow = new MoomooFarm();
		Farmer hachi = new Farmer(seacow);
		assertFalse(seacow.hasCow());
		Cow moomoo = new Cow();
		Carrot carrot = new Carrot(0);
		seacow.addCrop(carrot);
		hachi.tendToCrops("watering plants", carrot, null);
		assertEquals(5, seacow.showCrops().get(0).getDaysLeft());
		seacow.addAnimal(moomoo);
		assertTrue(seacow.hasCow());
		hachi.tendToCrops("watering plants", carrot, null);
		assertEquals(2, seacow.showCrops().get(0).getDaysLeft());
	}

	/**
	 * Ensure that items are properly
	 * consumed when used.
	 */
	@Test
	void itemDeletionTest() {
		Stockfeed stock = new Stockfeed();
		Compost compost = new Compost();
		Cow moomoo = new Cow();
		farm.addAnimal(moomoo);
		farm.addItem(compost);
		farm.addItem(stock);
		// add another different instance
		farm.addItem(new Stockfeed());
		brown.feedAnimals(stock);
		assertEquals(2, farm.showItems().size());
	}

	/**
	 * More item deletion tests.
	 */
	@Test
	void itemDeletionTest2() {
		Carrot carrot = new Carrot(0);
		Compost compost = new Compost();
		Compost compost2 = new Compost();
		farm.addCrop(carrot);
		farm.addItem(compost);
		farm.addItem(compost2);
		brown.tendToCrops("use item", carrot, compost2);
		assertEquals(1, farm.showItems().size());
	}

	/**
	 * Tests to ensure constructor, getter
	 * and setter methods are correct.
	 */
	@Test
	void newFarmerTest() {
		Farmer George = new Farmer(farm, "George Orwell", 56);
		assertEquals("George Orwell", George.getName());
		assertEquals(56, George.getAge());
		George.setName("Hugo");
		assertEquals("Hugo", George.getName());
		George.setAge(100);
		assertEquals(100, George.getAge());
	}
}