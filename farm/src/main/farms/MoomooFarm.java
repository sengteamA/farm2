/**
 * This farm is powered by cows. 
 * Farmer gets discount when purchasing cows.
 * A cow in play causes crops to grow faster. 
 */

package main.farms;

import main.animals.Animal;
import main.animals.Cow;

public class MoomooFarm extends Farm {

	public MoomooFarm() {
		super("Moo Moo Farm",
				"Cows be thy god\n"
				+ "cows can be purchased with 20% discount\n"
				+ "tending to crops makes them mature 2 day faster if farm has a cow");
	}

	public boolean hasCow() {
		boolean outcome = false;
		for (Animal animal: this.showAnimals()) {
			if (animal.getName().equals("Cow")) {
				outcome = true;
			}
		}
		return outcome;
	}
}