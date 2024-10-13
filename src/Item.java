public class Item {
    private String name;
    private String description;
    private boolean usable; // whether or not item can be used

    // constructor number 1 
    public Item(String n, String d, boolean u) {
        name = n;
        description = d;
        usable = u;
    }

    // constructor number 2, sets usable to false by default
    public Item(String n, String d) {
        name = n;
        description = d;
        usable = false;
    }

    // getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsable() {
        return usable;
    }

    // method to determine what happens when the item is used
    public void use(Player player) {
        // if the item is not usable, print a message and return
        if (!usable) {
            System.out.println("You can't use this item.");
            return;
        }

        // switch statement to handle different items based on their name
        switch(name.toLowerCase()) {
            case "bird":
                // if the item is a bird, add a "bird feather" to the player's inventory and set the item as no longer usable
                player.addItem(new Item("Bird Feather", "A brown feather with black stripes from a bird."));
                usable = false;
                System.out.println("You touch the bird a little too harshly and a feather falls out. The birds seems relatively unharmed but now cowers from your touch.");
                break;
            case "inn room key":
                // if the item is an inn room key, check the player's current location
                Location currentLocation = player.getLocation();
                if (currentLocation.getName().equalsIgnoreCase("inn hallway")) {
                    // if the player is in the inn hallway, unlock the inn room
                    Location innRoom = currentLocation.getEnterLocation();
                    if (innRoom != null && !innRoom.isAccessible(player)) {
                        innRoom.setAccessible(true);
                        System.out.println("You have unlocked the inn room.");
                    } else {
                        System.out.println("The inn room is already unlocked.");
                    }
                } else {
                    System.out.println("You can't use that here.");
                }
                break;
                
            default:
                System.out.println("Nothing happens.");
                break;
        }
    }
}
