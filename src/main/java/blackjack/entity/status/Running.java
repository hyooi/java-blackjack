package blackjack.entity.status;

public abstract class Running implements Status {

    @Override
    public double getReward(int money) {
        throw new UnsupportedOperationException("아직 게임이 진행 중입니다.");
    }
}
