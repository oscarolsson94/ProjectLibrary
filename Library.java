import java.io.*;
import java.util.*;

public class Library {

	public static Map<Integer, Masterpiece> registry = new TreeMap<>();
	public static Map<Integer, String> idAndTitle = new TreeMap<>();
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

			if (registry.get(x) instanceof Book) {
				if (checkedOut.containsKey(x)) {
					System.out.println(
							key + " " + "(Book): " + idAndTitle.get(x) + ". Borrowed by: " + checkedOut.get(x));
				} else {
					System.out.println(key + " " + "(Book): " + idAndTitle.get(x) + ". (in stock)");
				}
			} else if (registry.get(x) instanceof Movie) {
				if (checkedOut.containsKey(x)) {
					System.out.println(
							key + " " + "(Movie): " + idAndTitle.get(x) + ". Borrowed by: " + checkedOut.get(x));
				} else {
					System.out.println(key + " " + "(Movie): " + idAndTitle.get(x) + ". (in stock)");
				}
			}

		}
	}

	public void handleCheckinCommand(String[] argument) {
		try {
			if (argument.length > 1)
				throw new IndexOutOfBoundsException();
			int key = Integer.parseInt(argument[0]);

			if (checkedOut.containsKey(key)) {
				checkedOut.remove(key);
				System.out.println("Successfully checked in " + idAndTitle.get(key) + " to the library");
			} else {
				System.out.println("There is no checked out product with that ID");
			}
		} catch (NumberFormatException | IndexOutOfBoundsException e) {

			System.out.println("Syntax error. Correct format is: 'checkin <productID>'");
		}
	}

	public void handleCheckoutCommand(String[] argument) {

		try {
			if (argument.length > 1)
				throw new IndexOutOfBoundsException();
			Scanner scan = new Scanner(System.in);
			int key = Integer.parseInt(argument[0]);
			if (!checkedOut.containsKey(key)) {
				System.out.println("Who is borrowing this book?");

				String name = scan.nextLine();
				System.out.println("What is " + name + "'s " + "phone number?");
				String number = scan.nextLine();

				if (registry.containsKey(key)) {
					checkedOut.put(key, new Customer(name, number));
					System.out
							.println("Successfully checked out " + idAndTitle.get(key) + " to " + checkedOut.get(key));

				} else {
					System.out.println("There is no product with that ID registered");
				}
			} else {
				System.out.println("That product is already borrowed by: " + checkedOut.get(key));
			}

		} catch (NumberFormatException | IndexOutOfBoundsException e) {

			System.out.println("Syntax error. Correct format is: 'checkout <productID>'");
		}

	}

	public void handleRegisterCommand() {

		try {

			Scanner scan = new Scanner(System.in);
			System.out.println("Enter product ID:");
			int prodID = Integer.parseInt(scan.nextLine());
			System.out.println("What are you registering? Book (b), Movie (m):");
			String bookOrMovie = scan.nextLine();
			if (!(bookOrMovie.equals("b") || bookOrMovie.equals("m")))
				throw new NumberFormatException();

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
						idAndTitle.put(prodID, title);
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
						idAndTitle.put(prodID, title);
					}
				} else {
					System.out.println("Unknown command, available commands are 'b' and 'm' ");
				}

				System.out.println("Successfully registered " + title);
			} else {
				System.out.println("A product with that ID is already registered");
			}
		} catch (NumberFormatException e) {
			System.out.println("Illegal input, please follow the instructions.");
		}
	}

	public void handleDeregisterCommand(String[] argument) {
		try {
			if (argument.length > 1)
				throw new IndexOutOfBoundsException();
			int key = Integer.parseInt(argument[0]);
			if (registry.containsKey(key)) {
				registry.remove(key);
				System.out.println("Successfully deregistered " + idAndTitle.get(key));
				idAndTitle.remove(key);
				if (checkedOut.containsKey(key))
					checkedOut.remove(key);
			} else {
				System.out.println("There is no product with that ID registered");
			}
		} catch (NumberFormatException | IndexOutOfBoundsException e) {

			System.out.println("Syntax error. Correct format is: 'deregister <productID>'");
		}
	}

	public void handleInfoCommand(String[] argument) {
		try {
			if (argument.length > 1)
				throw new IndexOutOfBoundsException();
			int temp = Integer.parseInt(argument[0]);
			if (registry.containsKey(temp)) {
				if (registry.get(temp) instanceof Movie) {
					System.out.println("(Movie) " + idAndTitle.get(temp) + ": " + "Value " + registry.get(temp).value
							+ "kr, " + registry.get(temp));
				} else {
					System.out.println("(Book) " + idAndTitle.get(temp) + ": " + "Value " + registry.get(temp).value
							+ "kr, " + registry.get(temp));
				}
			} else {
				System.out.println("There is no product with that ID registered");
			}
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			System.out.println("Syntax error. Correct format is: 'info <productID>'");
		}
	}

	public void handleQuitCommand() {
		System.out.println("terminating program");
		System.exit(0);
	}

	public void save() {
		File[] fileArray = { new File("hashmap.ser"), new File("treemap1.ser"), new File("treemap2.ser") };
		for (int i = 0; i < 3; i++) {
			try (FileOutputStream fos = new FileOutputStream(fileArray[i]);
					ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				switch (i) {
				case 0:
					oos.writeObject(checkedOut);
					break;
				case 1:
					oos.writeObject(registry);
					break;
				case 2:
					oos.writeObject(idAndTitle);
					break;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void resume() {

		File[] fileArray = { new File("hashmap.ser"), new File("treemap1.ser"), new File("treemap2.ser") };

		for (int i = 0; i < 3; i++) {
			try (FileInputStream fis = new FileInputStream(fileArray[i]);
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				if (!fileArray[i].exists()) {
					fileArray[i].createNewFile();
				}
				if (fileArray[i].length() != 0) {
					switch (i) {
					case 0:
						checkedOut = (HashMap) ois.readObject();
						break;
					case 1:
						registry = (TreeMap) ois.readObject();
						break;
					case 2:
						idAndTitle = (TreeMap) ois.readObject();
						break;
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Class not found");
				c.printStackTrace();
				return;
			}
		}
		System.out.println("Successfully initialized state from files");

	}

}
