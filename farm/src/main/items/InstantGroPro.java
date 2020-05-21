package main.items;

/**
 * Instant-Grow Pro(R) - instantly readies a crop type (e.g. all "Wasabi"
 * instances) for harvest.
 *
 * @author Nick
 */
public class InstantGroPro extends Item {
	/**
	 * Creates a new Instant-Grow Pro(R). More expensive and more effective
	 * than Instant-Grow Lite(R).
	 */
	public InstantGroPro() {
		super("Instant-Grow Pro(R)", 1000, "Crop",
				"Immediately harvest a crop",
				"Immediately readies a crop for harvest. " +
				"Infused with patented witchcraft technology");
	}
}