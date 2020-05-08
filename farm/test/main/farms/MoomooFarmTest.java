package main.farms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.animals.Cow;

public class MoomooFarmTest {

	public MoomooFarm moo = new MoomooFarm();

	@BeforeEach
	public void init() {
		moo = new MoomooFarm();
	}

	@Test //test completed 01/05/2020
	void cowFactorTest() {
		Cow sophie = new Cow();
		assertFalse(moo.hasCow());
		moo.addAnimal(sophie);
		assertTrue(sophie instanceof Cow);
		assertEquals((float)1, moo.showAnimals().size());
		assertTrue(moo.hasCow());
		assertEquals((float)1.2, moo.getCowFactor());
		moo.delAnimal(sophie);
		assertFalse(moo.hasCow());
	}

}
