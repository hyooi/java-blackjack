package blackjack.entity;

import blackjack.enums.StatusType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Participant(Dealer dealer, List<Player> players) {
    public static final int BLACKJACK_SCORE = 21;

    public Map<Player, StatusType> getPlayerStatus() {
        return players().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> getStatus(dealer.calculateScore(), player.calculateScore()))
                );
    }

    private StatusType getStatus(int dealerScore, int playerScore) {
        if (dealerScore == playerScore || (dealerScore > BLACKJACK_SCORE && playerScore > BLACKJACK_SCORE)) {
            return StatusType.DRAW;
        }

        if (dealerScore > BLACKJACK_SCORE) {
            return StatusType.WIN;
        }

        if (playerScore > BLACKJACK_SCORE) {
            return StatusType.LOSE;
        }

        if (dealerScore > playerScore) {
            return StatusType.LOSE;
        }

        return StatusType.WIN;
    }
}
