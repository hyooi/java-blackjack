package blackjack.entity;

import blackjack.entity.status.BlackJack;
import blackjack.entity.status.Bust;
import blackjack.entity.status.Start;
import blackjack.entity.status.Status;

import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {
    private final String name;
    protected final List<Card> cards = new ArrayList<>();
    protected Status status = new Start();

    protected Gamer(String name, List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("처음에는 반드시 두 장의 카드를 가져야 합니다.");
        }

        this.name = name;
        this.cards.addAll(cards);
        this.status = this.status.process(score());
    }

    public boolean isBlackjack() {
        return status instanceof BlackJack;
    }

    public boolean isBust() {
        return status instanceof Bust;
    }

    public Status play(Card card) {
        cards.add(card);
        this.status = status.process(score());

        return status;
    }

    public int score() {
        int result = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();

        if (aceCount > 0 && result < 21) {
            return result + 10;
        }

        return result;
    }
}
