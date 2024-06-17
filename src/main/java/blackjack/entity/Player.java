package blackjack.entity;

import blackjack.enums.ResultStatusType;

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

    public String getName() {
        return name;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int totalScore = 0;
        int aceCount = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                aceCount++;
            }
            totalScore += card.getScore();
        }
        for (int i = 0; i < aceCount; i++) {
            if (totalScore+10>21){
                return totalScore;
            }
            totalScore+=10;
        }
        return totalScore;
    }

    public ResultStatusType checkResult(int score) {
        if (score == calculateScore()) {
            return ResultStatusType.DRAW;
        }

        if (score > 21) {
            return ResultStatusType.WIN;
        }

        if (calculateScore() > 21) {
            return ResultStatusType.LOSE;
        }

        return score < calculateScore()? ResultStatusType.WIN : ResultStatusType.LOSE;
    }
}
