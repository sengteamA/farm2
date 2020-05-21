package main.items;

/**
 * Instant-Grow Lite(R), used to halve time required to harvest.
 *
 * @author Nick
 */
public class InstantGrowLite extends Item {
	/**
	 * Initialises a new Instant-Grow Lite(R) item. This is less expensive
	 * than the Instant-Grow Pro(R).
	 */
	public InstantGrowLite() {
		super("Instant-Grow Lite(R)", 700, "Crop",
				"Halves days to harvest",
				"a lesser brand of witchcraft for the casual farmer");
	}
}
