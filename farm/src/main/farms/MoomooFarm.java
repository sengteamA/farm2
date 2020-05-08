package main.farms;

import main.animals.Animal;
import main.animals.Cow;

public class MoomooFarm extends Farm {
	
	public float cow_discount = (float)0.8;
	public float cow_factor = (float)1;
	public String flavour = "Cows be thy god\n"
			+ "cows can be purchased with 20% discount\n"
			+ "tend crops 20% more effective when farm has cows";
	
	public MoomooFarm() {
		super();
	}
	
	public boolean hasCow() {
		boolean outcome = false;
		for (Animal animal : this.showAnimals()) {
			if (animal instanceof Cow) {
				outcome = true;
			}
		}
		return outcome;
	}
	
	public float getCowFactor() {
		if (this.hasCow() == true) {
			cow_factor = (float)1.2;
		}
		return cow_factor;
	}
}