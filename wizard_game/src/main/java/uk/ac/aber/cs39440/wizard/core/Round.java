package main.java.uk.ac.aber.cs39440.wizard.core;


import main.java.uk.ac.aber.cs39440.wizard.MonteCarlo.MonteCarloTreeSearch;

import java.util.LinkedList;
import java.util.Scanner;

public class Round{

    private Scanner reader = new Scanner(System.in);
    public  Deck deck;
    private LinkedList<Player> players   = new LinkedList<>();
public  Card trump;

    public Round() {
    }



    public Card getTrump() {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
    }

public Round(Deck deck, LinkedList<Player> players){
    this.deck = deck;
    this.players = players;
}


    public void roundSetup(){

    setTrump(deck.getCard(0));
    deck.removeCard(0);
    for(int i=0; i<=2; i++){
        Player p = players.get(i);
        p.setBid(0);
        p.setTricksWon(0);
        p.populateHand(deck);
    }
}

public void playSimHand(){
    for(int i=0; i<=2; i++){

        Player p = players.get(i);
        if(p.getPlayCard() == null)

            if(containsCard(p) == true){
                do{
                    p.randomSelect();
                } while(checkSuit(p) == false);}
            else {
              p.randomSelect();
            }

            p.getHand().remove(p.getPlayCard());
    }

    applyRules();
}

public void playHand() {
for(int i=0; i<=2; i++){
    Player p = players.get(i);
    Card c;
        if (p.isAI == false) {
            playerSelect(p);
            } else {
                p.handToString();
            MonteCarloTreeSearch m = new MonteCarloTreeSearch();

               p.setPlayCard(m.findNextMove(p,players,deck,trump));}

                System.out.println(p.getPlayCard().toString());
                p.getHand().remove(p.getPlayCard());


}

applyRules();
}

    public void playRandomHand() {
        for(int i=0; i<=2; i++){
            Player p = players.get(i);
            Card c = new Card();
            if (p.isAI == false) {
                playerSelect(p);
            } else {
                p.handToString();
                if(containsCard(p) == true){
                    do{
                       p.randomSelect();
                    } while(checkSuit(p) == false);}
                else {
                   p.randomSelect();
                }
                p.getHand().remove(p.getPlayCard());
            }
            System.out.println(p.getPlayCard().toString());
        }

        applyRules();
    }


    public void playRuleBasedHand() {
        for(int i=0; i<=2; i++){
            Player p = players.get(i);
            Card c = new Card();
            if (p.isAI == false) {
                playerSelect(p);
            } else {
                p.handToString();
                if(containsCard(p) == true){
                    do{
                        p.ruleBasedSelect(trump);
                    } while(checkSuit(p) == false);}
                else {
                    p.ruleBasedSelect(trump);
                }
                p.getHand().remove(p.getPlayCard());
            }
            System.out.println(p.getPlayCard().toString());
        }

        applyRules();
    }
public void playerSelect(Player p){
    p.handToString();
    int n;
    if(containsCard(p) == true){
    do{
    System.out.println("Trump: " + getTrump().toString());
    System.out.println("Select Card to Play");
     n = reader.nextInt();
p.setPlayCard(p.getCard(n));}
    while (checkSuit(p) == false);}
    else{
        System.out.println("Trump: " + getTrump().toString());
        System.out.println("Select Card to Play");
        n = reader.nextInt();
        p.setPlayCard(p.getCard(n)) ;
    }

    p.getHand().remove(n);
}
public void applyRules(){
    Rules r = new Rules(players, trump);
    r.scoringTrick();
    System.out.println("---------------------------");
    changeDealer();
}
    public boolean checkSuit(Player p){
        if(p.getPlayCard().getValue() != 'w'){
            if( p.getPlayCard().getSuit() != players.get(0).getPlayCard().getSuit() ){
                if(p.getPlayCard().getSuit() != trump.getSuit()){
                    if(p.isAI == false){
                    System.out.println("Play Card isn't equal to trump or Dealers Suit");}
                    return false;}
                return true;
            }
        }
        return true;
    }
public boolean containsCard(Player p){

    if(p != players.get(0)){
        for(int i=0; i < p.getHand().size(); i++){
            Card c = p.getHand().get(i);
            if(c.getSuit() == trump.getSuit() || c.getSuit() == players.get(0).getPlayCard().getSuit()) {
                return true;
            }
        }
        return false;
    }
    return true;

}

public void check(){
    for(int i=0; i<players.size();i++){
        System.out.println("Tricks Won "+players.get(i).getTricksWon() + " " + players.get(i));
    }
}

public void biddingforRound(){
    for(int i=0; i<=2; i++){
        Player p =players.get(i);
        if(p.isAI() == false){
            p.handToString();
            System.out.println("Enter Bid:");
            System.out.println("Trump: " + trump.toString());
            int n = reader.nextInt();
            players.get(i).setBid(n);
        } else {
            players.get(i).ruleBid(trump);
           System.out.println( players.get(i).getBid());
        }
    }
}
public void playSimRound(int id){

    Player p = new Player();
    for(int i=0; i< players.size(); i++){
        if(players.get(i).getPlayerID() == id){
            p = players.get(i);
        }
    }
    do{
        playSimHand();
    } while(p.hand.size() > 0);
    Rules r = new Rules(players,trump);
    r.scoring();
    for(int i=0; i<players.size(); i++){
        System.out.println(players.get(i).getScore());
    }
    check();
}

public void playRound(){
    roundSetup();
    biddingforRound();
   Player p  = players.getLast();

    do{
        playHand();
    } while(p.hand.size() > 0);
    Rules r = new Rules(players,trump);
    r.scoring();
    for(int i=0; i<players.size(); i++){
       System.out.println(players.get(i).getScore());
    }
    check();
}

   public void changeDealer() {
        for(int i=0; i < players.size(); i++){
            players.get(i).setPlayCard(null);
        }
     Player player=players.remove();
     players.add(player);
}


}

