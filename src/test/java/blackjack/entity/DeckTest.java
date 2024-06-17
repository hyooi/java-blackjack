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
}