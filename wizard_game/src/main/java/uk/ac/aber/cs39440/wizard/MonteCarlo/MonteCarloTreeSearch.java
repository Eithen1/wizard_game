package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Game;
import main.java.uk.ac.aber.cs39440.wizard.core.Player;

import java.util.ArrayList;
import java.util.List;

public class MonteCarloTreeSearch {

Game game = new Game();
ArrayList<Player> players = new ArrayList<>();
Node[] children;
int bid;


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

/*private void backPropogation(Node nodeToExplore, int playerNo){
    Node tempNode = nodeToExplore;
    while (tempNode !=){
        tempNode.getState().incrementVisit();
        if(tempNode.getState())
    }
} */
private int simulateRandomPlay(Node n){
return Integer.parseInt(null);
}

}
