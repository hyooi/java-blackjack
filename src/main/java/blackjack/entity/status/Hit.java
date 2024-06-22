package blackjack.entity.status;

public final class Hit extends Running {

    @Override
    public Status process(int score) {
        if (score == 21) {
            return new BlackJack();
        }

        if (score > 21) {
            return new Bust();
        }

        return this;
    }

    public Stand stand() {
        return new Stand();
    }
}
