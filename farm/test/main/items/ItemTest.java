package main;

import static org.junit.jupiter.api.Assertions.*;
import main.items.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author nickl
 * Tests that the general getter and setter functions are functionally correctly
 *
 */
class ItemTests {
	/**
	 * tests flavour text getter
	 */
	@Test
	void chemical_spray_test() {
		ChemicalSpray Chemical = new ChemicalSpray();
		assertEquals("kills bugs, and humans if you're not careful", Chemical.getFlavour());
	}
	
	/**
	 * tests that benefits are displayed correctly
	 */
	@Test
	void panda_gummy_test() {
		PandaGummy panda = new PandaGummy();
		assertEquals("Health + 100", panda.getBenefit());
	}
	
	/**
	 * tests name of product is correctly displayed
	 */
	@Test
	void ig_pro_test() {
		InstantGroPro ig = new InstantGroPro();
		assertEquals("Instant-Grow Pro(R)", ig.getName());
	}
	/**
	 * test for price of item
	 */
	@Test
	void compost_test() {
		Compost compost = new Compost();
		assertEquals(150, compost.getPurchasePrice());
	}
	
	@Test
	void iglTest() {
		InstantGroLite igl = new InstantGroLite();
		assertEquals("Instant-Grow Lite(R)", igl.getName());
		assertEquals(700, igl.getPurchasePrice());
		assertEquals("Halves days to harvest", igl.getBenefit());
		assertEquals("a lesser brand of witchcraft for the casual farmer", igl.getFlavour());
	}
	
	@Test
	void stockTest() {
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
