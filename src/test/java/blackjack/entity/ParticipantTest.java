package blackjack.entity;

import blackjack.enums.CardType;
import blackjack.enums.ScoreType;
import blackjack.enums.StatusType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void getPlayerStatus() {
        var dealer = new Dealer();
        dealer.receiveCard(
                new Card(CardType.CLOVER, ScoreType.TEN),
                new Card(CardType.CLOVER, ScoreType.ACE)
        );

        var players = List.of(
                player("pobi", new Card(CardType.HEART, ScoreType.TWO),
                        new Card(CardType.DIAMOND, ScoreType.TWO)),
                player("jason", new Card(CardType.HEART, ScoreType.FIVE),
                        new Card(CardType.CLOVER, ScoreType.ACE))
        );

        var participant = new Participant(dealer, players);
        assertThat(participant.getPlayerStatus())
                .hasSize(2)
                .containsEntry(players.get(0), StatusType.LOSE)
                .containsEntry(players.get(1), StatusType.LOSE);


    }

    private Player player(String name, Card... cards) {
        var result = new Player(name);
        result.receiveCard(cards);

        return result;
    }
}