package main.animal;

public class Animal {
	
	public String name;
	public int health;
	public int happiness;
	public int maxHealth;
	public int maxHappiness;
	public float price;
	public int unitsOwned;
	
	Animal() {};
	Animal(String name, int health, int happiness, int maxHealth, int maxHappiness, float price) {
		this.name = name;
		this.health = health;
		this.happiness = happiness;
		this.price = price;
		this.maxHealth = maxHealth;
		this.maxHappiness = maxHappiness;
		
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public int getUnitsOwned() {
		return unitsOwned;
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
	
	public void updateUnitsOnwed(int amount) {
		unitsOwned += amount;
	}
	
	///subject to revision
	public int bonus() { 
		return (health + happiness) * 4;
	}
	
	public static void main(String[] args) {
		Animal pig = new Animal("Napoleon", 10, 10, 20, 20, 200);
		pig.updateHappiness(5);
		pig.updateHealth(3);
		System.out.println(pig.getHappiness());
		System.out.println(pig.getHealth());
	}

	public int anotherUselessTestFunction() {
		return 5;
	}

	public boolean testFunction() {
		return true;
	}
}
