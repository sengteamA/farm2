package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.farms.Farm;

class StoreTest {
	Farm farm;
	Store store;

	@BeforeEach
	public void init() {
		farm = new Farm();
		store = new Store();
	}
	
	
	
	/**
	 * Test purchasing every type of animal, with no regard
	 * for whether the user has enough money.
	 */
	@Test
	void purchaseAnimalTest() {
		farm.updateBankBalance(10000000);
		assertTrue(store.purchaseAnimal(farm, "Cow"));
		assertTrue(store.purchaseAnimal(farm, "Fox"));
		assertTrue(store.purchaseAnimal(farm, "Sheep"));
	}
	
	/**
	 * Test purchasing an animal with not enough money.
	 */
	@Test
	void purchaseAnimalNotEnoughMoneyTest() {
		farm.updateBankBalance(-farm.getBankBalance() - 1);
		assertFalse(store.purchaseAnimal(farm, "Cow"));
	}
	
	/**
	 * Test purchasing an animal that doesn't exist.
	 */
	@Test
	void purchaseInvalidAnimalTest() {
		assertFalse(store.purchaseAnimal(farm, "Shiba Inu"));
	}
	
	/**
	 * Test purchasing every type of crop, with no regard
	 * for whether the user has enough money.
	 */
	@Test
	void purchaseCropTest() {
		farm.updateBankBalance(10000000);
		assertTrue(store.purchaseCrop(farm, "Carrot"));
		assertTrue(store.purchaseCrop(farm, "Hipotke Grass"));
		assertTrue(store.purchaseCrop(farm, "Mushroom"));
		assertTrue(store.purchaseCrop(farm, "Tomacco"));
		assertTrue(store.purchaseCrop(farm, "Wasabi"));
		assertTrue(store.purchaseCrop(farm, "Wheat"));
	}
	
	/**
	 * Test purchasing a crop that doesn't exist.
	 */
	@Test
	void purchaseInvalidCropTest() {
		assertFalse(store.purchaseCrop(farm, "Strawberry"));
	}
	
	/**
	 * Test purchasing a crop with not enough money.
	 */
	@Test
	void purchaseCropNotEnoughMoneyTest() {
		farm.updateBankBalance(-farm.getBankBalance() - 1);
		assertFalse(store.purchaseCrop(farm, "Carrot"));
	}
	
	/**
	 * Test purchasing every type of item, with no regard
	 * for whether the user has enough money.
	 */
	@Test
	void purchaseItemTest() {
		farm.updateBankBalance(10000000);
		assertTrue(store.purchaseCrop(farm, "Chemical Spray"));
		assertTrue(store.purchaseCrop(farm, "Compost"));
		assertTrue(store.purchaseCrop(farm, "Instant-Grow Lite"));
		assertTrue(store.purchaseCrop(farm, "Instant-Grow Pro"));
		assertTrue(store.purchaseCrop(farm, "Panda Gummy"));
		assertTrue(store.purchaseCrop(farm, "Stockfeed"));
	}
	
	/**
	 * Test purchasing an item that doesn't exist.
	 */
	@Test
	void purchaseInvalidItemTest() {
		assertFalse(store.purchaseItem(farm, "Max Potion"));
	}
	
	/**
	 * Test purchasing an item with not enough money.
	 */
	@Test
	void purchaseItemNotEnoughMoneyTest() {
		farm.updateBankBalance(-farm.getBankBalance() - 1);
		assertFalse(store.purchaseItem(farm, "Compost"));
	}
}
