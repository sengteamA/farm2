package main;

public class TomaccoLand extends Farm {
	public float cropGrowth = (float)1.2;
	//drought less likely yet to be implemented
	
	TomaccoLand() {
		super();
	}
	
	public String Flavour() {
		return "The dangers of nuclear waste meets the genius of genetic engineering\n"
				+ "tending to crops is 20% more effective\n"
				+ "droughts are 20% less likely";
	}
}