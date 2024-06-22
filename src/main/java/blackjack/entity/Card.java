package blackjack.entity;

import blackjack.enums.CardNumberType;
import blackjack.enums.CardType;

public class Card {
    private final CardType cardType;
    private final CardNumberType cardNumberType;

    public Card(CardType cardType, CardNumberType cardNumberType) {
        this.cardType = cardType;
        this.cardNumberType = cardNumberType;
    }

    public int getScore() {
        return cardNumberType.getScore();
    }

    public boolean isAce() {
        return CardNumberType.ACE == cardNumberType;
    }
}
