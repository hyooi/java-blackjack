package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void dealer_giveCard() {
        var dealer = new Dealer();
        var pobi = new Player("pobi");
        assertThat(dealer.cardSize())
                .isEqualTo(0);
        assertThat(pobi.cardSize())
                .isEqualTo(0);

        dealer.giveCard(pobi);
        assertThat(pobi.cardSize())
                .isEqualTo(1);

        dealer.giveCard(dealer);
        assertThat(dealer.cardSize())
                .isEqualTo(1);
    }

    @Test
    void dealer_giveCard2() {
        var dealer = new Dealer();
        dealer.receiveCard(
                new Card(CardType.CLOVER, ScoreType.TEN),
                new Card(CardType.HEART, ScoreType.TEN)
        );

        var pobi = new Player("pobi");
        assertThat(dealer.cardSize())
                .isEqualTo(2);
        assertThat(pobi.cardSize())
                .isEqualTo(0);

        dealer.giveCard(pobi);
        assertThat(pobi.cardSize())
                .isEqualTo(1);

        dealer.giveCard(dealer);
        assertThat(dealer.cardSize())
                .isEqualTo(2);
    }
}