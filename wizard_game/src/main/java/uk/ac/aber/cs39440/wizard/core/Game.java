package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.Scanner;

public class Game {

    private static Deck deck;
    private static Player player;
private  static Scanner reader = new Scanner(System.in);


public  static void generate(){
    deck = new Deck();
    player = new Player();
    deck.generateDeck();
    System.out.println(deck.getSize());
    deck.printDeck();
    player.populateHand(deck);
    player.handToString();
    System.out.println("Select Card to Play");
    int n = reader.nextInt();
    player.getPlayCard(n);

}


    public static void main(String[] args) {
    generate();
    }
}
