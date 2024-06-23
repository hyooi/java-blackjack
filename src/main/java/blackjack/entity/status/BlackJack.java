package blackjack.entity.status;

public final class BlackJack extends End {

    @Override
    public double getReward(int money) {
        return money * 1.5;
    }
}
