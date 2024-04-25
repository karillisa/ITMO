package params;

public enum Size {
    UNKNOWN (""),
    HUGE ("huge"),
    SMALL ("small");
    private final String description;
    Size (String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
