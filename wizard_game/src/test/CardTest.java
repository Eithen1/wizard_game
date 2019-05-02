import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Suit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    public Card card;

    @Before
    public void createTestCard(){
        card = new Card(Suit.hearts,'a',14);
    }

    @Test
    public  void testCard(){
        assertEquals(Suit.hearts,card.getSuit());
        assertEquals('a',card.getValue());
        assertEquals(14,card.getNumber());
    }
}
