import blackjack.entity.Card;
import blackjack.entity.Dealer;
import blackjack.entity.Player;
import blackjack.enums.ResultStatusType;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리");
        var names = scan.nextLine().split(",");
        var players = Arrays.stream(names)
                .map(name -> new Player(name.trim()))
                .collect(Collectors.toList());
        var dealer = new Dealer();
        players.add(dealer);

        players.forEach(dealer::giveCard);
        players.forEach(dealer::giveCard);

        players.forEach(player -> {
            var cards = player.getCards();
            System.out.printf("%s: %s\n", player.getName(), cards.stream()
                    .map(Card::toString)
                    .collect(Collectors.joining(",")));
        });

        players.forEach(player -> {
            if (player instanceof Dealer) {
                if (dealer.giveCard(player)) {
                    System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
                }
            } else {
                System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
                var result = scan.nextLine().toUpperCase();
                if ("Y".equals(result)) {
                    dealer.giveCard(player);
                }
            }
        });

        players.forEach(player -> {
            var cards = player.getCards();
            System.out.printf("%s 카드: %s - 결과 : %s\n", player.getName(), cards.stream()
                    .map(Card::toString)
                    .collect(Collectors.joining(",")),
                    player.calculateScore());
        });

        System.out.println("## 최종 승패");
        int winningDealerScore = 0;
        int losingDealerScore = 0;
        int drawScore = 0;

        for (var player : players) {
            if (!(player instanceof Dealer)) {
                var resultStatus = player.checkResult(dealer.calculateScore());
                if (resultStatus == ResultStatusType.WIN) {
                    winningDealerScore++;
                } else if (resultStatus == ResultStatusType.LOSE) {
                    losingDealerScore++;
                } else if (resultStatus == ResultStatusType.DRAW) {
                    drawScore++;
                }

                System.out.printf("%s : %s\n", player.getName(), resultStatus);
            }
        }

        System.out.printf("딜러 : %s승 %s패 %s무\n", winningDealerScore, losingDealerScore, drawScore);
    }
}
