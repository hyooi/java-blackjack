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
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.TWO),
                new Card(CardType.CLOVER, CardNumberType.FIVE)
        ));
        dealer.play(dealer);
        assertThat(dealer.score()).isGreaterThan(7);
    }

    @Test
    @DisplayName("딜러는 17점 이상이면 카드를 받지 않는다.")
    void test2() {
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.CLOVER, CardNumberType.QUEEN)
        ));
        dealer.play(dealer);
        assertThat(dealer.score()).isEqualTo(18);
    }
}