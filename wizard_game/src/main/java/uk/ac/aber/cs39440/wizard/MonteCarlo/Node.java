package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import com.sun.xml.internal.bind.marshaller.NioEscapeHandler;
import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Game;

import java.util.*;

public class Node {
Node parent;
List<Node> children;
GameState state;

public Node(GameState gameState){
this.state = new GameState();
children = new ArrayList<>();
}

public  Node(Node parent, GameState  State , List<Node> children){
    this.parent = parent;
    this.children = new ArrayList<>();
    this.state = State ;

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
        return Collections.max(this.children, Comparator.comparing(c -> {
            return c.getState().getVisitCount();
        }));
    }


}


