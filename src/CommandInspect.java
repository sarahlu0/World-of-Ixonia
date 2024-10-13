import java.util.ArrayList;

public class CommandInspect extends Command {
	private String itemName;
	
	public CommandInspect(Player p, String item) {
		super(p);
		this.itemName = item;
	}
	
	public void execute() {
		Player p = getPlayer();
		ArrayList<Item> inv = p.getInventory();
		boolean found = false;
		
		// searches for item in player's inventory
		for (Item item : inv) {
			if (item.getName().toLowerCase().equals(itemName)){
				found = true;
                // Print item description
				System.out.println("Item: " + item.getName());
                System.out.println("Description: " + item.getDescription());
                break;
			}
		}
		
		// if player does not have the item, search for an NPC in the current location
		if (!found) {
			Location currentLocation = p.getLocation();
			for (NPC npc : currentLocation.getNPCs()) {
				if (npc.getName().toLowerCase().equals(itemName)) {
					found = true;
					// print npc direction
					System.out.println("NPC: " + npc.getName());
					System.out.println("Description: " + npc.getDescription());
					break;
				}
			}
		}

        // If neither item or npc was found
		if (!found) {
			if (itemName.trim().equals("")) {
	            System.out.println("What are you inspecting? Your empty hand?");
			} else {
            System.out.println("You do not have an item named " + itemName + ".");
			}
        }
		
	}

}
