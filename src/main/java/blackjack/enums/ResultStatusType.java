package blackjack.enums;

public enum ResultStatusType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    ResultStatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
