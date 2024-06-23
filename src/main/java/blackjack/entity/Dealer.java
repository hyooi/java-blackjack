package blackjack.entity;

import java.util.List;

public class Dealer extends Gamer {
    private final Deck deck;

    public Dealer(Deck deck, List<Card> cards) {
        super("딜러", cards);
        this.deck = deck;
    }

    public static Dealer of() {
        var deck = Deck.of();
        return new Dealer(deck, List.of(deck.getCard(), deck.getCard()));
    }

    public boolean play(Gamer gamer) {
        if (gamer instanceof Dealer && score() >= 17) {
            return false;
        }

        gamer.play(getCard());
        return true;
    }

    public Card getCard() {
        return deck.getCard();
    }

    @Override
    public double getRewardWithGamer(Gamer gamer) {
        if (gamer instanceof Player player) {
            return player.getMoney() - player.getRewardWithGamer(this);
        }

        return 0;
    }
}
