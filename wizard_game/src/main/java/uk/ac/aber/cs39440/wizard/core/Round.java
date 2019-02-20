package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Scanner;

public class Round extends Game{

    private Scanner reader = new Scanner(System.in);



public Round(Deck deck, ArrayList<Player> players){
    this.deck = deck;
    this.players = players;
}

public void playRound(){

    deck.printDeck();
  Player mainPlayer = players.get(0);
  mainPlayer.populateHand(deck);
    mainPlayer.handToString();
    System.out.println("Select Card to Play");
    int  n = reader.nextInt();
    mainPlayer.getPlayCard(n);
for (int i=1; i<=2; i++) {
   AI p = (AI) players.get(i);
   p.populateHand(deck);
   p.handToString();
   p.randomSelect();
}
}


}
