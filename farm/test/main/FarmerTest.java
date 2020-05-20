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

	@BeforeEach
	public void init() {
		farm = new Farm("farm", "test");
		brown = new Farmer(farm);
	}

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

	@Test
	void animalFarmLandTest() {
		Sheep blackie = new Sheep();
		AnimalFarm snowball = new AnimalFarm();
		Farmer duncan = new Farmer(snowball);
		snowball.addAnimal(blackie); //initial bonus health & happiness will not be applicable here
		duncan.tendToLand();
		assertEquals("Sheep", snowball.showAnimals().get(0).getName());
		assertEquals(74, snowball.showAnimals().get(0).getHappiness());
	}

	@Test
	void harvestTest() {
		Tomacco tomacco = new Tomacco();
		Tomacco tomacco2 = new Tomacco();
		farm.addCrop(tomacco);
		farm.addCrop(tomacco2);
		assertEquals(2, farm.showCrops().size());
		InstantGroPro igp = new InstantGroPro();
		farm.addItem(igp);
		brown.tendToCrops("use item", tomacco, igp);
		brown.harvestCrops();
		assertEquals(0, farm.showCrops().size());
		assertEquals(1130, farm.getBankBalance());
	}

	@Test
	void trumpHarvestTest() {
		Carrot carrot = new Carrot();
		Wheat wheat = new Wheat();
		TrumpRanch tower = new TrumpRanch();
		Farmer Donald = new Farmer(tower);
		tower.addCrop(wheat);
		tower.addCrop(carrot);
		assertEquals(2, tower.showCrops().size());
		InstantGroPro igp = new InstantGroPro();
		Donald.tendToCrops("use item", carrot, igp);
		assertEquals(0, tower.showCrops().get(1).getDaysLeft());
		Donald.harvestCrops();
		assertEquals(1, tower.showCrops().size());
		assertEquals(1226, tower.getBankBalance());
	}

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

	@Test
	void cropTendingTest() {
		Carrot carrot = new Carrot();
		Hipotke hip = new Hipotke();
		InstantGroLite igl = new InstantGroLite();
		farm.addCrop(carrot);
		farm.addCrop(hip);
		farm.addItem(igl);
		brown.tendToCrops("watering plants", carrot, null);
		assertEquals(5, farm.showCrops().get(0).getDaysLeft());
	}

	@Test
	void TomaccoCropTest() {
		Tomacco cig = new Tomacco();
		Tomacco cig2 = new Tomacco();
		Tomacco cig3 = new Tomacco();
		TomaccoLand spring = new TomaccoLand();
		Farmer homer = new Farmer(spring);
		InstantGroLite igl = new InstantGroLite();
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

	@Test
	void moreCropItemsTests() {
		InstantGroPro igp = new InstantGroPro();
		Carrot carrot = new Carrot();
		Hipotke hip = new Hipotke();
		farm.addItem(igp);
		farm.addCrop(hip);
		farm.addCrop(carrot);
		brown.tendToCrops("use item", carrot, igp);
		assertEquals(0, farm.showCrops().get(1).getDaysLeft());
		assertEquals(18, farm.showCrops().get(0).getDaysLeft());
	}

	@Test
	void moomooFarmCropTests() {
		MoomooFarm seacow = new MoomooFarm();
		Farmer hachi = new Farmer(seacow);
		assertFalse(seacow.hasCow());
		Cow moomoo = new Cow();
		Carrot carrot = new Carrot();
		seacow.addCrop(carrot);
		hachi.tendToCrops("watering plants", carrot, null);
		assertEquals(5, seacow.showCrops().get(0).getDaysLeft());
		seacow.addAnimal(moomoo);
		assertTrue(seacow.hasCow());
		hachi.tendToCrops("watering plants", carrot, null);
		assertEquals(2, seacow.showCrops().get(0).getDaysLeft());
	}

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

	@Test
	void itemDeletionTest2() {
		Carrot carrot = new Carrot();
		Compost compost = new Compost();
		Compost compost2 = new Compost();
		farm.addCrop(carrot);
		farm.addItem(compost);
		farm.addItem(compost2);
		brown.tendToCrops("use item", carrot, compost2);
		assertEquals(1, farm.showItems().size());
	}
}


