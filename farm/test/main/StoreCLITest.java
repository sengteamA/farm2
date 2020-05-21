package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.*;
import main.crops.*;
import main.farms.Farm;
import main.items.*;

/**
 * Tests for the Store class, which implements a command-line interface for
 * interacting with the county general store.
 * @author Grant and Nick
 */
class StoreCLITest {
	Farm farm;
	StoreCLI store;

	@BeforeEach
	public void init() {
		farm = new Farm("Test", "Test");
		store = new StoreCLI();
	}

	/**
	 * Test purchasing every type of animal, with no regard
	 * for whether the user has enough money.
	 */
	@Test
	void purchaseAnimalTest() {
		farm.updateBankBalance(10000000);
		assertTrue(store.purchaseAnimal(farm, new Cow().getName()));
		assertTrue(store.purchaseAnimal(farm, new Fox().getName()));
		assertTrue(store.purchaseAnimal(farm, new Sheep().getName()));
	}

	/**
	 * Test purchasing an animal with not enough money.
	 */
	@Test
	void purchaseAnimalNotEnoughMoneyTest() {
		farm.updateBankBalance(-farm.getBankBalance() - 1);
		assertFalse(store.purchaseAnimal(farm, new Cow().getName()));
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
		assertTrue(store.purchaseCrop(farm, new Carrot().getName()));
		assertTrue(store.purchaseCrop(farm, new Hipotke().getName()));
		assertTrue(store.purchaseCrop(farm, new Mushroom().getName()));
		assertTrue(store.purchaseCrop(farm, new Tomacco().getName()));
		assertTrue(store.purchaseCrop(farm, new Wasabi().getName()));
		assertTrue(store.purchaseCrop(farm, new Wheat().getName()));
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
		assertFalse(store.purchaseCrop(farm, new Carrot().getName()));
	}

	/**
	 * Test purchasing every type of item, with no regard
	 * for whether the user has enough money.
	 */
	@Test
	void purchaseItemTest() {
		farm.updateBankBalance(10000000);
		assertTrue(store.purchaseItem(farm, new ChemicalSpray().getName()));
		assertTrue(store.purchaseItem(farm, new Compost().getName()));
		assertTrue(store.purchaseItem(farm, new InstantGroLite().getName()));
		assertTrue(store.purchaseItem(farm, new InstantGroPro().getName()));
		assertTrue(store.purchaseItem(farm, new PandaGummy().getName()));
		assertTrue(store.purchaseItem(farm, new Stockfeed().getName()));
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
		assertFalse(store.purchaseItem(farm, new Compost().getName()));
	}
}
