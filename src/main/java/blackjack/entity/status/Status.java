package blackjack.entity.status;

public interface Status {
    int MAX_SCORE = 21;
    Status process(int score);

    double getReward(int money);
}
