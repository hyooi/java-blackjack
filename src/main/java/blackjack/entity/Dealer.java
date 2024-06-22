package blackjack.entity;

import java.util.List;

public class Dealer extends Gamer {
    private final Deck deck = Deck.of();

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    public void play(Gamer gamer) {
        if (gamer instanceof Dealer && score() >= 17) {
            return;
        }

        gamer.play(deck.getCard());
    }
}
