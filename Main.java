import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Library library = new Library();

		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("Welcome! Would you like to list/checkout/checkin/register/deregister/info/quit");
			
			
			String input = scan.nextLine();
			
			Library.Command command = library.parseCommand(input);
			String[] argument = library.parseArguments(input);

			if (command == Library.Command.unknown) { //klar
				library.handleUnknownCommand();
				
			}
			else if (command == Library.Command.list) 
			{
				library.handleListCommand();
			}
			else if (command == Library.Command.checkout) 
			{
				library.handleCheckoutCommand(argument);
			}
			else if (command == Library.Command.checkin) 
			{
				library.handleCheckinCommand(argument);
			} 
			else if (command == Library.Command.register)
			{
				library.handleRegisterCommand();
				
			} 
			else if (command == Library.Command.deregister) 
			{
				library.handleDeregisterCommand(argument);
			} 
			else if (command == Library.Command.info) 
			{
				library.handleInfoCommand(argument);
			}
			else if (command == Library.Command.quit) //klar
			{
				library.handleQuitCommand();
			}

		}

	}

}
