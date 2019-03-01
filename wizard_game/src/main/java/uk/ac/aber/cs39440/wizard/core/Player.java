package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    ArrayList<Card> hand;
    int bid;
    int Score;
    Card playCard;
    int tricksWon;
    boolean isAI =true;

    public Player() {
        this.hand = new ArrayList<>();
        this.bid = bid;
        this.playCard = playCard;
        this.tricksWon = tricksWon;
    }


    public void populateHand(Deck deck) {
        for (int i = 0; hand.size()< 15; i++) {
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


public Card getCard(int i){
        return hand.get(i);
}
    public Card getPlayCard() {
        return playCard;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
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

