

public class CommandGet extends Command {
    private String itemName;

    public CommandGet(Player p, String itemName) {
        super(p);
        this.itemName = itemName;
    }

    
    public void execute() {
        Player player = getPlayer();
        Location currentLocation = player.getLocation();
        Item item = currentLocation.removeItem(itemName);

        if (item != null) {
            player.addItem(item);
            System.out.println("You have picked up: " + item.getName());
        } else if (itemName.trim().equals("")){
        	System.out.println("What are you trying to get? The air?");
        } else {
        	System.out.println("There is no such item here.");
        }
    }
}