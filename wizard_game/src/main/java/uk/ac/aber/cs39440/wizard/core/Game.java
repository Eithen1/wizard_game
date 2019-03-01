package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;


public class Game {

    public  static Deck deck;
    public static LinkedList<Player> players   = new LinkedList<>();
private static Round r = new Round(deck,players);



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

public void reSetup(){
    deck = new Deck();
    deck.generateDeck();
    deck.shuffle();
    r.roundSetup();
    for(int i=0; i<2; i++){
        players.get(i).populateHand(deck);
    }
}

public void playGame(){
Rules rules = new Rules(players);
    r.playRound();
    for(int i=0; i<5; i++ ){
        i++;
        reSetup();
        r.playRound();
        rules.scoring();
    }
}
    public void play() {

        r.gameSetup();
        playGame();
        winner();
    }

public void winner(){
    Player winner = new Player();
    for(int i=0; i<players.size(); i++){
        if(winner.getScore() < players.get(i).getScore()){
            winner = players.get(i);
        }
    }
    if(winner.isAI == false){
        System.out.println("You Win!!!");
    }
    else{
        System.out.println("You Lose!!!");
    }
}

}
