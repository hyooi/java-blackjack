package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;

public class Card {
    private final CardType type;
    private final ScoreType score;

    public Card(CardType type, ScoreType score) {
        this.type = type;
        this.score = score;
    }
}
