package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ResultStatusType;
import blackjack.enums.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void test1(){
        var player = new Player("pobi");
        player.receiveCard(
                new Card(CardType.CLOVER, ScoreType.ACE),
                new Card(CardType.SPADE, ScoreType.QUEEN)
        );

        assertThat(player.cardSize())
                .isEqualTo(2);
        assertThat(player.calculateScore())
                .isEqualTo(21);
        assertThat(player.checkResult(10))
                .isEqualTo(ResultStatusType.WIN);
    }

    @Test
    void test2(){
        var player = new Player("pobi");
        player.receiveCard(
                new Card(CardType.CLOVER, ScoreType.ACE),
                new Card(CardType.SPADE, ScoreType.ACE)
        );

        assertThat(player.cardSize())
                .isEqualTo(2);
        assertThat(player.calculateScore())
                .isEqualTo(12);
        assertThat(player.checkResult(12))
                .isEqualTo(ResultStatusType.DRAW);
    }

    @Test
    void test3(){
        var player = new Player("pobi");
        player.receiveCard(
                new Card(CardType.CLOVER, ScoreType.QUEEN),
                new Card(CardType.SPADE, ScoreType.TWO)
        );

        assertThat(player.cardSize())
                .isEqualTo(2);
        assertThat(player.calculateScore())
                .isEqualTo(12);
        assertThat(player.checkResult(15))
                .isEqualTo(ResultStatusType.LOSE);
    }
}