package test;

import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeckTest {

private Deck deck;

@Before
public void createTestDeck(){
    deck = new Deck();
    deck.generateDeck();

}
@Test
public void test60inDeck(){
    assertEquals(60,deck.getSize());
}

@Test
public void testShuffle(){
   assertFalse(deck.isShuffled());
   deck.shuffle();
   assertTrue(deck.isShuffled());
}

@Test
public void containsFourWizards(){
   int counter = 0;
    for(int i=0; i< deck.getSize(); i++){
        if(deck.getCard(i).getNumber() == 15 ){
            counter++;
        }
    }
    assertEquals(4,counter);
}

@Test
public void containsFourJesters(){

    int counter = 0;
    for(int i=0; i< deck.getSize(); i++){
        if(deck.getCard(i).getNumber() == 1 ){
            counter++;
        }
    }
    assertEquals(4,counter);
}

}
