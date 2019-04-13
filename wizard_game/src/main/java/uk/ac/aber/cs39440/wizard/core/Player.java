package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Player {

    int playerID;
    ArrayList<Card> hand;
    int bid;
    int score;
    Card playCard;
    int tricksWon;
    boolean isAI =true;

    public Player() {
        this.playerID = 0;
        this.hand = new ArrayList<>();
        this.bid = bid;
        this.playCard = playCard;
        this.tricksWon = tricksWon;
    }
public Player(Player p){
        this.playerID = p.getPlayerID();
        this.hand = (ArrayList<Card>) p.getHand().clone();
        this.bid = p.getBid();
        this.playCard = p.getPlayCard();
        this.tricksWon = p.getTricksWon();
        this.score = p.getScore();
        this.isAI = p.isAI();
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


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public ArrayList<Card> getHand() {
        return hand;
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
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        if(hand.size() != 0) {
            setPlayCard(hand.get(i));
        }
    }

    public void ruleBid(Card trump){
        int counter = 0;
        ArrayList<Card> temp = getHand();
        for(int i=0; i< temp.size(); i++){
            if(temp.get(i).getSuit() == trump.getSuit() || temp.get(i).getValue() > 11){
                counter++;
            }


        }

        setBid(counter/2);
    }

    public  void randomBid(){
        Random r = new Random();
        int i = r.nextInt(hand.size());
        setBid(i);

    }
    /**
     * Created List of Cards that match the Suit of the Trump Card in the Players Hand
     */
    public LinkedList<Card> ListOfTrumpSuit(Card trump){
        LinkedList<Card> TrumpPriority = new LinkedList<Card>();
        TrumpPriority.addAll(getHand());
        for(int i=TrumpPriority.size()-1; i > 0; i--){
            Card c= TrumpPriority.get(i);
            if(c.getSuit() != trump.getSuit()){
                if(c.getNumber() != 15){
                TrumpPriority.remove(i);}
            }
        }
        return sortList(TrumpPriority);
    }

    public LinkedList<Card> ListOfOtherSuit(Card trump){
        LinkedList<Card> OtherPriority = new LinkedList<>();
        OtherPriority.addAll(getHand());
        for(int i=OtherPriority.size()-1; i >0; i--
                ){
            Card c= OtherPriority.get(i);
            if(c.getSuit() == trump.getSuit() || c.getNumber() == 15){
                OtherPriority.remove(i);
            }
        }

        return sortList(OtherPriority);
    }
    public LinkedList<Card> sortList(LinkedList<Card> list){

            for(int j=0; j < list.size()-1; j++)
                if(list.get(j).getNumber() > list.get(j+1).getNumber()){
                Card temp = list.get(j);
                list.set(j,list.get(j+1));
                list.set(j+1,temp);
                    }

        return list;
    }


    public void ruleBasedSelect(Card trump){

        LinkedList<Card> OtherPriority = ListOfOtherSuit(trump);
        LinkedList<Card> TrumpPriority =ListOfTrumpSuit(trump);

        if(tricksWon != bid){
            setPlayCard(TrumpPriority.getFirst());
        }
        if(tricksWon == bid){
            setPlayCard(OtherPriority.getLast());
        }
    }


}

