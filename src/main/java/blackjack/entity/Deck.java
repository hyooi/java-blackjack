package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck() {
        this.cards = new ArrayList<>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> {
                    Arrays.stream(ScoreType.values())
                            .forEach(scoreType -> {
                                cards.add(new Card(cardType, scoreType));
                            });
                });
    }

    public static Deck of() {
        var result = new Deck();
        result.shuffle();

        return result;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card getRandomCard() {
        var card = cards.get(0);
        cards.remove(card);

        return card;
    }
}
