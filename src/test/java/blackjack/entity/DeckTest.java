package blackjack.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @Test
    @DisplayName("가질 수 있는 모든 카드 타입을 가진다.")
    void test() {
        var deck = Deck.of();
        assertThat(deck.size())
                .isEqualTo(48);
    }

    @Test
    @DisplayName("한 장씩 카드를 반환한다.")
    void test2() {
        var deck = Deck.of();
        var card1 = deck.getCard();
        var card2 = deck.getCard();

        assertThat(card1 != card2).isTrue();
        assertThat(deck.size()).isEqualTo(46);

    }

    @Test
    @DisplayName("더 이상 카드가 없으면 에러를 발생시킨다.")
    void test3() {
        var deck = Deck.of();
        for (int i=0; i<48; i++) {
            deck.getCard();
        }

        assertThatThrownBy(deck::getCard)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("더 이상 카드가 없습니다.");
    }
}