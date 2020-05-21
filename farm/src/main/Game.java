package main;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.animals.*;
import main.crops.*;
import main.farms.*;
import main.items.*;

/**
 * Command-line interface class that runs the game loop, and handles
 * every object in the game.
 *
 * @author Grant and Nick
 */
public class Game {
	private Farmer farmer;
	private Farm farm;
	private Store store;
	private int dayNumber = 1;
	private int maxDays = 0;

	/**
	 * Command line interface for setting the duration of the game.
	 * @param sc where to get the user input from (typically System.in)
	 */
	private void setGameDuration(final Scanner sc) {
		System.out.print("For how many days (from 5 to 10) would you like to play? ");
		while (true) {
			try {
				maxDays = sc.nextInt();
				sc.nextLine(); // discard rest of line
				if (5 <= maxDays && maxDays <= 10) {
					System.out.printf("Number of days set to %d\n", maxDays);
					break;
				} else {
					System.out.printf("%d is not between 5 and 10 inclusive!", maxDays);
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next(); // discard the input
			}
		}
	}

	/**
	 * Creates a new Store instance for the game.
	 */
	private void createStore() {
		store = new Store();
	}

	/**
	 * Prompts the user about what farm to create, and assigns Game.farm to
	 * this value.
	 */
	private void selectFarm(Scanner sc) {
		System.out.println("What farm would you like to create?");
		farmSelectLoop: while (true) {
			System.out.println("Input 1 for Animal Farm.");
			System.out.println("Input 2 for Trump Ranch.");
			System.out.println("Input 3 for Tomacco Land.");
			System.out.println("Input 4 for Moo Moo Farm.");
			try {
				switch (sc.nextInt()) {
				case 1:
					farm = new AnimalFarm();
					break farmSelectLoop; // breaks out of the while loop
				case 2:
					farm = new TrumpRanch();
					break farmSelectLoop;
				case 3:
					farm = new TomaccoLand();
					break farmSelectLoop;
				case 4:
					farm = new MoomooFarm();
					break farmSelectLoop;
				default:
					System.out.println("Number must be from 1 to 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a number from 1 to 4.");
				sc.next();
			}
		}
		// we include this because nextInt only "consumed" the number
		// and didn't consume the new line afterwards
		sc.nextLine();
		// printing the type of farm is optional
		System.out.printf("Farm %s successfully created!\n", farm.getType());
		System.out.printf("Farm info: %s\n", farm.getFlavour());
	}

	/**
	 * Rrompts user to enter a name and sets it to that, after performing
	 * a check for validity. Used by the command-line interface only.
	 *
	 * @param sc a scanner object to capture user entry
	 */
	public void setFarmName(final Scanner sc) {
		while (true) {
			String s;
			System.out.println("Please enter a name between 3 to 15 characters " +
					"long without numbers of symbols");
			s = sc.nextLine();
			farm.setName(s);
			if (isValidName(farm.getName())) {
				break;
			} else {
				farm.setName(null);
			}
		}
	}

	/**
	 * Checks if the given farm/farmer name is valid according to project
	 * requirements. (3-15 characters, alphabetical characters separated
	 * by single space, no extra spaces)
	 *
	 * @param name the name to check
	 * @return whether name is valid
	 */
	public boolean isValidName(String name) {
		if (name.length() < 3 || name.length() > 15) {
			System.out.println("Name is outside required length");
			return false;
		} else if (!name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
			System.out.println("Name cannot contain numbers or symbols, or extra spaces");
			return false;
		}
		return true;
	}

	/**
	 * Prompt user for their name and age. Used by the command-line
	 * interface.
	 *
	 * @param sc where to get user input from (usually System.in)
	 */
	public void setFarmerDetails(final Scanner sc) {
		System.out.print("Type your own name here: ");
		String name;
		while (true) {
			name = sc.nextLine();
			if (isValidName(name)) {
				break;
			} else {
				name = null;
			}
		}
		System.out.println("Hello " + name + "!");
		System.out.print("Type your age: ");
		int age;
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
		farmer = new Farmer(farm, name, age);
	}

	/**
	 * Starts and sets up game, and prompts required information from user.
	 * @param sc where to get user input from (typically System.in)
	 */
	private void startGame(Scanner sc) {
		System.out.println("Welcome to the game.");
		setGameDuration(sc);
		selectFarm(sc);
		setFarmName(sc);
		setFarmerDetails(sc);
		// set up store, now that we have a farm
		// (this could go anywhere to be honest)
		createStore();
	}

	/**
	 * Displays the animals on the farm to the console.
	 */
	private void viewAnimals() {
		ArrayList<Animal> animals = farm.showAnimals();
		for (Animal animal : animals) {
			System.out.printf("%s\n", animal);
		}
	}

	/**
	 * Displays the crops on the farm to the console.
	 */
	private void viewCrops() {
		ArrayList<Crop> crops = farm.showCrops();
		for (Crop crop : crops) {
			// it ain't pretty, but it's a proof of concept
			System.out.printf("%s\n", crop);
		}
	}

	/**
	 * Displays the items on the farm to the console.
	 */
	private void viewItems() {
		ArrayList<Item> items = farm.showItems();
		for (Item item : items) {
			System.out.printf("%s\n", item);
		}
	}

	/**
	 * Prints status of farm (including amount of money), and
	 * prompts if the user wants to see status of crops, animals,
	 * and items.
	 *
	 * @param sc where to get user input from (typically System.in)
	 */
	private void viewStatus(Scanner sc) {
		System.out.printf("%s, owned by %s (%d y.o.)\n", farm.getName(), farmer.getName(), farmer.getAge());
		System.out.printf("Money: %d\n", farm.getBankBalance());
		System.out.println("===");
		outerLoop: while (true) {
			System.out.println("Type 1 to view crops");
			System.out.println("Type 2 to view animals");
			System.out.println("Type 3 to view items");
			System.out.println("Type 4 to go back");
			optionLoop: while (true) {
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Viewing crops...");
						viewCrops();
						sc.nextLine();
						break optionLoop;
					case 2:
						System.out.println("Viewing animals...");
						viewAnimals();
						sc.nextLine();
						break optionLoop;
					case 3:
						System.out.println("Viewing items...");
						viewItems();
						sc.nextLine();
						break optionLoop;
					case 4:
						System.out.println("Returning...");
						break outerLoop;
					}
				} catch (InputMismatchException e) {
					System.out.println("Please type a number.");
					sc.next();
				}
			}
		}
	}

	/**
	 * Prompt user for a game loop option that doesn't cost any actions.
	 *
	 * @param sc where to get the user input from (typically System.in)
	 */
	private void promptNonAction(Scanner sc) {
		System.out.println("1. View status of farm OR farm's " +
				"crops/animals/items.");
		System.out.println("2. View general store.");
		System.out.println("3. Go back.");
		optionLoop: while (true) {
			try {
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Viewing status...");
					viewStatus(sc);
					break optionLoop;
				case 2:
					store.visitStore(farm, sc);
					break optionLoop;
				case 3:
					break optionLoop;
				default:
					System.out.println("Please type a valid number.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		sc.nextLine();
	}

	/**
	 * Handles all the daily actions do-able by the player.
	 *
	 * @param sc where to get user input from (typically System.in)
	 */
	private void promptAction(Scanner sc) {
		System.out.printf("You have %d actions left.\n", farm.getActionsLeft());
		System.out.println("1. Tend to crops.");
		System.out.println("2. Feed animals.");
		System.out.println("3. Play with animals.");
		System.out.println("4. Harvest crops.");
		System.out.println("5. Tend to farm land.");
		System.out.println("6. Go back.");
		optionLoop: while (true) {
			if (farm.getActionsLeft() == 0) {
				System.out.println("You have no actions left for today.");
				break optionLoop;
			}
			try {
				switch (sc.nextInt()) {
				case 1:

					System.out.println("Tending to the crops...");
					if (farm.hasCrops() == false) {
						System.out.println("You have no crops.");
						break optionLoop;
					}
					else {
						for (Crop crop: farm.showCrops()) {
							System.out.println(crop);
						}
						System.out.println("Enter 1 to tend to Carrots");
						System.out.println("Enter 2 to tend to Hipotkes");
						System.out.println("Enter 3 to tend to Mushrooms");
						System.out.println("Enter 4 to tend to Tomaccos");
						System.out.println("Enter 5 to tend to Wasabi");
						System.out.println("Enter 6 to tend to Wheat");
						System.out.println("Enter 7 to exit");
						innerLoop1: while (true) {
							try {
								switch (sc.nextInt()) {
								case 1:
									Carrot carrot = new Carrot();
									if (farm.plantInStock(carrot) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", carrot, null);
													System.out.println("Watered crops...");
													break actionLoop;

												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", carrot, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}
															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", carrot, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", carrot, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", carrot, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}
															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}
								case 2:
									Hipotke hip = new Hipotke();
									if (farm.plantInStock(hip) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", hip, null);
													System.out.println("Watered crops...");
													break actionLoop;

												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", hip, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", hip, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", hip, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", hip, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}
								case 3:
									Mushroom mush = new Mushroom();
									if (farm.plantInStock(mush) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", mush, null);
													System.out.println("Watered crops...");
													break actionLoop;

												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", mush, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", mush, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", mush, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", mush, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}

								case 4:
									Tomacco tomacco = new Tomacco();
									if (farm.plantInStock(tomacco) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", tomacco, null);
													System.out.println("Watered crops...");
													break actionLoop;

												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", tomacco, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", tomacco, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", tomacco, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", tomacco, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}

														}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}
								case 5:
									Wasabi wasabi = new Wasabi();
									if (farm.plantInStock(wasabi) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", wasabi, null);
													System.out.println("Watered crops...");
													break actionLoop;
												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wasabi, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wasabi, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wasabi, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wasabi, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}

								case 6:
									Wheat wheat = new Wheat();
									if (farm.plantInStock(wheat) == false) {
										System.out.println("You do not have this crop");
										break innerLoop1;
									}
									else {
										System.out.println("Enter 1: water plants");
										System.out.println("Enter 2: use an item");
										System.out.println("Enter 3: exit");
										actionLoop: while (true) {
											try {
												switch (sc.nextInt()) {
												case 1:
													farmer.tendToCrops("watering plants", wheat, null);
													System.out.println("Watered crops...");
													break actionLoop;

												case 2:
													if (farm.hasPlantItems() == false) {
														System.out.println("You do not have crop items in stock");
														break actionLoop;
													}
													else {
														Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Crop")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
														for (Map.Entry<String, Long> entry: counts.entrySet()) {
															System.out.println(entry.getKey() + ": " + entry.getValue());
														}
														System.out.println("Enter 1: use chemical spray");
														System.out.println("Enter 2: use compost");
														System.out.println("Enter 3: use Instant-Grow Lite�");
														System.out.println("Enter 4: use Instant-Grow Pro�");
														System.out.println("Enter 5: exit");
														itemLoop: while (true) {
														try {
															switch (sc.nextInt()) {
															case 1:
																ChemicalSpray spray = new ChemicalSpray();
																if (farm.itemInHand(spray) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wheat, spray);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 2:
																Compost compost = new Compost();
																if (farm.itemInHand(compost) == false) {
																	System.out.println("You do not have this item in stock");
																}
																else {
																	farmer.tendToCrops("use item", wheat, compost);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 3:
																InstantGroLite igl = new InstantGroLite();
																if (farm.itemInHand(igl) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wheat, igl);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 4:
																InstantGroPro igp = new InstantGroPro();
																if (farm.itemInHand(igp) == false) {
																	System.out.println("You do not have this item in stock");
																	break itemLoop;
																}
																else {
																	farmer.tendToCrops("use item", wheat, igp);
																	System.out.println("Item used...");
																	break itemLoop;
																}

															case 5:
																System.out.println("Returning...");
																break itemLoop;
															default:
																System.out.println("Please type a valid number.");
																sc.next();
															}

													}catch (InputMismatchException e) {
														System.out.println("Please type a valid number.");
														sc.next();
													}}
														}

											}}catch (InputMismatchException e) {
											System.out.println("Please type a valid number.");
											sc.next();
										}
											break actionLoop;
									}
										break innerLoop1;
									}

								case 7:
									System.out.println("Returning");
									break innerLoop1;

								default:
									System.out.println("Please type a valid number.");
									sc.next();
								}

						} catch (InputMismatchException e) {
							System.out.println("Please type a valid number.");
							sc.next();
						}
					}
				}
				break optionLoop;

				case 2:
					System.out.println("Feeding the animals...");
					if (farm.hasAnimals() == false) {
						System.out.println("You have no animals to feed.");
						break optionLoop;
					}

					else if (farm.hasFoodItems() == false) {
						System.out.println("You have no food for your animals");
						break optionLoop;
					}
					else {
						Map<String, Long> counts = farm.showItems().stream().filter(e -> e.getType().equals("Animal")).collect(Collectors.groupingBy(e -> e.getName(), Collectors.counting()));
						for (Map.Entry<String, Long> entry: counts.entrySet()) {
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
						System.out.println("Enter 1: use Stockfeed");
						System.out.println("Enter 2: use Panda Gummy");
						System.out.println("Enter 3: Go back");
						innerLoop: while (true) {
							try {
								switch (sc.nextInt()) {
								case 1:
									if (counts.containsKey("Stockfeed") == false) {
										System.out.println("You do not have this item in stock");
										break innerLoop;
									}
									else {
										System.out.println("Gave Stockfeed to animals");
										Stockfeed stock = new Stockfeed();
										farmer.feedAnimals(stock);
										break innerLoop;
									}
								case 2:
									if (counts.containsKey("Panda Gummy") == false) {
										System.out.println("You do not have this item in stock");
										break innerLoop;
									}
									else {
										System.out.println("Gave Panda Gummy to animals");
										PandaGummy panda = new PandaGummy();
										farmer.feedAnimals(panda);
										break innerLoop;
									}
								case 3:
									System.out.println("Returning...");
									break innerLoop;

								default:
									System.out.println("Please type a valid number.");
									sc.next();
								}


							} catch (InputMismatchException e) {
								System.out.println("Please type a valid number.");
								sc.next();
						}
					}}
					break optionLoop;

				case 3:
					if (!farm.hasAnimals()) {
						System.out.println("You have no animals to play with.");
						break optionLoop;
					}
					else {
						System.out.println("Playing with the animals...");
						farmer.playWithAnimals();
						break optionLoop;
					}

				case 4:
					if (!farm.hasCrops()) {
						System.out.println("You have no crops.");
						break optionLoop;
					} else if (farm.showCrops().stream().allMatch(
							e -> e.getDaysLeft() > 0)) {
						System.out.println("No crops are ready for harvest");
						break optionLoop;
					} else {
						System.out.println("Harvesting the crops...");
						farmer.harvestCrops(); //completed
						break optionLoop;
					}

				case 5:
					System.out.println("Tending to the farm land...");
					farmer.tendToLand(); //completed
					break optionLoop;
				case 6:
					System.out.println("Returning...");
					break optionLoop;
				default:
					System.out.println("Please type a valid number.");
					sc.next();
				}
			} catch (InputMismatchException e) {
				System.out.println("Please type a valid number.");
				sc.next();
			}
		}
		sc.nextLine();
	}

	/**
	 * Main game loop, loops over and over and prompts user for the
	 * next action.
	 *
	 * @param scanner used to get user input (System.in)
	 */
	private void mainGameLoop(Scanner sc) {
		outerLoop: while (dayNumber <= maxDays) {
			System.out.printf("Day number: %d\n", dayNumber);
			System.out.println("Please type a number from 1-9 below.");
			System.out.println("1. View status or visit store.");
			System.out.printf("2. Take an action (%d left).\n",
					farm.getActionsLeft());
			System.out.println("3. Move on to next day.");
			optionLoop: while (true) {
				switch (sc.nextInt()) {
				case 1:
					promptNonAction(sc);
					break optionLoop;
				case 2:
					promptAction(sc);
					break optionLoop;
				case 3:
					System.out.println("Receiving bonus money...");
					int income = farm.getDailyBonusMoney();
					farm.updateBankBalance(income);
					farm.refreshAP();
					// ensure crops mature by each day
					for (Crop crop : farm.showCrops()) {
						crop.updateDaysElapsed(1);
					}
					++dayNumber;
					continue outerLoop; // should skip to next day
				default:
					System.out.println("Please type a valid number.");
				}
			}
		}
	}

	/**
	 * Wraps up the game by printing game results and credits.
	 */
	private void finishGame() {
		System.out.println("==========");
		System.out.println("You have completed the farm simulator!");
		System.out.println("Thank you for playing.");
		System.out.println("==========");
		System.out.println("Farm name: " + farm.getName());
		System.out.println("Game duration: " + (dayNumber-1));
		System.out.println("Profit: " + farm.getBankBalance());
		System.out.println("Final score:" + farm.getScore());
	}

	/**
	 * Runs the game from start to finish.
	 *
	 * @param sc where to get user input from (typically System.in)
	 */
	public void runGame(Scanner sc) {
		// good practice to have only one Scanner open at a time
		// and to use the same Scanner throughout the program
		// (because when one Scanner is closed, System.in is closed too)
		startGame(sc);
		mainGameLoop(sc);
		finishGame();
	}

	/**
	 * Runs the game upon execution of this program.
	 *
	 * @param args arguments to the program, unused
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Game game = new Game();
		game.runGame(sc);
		sc.close(); // wrap up the Scanner, no longer accept input
	}
}
