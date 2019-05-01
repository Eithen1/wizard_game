

import main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.GameState;
import main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.Tree;
import main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.MonteCarloTreeSearch;
import main.java.uk.ac.aber.cs39440.wizard.core.Game;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.UCT.uctValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MonteCarloTreeSearchTest{
    public Game g;
   private MonteCarloTreeSearch m;

     @Before
    public void setup(){
m = new MonteCarloTreeSearch();
     }

@Test
    public void checkUCT(){
        double uctValue = 15.79;
    assertEquals(uctValue(600, 300, 20), uctValue, 0.01);
    }
    @Test
    public void checksGetAllPossibleStatesNotEmpty() {
         g = new Game();
         g.gameSetup();
         g.getR().roundSetup();
         g.getPlayers().get(0).setPlayCard(g.getPlayers().get(0).getCard(0));
        GameState initState = new GameState(g.getDeck(),g.getPlayers(),g.getPlayers().get(1),g.getR().getTrump());
        List<GameState> possibleStates = initState.getAllStates();
        assertTrue(possibleStates.size() > 0);
    }

}
