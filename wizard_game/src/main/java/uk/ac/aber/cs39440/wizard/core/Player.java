package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Player {

    private int playerID;
    final ArrayList<Card> hand;
    private int bid;
    private int score;
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

    /**
     * Populates the hand of the player with cards from the deck and then removes them from the deck.
     * @param deck
     */
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

    /**
     * Converts the array of cards in the players hand to string.
     */

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

    /**
     * Randomly selects a card to be use by the player.
     */
    public void randomSelect() {
        Random r = new Random();
        int i = r.nextInt(hand.size());
        if(hand.size() != 0) {
            setPlayCard(hand.get(i));
        }
    }

    /**
     * Counts how many cards are above a number of 11 and of the trump suit then divide by 2 to give a bid
     * @param trump the trump card use in the round
     */
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

    /**
     * Random choose a bid
     */
    public  void randomBid(){
        Random r = new Random();
        int i = r.nextInt(hand.size());
        setBid(i);

    }
    /**
     * Creates a list of cards that are more likely to be used the the trump suit list
     * @param trump The card of which it suit these cards wont contain
     * @return an array of cards that are of the trumps suit
     */
    private LinkedList<Card> ListOfTrumpSuit(Card trump){
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

    /**
     * Creates a list of cards that are less likely to be used the the trump suit list
     * @param trump The card of which it suit these cards wont contain
     * @return an array of cards that are not of the trumps suit
     */
    private LinkedList<Card> ListOfOtherSuit(Card trump){
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

    /**
     * Sorts the hand of the player by the best number of the card
     * @param list the hand of the player
     * @return a sorted list of the hand of the player
     */
    private LinkedList<Card> sortList(LinkedList<Card> list){

            for(int j=0; j < list.size()-1; j++)
                if(list.get(j).getNumber() > list.get(j+1).getNumber()){
                Card temp = list.get(j);
                list.set(j,list.get(j+1));
                list.set(j+1,temp);
                    }

        return list;
    }

    /**
     * Selects the card by who has from the two list if the value is close to the bid.
     * @param trump the trump card for the round.
     */
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

