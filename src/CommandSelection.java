import java.util.*;

public class CommandSelection {
	
	// create map of all command words (basically a dictionary)
	private static final Map<String, String> commandMap = new HashMap<>();

	 
	static {
		commandMap.put("move", "go");
        commandMap.put("move to", "go");
        commandMap.put("go", "go");
        commandMap.put("go to", "go");
        commandMap.put("travel", "go");
        commandMap.put("travel to", "go");
        commandMap.put("walk", "go");
        commandMap.put("walk to", "go");
        commandMap.put("advance", "go");
        commandMap.put("advance to", "go");
        commandMap.put("proceed", "go");
        commandMap.put("proceed to", "go");
        commandMap.put("journey", "go");
        commandMap.put("journey to", "go");

        commandMap.put("enter", "enter");
        commandMap.put("exit", "exit");

        commandMap.put("look", "look");
        commandMap.put("observe", "look");
        commandMap.put("see", "look");
        commandMap.put("watch", "look");
        commandMap.put("view", "look");
        commandMap.put("look around", "look");
        commandMap.put("take a look", "look");
        commandMap.put("take a look around", "look");
        

        commandMap.put("inspect", "inspect");
        commandMap.put("examine", "inspect");


//        commandMap.put("self", "status");
//        commandMap.put("check", "status");
//        commandMap.put("status", "status");
//        commandMap.put("health", "status");
//        commandMap.put("inspect self", "status");
//        commandMap.put("check self", "status");
//        commandMap.put("examine self", "status");
//        commandMap.put("check status", "status");
//        commandMap.put("get status", "status");

        commandMap.put("get", "get");
        commandMap.put("take", "get");
        commandMap.put("obtain", "get");
        commandMap.put("acquire", "get");
        commandMap.put("grab", "get");
        commandMap.put("pick up", "get");
        

        commandMap.put("use", "use");

        commandMap.put("talk", "talk");
        commandMap.put("speak", "talk");
        commandMap.put("converse", "talk");
        commandMap.put("talk to", "talk");
        commandMap.put("speak to", "talk");
        commandMap.put("chat to", "talk");
        commandMap.put("chat with", "talk");
        commandMap.put("chat", "talk");
        commandMap.put("communicate", "talk");
        commandMap.put("communicate with", "talk");
        commandMap.put("dialogue", "talk");
        commandMap.put("converse with", "talk");

        commandMap.put("inventory", "inventory");
        commandMap.put("open inventory", "inventory");

        commandMap.put("help", "help");
        commandMap.put("info", "help");
//
//        commandMap.put("give", "give");
//        commandMap.put("gift", "give");

        commandMap.put("fish", "fish");
        commandMap.put("fishing", "fish");
        
        // cheat code

        commandMap.put("explode everything", "explode");
        
        
	}
	
	// changes input to direction for go command
	private static int getDirection(String arguments) {
        switch (arguments) {
            case "north": case "up": return 1;
            case "east": case "right": return 2;
            case "south": case "down": return 3;
            case "west": case "left": return 4;
            default: return -1;
        }
    }
	
	public static Command getCommand(String input, Player p, DialogueSystem dialogueSystem, Game game) {
		
		input = input.trim().toLowerCase(); // all input is lowercase
		String commandName = null;
		String arguments = "";
		
		// iterates through each key in the commandMap
		for (String key : commandMap.keySet()) {
			// if the input starts with key and there is no assigned command yet or the key length is greater than the current command
			// (checks if there are keys that match better) 
			  if (input.startsWith(key) && (commandName == null || key.length() > commandName.length())) { 
				commandName = commandMap.get(key); // get the matching value 
				arguments = input.substring(key.length()).trim(); // sets the arguments to the rest of the input
			}
		}
		
		
		if (commandName == null) {
			return new CommandUnknown(p);
		}
		
		switch (commandName) {
		// go command
        case "go":
       
            int direction = getDirection(arguments);
            if (direction == -1) {
            	return new CommandPromptDirection(p);
            }
            return new CommandGo(p, direction);
        case "enter":
            return new CommandGo(p, 5);
        case "exit":
            return new CommandGo(p, 6);
    		
        case "look":
            return new CommandLook(p);
        case "fish":
            return new CommandFish(p);
//        case "status":
//            return new CommandStatus(p);
        case "get":
            return new CommandGet(p, arguments);
//        case "give":
//            return new CommandGive(p, arguments);
        case "inventory":
            return new CommandInventory(p);
        case "help":
            return new CommandHelp(p);
        case "talk":
            return new CommandTalk(p, arguments, dialogueSystem, game);
        case "inspect":
            return new CommandInspect(p, arguments);
        case "use":
            return new CommandUse(p, arguments);
        case "explode":
        	return new CommandCheat(p);
        default:
            return new CommandUnknown(p);
    }
		
		
	}
}
