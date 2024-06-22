package blackjack.entity.status;

public interface Status {
    Status process(int score);

    double getReward(int money);
}
