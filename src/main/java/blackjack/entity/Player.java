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

    public int calculateScore() {
        int totalScore = 0;
        int aceCount = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                aceCount++;
            }
            totalScore += card.getScore().get(0);
        }
        for (int i = 0; i < aceCount; i++) {
            if (totalScore+10>21){
                return totalScore;
            }
            totalScore+=10;
        }
        return totalScore;
    }
}
