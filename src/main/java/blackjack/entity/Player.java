package blackjack.entity;

import blackjack.entity.status.Stand;

import java.util.List;

public class Player extends Gamer {
    private final int money;

    public Player(String name, int money, List<Card> cards) {
        super(name, cards);

        if (money <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이어야 합니다.");
        }

        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public double getRewardWithGamer(Gamer gamer) {
        if (isBlackjack() && gamer.isBlackjack()) {
            return money;
        }

        if (status instanceof Stand stand) {
            return stand.getReward(gamer.score(), score(), money);
        }

        return status.getReward(money);
    }
}
