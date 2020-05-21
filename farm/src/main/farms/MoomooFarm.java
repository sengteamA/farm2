package main.farms;

import main.animals.Animal;
import main.animals.Cow;

/**
 * Moo Moo Farm - a farm powered by cows.
 * Farmer gets discount when purchasing cows.
 * A cow in play causes crops to grow faster.
 *
 * @author Nick
 */
public class MoomooFarm extends Farm {
	/**
	 * Creates a Moo Moo Farm.
	 */
	public MoomooFarm() {
		super("Moo Moo Farm",
				"Cows be thy god\n"
				+ "Cows can be purchased with 20% discount.\n"
				+ "Tending to crops makes them mature 2 day faster if farm has a cow.");
	}

	/**
	 * Returns whether there is a cow on the farm.
	 * @return existence of a cow
	 */
	public boolean hasCow() {
		for (Animal animal : this.showAnimals()) {
			if (animal.getName().equals(new Cow().getName())) {
				return true;
			}
		}
		return false;
	}
}