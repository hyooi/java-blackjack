package blackjack.view;

import blackjack.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackInputView extends InputView {
    public List<Player> inputPlayer() {
        return process(() -> {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
            return Arrays.stream(inputString().split(","))
                    .map(s -> new Player(s.trim()))
                    .collect(Collectors.toList());
        });
    }

    public boolean inputBoolean(Player player) {
        return process(() -> {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player);
            var input = inputString().toUpperCase();
            if (!"Y".equals(input) && !"N".equals(input)) {
                throw new IllegalArgumentException(input);
            }

            return "Y".equalsIgnoreCase(input);
        });
    }
}
