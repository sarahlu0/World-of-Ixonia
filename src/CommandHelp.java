
public class CommandHelp extends Command {
	
	private String[] helpList = {"Forget where you are? Try taking a look.",
//			"Sometimes, things might be hidden from plain view. Try taking another look.",
//			"Use 'status' to check yourself.", 
//			"Stealing is OK! (In open world action RPGs)",
			"Use 'inventory' to see your stuff.",
			"Your bag is enchanted to never run out of space! How convenient.",
//			"Use 'help' to see more tips!",
			"Some items can be used!",
			"Not all items can be used in an obvious way! Maybe they can serve as gifts, too?",
			"You take a closer look at items by using 'inspect.'",
			"If you want to enter or exit a building, try 'enter' or 'exit.'",
			"You can use 'inspect' on people, as well.",
			"Some characters might want a specific item, and they'll give you something else in exchange."};
	
	
	public CommandHelp(Player p) {
		super(p);
	}
	
	public void execute() {
		// selects and prints out a random tip from the helpList 
		int randInt = (int) (Math.random()*helpList.length);
		System.out.println(helpList[randInt]);
	}

}
