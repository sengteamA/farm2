package main.farms;

/**
 * An animal farm.
 *
 * This farm has an advantage in all things to do with animals. 20% bonus
 * happiness and health upon purchase, and playing and feeding animals also
 * comes with 20% bonus.
 *
 * @author Nick
 */
public class AnimalFarm extends Farm {
	private float animalBonus = (float) 0.2;

	/**
	 * Creates an animal farm.
	 */
	public AnimalFarm() {
		super("Animal Farm",
				"All Animals are equal, but some are more equal than others.\n"
				+ "All animals start 20% happier and healthier on purchase.\n"
				+ "Feeding and playing with animals is 20% more effective.\n");
	}

	/**
	 * Returns the bonus animals get for health and happiness upon purchasing.
	 * This is 0.2 for all Animal Farm instances.
	 *
	 * @return Bonus amount (0.2 for Animal Farm)
	 */
	@Override
	public float getAnimalBonus() {
		return animalBonus;
	}
}
