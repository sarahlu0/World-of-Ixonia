
public class NPC {
	private String name;
	private Location currentLocation;
	private DialoguePiece initialD; // initial dialogue option 
	private String description;
	public NPC(String n, String d, DialoguePiece initial) {
		name = n;
		description= d;
		initialD = initial;
	}
	
	// setter methods
	public void setDescription(String d) {
		description = d;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setLocation(Location l) {
		currentLocation = l;
	}
	
	// getter methods 
	public String getName() {
		return name;
	}
	
	public DialoguePiece getInitialD() {
		return initialD;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Location getLocation() {
		return currentLocation;
	}
	
}
