package blackjack.entity.status;

public final class Bust extends End {
    @Override
    public double getReward(int money) {
        return 0;
    }
}
