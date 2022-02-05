/**
 * @author SSene - Suzette Senephansiri
 * CIS175 - Spring 2022
 * Feb 2, 2022
 */

import java.util.List;
import java.util.Scanner;

import controller.OrderHelper;
import model.OrderList;

public class StartProgram {
		static Scanner in = new Scanner(System.in);
		static OrderHelper oh = new OrderHelper();

		private static void addAnItem() {
			System.out.print("Enter customer name: ");
			String name = in.nextLine();
			System.out.print("Enter drink: ");
			String drink = in.nextLine();
			OrderList toAdd = new OrderList(name, drink);
			oh.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			System.out.print("Enter customer name, to delete drink item: ");
			String name = in.nextLine();
			System.out.print("Enter drink item to delete: ");
			String drink = in.nextLine();
			OrderList toDelete = new OrderList(name, drink);
			oh.deleteItem(toDelete);
		}

		private static void editAnItem() {
			System.out.println("Search order by: ");
			System.out.println("1: Customer name");
			System.out.println("2: Drink item");
			int searchBy = in.nextInt();
			in.nextLine();
			List<OrderList> foundItems;
			
			if (searchBy == 1) {
				System.out.print("Enter the customer name: ");
				String customerName = in.nextLine();
				foundItems = oh.searchDrinkByName(customerName);
			} 
			
			else {
				System.out.print("Enter the drink: ");
				String drinkName = in.nextLine();
				foundItems = oh.searchDrinkByDrink(drinkName);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results:");
				for (OrderList l : foundItems) {
					System.out.println("Order # " + l.getNumber() + ", for " + l.getName());
				}
				System.out.print("Enter order #, to edit: ");
				int numToEdit = in.nextInt();

				OrderList toEdit = oh.searchDrinkByNum(numToEdit);
				System.out.println("Order: " + toEdit.getDrink() + " for " + toEdit.getName());
				System.out.println("1: Update name");
				System.out.println("2: Update drink");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} 
				
				else if (update == 2) {
					System.out.print("New drink: ");
					String newDrink = in.nextLine();
					toEdit.setDrink(newDrink);
				}

				oh.updateItem(toEdit);
			} 
			
			else {
				System.out.println("Oh no! No results found.");
			}
		}

		public static void main(String[] args) {
			runMenu();
		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("Welcome to Café Suze's Coffee Order App");
			while (goAgain) {
				System.out.println("1: Add a drink");
				System.out.println("2: Edit a drink");
				System.out.println("3: Delete a drink");
				System.out.println("4: View order");
				System.out.println("5: Exit! - Goodbye -");
				System.out.print("Choose a number option from the menu listed above: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} 
				
				else if (selection == 2) {
					editAnItem();
				} 
				
				else if (selection == 3) {
					deleteAnItem();
				} 
				
				else if (selection == 4) {
					viewTheList();
				} 
				
				else {
					oh.cleanUp();
					System.out.println("Thank you and come again, we hope you have a GRANDE!");
					goAgain = false;
				}
			}
		}

		private static void viewTheList() {
			List<OrderList>allItems = oh.showAllItems();
			
			for(OrderList singleItem : allItems) {
				System.out.println(singleItem.returnCoffeeOrder());
			}
		}
	}
