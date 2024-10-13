import java.util.*;

public class DialoguePiece {
    private String text;
    private List<DialogueOption> options;

    public DialoguePiece(String text) {
        this.text = text;
        this.options = new ArrayList<>();
    }

    // Print out options that the player can say (ORDER MATTERS)
    public void printOptions(Player player, Game game) {
        int count = 1;
        for (DialogueOption option : options) {
            System.out.println("(" + count + ") " + option.getResponse());
            count++;
            
        }
    }

    // Setter methods
    public void addOption(DialogueOption option) {
        options.add(option);
    }

    // Getter methods
    public String getText() {
        return text;
    }

    public List<DialogueOption> getOptions(Player player, Game game) {
        List<DialogueOption> availableOptions = new ArrayList<>();
        for (DialogueOption option : options) {
            availableOptions.add(option);
        }
        return availableOptions;
    }
}
