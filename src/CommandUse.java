
public class CommandUse extends Command {
    private String itemName;

    public CommandUse(Player p, String itemName) {
        super(p);
        this.itemName = itemName;
    }

    
    public void execute() {
        Player player = getPlayer();
        boolean hasItem = false;

        // Search for item in player's inventory
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                hasItem = true;
                item.use(player);
                break;
            }
        }

        // If player does not have the item
        if (itemName.trim().equals("")) {
        	System.out.println("Are you trying to use nothing, expecting something to happen?");
        } else if (!hasItem) {
            System.out.println("You do not have an item named " + itemName + ".");
        }
    }
}
