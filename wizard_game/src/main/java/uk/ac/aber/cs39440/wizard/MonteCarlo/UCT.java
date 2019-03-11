package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import java.util.Collections;
import java.util.Comparator;

public class UCT {
   public static double uctValue(int totalVisit, int nodeWins, int nodeVisit){
     if(nodeVisit == 0){
        return Integer.MAX_VALUE;
     }
     return (nodeWins/nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit)/nodeVisit);
   }

   static Node findBestNodeWithUCT(Node node) {
      int parentVisit = node.getState().getVisitCount();
      return Collections.max(
              node.getChildren(),
              Comparator.comparing(c -> uctValue(parentVisit, c.getState().getWins(), c.getState().getVisitCount()))
      );
   }
}
