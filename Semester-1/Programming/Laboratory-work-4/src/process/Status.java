package process;

public enum Status {
    FINISH ("finished"),
    START ("in progress"),
    PLAN ("planned");
    private final String description;
    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
