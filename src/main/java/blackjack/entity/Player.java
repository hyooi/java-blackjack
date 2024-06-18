package blackjack.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    public static final int MAX_SCORE = 21;

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

    public Set<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        var extraScores = cards.stream()
                .filter(card -> card.extraScore() > 0)
                .mapToInt(Card::extraScore)
                .boxed()
                .toList();

        return applyExtraScore(extraScores, totalScore);
    }

    private static int applyExtraScore(List<Integer> extraScores, int totalScore) {
        for (int extraScore : extraScores) {
            if (totalScore + extraScore > MAX_SCORE) {
                return totalScore;
            }

            totalScore += extraScore;
        }

        return totalScore;
    }

    @Override
    public String toString() {
        return name;
    }
}
