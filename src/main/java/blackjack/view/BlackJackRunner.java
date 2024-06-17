package blackjack.view;

import blackjack.entity.Dealer;
import blackjack.entity.Player;
import blackjack.enums.StatusType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackRunner {
    private final BlackJackInputView blackJackInputView = new BlackJackInputView();
    private final BlackJackResultView blackJackResultView = new BlackJackResultView();

    public void run() {
        var players = blackJackInputView.inputPlayer();
        var dealer = new Dealer();
        players.add(dealer);

        var playerStatus = new HashMap<Player, StatusType>();

        players.forEach(dealer::giveCard);
        players.forEach(dealer::giveCard);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", players.stream()
                .filter(player -> !player.isDealer())
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
        blackJackResultView.printPlayerCard(players);

        while (true) {
            if (checkPlayerStatus(players, playerStatus)) {
                blackJackResultView.printAward(playerStatus);
                return;
            }

            for (var player : players) {
                if (playerStatus.containsKey(player)) {
                    continue;
                }

                if (player instanceof Dealer) {
                    if (dealer.giveCard(player)) {
                        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
                    }
                } else {
                    if (blackJackInputView.inputBoolean(player.getName())) {
                        dealer.giveCard(player);
                    }
                }
            }
            blackJackResultView.printPlayerCard(players);
        }
    }

    // 딜러가 17이상이면 정산. 게임하다가 blackjack나거나 bust난 유저는 게임 종료
    private boolean checkPlayerStatus(Set<Player> players, Map<Player, StatusType> playerStatus) {
        var isBreak = false;
        for (var player : players) {
            if (!player.isDealer() && player.isBust()) {
                playerStatus.put(player, StatusType.LOSE);
            } else if(!player.isDealer() && player.isBlackJack()) {
                playerStatus.put(player, StatusType.WIN);
            }

            if (player.isDealer() && player.calculateScore() >= 17) {
                isBreak = true;
            }
        }

        if (isBreak) {
            var dealer = players.stream()
                    .filter(Player::isDealer)
                    .findFirst()
                    .get();
            blackJackResultView.printPlayerCardWithScore(players);

            for (var player : players) {
                if (player.isDealer()) {
                    continue;
                }

                playerStatus.putIfAbsent(player, getStatus(dealer.calculateScore(), player.calculateScore()));
            }

            return true;
        }

        return false;
    }

    private StatusType getStatus(int dealerScore, int playerScore) {
        if (dealerScore == playerScore || (dealerScore > 21 && playerScore > 21)) {
            return StatusType.DRAW;
        }

        if (dealerScore > 21) {
            return StatusType.WIN;
        }

        if (playerScore > 21) {
            return StatusType.LOSE;
        }

        if (dealerScore > playerScore) {
            return StatusType.LOSE;
        }

        return StatusType.WIN;
    }

}
