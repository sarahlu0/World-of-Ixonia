import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CommandFish extends Command {

	public CommandFish(Player p) {
		super(p);
	}
	
	// prints ASCII water with bubbles
	// returns the number of bubbles generated
	public int generateBubbles() {

		// define pond dimensions
		int width = 30;
		int height = 10;
		
		// create pond array
		char[][]pond = new char[height][width];
		
		// fills array with water '~'
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pond[i][j] = '~';
			}
		}
		
		// generates bubbles between 5 and 10 
		int bubbles = (int)(Math.random()*6 + 5);
		
		// randomly replaces water with bubbles '0'
		for (int i = 0; i < bubbles; i++) {
			int x = (int)(Math.random() * height);
			int y = (int)(Math.random() * width);
			
			// checks if there is a bubble there
			if (pond[x][y] == '~') {
				pond[x][y] = '0';
			} else { 
				// subtract number of total bubbles
				bubbles--;
			}
			
		}
		
		// prints pond
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(pond[i][j]);
            }
            System.out.println();
        }
        
		
		// returns number of bubbles 
		return bubbles;
	}
	
	// causes program to sleep for set time without interruption
	public void sleep(int time) {
		long startTime = System.currentTimeMillis();
		long remainingTime = time;
		
		// keep looping until time is 0 
		while (remainingTime > 0) {
			try {
				Thread.sleep(remainingTime);
				break; // when sleep is done
			} catch (InterruptedException e) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				remainingTime = time - elapsedTime; // find how much time is remaining 
			}
		}
	}
	
	// keeps track of fish 
	public void handleFishCatch() {
		Player p = getPlayer();
		
		String locationName = p.getLocation().getName();
		int fishCount = p.getFishCount(locationName);
		
		switch (locationName) {
			case "beach":
				if (fishCount==0) {
					p.addFish(locationName);
					p.addItem(new Item("Flounder Fish", "A flat, disc-shaped fish that is white on the right side and "
							+ "gray on the left side."));
					System.out.println("Success! You caught a flounder fish!");
				} else if (fishCount == 1) {
					p.addFish(locationName);
					p.addItem(new Item("Leather Boots", "A pair of well-worn leather boots. "
							+ "It is thoroughly soaked but you can tell that it was originally made of fine quality. "
							+ "The leather seems to have been dyed a dark purple-ish color. Perhaps it is still worth something."));
					System.out.println("You fished up a pair of... leather boots?");
				} else if (fishCount == 2) {
					p.addFish(locationName);
					p.addItem(new Item("Message in a Bottle", "A small, clear bottle with a piece of paper inside of it. It seems relatively intact, if a little old.", true));
					System.out.println("A clear, shining object flies out of the water. It seems to be a small bottle with a piece of paper inside of it.");
				} else if (fishCount == 3) {
					p.addFish(locationName);
					System.out.println("The water appears to be quiet.");
				} else if (fishCount == 4) {
					p.addFish(locationName);
					System.out.println("You sit and wait, but nothing appears.");
				} else if (fishCount == 5) {
					p.addFish(locationName);
					System.out.println("Why are you still here? Can't you see there's nothing in the water?");
				} else if (fishCount == 6) {
					p.addFish(locationName);
					System.out.println("You feel a sharp tug on your fishing rod.");
					sleep(2000);
					System.out.println("A white figure splashes out of the water and onto the sandy shore of the beach beside you.");
					sleep(2000);
					System.out.println("You hear a high pitched squeal and coughing, before the figure turns to you.");
					sleep(2000);
					System.out.println("PAIMON: \"Thank you for saving me! If you hadn't fished me up, I would have drowned!\"");
					sleep(2000);
					System.out.println("What an interesting creature, you think to yourself, before she disappears into a mist of sparkling particles.");
				} else if (fishCount > 6 && fishCount < 10) {
					p.addFish(locationName);
					System.out.println("Okay, for real now, there's no more stuff in the ocean. Stop before you destroy the ecosystem.");
				} else {
					System.out.println("The water appears to be quiet.");
				}
			case "longshan pond":
				// to be added 
		}
		
	}
	
	// game to fish
	public void fishingGame(){
		System.out.println("You start to fish...");
		
		// generates random seconds between 3 and 10 (time in milliseconds)
		int randint = (int)(Math.random() * 6 + 3) * 1000;
		
		// waits that number of seconds
		sleep(randint);
		
		// start fishing game
		System.out.println("You feel a tug on your bobber!");
		sleep(750);
		System.out.println("Quick, count the number of bubbles!");
		int bubbles = generateBubbles();
		
		Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            System.out.print("Enter the number of bubbles: ");
            return scanner.nextInt();
        });

        try {
            Integer userBubbles = future.get(10, TimeUnit.SECONDS);
            if (userBubbles == bubbles) {
               	handleFishCatch();
            } else {
                System.out.println("Incorrect. The fish got away!");
            }
        } catch (TimeoutException e) {
            System.out.println("You took too long! The fish swam away.");
            future.cancel(true);
        } catch (ExecutionException e) {
        	if (e.getCause() instanceof InputMismatchException) {
            	System.out.println("Please enter a whole number.");
        	} else {
                System.out.println("Please do not do anything while waiting for a fish to come.");
        	}
        } catch (Exception e) {
//        	System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Please do not do anything while waiting for a fish to come.");
        } finally {
            executor.shutdown();
        }
	
		
	}
	
	
	public void execute() {
		String locationName = getPlayer().getLocation().getName();
		int fishCount = getPlayer().getFishCount(locationName);
		
		// only does fishing game when there are fish to be fished
		switch (locationName) {
			case "beach":
				if (fishCount < 3) {
					fishingGame();
					break;
				} else {
					handleFishCatch();
					break;
				}
			  default:
		            System.out.println("You can't fish here.");
		            break;
		}

		
	}

}
