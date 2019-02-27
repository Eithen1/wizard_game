package test;

import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

public class DeckTest {

public Deck deck;

@Before
public void createTestDeck(){
    deck = new Deck();
    deck.generateDeck();

}
@Test
public void test60inDeck(){
    assertEquals(60,deck.getSize());
}


/*public void testShuffle(){
    Deck shuffled = deck;
    shuffled.shuffle();
    assertNotSame(shuffled, deck);
} */
}
