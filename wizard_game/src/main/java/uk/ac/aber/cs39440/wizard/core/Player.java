package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    ArrayList<Card> hand;
    int bid;
    String name;
    Card playCard;
    int tricksWon = 0;
    boolean isAI =true;

    public Player() {
        this.hand = new ArrayList<>();
        this.bid = bid;
        this.name = name;
        this.playCard = playCard;
        this.tricksWon = tricksWon;
    }


    public void populateHand(Deck deck) {
        for (int i = 0; i < 15; i++) {
            if (deck.getCard(i) != null) {
                hand.add(deck.getCard(i));
            } else {
                populateHand(deck);
            }
            deck.removeCard(i);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void handToString() {
        System.out.println();
        for (int i = 0; i < hand.size(); i++) {
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

    public void setName(String name)
    {
        this.name = name;
    }
public Card getCard(int i){
        return hand.get(i);
}
    public Card getPlayCard() {
        return playCard;
    }

    public int getTricksWon() {
        return tricksWon;
    }

    public void setTricksWon(int tricksWon) {
        this.tricksWon = tricksWon;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public void setPlayCard(Card playCard) {
        this.playCard = playCard;
    }

    public void bidding() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Select Bidding Amount");
        int i = reader.nextInt();
        bid = i;

    }


    public void randomSelect() {
        Random r = new Random();
        int i = r.nextInt(hand.size());
        setPlayCard(hand.get(i));
        hand.remove(i);

    }
    public  void randomBid(){
        Random r = new Random();
        int i = r.nextInt(hand.size());
        setBid(i);

    }

}

