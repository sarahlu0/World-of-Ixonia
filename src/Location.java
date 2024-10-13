import java.util.ArrayList;

public class Location {
	
    private String name;
    private String description;

    private int x;
    private int y;

    private Location enterLocation; // location if player 'enter'
    private Location exitLocation; // location if player 'exit'

    private boolean isAccessible; // whether or not the location can be moved to
    private String requiredItem; // needed only if the location needs an item to be accessed 

    private ArrayList<Item> items; // list of all items in the location
    private ArrayList<NPC> npcs; // list of all npcs in the location

    public Location(String name, String description, int x, int y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.isAccessible = true;
        this.items = new ArrayList<>(); 
        this.npcs = new ArrayList<>();
    }

    // getters and setters for coordinates, name, and description
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // setter and getter for enter location
    public void setEnterLocation(Location enterLocation) {
        this.enterLocation = enterLocation;
    }

    public Location getEnterLocation() {
        return enterLocation;
    }

    // setter and getter for exit location
    public void setExitLocation(Location exitLocation) {
        this.exitLocation = exitLocation;
    }

    public Location getExitLocation() {
        return exitLocation;
    }

    // method to check if location is accessible for the player
    public boolean isAccessible(Player player) {
        if (requiredItem == null) {
            return isAccessible;
        }
        return isAccessible && player.hasItem(requiredItem);
    }

    // setter for accessibility
    public void setAccessible(boolean isAccessible) {
        this.isAccessible = isAccessible;
    }

    // getter for items list
    public ArrayList<Item> getItems() {
        return items;
    }

    // add an item to the location
    public void addItem(Item item) {
        items.add(item);
    }

    // method to remove an item from the location by name
    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    // setter for required item
    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    // getter for npcs list
    public ArrayList<NPC> getNPCs(){
        return npcs;
    }

    // method to add an npc to the location
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    // method to remove an npc from the location by name
    public void removeNPC(String npcName) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                npcs.remove(npc);
                break;
            }
        }
    }

    // method to get an npc by name
    public NPC getNPCByName(String npcName) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                return npc;
            }
        }
        return null; // return null if no npc with the given name is found
    }
}
