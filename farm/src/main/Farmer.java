package main;

import java.util.Scanner;

public class Farmer {
	private String name;
	private int age;

	Farmer() {}

	// TODO: add some stuff to verify farmer name,
	// e.g. Farm.isValidName()
	//
	// also add something to verify age perhaps?
	/**
	 * Prompt user for their name and age.
	 */
	public void setDetails(Scanner sc) {
		System.out.print("Type your own name here: ");
		name = sc.nextLine();
		System.out.println("Hello " + name + "!");
		System.out.print("Type your age: ");
		age = sc.nextInt();
		System.out.println("Got it.");
	}
}
