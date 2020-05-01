package main.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTests {

	@Test
	void chemical_spray_test() {
		ChemicalSpray Chemical = new ChemicalSpray();
		assertEquals("kills bugs, and humans if you're not careful", Chemical.getFlavour());
	}
	
	@Test
	void panda_gummy_test() {
		PandaGummy panda = new PandaGummy();
		assertEquals("Health + 10", panda.getBenefit());
	}
	
	@Test
	void ig_pro_test() {
		InstantGroPro ig = new InstantGroPro();
		assertEquals("Instant-Grow Pro®", ig.getName());
	}
	
	@Test
	void compost_test() {
		Compost compost = new Compost();
		assertEquals(150, compost.getPurchasePrice());
	}
	
	@Test
	void item_count_test() {
		Compost compost = new Compost();
		assertEquals(0, compost.getUnits());
		compost.updateUnits(15);
		assertEquals(15, compost.getUnits());
	}


}
