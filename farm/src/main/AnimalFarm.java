package main;

public class AnimalFarm extends Farm {
	//20% happiness and health bonus for all animals - Nick 16/04/2020
	public float happinessMultiplier = (float)1.2;
	public float healthMultiplier = (float)1.2;
	
	AnimalFarm() {
		super();
	}
	
	public String Flavour() {
		return "All Animals are equal, but some are more equal than others.\n"
				+ "All animals start 20% happier and healthier on purchase\n"
				+ "Feeding and playing with animals is 20% more effective\n";
	}
}
