import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Library library = new Library();

		Scanner scan = new Scanner(System.in);

		while (true) {
			String input = scan.nextLine();
			Library.Command command = library.parseCommand(input);

			if (command == Library.Command.unknown) {
				library.handleUnknownCommand();
				continue;
			} else if (command == Library.Command.list) 
			{
				library.handleListCommand();
			}
			else if (command == Library.Command.checkout) 
			{
				library.handleCheckoutCommand();
			}
			else if (command == Library.Command.checkin) 
			{
				library.handleCheckinCommand();
			} 
			else if (command == Library.Command.register) 
			{
				library.handleRegisterCommand();
			} 
			else if (command == Library.Command.deregister) 
			{
				library.handleDeregisterCommand();
			} 
			else if (command == Library.Command.info) 
			{
				library.handleInfoCommand();
			}
			else if (command == Library.Command.quit) 
			{
				library.handleQuitCommand();
			}

		}

	}

}
