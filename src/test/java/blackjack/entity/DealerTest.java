package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    void dealer() {
        var dealer = new Dealer();
        assertThat(dealer.deckSize())
                .isEqualTo(Deck.of().size());
    }

    @Test
    void dealer2() {
        var dealer = new Dealer();
        var pobi = new Player("pobi");

        dealer.giveCard(pobi);
    }

    @Test
    void dealer3() {
        var dealer = new Dealer();
        dealer.receiveCard(
                new Card(CardType.CLOVER, ScoreType.ACE),
                new Card(CardType.SPADE, ScoreType.QUEEN)
        );

        assertThat(dealer.cardSize())
                .isEqualTo(2);
    }
}