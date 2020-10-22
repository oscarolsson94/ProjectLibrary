import java.io.*;
import java.util.*;

public class Library {

	public static Map<Integer, Masterpiece> registry = new TreeMap<>();
	public static Map<Integer, String> IDandTitle = new TreeMap<>();
	public static Map<Integer, Customer> checkedOut = new HashMap<>();

	enum Command {

		list, checkout, checkin, register, deregister, info, quit, unknown

	}

	public Command parseCommand(String input) {

		String commandString = input.split(" ")[0];
		commandString = commandString.toLowerCase();

		switch (commandString) {

		case "list":
			return Command.list;
		case "checkout":
			return Command.checkout;
		case "checkin":
			return Command.checkin;
		case "register":
			return Command.register;
		case "deregister":
			return Command.deregister;
		case "info":
			return Command.info;
		case "quit":
			return Command.quit;
		default:
			return Command.unknown;

		}

	}

	public String[] parseArguments(String input) {
		String[] commandAndArguments = input.split(" ");
		String[] arguments = new String[commandAndArguments.length - 1];

		for (int i = 1; i < commandAndArguments.length; i++) {
			arguments[i - 1] = commandAndArguments[i];
		}
		return arguments;
	}

	public void handleUnknownCommand() {
		System.out.println("Unknown command, try again");
	}

	public void handleListCommand() {

		for (Integer x : registry.keySet()) {
			String key = x.toString();
			String value = registry.get(x).toString();

			if (registry.get(x) instanceof Book) {
				if (checkedOut.containsKey(x)) {
					System.out.println(
							key + " " + "(Book): " + IDandTitle.get(x) + ". Borrowed by: " + checkedOut.get(x));
				} else {
					System.out.println(key + " " + "(Book): " + IDandTitle.get(x) + ". (in stock)");
				}
			} else if (registry.get(x) instanceof Movie) {
				if (checkedOut.containsKey(x)) {
					System.out.println(
							key + " " + "(Movie): " + IDandTitle.get(x) + ". Borrowed by: " + checkedOut.get(x));
				} else {
					System.out.println(key + " " + "(Movie): " + IDandTitle.get(x) + ". (in stock)");
				}
			}

		}
	}

	public void handleCheckinCommand(String[] argument) {
		int key = Integer.parseInt(argument[0]);

		if (checkedOut.containsKey(key)) {
			checkedOut.remove(key);
			System.out.println("Successfully checked in " + IDandTitle.get(key) + " to the library");
		} else {
			System.out.println("There is no checked out product with that ID");
		}
	}

	public void handleCheckoutCommand(String[] argument) {
		int key = Integer.parseInt(argument[0]);
		if (!checkedOut.containsKey(key)) {
			System.out.println("Who is borrowing this book?");
			Scanner scan = new Scanner(System.in);
			String name = scan.nextLine();
			System.out.println("What is " + name + "'s " + "phone number?");
			String number = scan.nextLine();
			if (registry.containsKey(key)) {
				checkedOut.put(key, new Customer(name, number));
				System.out.println("Successfully checked out " + IDandTitle.get(key) + " to " + checkedOut.get(key));

			} else {
				System.out.println("There is no product with that ID registered");
			}
		} else {
			System.out.println("That product is already borrowed by: " + checkedOut.get(key));
		}
	}

	public void handleRegisterCommand() {
		Scanner scan = new Scanner(System.in);

		System.out.println("What are you registering? Book (b), Movie (m):");
		String bookOrMovie = scan.nextLine();
		System.out.println("Enter product ID:");
		int prodID = Integer.parseInt(scan.nextLine());

		if (!registry.containsKey(prodID)) {
			System.out.println("Enter title:");
			String title = scan.nextLine();
			System.out.println("Enter value:");
			int value = Integer.parseInt(scan.nextLine());

			if (bookOrMovie.equals("m")) {
				System.out.println("Enter length:");
				int length = Integer.parseInt(scan.nextLine());
				System.out.println("Enter rating:");
				float rating = Float.parseFloat(scan.nextLine());
				if (registry.containsKey(prodID)) {
					System.out.println("Error: Product with ID " + prodID + " already registered");
					scan.close();
					return;
				} else {
					registry.put(prodID, new Movie(value, prodID, length, rating));
					IDandTitle.put(prodID, title);
				}
			} else if (bookOrMovie.equals("b")) {
				System.out.println("Enter number of pages:");
				int pages = Integer.parseInt(scan.nextLine());
				System.out.println("Enter publisher:");
				String publisher = scan.nextLine();

				if (registry.containsKey(prodID)) {
					System.out.println("Error: Product with ID " + prodID + " already registered");
					scan.close();
					return;
				} else {
					registry.put(prodID, new Book(value, prodID, pages, publisher));
					IDandTitle.put(prodID, title);
				}
			} else {
				System.out.println("Unknown command, available commands are 'b' and 'm' ");
			}

			System.out.println("Successfully registered " + title);
		} else {
			System.out.println("A product with that ID is already registered");
		}
	}

	public void handleDeregisterCommand(String[] argument) {
		int key = Integer.parseInt(argument[0]);
		if (registry.containsKey(key)) {
			registry.remove(key);
			System.out.println("Successfully deregistered " + IDandTitle.get(key));
			IDandTitle.remove(key);
			if (checkedOut.containsKey(key))
				checkedOut.remove(key);
		} else {
			System.out.println("There is no product with that ID registered");
		}
	}

	public void handleInfoCommand(String[] argument) {
		int temp = Integer.parseInt(argument[0]);

		if (registry.containsKey(temp)) {
			if (registry.get(temp) instanceof Movie) {
				System.out.println("(Movie) " + IDandTitle.get(temp) + ": " + "Value " + registry.get(temp).value
						+ "kr, " + registry.get(temp));
			} else {
				System.out.println("(Book) " + IDandTitle.get(temp) + ": " + "Value " + registry.get(temp).value
						+ "kr, " + registry.get(temp));
			}
		} else {
			System.out.println("There is no product with that ID registered");
		}
	}

	public void handleQuitCommand() {
		System.out.println("terminating program");
		System.exit(0);
	}

	public void save() {

		try {
			FileOutputStream fos1 = new FileOutputStream("hashmap.ser");
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			oos1.writeObject(checkedOut);
			oos1.close();
			fos1.close();
			System.out.println("Serialized HashMap data saved");

			FileOutputStream fos2 = new FileOutputStream("treemap1.ser");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(registry);
			oos2.close();
			fos2.close();
			System.out.println("Serialized TreeMap data saved");

			FileOutputStream fos3 = new FileOutputStream("treemap2.ser");
			ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
			oos3.writeObject(IDandTitle);
			oos3.close();
			fos3.close();
			System.out.println("Serialized TreeMap data saved");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void resume() {

		try {
			FileInputStream fis1 = new FileInputStream("hashmap.ser");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			checkedOut = (Map<Integer, Customer>) ois1.readObject();
			ois1.close();
			fis1.close();

			FileInputStream fis2 = new FileInputStream("treemap1.ser");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			registry = (TreeMap<Integer, Masterpiece>) ois2.readObject();
			ois2.close();
			fis2.close();

			FileInputStream fis3 = new FileInputStream("treemap2.ser");
			ObjectInputStream ois3 = new ObjectInputStream(fis3);
			IDandTitle = (HashMap<Integer, String>) ois3.readObject();
			ois3.close();
			fis3.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Successfully initialized state from files");

	}

}
