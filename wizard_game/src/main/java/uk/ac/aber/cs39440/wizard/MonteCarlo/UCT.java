package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class UCT {
   public static double uctValue(int totalVisit, int nodeWins, int nodeVisit) {
       if (nodeVisit == 0) {
           return Integer.MAX_VALUE;
       }
       return (nodeWins / (double) nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);

   }

public static Node findBestNodeWithUCT(Node node) {
      int parentVisit = node.getState().getVisitCount();
      return Collections.max(
              node.getChildren(),
              Comparator.comparing(c -> uctValue(parentVisit, c.getState().getSimWins(), c.getState().getVisitCount()))
      );
   }

    public static Node bestChild(Node node) {
        Iterator<Node> it = node.getChildren().iterator();
        Node best = it.next();
        int parentVisit = node.getState().getVisitCount();
        double bestUCTValue = uctValue(parentVisit, best.getState().getSimWins(), best.getState().getVisitCount());
        while (it.hasNext()) {
            Node next = it.next();
            if (bestUCTValue < uctValue(parentVisit, next.getState().getSimWins(), next.getState().getVisitCount())) {
                bestUCTValue = uctValue(parentVisit, next.getState().getSimWins(), next.getState().getVisitCount());
                best = next;

            } else if (Math.abs(bestUCTValue - uctValue(parentVisit, next.getState().getSimWins(), next.getState().getVisitCount())) < 0.1) {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    bestUCTValue = uctValue(parentVisit, next.getState().getSimWins(), next.getState().getVisitCount());
                    best = next;
                }
            }
        }
        return best;
    }
}
