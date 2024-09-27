package params;

public enum ConversationStyle {
    UNKNOWN (""),
    SCIENTIFIC ("scientific"),
    FORMAL ("formal"),
    INFORMAL ("informal"),
    JOURNALISTIC ("journalistic");
    private final String description;
    ConversationStyle (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
