/**
 * This is an extension of the Crop class.
 * It is an item that can be bought in game.
 * Once its daystoHarvest reaches 0, it can be harvested for money.  
 * 
 * @author Grant Wong
 */


package main.crops;

public class Carrot extends Crop {
	public Carrot() {
		super("Carrot", 20, 6, 24);
	}
}
