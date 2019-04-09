package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

 /**
 Reference from for creating a MCTS with UCT.
  https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/mcts/montecarlo/M

  **/
public class MonteCarloTreeSearch {

Game game = new Game();
LinkedList<Player> players;
Player ai;
int bid;
Deck deck;
Card trump;



public Card findNextMove(Player ai, LinkedList<Player> p, Deck d, Card trump){
    this.ai = new Player(ai);
    this.players = new LinkedList<>((Collection<? extends Player>) p.clone());
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

  //  while(System.currentTimeMillis() < end)
    while(iterations < 2) {

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


    private Node expandNode(Node node){
    List<GameState> possibleStates = node.getState().getAllStates();
    possibleStates.forEach(gameState -> {
        Node newNode = new Node(gameState);
        newNode.setParent(node);
        newNode.getState().setPlayers(node.getState().getPlayers());
        node.getChildren().add(newNode);
    });
    Player p = node.getState().getPlayers().getFirst();
    for(int i=0; i< p.getHand().size();i++){
        if(p.getPlayCard() == p.getHand().get(i)){
            p.getHand().remove(i);
        }
    }
         return node;
     }

private void backPropogation(Node nodeToExplore, int wins){
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
    int wins = tempState.getSimWins();
    LinkedList<Player> p = new LinkedList<Player>((Collection<? extends Player>) tempNode.getState().getPlayers().clone());
        Round r = new Round(deck,p);
       r.setTrump(trump);
       r.playSimRound();
       wins = tempState.winsSim();


return wins;
}



}
