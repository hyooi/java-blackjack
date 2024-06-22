package blackjack.entity;

import blackjack.entity.status.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final int money;
    private final List<Card> cards = new ArrayList<>();
    private Status status = new Start();

    public Player(String name, int money, List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("처음에는 반드시 두 장의 카드를 가져야 합니다.");
        }

        if (money <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이어야 합니다.");
        }

        this.name = name;
        this.money = money;
        this.cards.addAll(cards);
        this.status = this.status.process(score());
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

    public Status play(Card card) {
        cards.add(card);
        this.status = status.process(score());

        return status;
    }

    public Status stop() {
        this.status = new Stand();
        return status;
    }

    public double getReward(Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return money;
        }

        if (status instanceof Stand stand) {
            return stand.getReward(dealer.score(), score(), money);
        }

        return status.getReward(money);
    }

    public boolean isBlackjack() {
        return status instanceof BlackJack;
    }

    public boolean isBust() {
        return status instanceof Bust;
    }
}
