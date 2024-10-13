import java.util.*;

public class DialogueSystem {
    private Scanner scanner;

    public DialogueSystem() {
        this.scanner = new Scanner(System.in);
    }

    // Gets the player's choice (a number between 1 and the number of options)
    private int getPlayerChoice(int numOptions) {
        int choice = 0;
        while (choice < 1 || choice > numOptions) {
            System.out.print("Enter your choice (1-" + numOptions + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > numOptions) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + numOptions);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and " + numOptions);
            }
        }
        return choice;
    }

    // Main method
    public void startDialogue(NPC npc, Player player, Game game) {
        DialoguePiece currentD = npc.getInitialD(); // First dialogue

        if (currentD == null) {
            System.out.println("They do not seem like they want to talk.");
            return;
        }

        while (currentD != null) {
            // Print the NPC name and first dialogue
            System.out.println(npc.getName() + ": " + currentD.getText());
            // Get a list of available options
            List<DialogueOption> options = currentD.getOptions(player, game);

            if (options.isEmpty()) {
                break;
            }

            // Prints out all options with numbers starting from one (ORDER MATTERS)
            currentD.printOptions(player, game);

            int choice = getPlayerChoice(options.size());
            
            DialogueOption selectedOption = options.get(choice - 1);

            
            
         // Handle transactions here (case by case basis) - maybe change later to be more efficient
            boolean transactionSuccessful = false;
            switch (selectedOption.getResponse()) {
                case "Okay, I will give you a bird feather for 1 copper.":
                    if (player.hasItem("bird feather")) {
                        player.addItem(new Item("Copper", "A round, bronze coin."));
                        player.removeItem("bird feather");
                        transactionSuccessful = true;
                    } else {
                        System.out.println("You do not have that!");
                    }
                    break;
                case "Okay, I will give you these leather boots for 2 copper.":
                    if (player.hasItem("leather boots")) {
                        player.addItem(new Item("Copper", "A round, bronze coin."));
                        player.addItem(new Item("Copper", "A round, bronze coin."));
                        player.removeItem("leather boots");
                        transactionSuccessful = true;
                    } else {
                        System.out.println("You do not have that!");
                    }
                    break;
                 
                case "Sure, I'll purchase the sapphire pendant.":
                	
                	if (player.hasItem("copper", 2)) {
                        player.addItem(new Item("Sapphire Pendant", "A stunning sapphire pendant featuring a deep blue, oval-cut sapphire encased in a delicate halo of sparkling diamonds, suspended elegantly from a slender white gold chain, exuding timeless elegance and sophistication."));
                        player.removeItem("copper");
                        player.removeItem("copper");
                        transactionSuccessful = true;
                    } else {
                        System.out.println("You do not have that!");
                    }
                    break;
                    
                    
                    
                    
                default:
                    transactionSuccessful = true;
                    break;
                    
            }

            // Only move to the next dialogue piece if the transaction was successful
            if (transactionSuccessful) {
                currentD = selectedOption.getNextD();
            }

            
            
            
        }
    }
}
