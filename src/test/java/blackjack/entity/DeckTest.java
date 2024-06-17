package blackjack.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("덱은 52개의 카드를 가진다.")
    void testDeck() {
        var deck = Deck.of();
        assertThat(deck.size())
                .isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 가져오면 해당 카드는 덱에서 삭제한다.")
    void testDeck2() {
        var deck = Deck.of();
        assertThat(deck.size())
                .isEqualTo(52);

        var card = deck.getFirstCard();
        assertThat(deck.size())
                .isEqualTo(51);
    }
}