package blackjack.enums;

public enum StatusType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    StatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
