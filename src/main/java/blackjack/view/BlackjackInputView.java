package blackjack.view;

import blackjack.entity.Dealer;
import blackjack.entity.Gamer;
import blackjack.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BlackjackInputView extends InputView {
    public List<String> inputPlayerNames() {
        return process(() -> {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
            return Arrays.stream(inputString().split(","))
                    .map(String::trim)
                    .toList();
        });
    }

    public List<Player> inputPlayerMoney(List<String> playerNames, Dealer dealer) {
        return process(() -> {
            var result = new ArrayList<Player>();
            playerNames.forEach(name -> {
                System.out.println(name + "의 배팅 금액은?");
                result.add(new Player(name, inputNumber(), List.of(dealer.getCard(), dealer.getCard())));

                System.out.println();
            });

            return result;
        });
    }

    public boolean inputReceiveCardYn(Gamer gamer) {
        return process(() -> {
            System.out.println(gamer.getName() + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            var result = inputString().toUpperCase();
            if ("Y".equals(result)) {
                return true;
            }

            if ("N".equals(result)) {
                return false;
            }

            throw new IllegalArgumentException(result);
        });
    }
}
