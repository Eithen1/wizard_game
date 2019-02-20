package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Deck deck;
    public static ArrayList<Player> players = new ArrayList<>();
private static Round r = new Round(deck,players);
public static Card trump;

public void setupPlayers(){
    Player player1 = new Player();
    players.add(player1);
    for(int i=0; i<=3; i++){
        AI computer = new AI();
        players.add(computer);
    }
}

public void gameSetup(){
    deck = new Deck();
    deck.generateDeck();
    setupPlayers();
}
    public static void main(String[] args) {
    r.gameSetup();
    r.playRound();
    }
}
