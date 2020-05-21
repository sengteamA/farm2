package main.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTests {
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
	void InstantGrowProTest() {
		InstantGrowPro ig = new InstantGrowPro();
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
	 * Tests InstantGrowLite attributes
	 */
	@Test
	void InstantGrowLiteTest() {
		InstantGrowLite igl = new InstantGrowLite();
		assertEquals("Instant-Grow Lite(R)", igl.getName());
		assertEquals(700, igl.getPurchasePrice());
		assertEquals("Halves days to harvest", igl.getBenefit());
		assertEquals("a lesser brand of witchcraft for the casual farmer", igl.getFlavour());
	}
	
	/**
	 * Tests stockfeed attributes
	 */
	@Test
	void stockfeedTest() {
		Stockfeed stock = new Stockfeed();
		assertEquals("Stockfeed", stock.getName());
		assertEquals(200, stock.getPurchasePrice());
		assertEquals("Animal", stock.getType());
		assertEquals("Health + 50", stock.getBenefit());
		assertEquals("Staple of all living livestocks", stock.getFlavour());
		assertEquals("Stockfeed: Health + 50", stock.toString());
		assertEquals("Stockfeed: (for Animal)\nHealth + 50\nStaple of all living livestocks", stock.getInfo());
	}
}