/**
 * A child of the Farm class.
 * This farm has an advantage in all things to do with animals
 * 20% bonus happiness and health upon purchase. Playing and feeding animals also comes with 20% bonus. 
 */

package main.farms;

public class AnimalFarm extends Farm {
	public float animalBonus =(float)0.2;
	public AnimalFarm() {
		super("Animal Farm",
				"All Animals are equal, but some are more equal than others.\n"
				+ "All animals start 20% happier and healthier on purchase.\n"
				+ "Feeding and playing with animals is 20% more effective.\n");
	}

	@Override
	public float getAnimalBonus() {
		return animalBonus;
	}
}
