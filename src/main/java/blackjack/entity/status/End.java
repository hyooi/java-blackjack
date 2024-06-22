package blackjack.entity.status;

public abstract class End implements Status {
    @Override
    public Status process(int score) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }
}
