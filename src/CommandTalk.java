import java.util.List;

public class CommandTalk extends Command {
    private NPC npc = null;
    private String npcName;
    private DialogueSystem dialogueSystem;
    private Game game;

    public CommandTalk(Player p, String arguments, DialogueSystem dialogueSystem, Game game) {
        super(p);
        npcName = arguments.trim().toLowerCase();
        this.dialogueSystem = dialogueSystem;
        this.game = game;
    }

    public void execute() {
        Location currentLocation = getPlayer().getLocation();
        List<NPC> npcs = currentLocation.getNPCs();

        if (npcs.size() == 1 && npcName.isEmpty()) {
            // Only one NPC in the location and no name provided
            npc = npcs.get(0);
        } else {
            // Find NPC by name
            for (NPC npc : npcs) {
                if (npc.getName().toLowerCase().equals(npcName)) {
                    this.npc = npc;
                    break;
                }
            }
        }

        if (npc == null) {
            if (npcName.isEmpty() && npcs.size() > 1) {
                System.out.println("There are multiple people here. Specify who you want to talk to.");
            } else {
                System.out.println("There's no one by that name here.");
            }
            return; // Exit the method
        }

        // Start dialogue
        dialogueSystem.startDialogue(npc, getPlayer(), game);
    }
}
