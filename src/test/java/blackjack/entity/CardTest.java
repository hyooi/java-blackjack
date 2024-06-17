package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void card() {
        var card = new Card(CardType.CLOVER, ScoreType.EIGHT);
    }

    @Test
    void card2() {
        var card = new Card(CardType.SPADE, ScoreType.ACE);
    }
}