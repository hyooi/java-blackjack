package blackjack.entity;

public class Dealer extends Player {
    private final Deck deck = Deck.of();

    public Dealer() {
        super("dealer");
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

    @Override
    public boolean isDealer() {
        return true;
    }
}
