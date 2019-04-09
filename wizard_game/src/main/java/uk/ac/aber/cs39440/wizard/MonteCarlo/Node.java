package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import java.util.*;

public class Node {
Node parent;
List<Node> children;
GameState state;


public Node(){
    this.state = new GameState();
    children = new ArrayList<>();
}

public Node(GameState gameState){
this.state = new GameState();
children = new ArrayList<>();
}

public  Node(Node parent, GameState  State , List<Node> children){
    this.parent = parent;
    this.children = new ArrayList<>();
    this.state = State ;

}
    public Node(Node node) {
        this.children = new ArrayList<>();
        this.state = new GameState(node.getState());
        if (node.getParent() != null)
            this.parent = node.getParent();
        List<Node> childArray = node.getChildren();
        for (Node child : childArray) {
            this.children.add(new Node(child));
        }
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Node getRandomChildNode() {
        int noOfPossibleMoves = this.children.size();
        int selectRandom = (int) (Math.random() * noOfPossibleMoves);
        return this.children.get(selectRandom);
    }

    public Node getChildWithMaxScore() {
   Node winner = new Node();
for(int i=0; i<children.size(); i++){
    if(children.get(i) == children.get(0)){
        winner = children.get(0);
    }

   int winnerScore = 0;
    int otherscore =0;
    if(children.get(i).getState().getVisitCount() >0){

          otherscore = children.get(i).getState().getSimWins() / children.get(i).getState().getVisitCount();
    }
    if (winnerScore <= otherscore){
      winner = children.get(i);
    }
}
return winner;
    }



}


