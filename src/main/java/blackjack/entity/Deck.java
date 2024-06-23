package blackjack.entity;

import blackjack.enums.CardNumberType;
import blackjack.enums.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    static Deck of() {
        var cards = new ArrayList<Card>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> {
                    Arrays.stream(CardNumberType.values())
                            .forEach(cardNumberType -> cards.add(new Card(cardType, cardNumberType)));
                });
        Collections.shuffle(cards);

        return new Deck(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card getCard() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("더 이상 카드가 없습니다.");
        }

        return cards.remove(0);
    }
}
