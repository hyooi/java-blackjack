package blackjack.entity;

import blackjack.entity.status.Start;
import blackjack.entity.status.Status;

import java.util.List;

public class Player {
    private final String name;
    private final Status status;
    private final int money;
    private final List<Card> cards;

    public Player(String name, int money, List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("처음에는 반드시 두 장의 카드를 가져야 합니다.");
        }

        if (money <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이어야 합니다.");
        }

        this.name = name;
        this.money = money;
        this.cards = cards;
        this.status = new Start();
    }

}
