package blackjack.view;

import blackjack.entity.Dealer;
import blackjack.entity.Gamer;
import blackjack.entity.Player;

import java.util.List;

public class BlackjackRunner {
    private final BlackjackInputView inputView = new BlackjackInputView();
    private final BlackjackOutputView outputView = new BlackjackOutputView();

    public void run() {
        var playerNames = inputView.inputPlayerNames();
        var dealer = Dealer.of();
        var players = inputView.inputPlayerMoney(playerNames, dealer);
        outputView.printCards(dealer, players);

        players.forEach(player -> run0(player, dealer));
        run0(dealer);

        outputView.printFinalScore(dealer, players, dealarScore(dealer, players));
    }

    private double dealarScore(Dealer dealer, List<Player> players) {
        return players.stream()
                .mapToDouble(dealer::getRewardWithGamer)
                .sum();
    }

    private void run0(Dealer dealer) {
        while(true) {
            var result = dealer.play(dealer);
            if (!result) {
                break;
            }

            outputView.printDealerReceiveCard();
        }
    }

    private void run0(Gamer player, Dealer dealer) {
        while(true) {
            var isPlay = inputView.inputReceiveCardYn(player);
            if (!isPlay) {
                player.stop();
                break;
            }

            dealer.play(player);
            outputView.printPlayStatus(player);
        }
    }
}
