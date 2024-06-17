package blackjack.view;

import blackjack.entity.Player;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackInputView extends InputView {
    public Set<Player> inputPlayer() {
        return process(() -> {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
            return Arrays.stream(inputString().split(","))
                    .map(s -> new Player(s.trim()))
                    .collect(Collectors.toSet());
        });
    }

    public boolean inputBoolean(String name) {
        return process(() -> {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
            var result = inputString().toUpperCase();
            return "Y".equals(result);
        });
    }
}
