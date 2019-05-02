import main.java.uk.ac.aber.cs39440.wizard.core.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoundTest {
    public Round testRound;
    LinkedList<Player> players;
    @Before
    public void createTestRound(){
        Deck deck = new Deck();
        deck.generateDeck();
        deck.shuffle();
        players = new LinkedList<>();
        players.add(new Player());
        players.add(new Player());
        players.get(0).setPlayerID(1);
        players.get(1).setPlayerID(2);
     testRound = new Round(deck,players);
    }

    @Test
    public void testChangeDealer(){
        assertEquals(1,players.get(0).getPlayerID());
        testRound.changeDealer();
        assertNotEquals(1,players.get(0));
    }

    @Test
    public void testCheckSuit(){
        testRound.setTrump(new Card(Suit.hearts,'a',14));
        players.get(0).setPlayCard(new Card(Suit.hearts,'t',10));
        players.get(1).setPlayCard(new Card(Suit.spades,'2',2));
        assertEquals(false, testRound.checkSuit(players.get(1)));
        players.get(1).setPlayCard(new Card(Suit.hearts,'k',13));
        assertEquals(true,testRound.checkSuit(players.get(1)));
        players.get(1).setPlayCard(new Card(Suit.non,'s',1));
        assertEquals(true, testRound.checkSuit(players.get(1)));
    }

    @Test
    public void testContainsCard(){
        testRound.setTrump(new Card(Suit.hearts,'a',14));
        players.get(0).getHand().add(new Card(Suit.hearts,'t',10));
        players.get(1).getHand().add(new Card(Suit.spades,'2',2));
       assertEquals(false ,testRound.containsCard(players.get(0)));
        players.get(0).setPlayCard(new Card(Suit.hearts,'t',10));
        assertEquals(false ,testRound.containsCard(players.get(1)));
        players.get(1).getHand().add(new Card(Suit.hearts,'2',2));
        assertEquals(true,testRound.containsCard(players.get(1)));

    }
   @Test
    public void testPlayHand(){
        testRound.setTrump(new Card(Suit.hearts,'a',14));
        players.get(0).getHand().add(new Card(Suit.hearts,'t',10));
        players.get(1).getHand().add(new Card(Suit.spades,'2',2));
        testRound.playRandomHand();
        assertEquals(1, players.get(1).getTricksWon());
    }
}
