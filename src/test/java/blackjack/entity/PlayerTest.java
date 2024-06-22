package blackjack.entity;

import blackjack.entity.status.BlackJack;
import blackjack.entity.status.Bust;
import blackjack.entity.status.Stand;
import blackjack.enums.CardNumberType;
import blackjack.enums.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 베팅 금액, 상태, 카드 리스트를 가진다.")
    void test() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        assertThat(player)
                .hasFieldOrPropertyWithValue("name", "pobi")
                .hasFieldOrPropertyWithValue("money", 1000);
    }

    @Test
    @DisplayName("두 장의 카드가 아닐 시 에러를 발생시킨다.")
    void test_error() {
        assertThatThrownBy(() -> {
            var player = new Player("pobi", 1000, List.of());
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("처음에는 반드시 두 장의 카드를 가져야 합니다.");
    }

    @Test
    @DisplayName("베팅 금액이 0이하일 시 에러를 발생시킨다.")
    void test_error2() {
        assertThatThrownBy(() -> {
            var player = new Player("pobi", 0, List.of(
                    new Card(CardType.CLOVER, CardNumberType.EIGHT),
                    new Card(CardType.CLOVER, CardNumberType.ACE)
            ));
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 0 이상이어야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("cardsAndScore")
    void score(List<Card> cards, int score) {
        var player = new Player("foo", 1000, cards);
        assertThat(player.score()).isEqualTo(score);
    }

    private static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(CardType.CLOVER, CardNumberType.EIGHT),
                        new Card(CardType.CLOVER, CardNumberType.TWO)
                ), 10),
                Arguments.of(List.of(
                        new Card(CardType.CLOVER, CardNumberType.TWO),
                        new Card(CardType.CLOVER, CardNumberType.FIVE)
                ), 7),
                Arguments.of(List.of(
                        new Card(CardType.CLOVER, CardNumberType.JACK),
                        new Card(CardType.CLOVER, CardNumberType.QUEEN)
                ), 20),
                Arguments.of(List.of(
                        new Card(CardType.CLOVER, CardNumberType.JACK),
                        new Card(CardType.CLOVER, CardNumberType.ACE)
                ), 21),
                Arguments.of(List.of(
                        new Card(CardType.CLOVER, CardNumberType.ACE),
                        new Card(CardType.HEART, CardNumberType.ACE)
                ), 12)
        );
    }

    @Test
    @DisplayName("플레이어 블랙잭 승리")
    void play1() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.TWO),
                new Card(CardType.DIAMOND, CardNumberType.TWO)
        ));
        var status = player.play(new Card(CardType.CLOVER, CardNumberType.FIVE));
        assertThat(status).isInstanceOf(BlackJack.class);
        assertThat(player.getReward(dealer)).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭 무승부")
    void play2() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.ACE),
                new Card(CardType.DIAMOND, CardNumberType.JACK)
        ));
        var status = player.play(new Card(CardType.CLOVER, CardNumberType.FIVE));
        assertThat(status).isInstanceOf(BlackJack.class);
        assertThat(player.getReward(dealer)).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어 bust패배")
    void play3() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.QUEEN),
                new Card(CardType.DIAMOND, CardNumberType.JACK)
        ));
        var status = player.play(new Card(CardType.CLOVER, CardNumberType.NINE));
        assertThat(status).isInstanceOf(Bust.class);
        assertThat(player.getReward(dealer)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어 stand승리")
    void play4() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.TWO),
                new Card(CardType.DIAMOND, CardNumberType.JACK)
        ));
        var status = player.stop();
        assertThat(status).isInstanceOf(Stand.class);
        assertThat(player.getReward(dealer)).isEqualTo(2000);
    }

    @Test
    @DisplayName("플레이어 stand무승부")
    void play5() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.FIVE),
                new Card(CardType.DIAMOND, CardNumberType.ACE)
        ));
        var status = player.stop();
        assertThat(status).isInstanceOf(Stand.class);
        assertThat(player.getReward(dealer)).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어 stand패배")
    void play6() {
        var player = new Player("pobi", 1000, List.of(
                new Card(CardType.CLOVER, CardNumberType.EIGHT),
                new Card(CardType.DIAMOND, CardNumberType.EIGHT)
        ));
        var dealer = new Dealer(List.of(
                new Card(CardType.CLOVER, CardNumberType.KING),
                new Card(CardType.DIAMOND, CardNumberType.ACE)
        ));
        var status = player.stop();
        assertThat(status).isInstanceOf(Stand.class);
        assertThat(player.getReward(dealer)).isEqualTo(0);
    }
}