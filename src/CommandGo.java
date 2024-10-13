
public class CommandGo extends Command {
	
	
	private int direction; 
	
			
	public CommandGo(Player p, int direction) {
		super(p);
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		  Player player = getPlayer();
	        Location currentLocation = player.getLocation();
	        GameMap worldMap = player.getGameMap();

	        Location nextLocation = null;
	        if (direction == 5) {
	            nextLocation = currentLocation.getEnterLocation(); // enter
	        } else if (direction == 6) {
	            nextLocation = currentLocation.getExitLocation(); // exit
	        } else {
	            nextLocation = worldMap.getNextLocation(currentLocation, direction);
	        }

	        if (nextLocation != null) {
	            if (nextLocation.isAccessible(player)) {
	            	// if location is accessible, then player moves to that location and prints out description
	                player.setLocation(nextLocation);
	                System.out.println("You have moved to: " + nextLocation.getName());
	                System.out.println(nextLocation.getDescription());
	            } else {
	            	// checks for location and then prints out why that location is locked
	            	String locName = nextLocation.getName();
	            	switch(locName) {
		            	case "inn room":
		            		System.out.println("There is a door, but it seems to be locked.");
		            		break;
		            	default: 
		    	            	System.out.println("This location seems to be locked.");
			            		break;
	            	}
	            }
	        } else {
	            System.out.println("You can't move in that direction.");
	        }
	    }

}
