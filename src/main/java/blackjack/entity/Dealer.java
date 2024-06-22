package blackjack.entity;

import java.util.List;

public class Dealer {
    private final Player player;
    private final Deck deck;

    public Dealer() {
        this.deck = Deck.of();
        this.player = new Player("딜러", Integer.MAX_VALUE, List.of(deck.getCard(), deck.getCard()));
    }
}
