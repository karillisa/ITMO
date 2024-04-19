package params;

public enum Quality {
    UNKNOWN (""),
    SMARTEST ("the smartest"),
    KINDEST ("the kindest"),
    HONEST ("most honest"),
    BEAUTIFUL ("most beautiful"),
    RICHEST ("the richest");
    private final String description;
    Quality (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
