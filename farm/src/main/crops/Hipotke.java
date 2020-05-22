package main.crops;

/**
 * Represents Hipotke Grass, a reference to the series That Time I Got
 * Reincarnated as a Slime.
 *
 * Very expensive, but the rewards for successful harvesting is the highest
 * in the game.
 *
 * @author Nick
 */
public class Hipotke extends Crop {
	/**
	 * Creates some Hipotke Grass.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Hipotke(int dayPlanted) {
		super("Hipotke Grass", 200, 18, 300, dayPlanted);
	}
}
