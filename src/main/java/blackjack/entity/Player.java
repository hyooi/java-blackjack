package blackjack.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private final Set<Card> cards = new HashSet<>();

    public Player(String name) {
        this.name = name;
    }

    public void receiveCard(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public int cardSize() {
        return cards.size();
    }
}
