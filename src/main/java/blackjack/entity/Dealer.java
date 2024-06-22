package blackjack.entity;

import java.util.List;

public class Dealer extends Gamer {
    private final Deck deck;

    public Dealer(Deck deck, List<Card> cards) {
        super("딜러", cards);
        this.deck = deck;
    }

    public static Dealer of() {
        var deck = Deck.of();
        return new Dealer(deck, List.of(deck.getCard(), deck.getCard()));
    }

    public void play(Gamer gamer) {
        if (gamer instanceof Dealer && score() >= 17) {
            return;
        }

        gamer.play(deck.getCard());
    }
}
