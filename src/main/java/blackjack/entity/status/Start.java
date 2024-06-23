package blackjack.entity.status;

/**
 * 첫 두장의 카드를 받은 상태
 */
public final class Start implements Status {

    @Override
    public Status process(int score) {
        if (score == MAX_SCORE) {
            return new BlackJack();
        }

        return new Hit();
    }

    @Override
    public double getReward(int money) {
        return money;
    }
}
