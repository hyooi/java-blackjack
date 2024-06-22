package blackjack.view;

import blackjack.entity.Card;
import blackjack.entity.Dealer;
import blackjack.entity.Gamer;
import blackjack.entity.Player;

import java.util.List;

public class BlackjackOutputView {
    public void printCards(Dealer dealer, List<Player> gamers) {
        printCard(dealer);
        gamers.forEach(this::printCard);
        System.out.println();
    }

    private void printCard(Gamer gamer) {
        System.out.printf("%s:%s\n", gamer.getName(), getJoinedCards(gamer.getCards()));
    }

    public void printPlayStatus(Gamer gamer) {
        printCard(gamer);
        System.out.println();
    }

    public void printFinalScore(Dealer dealer, List<Player> gamers, double dealerScore) {
        printScore(dealer);
        gamers.forEach(this::printScore);

        System.out.println("## 최종 수익");
        System.out.printf("%s: %s\n", dealer.getName(), dealerScore);
        gamers.forEach(gamer -> {
            System.out.printf("%s: %s\n", gamer.getName(), gamer.getRewardWithGamer(dealer));
        });
    }

    private void printScore(Gamer gamer) {
        System.out.printf("%s:%s - 결과 : %s\n", gamer.getName(), getJoinedCards(gamer.getCards()), gamer.score());
    }

    private String getJoinedCards(List<Card> cards) {
        return cards.stream()
                .map(Object::toString).reduce((s, s2) -> s + ", " + s2)
                .orElse(null);
    }

    public void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
