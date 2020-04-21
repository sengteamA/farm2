package seng201.animal;

import seng201.Asset;

public class Animal extends Asset {
	
	private int health;
	private int happiness;
	private int maxHealth;
	private int maxHappiness;
	
	protected Animal(String name, int health, int happiness, int maxHealth, int maxHappiness, int purchasePrice) {
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
	
	///subject to revision
	public int bonus() { 
		return (health + happiness) * 4;
	}
}
