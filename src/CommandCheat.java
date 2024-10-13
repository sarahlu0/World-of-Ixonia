
public class CommandCheat extends Command {

	public CommandCheat(Player p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		getPlayer().addItem(new Item("C4", "If A is for Apple and B is for Banana, then what is C for?"));
	}

}
