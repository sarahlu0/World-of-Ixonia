
public class CommandLook extends Command {

	public CommandLook(Player p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		System.out.println(getPlayer().getLocation().getDescription());
	}

}
