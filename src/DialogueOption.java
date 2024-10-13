public class DialogueOption {
    private String response;
    private DialoguePiece nextD;

    public DialogueOption(String response, DialoguePiece next) {
        this.response = response;
        this.nextD = next;
    }


    // Getter methods
    public String getResponse() {
        return response;
    }

    public DialoguePiece getNextD() {
        return nextD;
    }
}
