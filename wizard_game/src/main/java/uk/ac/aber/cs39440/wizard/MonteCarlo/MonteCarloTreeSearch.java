package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.*;

import java.util.LinkedList;
import java.util.List;


 /**
 Reference from for cr4eeaqting a MCTS with UCT.
  https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/mcts/montecarlo/M

  **/
public class MonteCarloTreeSearch {

Game game = new Game();
LinkedList<Player> players;
Player ai;
Node[] children;
int bid;
Deck deck;


public Card findNextMove(Player ai, LinkedList<Player> p, int bid, Deck d){
    this.ai = ai;
    this.players = p;
    this.bid = bid;
    this.deck = d;
    int iterations = 0;

    long start = System.currentTimeMillis();
    long end = start + 60;
    Tree tree = new Tree();
    Node rootNode = tree.getRoot();
    rootNode.getState().setPlayers(players);

  //  while(System.currentTimeMillis() < end)
     while(iterations < 40) {

        //Selection
        Node promisingNode = selection(rootNode);

        // Expansion
        if(promisingNode.getState().getWins() != bid){
            expandNode(promisingNode);
        }

        //Simulation
        Node nodeToExplore = promisingNode;
        if(promisingNode.getChildren().size() >0){
            nodeToExplore = promisingNode.getRandomChildNode();
        }
        int playoutResult = simulateRandomPlay(nodeToExplore);

        //Update
       backPropogation(nodeToExplore,playoutResult);
       iterations++;
    }
Node winner = rootNode.getChildWithMaxScore();
    tree.setRoot(winner);
    return winner.getState().getAi().getPlayCard();
}

private Node selection(Node rootNode){
Node node =rootNode;
while(node.getChildren().size() !=0){
    node = UCT.findBestNodeWithUCT(node);
}
return node;
}

private void expandNode(Node node){
    List<GameState> possibleStates = node.getState().getAllStates();
    possibleStates.forEach(gameState -> {
        Node newNode = new Node(gameState);
        newNode.setParent(node);
        newNode.getState().setPlayers(node.getState().getPlayers());
        node.getChildren().add(newNode);
    });
}

private void backPropogation(Node nodeToExplore, int wins){
    Node tempNode = nodeToExplore;
    while (tempNode != null){
        tempNode.getState().incrementVisit();
        if(wins == bid){
            tempNode = tempNode.getParent();
        }
    }
}
private int simulateRandomPlay(Node n){
    Node tempNode = new Node(n);
    GameState tempState = tempNode.getState();
    int wins = tempState.getWins();

    if(tempState.getAi() == tempState.getPlayers().get(0)){
        tempNode.getParent().getState().setWins(Integer.MIN_VALUE);
        return wins ;
    }



        for(int i=0; i < tempState.getPlayers().size(); i++){
       playRandomHand();
       wins = tempState.getWins();
    }

return wins;
}

     public void playRandomHand() {
         Round r = new Round(deck,players);

         for(int i=0; i<=2; i++){
             Player p = players.get(i);
             Card c = new Card();
             if(r.containsCard(p) == true){
                     do{
                         p.randomSelect();
                     } while(r.checkSuit(p.getPlayCard()) == false);}
                 else {
                    p.randomSelect();
                 }
                 p.getHand().remove(p.getPlayCard());
             }

        r.applyRules();

     }
}
