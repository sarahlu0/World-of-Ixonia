import java.util.*;

public class Player {
	private String name;
	private Location currentLocation = null;
	private ArrayList<Item> inventory;
	private Map<String, Integer> fishInventory;
	private GameMap gameMap;
	
	public Player(String name) {
		this.name = name;
		this.inventory = new ArrayList<Item>();
		this.fishInventory = new HashMap<>();
	}
	
	
	
	// checks if player has an item in their inventory with at least n amount 
	public boolean hasItem(String itemName, int n) {
		int count = 0;
		for (Item item : inventory) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				count++;
			}
		}
		// returns true if there is enough of the item
        return count >= n;
	}
	
	// checks if player has an item (only 1 needed)
	public boolean hasItem(String itemName) {
		for (Item item : inventory) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	// prints out the inventory with amounts 
	public void printInventory() {
		
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

		// creates a new list with all the item names 
		// (INV HAS STRING NAMES, INVENTORY HAS ITEMS)
		ArrayList<String> inv = new ArrayList<String>();
		for (Item item : inventory) {
			inv.add(item.getName());
		}
		
		// sorts inventory in alphabetical order
		Collections.sort(inv);
		
		// initializes count to 1 since we are starting from the first item 
		int count = 1;
		// initializes first item to start 
		String current = inv.get(0);
		
		for (int i =1; i<inventory.size(); i++) {
			// if current item equals previous item, increment count
			if (current.equals(inv.get(i))) {
				count++;
			} else {
				// otherwise print out the current item and its count
				System.out.println(current + " x" + count);
				current = inv.get(i); // move to the next item
				count = 1; // reset counter 
			}
		}
		// prints out last item
		System.out.println(current + " x" + count);
	}
	

	public void addFish(String location) {
		// adds 1 to the current fish count of that location
		// if there is no key, then add key with default count of 0 + 1 = 1 fish
		fishInventory.put(location, fishInventory.getOrDefault(location, 0) + 1);
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void removeItem(String itemName) {
		if (hasItem(itemName)) {
			for (int i =0 ; i<inventory.size(); i++) {
				if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
					inventory.remove(i);
					break;
				}
			}

		} else {
			return;
		}
	}
	
	// setter methods
	public void setLocation(Location newLocation) {
		this.currentLocation = newLocation;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setGameMap(GameMap g) {
		gameMap = g;
	}
	
	// getter methods 
	public String getName() {
		return name;
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}

	public Location getLocation() {
		return currentLocation;
	}
	
	public int getFishCount(String location) {
		// returns the count of fish in the location; if there is no key then default is 0 
		return fishInventory.getOrDefault(location, 0);
	}
	
	public GameMap getGameMap() {
		return gameMap;
	}
	
	public Item getItem(String name) {
		for (Item item: inventory) {
			if (item.getName().equals(name)){
				return item;
			}
		}
		return null;
	}
		
}
