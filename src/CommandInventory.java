
public class CommandInventory extends Command {

	public CommandInventory(Player p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		getPlayer().printInventory();
	}

}
