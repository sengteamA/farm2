package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Farm {
	public String name;
	public int bankBalance = 1000; // amount of money on hand at any time. Other classes will need access to this. Set at 1000? - Nick 15/04/2020
	//public ArrayList<Animal> animals; Animals class yet to be implemented - Nick 15/04/2020
	//public ArrayList<Crops> crops; Crops class yet to be implemented - Nick 15/04/2020
	//public ArrayList<Items> items; Items class yet to be implemented - Nick 15/04/2020
	public float cropGrowthSpeed = 1;
	public int initialCashBonus;
	public float happinessMultiplier = 1;
	public float healthMultiplier = 1;
	public int actionsLeft = 2;
	
	Farm() {};
	
	public String getName() {
		return name;
	}
	
	public int getBankBalance() {
		return bankBalance;
	}
	
	public int getActionsLeft() {
		return actionsLeft;
	}
	
	/* the below three methods are not yet available as they are dependent on other classes - Nick 15/04/2020
	public ArrayList showCrops() {
		return crops;
	}
	
	public ArrayList showAnimals() {
		return animals
	}
	
	public ArrayList showItems() {
		return items;
	}
	*/
	
	// prompts players to enter a name for the farm - Nick 15/04/2020
	
	public void updateBankBalance(int amount) {
		bankBalance += amount;
	}
	
	public void setName() {
		String s;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a name between 3 to 15 characters long without numbers of symbols");
		s = sc.nextLine();
		name = s;
		validName();
	}
	
	// deducts actions left by 1 - Nick 15/04/2020
	public void updateAP() {
		actionsLeft -= 1;
	}
	
	//this method will continue to demand for entries until a valid name is provided - Nick 15/04/2020
	//should space in name be allowed?
	public void validName() {
		if (name.length() < 3 || name.length() > 15) {
			System.out.println("Name is outside required length");
			name = null;
			setName();
		}
		else if (name.matches("^[a-zA-Z]*$") == false) {
			System.out.println("Name cannot contain numbers or symbols");
			name = null;
			setName();
		}
	}
}
