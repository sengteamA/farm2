package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import main.animals.*;
import main.crops.*;
import main.items.*;
import main.farms.*;
import org.junit.jupiter.api.Test;

class FarmerTest {
	
	public Farmer brown;
	public Farm farm;
	@BeforeEach
	
	public void init() {
		farm = new Farm("farm", "test");
		brown = new Farmer(farm);
	}

	@Test
	void tend_land_test() {
		Sheep blackie = new Sheep();
		farm.addAnimal(blackie);
		assertEquals(50, blackie.getHappiness());
		assertEquals(10, farm.getFarmCap());
		brown.tendToLand();
		assertEquals(70, blackie.getHappiness());
		assertEquals(12, farm.getFarmCap());
		assertEquals(1, farm.getActionsLeft());
	}
	
	@Test
	void animal_farm_land_test() {
		Sheep blackie = new Sheep();
		AnimalFarm snowball = new AnimalFarm();
		Farmer duncan = new Farmer(snowball);
		snowball.addAnimal(blackie); //initial bonus health & happiness will not be applicable here
		duncan.tendToLand();
		assertEquals("Sheep", snowball.showAnimals().get(0).getName());
		assertEquals(74, snowball.showAnimals().get(0).getHappiness());
	}
	
	@Test // completed 03/05/2020
	void harvest_test() {
		Tomacco tomacco = new Tomacco();
		Tomacco tomacco2 = new Tomacco();
		tomacco.updateDaysElapsed(10);
		tomacco2.updateDaysElapsed(10);
		farm.addCrop(tomacco);
		farm.addCrop(tomacco2);
		assertEquals(2, farm.showCrops().size());
		brown.harvestCrops();
		assertEquals(0, farm.showCrops().size());
		assertEquals(1130, farm.getBankBalance());
	}
	
	@Test // completed 03/05/2020
	void trump_harvest_test() {
		Carrot carrot = new Carrot();
		Wheat wheat = new Wheat();
		TrumpRanch tower = new TrumpRanch();
		Farmer Donald = new Farmer(tower);
		tower.addCrop(wheat);
		tower.addCrop(carrot);
		for (Crop crop: tower.showCrops()) {
			crop.updateDaysElapsed(4);
		}
		assertEquals(2, carrot.getDaysLeft());
		assertEquals(0, wheat.getDaysLeft());
		Donald.harvestCrops();
		assertEquals(1, tower.showCrops().size());
		assertFalse(tower.showCrops().contains(wheat));
		assertEquals(1213, tower.getBankBalance());
		assertEquals(1, tower.getActionsLeft());
	}
	
	@Test // completed 03/05/05/20202
	void play_animals_test() {
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		farm.addAnimal(moomoo);
		farm.addAnimal(crash);
		brown.playWithAnimals();
		assertEquals(155, moomoo.getHappiness());
		assertEquals(230, crash.getHappiness());
		assertEquals(1, farm.getActionsLeft());
	}
	
	@Test
	void animal_farm_play_test() {
		Cow moomoo = new Cow();
		Fox crash = new Fox();
		AnimalFarm pigsy = new AnimalFarm();
		Farmer napoleon = new Farmer(pigsy);
		pigsy.addAnimal(moomoo);
		pigsy.addAnimal(crash);
		napoleon.playWithAnimals();
		assertEquals(161, moomoo.getHappiness());
		assertEquals(236, crash.getHappiness());
	}
	
	@Test //test completed 08/05/2020
	void animal_feeding_items_test() {
		Stockfeed stockfeed = new Stockfeed();
		PandaGummy panda = new PandaGummy();
		Cow moomoo = new Cow();
		Cow bubu = new Cow();
		farm.addItem(stockfeed);
		farm.addAnimal(moomoo);
		brown.feedAnimals(stockfeed);
		assertFalse(farm.hasFoodItems());
		assertEquals(175, moomoo.getHealth());
		farm.addItem(panda);
		farm.addAnimal(bubu);
		brown.feedAnimals(panda);
		assertEquals(250, moomoo.getHealth());
		assertEquals(225, bubu.getHealth());
	}
}
