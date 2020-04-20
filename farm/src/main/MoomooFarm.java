package main;

public class MoomooFarm extends Farm {
	
	public float cow_discount = (float)0.8;
	public float cow_factor = (float)1;
	//yet to implement cow factor
	public MoomooFarm() {
		super();
	}
	
	public String Flavour() {
		return "Cows be thy god\n"
				+ "cows can be purchased with 20% discount\n"
				+ "tend crops 20% more effective when farm has cows";
	}
}