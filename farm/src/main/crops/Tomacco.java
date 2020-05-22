package main.crops;

/**
 * Represents Tomacco, which is a solid mid-range crop.
 *
 * @author Nick
 */
public class Tomacco extends Crop {
	/**
	 * Creates some tomacco crop.
	 * @param dayPlanted the day this crop was purchased or planted
	 */
	public Tomacco(int dayPlanted) {
		super("Tomacco", 50, 9, 65, dayPlanted);
	}
}
