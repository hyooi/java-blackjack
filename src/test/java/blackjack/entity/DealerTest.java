package blackjack.entity;

import blackjack.enums.CardNumberType;
import blackjack.enums.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러는 16점 이하일 때만 카드를 받는다.")
    void test1() {
        var dealer = new Dealer(Deck.of(), List.of(
                new Card(CardType.CLOVER, CardNumberType.TWO),
                new Card(CardType.CLOVER, CardNumberType.FIVE)
        ));
        dealer.play(dealer);
        assertThat(dealer.score()).isGreaterThan(7);
    }

    @Test
    @DisplayName("딜러는 17점 이상이면 카드를 받지 않는다.")
    void test2() {
        var dealer = new Dealer(Deck.of(), List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.CLOVER, CardNumberType.QUEEN)
        ));
        dealer.play(dealer);
        assertThat(dealer.score()).isEqualTo(18);
    }

    @Test
    @DisplayName("dealer승리 시 수익")
    void test_reward1() {
        var dealer = new Dealer(Deck.of(), List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.CLOVER, CardNumberType.QUEEN)
        ));
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.CLOVER, CardNumberType.FIVE)
        ));
        player.stop();

        assertThat(dealer.getRewardWithGamer(player))
                .isEqualTo(1000.0);
    }

    @Test
    @DisplayName("dealer무승부 시 수익")
    void test_reward2() {
        var dealer = new Dealer(Deck.of(), List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.CLOVER, CardNumberType.QUEEN)
        ));
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.NINE),
                new Card(CardType.HEART, CardNumberType.NINE)
        ));
        player.stop();

        assertThat(dealer.getRewardWithGamer(player))
                .isEqualTo(0.0);
    }

    @Test
    @DisplayName("dealer패배 시 수익")
    void test_reward3() {
        var dealer = new Dealer(Deck.of(), List.of(
                new Card(CardType.CLOVER, CardNumberType.FIVE),
                new Card(CardType.CLOVER, CardNumberType.QUEEN)
        ));
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.QUEEN),
                new Card(CardType.CLOVER, CardNumberType.ACE)
        ));
        player.stop();

        assertThat(dealer.getRewardWithGamer(player))
                .isEqualTo(-500.0);
    }
}