package seng201;

public class TomaccoLand extends Farm {
	public float cropGrowth = (float)1.2;
	//drought less likely....
	
	TomaccoLand() {
		super();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public int getBankBalance() {
		return super.getBankBalance();
	}
	
	public int getActionsLeft() {
		return super.getActionsLeft();
	}
	
	/*
	 * animal, crops, items not yet implemented
	public ArrayList showCrops() {
		return super.showCrops();
	}
	
	public Arraylist showAnimals() {
		return super.showAnimals();
	}
	
	public Arraylist showItems() {
		return super.showItems();
	}*/
	
	public void setName() {
		super.setName();
	}
	
	public void updateAP() {
		super.updateAP();
	}
	
	public void updateBankbalance(int amount) {
		super.updateBankBalance(amount);
	}
	
	public String Flavour() {
		return "The dangers of nuclear waste meets the genius of genetic engineering\n"
				+ "tending to crops is 20% more effective\n"
				+ "droughts are 20% less likely";
	}

}
