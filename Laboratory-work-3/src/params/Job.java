package params;

public enum Job {
    UNKNOWN (""),
    MANUFACTURE ("manufacture"),
    OFFICE ("office");
    private final String description;
    Job (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
