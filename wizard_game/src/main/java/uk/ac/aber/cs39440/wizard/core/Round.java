package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;
import java.util.Scanner;

public class Round extends Game{

    private Scanner reader = new Scanner(System.in);


public Round(Deck deck, LinkedList<Player> players){
    this.deck = deck;
    this.players = players;
}

public void roundSetup(){
    trump = deck.getCard(0);
    deck.removeCard(0);
    for(int i=0; i<=2; i++){
        Player p = players.get(i);
        p.populateHand(deck);
    }
}

public void playHand(){

for (int i=0; i<=2; i++) {
   Player p = players.get(i);
   if(p.isAI == false){
        p.handToString();
        System.out.println("Trump: " + trump.toString());
        System.out.println("Select Card to Play");
        int  n = reader.nextInt();
        players.get(i).setPlayCard(p.getCard(n));
        p.hand.remove(n);
    }
    else {
       p.handToString();
       p.randomSelect();
       players.get(i).setPlayCard(p.getPlayCard());
       System.out.println(p.playCard);

   }
}
Rules r = new Rules(players);
r.scoringTrick();
System.out.println("---------------------------");
changeDealer();
}


public void check(){
    for(int i=0; i<players.size();i++){
        System.out.println(players.get(i).tricksWon);
    }
}

public void biddingforRound(){
    for(int i=0; i<=2; i++){
        Player p =players.get(i);
        if(p.isAI == false){
            p.handToString();
            System.out.println("Enter Bid:");
            System.out.println("Trump: " + trump.toString());
            int n = reader.nextInt();
            players.get(i).setBid(n);
        } else {
            players.get(i).randomBid();
           System.out.println( players.get(i).bid);
        }
    }
}
public void playRound(){
    roundSetup();
    biddingforRound();
   Player p  = players.getLast();

    do{
        playHand();
    } while(p.hand.size() > 0);

    check();
}
   public void changeDealer() {
     Player player=players.remove();
     players.add(player);
}


}

