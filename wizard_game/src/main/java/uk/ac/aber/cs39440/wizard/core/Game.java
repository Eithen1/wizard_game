package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {

    public static Deck deck;
    public static LinkedList<Player> players   = new LinkedList<>();
private static Round r = new Round(deck,players);
public static Card trump;

public void setupPlayers(){
    Player player1 = new Player();
    player1.setAI(false);
    players.add(player1);
    for(int i=0; i<2; i++){
        Player computer = new Player();
        players.add(computer);
    }
}

public void gameSetup(){
    deck = new Deck();
    deck.generateDeck();
    deck.shuffle();
    setupPlayers();
}

public static void reSetup(){
    deck = new Deck();
    deck.generateDeck();
    deck.shuffle();
    trump = deck.getCard(0);
    for(int i=0; i<=2; i++){
        players.get(i).populateHand(deck);
    }
}

public  static void playGame(){
    int i=0;
    r.playRound();
    do {
        i++;
        reSetup();
        r.playRound();
    } while(i <5);
}
    public static void main(String[] args) {
        r.gameSetup();
        playGame();
    }



}
