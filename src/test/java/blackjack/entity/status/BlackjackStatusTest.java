package blackjack.entity.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackStatusTest {

    @Test
    @DisplayName("Start에서 카드 합이 21을 초과하지 않으면 Hit")
    void start_to_hit() {
        var start = new Start();
        var result = start.process(20);
        assertThat(result)
                .isInstanceOf(Hit.class);
        assertThatThrownBy(() -> {
            result.getReward(1000);
        })
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("아직 게임이 진행 중입니다.");
    }

    @Test
    @DisplayName("Start에서 첫 상태에서 카드 합이 21이면 BlackJack")
    void start_to_blackjack() {
        var start = new Start();
        var result = start.process(21);
        assertThat(result)
                .isInstanceOf(BlackJack.class);
        assertThat(result.getReward(1000))
                .isEqualTo(1500);
    }

    @Test
    @DisplayName("Start에서 카드를 더 이상 받지 않으면 stand. 게임이 종료된다.")
    void start_to_stand() {
        var start = new Start();
        var result = start.stand();
        assertThat(result)
                .isInstanceOf(Stand.class);
        assertThat(result.getReward(10, 20, 1000))
                .isEqualTo(2000);
        assertThat(result.getReward(20, 10, 1000))
                .isEqualTo(0);
        assertThat(result.getReward(10, 10, 1000))
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("Hit에서 카드 합이 21을 초과한다. 즉시 패배한다.")
    void hit_to_bust() {
        var hit = new Hit();
        var result = hit.process(25);
        assertThat(result)
                .isInstanceOf(Bust.class);
        assertThat(result.getReward(1000))
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Hit에서 카드 합이 21을 초과하지 않으면 Hit")
    void hit_to_hit() {
        var hit = new Hit();
        var result = hit.process(20);
        assertThat(result)
                .isInstanceOf(Hit.class);
        assertThatThrownBy(() -> {
            result.getReward(1000);
        })
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("아직 게임이 진행 중입니다.");
    }

    @Test
    @DisplayName("Hit에서 카드 합이 21이면 BlackJack")
    void hit_to_blackjack() {
        var hit = new Hit();
        var result = hit.process(21);
        assertThat(result)
                .isInstanceOf(BlackJack.class);
        assertThat(result.getReward(1000))
                .isEqualTo(1500);
    }

    @Test
    @DisplayName("Hit에서 카드를 더 이상 받지 않으면 stand. 게임이 종료된다.")
    void hit_to_stand() {
        var hit = new Hit();
        var result = hit.stand();
        assertThat(result)
                .isInstanceOf(Stand.class);
        assertThat(result.getReward(10, 20, 1000))
                .isEqualTo(2000);
        assertThat(result.getReward(20, 10, 1000))
                .isEqualTo(0);
        assertThat(result.getReward(10, 10, 1000))
                .isEqualTo(1000);
    }
}
