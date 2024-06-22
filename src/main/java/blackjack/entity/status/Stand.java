package blackjack.entity.status;

public final class Stand extends End {

    @Override
    public double getReward(int money) {
        throw new UnsupportedOperationException("딜러 스코어와 플레이어 스코어가 필요합니다.");
    }

    public double getReward(int dealerScore, int playerScore, int money) {
        if (dealerScore == playerScore) {
            return money;
        }

        if (dealerScore > playerScore) {
            return 0;
        }

        return money * 2;
    }
}
