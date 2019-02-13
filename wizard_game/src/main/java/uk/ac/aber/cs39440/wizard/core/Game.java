package main.java.uk.ac.aber.cs39440.wizard.core;

public class Game {

    private static Deck deck;
    private static Player player;



public  static void generate(){
    deck = new Deck();
    player = new Player();
    deck.generateDeck();
    System.out.println(deck.getSize());
    deck.printDeck();
    player.populateHand(deck);
    player.handToString();
}

    public static void main(String[] args) {
    generate();
    }
}
