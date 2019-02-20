package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;

public class Player {

    ArrayList<Card>  hand;
    int bid;
    String name;
    Card playCard;
    int tricksWon = 0;

    public Player() {
        this.hand = new ArrayList<>();
        this.bid = bid;
        this.name = name;
        this.playCard = playCard;
        this.tricksWon = tricksWon;
    }


    public void populateHand(Deck deck){
        for(int i=0; i<15; i++){
            if(deck.getCard(i) != null){
            hand.add(deck.getCard(i));
            }else{populateHand(deck);}
            deck.removeCard(i);
    }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void handToString(){
        System.out.println();
        for(int i=0; i<hand.size(); i++){
            System.out.println(i + ": " + hand.get(i).toString());
        }
    }
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getPlayCard() {
        return playCard;}

   public void getPlayCard(int i) {
        playCard = hand.get(i);
        System.out.println(playCard);
   }

    public void setPlayCard(Card playCard) {
        this.playCard = playCard;
    }
}
