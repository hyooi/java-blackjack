package blackjack.entity;

import blackjack.enums.CardNumberType;
import blackjack.enums.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}