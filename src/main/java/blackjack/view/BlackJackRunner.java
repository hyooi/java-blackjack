package blackjack.view;

import blackjack.entity.Dealer;
import blackjack.entity.Participant;

public class BlackJackRunner {
    private final BlackJackInputView blackJackInputView = new BlackJackInputView();
    private final BlackJackResultView blackJackResultView = new BlackJackResultView();

    public void run() {
        var dealer = new Dealer();
        var players = blackJackInputView.inputPlayer();

        var participant = new Participant(dealer, players);
        dealer.giveFirstCard(players);
        blackJackResultView.printPlayerCard(participant);

        for (var player : players) {
            while (blackJackInputView.inputBoolean(player)) {
                dealer.giveCard(player);
                blackJackResultView.printPlayerCard(player);
            }
        }

        if (dealer.giveCard(dealer)) {
            System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        }

        blackJackResultView.printPlayerCardWithScore(participant, participant.getPlayerStatus());
    }
}
