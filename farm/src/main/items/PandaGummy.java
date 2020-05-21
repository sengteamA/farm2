package main.items;

/**
 * Panda Gummy - feed to animals to boost double the health of Stockfeed.
 *
 * @author Nick
 */
public class PandaGummy extends Item {
	/**
	 * Creates a panda gummy treat.
	 */
	public PandaGummy() {
		super("Panda Gummy", 350, "Animal",
				"Health + 100",
				"Treat loved by all animals. WARNING: may contain Panda fur");
	}
}