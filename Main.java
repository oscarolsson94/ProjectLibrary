import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Library library = new Library();
		library.resume();
		
		System.out.println("Welcome!");
		
		while (true) {

			System.out.println(
					"What would you like to do next? Available commands are: list/checkout/checkin/register/deregister/info/quit");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			Library.Command command = library.parseCommand(input);
			String[] argument = library.parseArguments(input);

			if (command == Library.Command.unknown) { // klar
				library.handleUnknownCommand();
				library.save();

			} else if (command == Library.Command.list) {
				library.handleListCommand();
				library.save();
			} else if (command == Library.Command.checkout) {
				library.handleCheckoutCommand(argument);
				library.save();
			} else if (command == Library.Command.checkin) {
				library.handleCheckinCommand(argument);
				library.save();
			} else if (command == Library.Command.register) {
				library.handleRegisterCommand();
				library.save();

			} else if (command == Library.Command.deregister) {
				library.handleDeregisterCommand(argument);
				library.save();
			} else if (command == Library.Command.info) {
				library.handleInfoCommand(argument);
				library.save();
			} else if (command == Library.Command.quit) // klar
			{
				library.handleQuitCommand();
				library.save();
			}

		}
	}

}
