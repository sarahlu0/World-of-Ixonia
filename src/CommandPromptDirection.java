
public class CommandPromptDirection extends Command {

    public CommandPromptDirection(Player p) {
        super(p);
    }

    public void execute() {
        System.out.println("You need to specify a direction to go. Try 'go north', 'go east', 'go south', or 'go west'.");
    }
}