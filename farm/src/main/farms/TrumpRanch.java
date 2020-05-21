package main.farms;

/**
 * Trump Ranch - the name says it all.
 *
 * This farmer is a sharp cookie when it comes to trading. 10% discount on
 * all purchases, and 10% bonus on sale of harvested crops.
 *
 * Also starts with extra cash.
 *
 * @author Nick
 */
public class TrumpRanch extends Farm {
	/**
	 * Creates a Trump Ranch.
	 */
	public TrumpRanch() {
		super("Trump Ranch",
				"NOBODY KNOWS MORE ABOUT GETTING GOOD DEALS THAN ME!!\n"
				+ "All purchases are 10% cheaper!\n"
				+ "10% bonus on all sales!\n"
				+ "Starts game with 20% more cash!", 1200);
	}
}