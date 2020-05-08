package main.farms;

public class TrumpRanch extends Farm {
	public float discount = (float)0.9; //10% discount on all purchases
	public float bonus = (float)1.1; //10% bonus on selling crops
	public int bankBalance = 1200; //hard coded 20% starting bonus
	public String flavour = "NOBODY KNOWS MORE ABOUT GETTING GOOD DEALS THAN ME!!\n"
			+ "All purchases are 10% cheaper\n"
			+ "10% bonus on all sales\n"
			+ "Starts game with 20% more cash";
	
	TrumpRanch() {
		super();
	}
	
	public int getBankBalance() {
		return bankBalance;
	}
	
	public void updateBankbalance(int amount) {
		bankBalance += amount;
	}
}