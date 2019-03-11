package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

public class Tree {

    Node root;

    public Tree(Node root){

        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void addChild(Node parent, Node child){
        parent.getChildren().add(child);
    }
}
