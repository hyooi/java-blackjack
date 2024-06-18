package blackjack.view;

import blackjack.entity.Participant;
import blackjack.entity.Player;
import blackjack.enums.StatusType;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultView {

    public void printPlayerCard(Participant participant) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", getJoinedNames(participant.players()));
        printPlayerCardStatus(participant);
    }

    public void printPlayerCard(Player player) {
        System.out.printf("%s: %s\n", player, getJoinedNames(player.getCards()));
    }

    private <T> String getJoinedNames(Collection<T> cards) {
        return cards.stream()
                .map(T::toString)
                .collect(Collectors.joining(","));
    }

    public void printPlayerCardStatus(Participant participant) {
        printPlayerCard(participant.dealer());
        participant.players().forEach(this::printPlayerCard);
        System.out.println();
    }

    public void printPlayerCardWithScore(Participant participant, Map<Player, StatusType> playerStatus) {
        printDealerCardWithScore(participant.dealer());
        participant.players().forEach(this::printDealerCardWithScore);
        System.out.println();

        printAward(playerStatus);
    }

    private void printDealerCardWithScore(Player player) {
        System.out.printf("%s 카드: %s - 결과 : %s\n", player,
                getJoinedNames(player.getCards()),
                player.calculateScore());
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
            System.out.printf("%s : %s\n", key, value.getDescription());
        });
    }
}