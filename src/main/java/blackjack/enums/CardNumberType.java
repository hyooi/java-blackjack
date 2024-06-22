package blackjack.enums;

public enum CardNumberType {
    ACE("A", 1), // 11점으로도 계산한다.
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    private final String name;
    private final int score;

    CardNumberType(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

}
