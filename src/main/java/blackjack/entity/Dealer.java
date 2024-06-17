package blackjack.entity;

import java.util.HashSet;
import java.util.Set;

public class Dealer extends Player {

    private final Deck deck = Deck.of();
    private final Set<Card> cards = new HashSet<>();

    public Dealer() {
        super("dealer");
    }

    public int deckSize() {
        return deck.size();
    }

    public boolean giveCard(Player player) {
        if (player instanceof Dealer) {
            if (calculateScore() < 16) {
                player.receiveCard(deck.getRandomCard());
                return true;
            }

            return false;
        }

        player.receiveCard(deck.getRandomCard());
        return true;
    }
}
