package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.*;

import java.util.LinkedList;
import java.util.List;

 /**
 Reference from for creating a MCTS with UCT.
  https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/mcts/montecarlo/M

  **/
public class MonteCarloTreeSearch {

private LinkedList<Player> players;
Player ai;
int bid;
Deck deck;
Card trump;



public Card findNextMove(Player ai, LinkedList<Player> p, Deck d, Card trump){
    this.ai = new Player(ai);
    this.players = new LinkedList<>();
    for(int i =0; i<p.size();i++){
        this.players.add(new Player(p.get(i)));
    }
    this.bid = ai.getBid();
    this.deck =  d;
    this.trump = new Card(trump);
    int iterations = 0;
    GameState gameState = new GameState(deck,players,ai);
    long start = System.currentTimeMillis();
    long end = start + 60;
    Tree tree = new Tree();
    Node rootNode = tree.getRoot();
    rootNode.setState(gameState);


  while(iterations < 100
          ) {

       // Selection
       Node promisingNode = selection(rootNode);

       // Expansion
        if(promisingNode.getState().getPlayers().getLast().getHand().size() > 0){
           expandNode(promisingNode);
        }

        //Simulation
        Node nodeToExplore = promisingNode;
        if(promisingNode.getChildren().size() > 0){
            nodeToExplore = promisingNode.getRandomChildNode();
        }
        int playoutResult = simulateRandomPlay(nodeToExplore);

        //Update
       backPropogation(nodeToExplore);
       iterations++;
   }
Node winner = rootNode.getChildWithMaxScore();
    tree.setRoot(winner);
    Card winningCard  = new Card();
    for(int i=0; i<winner.getState().getPlayers().size();i++){
        if(winner.getState().getPlayers().get(i).getPlayerID() == winner.getState().getAi().getPlayerID() ){
            winningCard = winner.getState().getPlayers().get(i).getPlayCard();
        }
    }
    return winningCard;
}

private Node selection(Node rootNode){
Node node =rootNode;
while(node.getChildren().size() !=0 ){
    node = new Node(UCT.bestChild(node));
}
return node;
}


    private void expandNode(Node node){
    List<GameState> possibleStates = node.getState().getAllStates();
    possibleStates.forEach(gameState -> {
        Node newNode = new Node(gameState);
        newNode.setParent(node);
        node.getChildren().add(newNode);
    });
    }

private void backPropogation(Node nodeToExplore){
    Node tempNode = nodeToExplore;
    while (tempNode != null) {
        tempNode.getState().incrementVisit();
        tempNode.getState().addScore(tempNode.getState().winsSim());
        tempNode = tempNode.getParent();
    }
}
private int simulateRandomPlay(Node n){
    Node tempNode = new Node(n);
    GameState tempState =  new GameState(tempNode.getState());
    int wins;
    LinkedList<Player> p = new LinkedList<>();
    for(int i=0; i <tempState.getPlayers().size(); i++){
        p.add(new Player(tempState.getPlayers().get(i)));
    }
        Round r = new Round(deck,p);
    r.setTrump(trump);
       r.playSimRound(ai.getPlayerID());
       wins = tempState.winsSim();


return wins;
}



}
