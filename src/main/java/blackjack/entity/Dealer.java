package blackjack.entity;

import java.util.List;
import java.util.Optional;

public class Dealer {
    private final Player player;
    private final Deck deck;

    public Dealer() {
        this(null);
    }

    public Dealer(List<Card> cards) {
        this.deck = Deck.of();
        this.player = new Player("딜러", Integer.MAX_VALUE,
                Optional.ofNullable(cards)
                .orElseGet(() -> List.of(deck.getCard(), deck.getCard())));
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public int score() {
        return player.score();
    }

    public void play(Player player) {
        if (this.player == player && player.score() >= 17) {
            return;
        }

        player.play(deck.getCard());
    }

    public boolean isBust() {
        return player.isBust();
    }
}
