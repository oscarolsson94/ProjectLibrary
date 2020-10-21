public class Library{
	
enum Command{
	
	list,
	checkout,
	checkin,
	register,
	deregister,
	info,
	quit,
	unknown
	
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
	
	
	
	
}
