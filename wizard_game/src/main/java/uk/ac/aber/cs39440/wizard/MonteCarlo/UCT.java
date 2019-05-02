package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * The Upper confidence bound applied to tree value.
 */
public class UCT {
   public static double uctValue(int totalVisit, int nodeWins, int nodeVisit) {
       if (nodeVisit == 0) {
           return Integer.MAX_VALUE;
       }
       return (nodeWins / (double) nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
   }

    /**
     * Selects the best child based on the fraction between the amount of simulation wins and the amount of visits the node has had already
     * @param node the root node from which all child nodes come from.
     * @return the node best node to be expand on next.
     */
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
