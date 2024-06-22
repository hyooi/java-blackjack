package blackjack.enums;

public enum CardNumberType {
    ACE(1), // 11점으로도 계산한다.
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    KING(10),
    QUEEN(10),
    JACK(10);

    private final int score;

    CardNumberType(int score) {
        this.score = score;
    }
}
