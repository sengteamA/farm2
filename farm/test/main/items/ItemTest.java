package main.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Comprehensively tests the Item class.
 * @author Nick
 */
class ItemTest {
	/**
	 * Tests flavour text getter
	 */
	@Test
	void chemicalSprayTest() {
		ChemicalSpray Chemical = new ChemicalSpray();
		assertEquals("kills bugs, and humans if you're not careful", Chemical.getFlavour());
	}

	/**
	 * Tests that benefits are displayed correctly
	 */
	@Test
	void pandaGummyTest() {
		PandaGummy panda = new PandaGummy();
		assertEquals("Health + 100", panda.getBenefit());
	}

	/**
	 * Tests name of product is correctly displayed
	 */
	@Test
	void instantGroProTest() {
		InstantGroPro ig = new InstantGroPro();
		assertEquals("Instant-Grow Pro(R)", ig.getName());
	}

	/**
	 * Test for price of item
	 */
	@Test
	void compostTest() {
		Compost compost = new Compost();
		assertEquals(150, compost.getPurchasePrice());
	}

	/**
	 * Test toString works
	 */
	@Test
	void toStringTest() {
		String name = "Blue";
		String benefit = "Benefit";
		Item blue = new Item(name, 500, "Crop", benefit, "Flavour text");
		assertEquals(name + ": " + benefit, blue.toString());
	}

	/**
	 * Test getInfo works
	 */
	@Test
	void getInfoTest() {
		String name = "Pink";
		String type = "Crop";
		String benefit = "Benefit text";
		String flavour = "Flavour text";
		Item blue = new Item(name, 500, type, benefit, flavour);
		assertEquals(name + ": (for " + type + ")\n" + benefit + "\n" + flavour,
				blue.getInfo());
	}
}
