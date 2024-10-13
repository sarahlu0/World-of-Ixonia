
public class GameMap {
	private Location[][] map;
	
	public GameMap(int r, int c) {
		map = new Location[r][c];
	}
	
	public void setLocation(Location l, int r, int c) {
		map[r][c]= l;
	}
	
	public Location getLocation(int r, int c) {
		return map[r][c];
	}
	
	// returns true if there is a location, false if null 
	public boolean hasLocation(int r, int c) {
		return (map[r][c] == null)? false: true;
	}
	
	public Location getNextLocation(Location currentLocation, int direction) {
        int x = currentLocation.getX();
        int y = currentLocation.getY();

        switch (direction) {
        // btw x is row and y is column thats why x and y seem "switched" and im too lazy to change it to be consistent 
        // so basically x = r and y = c
            case 1: return getLocation(x-1, y); // North
            case 2: return getLocation(x, y+1); // East
            case 3: return getLocation(x+1, y ); // South
            case 4: return getLocation(x , y-1); // West
            default: return null;
        }
    }
	
	
}
