package main.animals;

import main.Asset;

public class Animal extends Asset {
	private int health;
	private int happiness;
	private int maxHealth;
	private int maxHappiness;

	public Animal(String name, int health, int happiness, int maxHealth, int maxHappiness, int purchasePrice) {
		super(name, purchasePrice);
		this.health = health;
		this.happiness = happiness;
		this.maxHealth = maxHealth;
		this.maxHappiness = maxHappiness;
	}

	public int getHealth() {
		return health;
	}

	public int getHappiness() {
		return happiness;
	}

	public void updateHealth(int amount) {
		health += amount;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}

	public void updateHappiness(int amount) {
		happiness += amount;
		if (happiness > maxHappiness) {
			happiness = maxHappiness;
		}
	}

	public int bonus() {
		float bonus = (health + happiness) * (float)0.4;
		return (int)bonus;
	}

	@Override
	public String toString() {
		return name.toUpperCase() + " health " + health + " happiness " + happiness;
	}

	@Override
	public String getInfo() {
		return getName() + " - " + getPurchasePrice() + " (happiness " +
				getHappiness() + ", health " + getHealth() + ")";
	}
	//methods required for deep copy
		public int getMaxHealth() {
			return maxHealth;
		}
		
		public int getMaxHappy() {
			return maxHappiness;
		}
}
