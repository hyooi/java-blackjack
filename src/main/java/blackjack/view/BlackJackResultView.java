package blackjack.view;

import blackjack.entity.Card;
import blackjack.entity.Player;
import blackjack.enums.StatusType;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackResultView {

    public void printPlayerCard(Set<Player> players) {
        players.forEach(player -> {
            var cards = player.getCards();
            System.out.printf("%s: %s\n", player.getName(), cards.stream()
                    .map(Card::toString)
                    .collect(Collectors.joining(",")));
        });
        System.out.println();
    }

    public void printPlayerCardWithScore(Set<Player> players) {
        players.forEach(player -> {
            var cards = player.getCards();
            System.out.printf("%s 카드: %s - 결과 : %s\n", player.getName(), cards.stream()
                            .map(Card::toString)
                            .collect(Collectors.joining(",")),
                    player.calculateScore());
        });
        System.out.println();
    }

    public void printAward(Map<Player, StatusType> playerStatus) {
        System.out.println("## 최종 승패");
        var dealerWin = 0;
        var dealerDraw = 0;
        var dealerLose = 0;

        for (var player : playerStatus.keySet()) {
            var status = playerStatus.get(player);
            if (status == StatusType.WIN) {
                dealerLose++;
            } else if (status == StatusType.LOSE) {
                dealerWin++;
            }
            if (status == StatusType.DRAW) {
                dealerDraw++;
            }
        }

        System.out.printf("딜러: %s승 %s무 %s패\n", dealerWin, dealerDraw, dealerLose);
        playerStatus.forEach((key, value) -> {
            System.out.printf("%s : %s\n", key.getName(), value.getDescription());
        });
    }
}