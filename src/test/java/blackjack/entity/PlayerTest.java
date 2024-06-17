package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void player() {
        var player = new Player("pobi");
        player.receiveCard(
                new Card(CardType.CLOVER, ScoreType.ACE),
                new Card(CardType.SPADE, ScoreType.QUEEN)
        );

        assertThat(player.cardSize())
                .isEqualTo(2);
    }
}