package blackjack.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dealer {
    private final Deck deck = Deck.of();
    private final Set<Card> cards = new HashSet<>();

    public int deckSize() {
        return deck.size();
    }

    public int cardSize() {
        return cards.size();
    }

    public void giveCard(Player player) {
        player.receiveCard(deck.getRandomCard());
    }

    public void receiveCard(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }
}
