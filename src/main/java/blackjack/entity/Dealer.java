package blackjack.entity;

import java.util.List;

public class Dealer extends Player {
    public static final int FIRST_CARD_COUNT = 2;
    private final Deck deck = Deck.of();

    public Dealer() {
        super("딜러");
    }

    public void giveFirstCard(List<Player> players) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            players.forEach(this::giveCard);
            giveCard(this);
        }
    }

    public boolean giveCard(Player player) {
        if (player == this) {
            if (calculateScore() <= 16) {
                player.receiveCard(deck.getFirstCard());
                return true;
            }

            return false;
        }

        player.receiveCard(deck.getFirstCard());
        return true;
    }

}
