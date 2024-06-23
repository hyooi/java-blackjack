package blackjack.entity.status;

public final class Hit extends Running {

    @Override
    public Status process(int score) {
        if (score == MAX_SCORE) {
            return new BlackJack();
        }

        if (score > MAX_SCORE) {
            return new Bust();
        }

        return this;
    }
}
