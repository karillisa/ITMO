package params;

public enum ConversationStatus {
    UNKNOWN (""),
    IN_PROGRESS ("was in progress"),
    START ("just started"),
    FINISH ("just finished");
    private final String description;
    ConversationStatus (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
