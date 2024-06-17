package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;

import java.util.List;

public class Card {
    private final CardType type;
    private final ScoreType score;

    public Card(CardType type, ScoreType score) {
        this.type = type;
        this.score = score;
    }

    public List<Integer> getScore() {
        return score.getScores();
    }
    public boolean isAce(){
        return score==ScoreType.ACE;
    }
}
