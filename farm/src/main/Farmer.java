package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.farms.Farm;

/**
 * Handles daily actions made to the farm by the player.
 * Also stores name and age of player.
 * @author Nick
 *
 */
public class Farmer {
	private String name;
	private int age;
	private Farm farm;

	/**
	 * Initialises Farmer using an existing farm.
	 * @param my_farm
	 */
	Farmer(Farm my_farm) {
		farm = my_farm;
	}

	// TODO: add some errors if not initialised yet?
	// or force user to initialise name and age in the constructor?
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	// TODO: add some stuff to verify farmer name,
	// i.e. take the Farm.isValidName() method and put it here
	/**
	 * Prompt user for their name and age.
	 */
	public void setDetails(Scanner sc) {
		System.out.print("Type your own name here: ");
		name = sc.nextLine();
		System.out.println("Hello " + name + "!");
		System.out.print("Type your age: ");
		while (true) {
			try {
				age = sc.nextInt();
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		System.out.printf("Got it. You are %d years old.\n", age);
	}
}
