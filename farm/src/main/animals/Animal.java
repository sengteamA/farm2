package main.animals;

import main.Asset;

/**
 * Represents an Animal, with health and happiness levels.
 * @author Nick
 *
 */
public class Animal extends Asset {
	private int health;
	private int happiness;
	/**
	 * Health and happiness are always capped at maxHealth and maxHappiness,
	 * which are set in child classes of Animal
	 */
	private int maxHealth;
	private int maxHappiness;

	/**
	 * Creates a new Animal instance.
	 * @param name name of animal
	 * @param health default health level of animal
	 * @param happiness default happiness level of animal
	 * @param maxHealth maximum health level
	 * @param maxHappiness maximum happiness level
	 * @param purchasePrice default price in store (before any discounts)
	 */
	public Animal(String name, int health, int happiness, int maxHealth, int maxHappiness, int purchasePrice) {
		super(name, purchasePrice);
		this.health = health;
		this.happiness = happiness;
		this.maxHealth = maxHealth;
		this.maxHappiness = maxHappiness;
	}

	/**
	 * Returns health level.
	 * @return Health level of animal, used by farm
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Returns happiness level.
	 * @return Happiness level of animal, used by farm
	 */
	public int getHappiness() {
		return happiness;
	}

	/**
	 * Increases the animal's health level by the given amount, capping at
	 * maxHealth.
	 *
	 * @param amount amount to increase health level by
	 */
	public void updateHealth(int amount) {
		health += amount;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}

	/**
	 * Increases the animal's happiness level by the given amount, capping at
	 * maxHappiness.
	 *
	 * @param amount amount to increase happiness level by
	 */
	public void updateHappiness(int amount) {
		happiness += amount;
		if (happiness > maxHappiness) {
			happiness = maxHappiness;
		}
	}

	/**
	 * Returns the bonus points received from the animal, used at the end of
	 * the day.
	 *
	 * @return Bonus points from the animal, rounded down to the nearest
	 * 		   integer. Used by the farm to give the player the daily
	 *         bonus at the end of the day.
	 */
	public int bonus() {
		float bonus = (health + happiness) * (float)0.4;
		return (int)bonus;
	}

	/**
	 * Returns brief information about the animal on one line.
	 */
	@Override
	public String toString() {
		return name.toUpperCase() + " health " + health + " happiness " + happiness;
	}

	/**
	 * Returns the health/happiness levels and name of Animal, spread out on
	 * multiple lines.
	 */
	@Override
	public String getInfo() {
		return name + ":\n" +
				"Happiness: " + happiness + "\n" +
				"Health " + health;
	}
}
