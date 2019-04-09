package test;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Player;
import main.java.uk.ac.aber.cs39440.wizard.core.Rules;
import main.java.uk.ac.aber.cs39440.wizard.core.Suit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.util.LinkedList;

public class RuleTest {
 Card trump = new Card(Suit.hearts,'7',7);
   Player p = new Player();
   Player two = new Player();
   LinkedList<Player>  players = new LinkedList<>();
   Rules r;


@Before
public void setup(){
    players.add(p);
    players.add(two);

}

    @Test
    public void checkWizardWins(){
        p.setPlayCard(new Card(Suit.non,'w',15));
        two.setPlayCard(new Card(Suit.hearts,'a',14));
        r= new Rules(players,trump);
        r.wizardRule();
        assertEquals(p.getPlayCard(), r.getWinner().getPlayCard());
    }
@Test
    public void checkTrumpSuitWins(){
    p.setPlayCard(new Card(Suit.hearts,'a',14));
    two.setPlayCard(new Card(Suit.spades,'a',14));
    r= new Rules(players,trump);
        r.wizardRule();
        assertEquals(p.getPlayCard(), r.getWinner().getPlayCard());
}
@Test
public void checkValueWins(){
    p.setPlayCard(new Card(Suit.hearts,'t',10));
    two.setPlayCard(new Card(Suit.hearts,'a',14));
    r = new Rules(players,trump);
    r.wizardRule();
    assertEquals(two.getPlayCard(),r.getWinner().getPlayCard());
}
@Test
public void checkJesterLoses(){
    p.setPlayCard(new Card(Suit.non,'s',1));
    two.setPlayCard(new Card(Suit.spades,'2',2));
    r = new Rules(players,trump);
    r.wizardRule();
    assertEquals(two.getPlayCard(),r.getWinner().getPlayCard());
}
@Test
public void checkScoring(){
    p.setBid(7);
    p.setTricksWon(7);
    two.setBid(7);
    two.setTricksWon(6);
    r = new Rules(players,trump);
    r.scoring();
    assertEquals(50,two.getScore());
    assertEquals(90,p.getScore());
    }


}
