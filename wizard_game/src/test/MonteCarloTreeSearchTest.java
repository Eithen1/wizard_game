package test;

import main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.MonteCarloTreeSearch;
import main.java.uk.ac.aber.cs39440.wizard.core.Player;
import org.junit.Before;

import java.util.LinkedList;

class MonteCarloTreeSearchTest{
    LinkedList<Player>  p = new LinkedList<>();
    MonteCarloTreeSearch m = new MonteCarloTreeSearch();
     @Before
    public void setup(){
         p = new LinkedList<>();
         m = new MonteCarloTreeSearch();
     }

}
