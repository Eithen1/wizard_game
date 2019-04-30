package main.java.uk.ac.aber.cs39440.wizard.core;
/**
 * Deck.java 1.0 2018/05/02
 *
 * Copyright (c) 2018 Aberystwyth University.
 * All rights reserved.
 *
 *
 * Deck
 *
 * @author Eithen Howard
 * @version 1.0
 * @date 2019-04-30
 */

/**
 * Deck class, constructs the 60 card deck that is used in the game.
 */
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();
    final char[] values = {'2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a'};
final int[] numbers = {2,3,4,5,6,7,8,9,10,11,12,13,14};
Boolean isShuffled;


    public ArrayList<Card> getDeck()
    {
        return deck;
    }


    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Gets the Cards from the deck and returns it.
     * @param i the postion of the card in the deck.
     * @return A card from the deck
     */
    public Card getCard(int i){
        return deck.get(i);
    }

    /**
     * Removes a card from the deck based on it position in the array.
     * @param i The card to be removed from the deck
     */
    public void removeCard(int i){
        deck.remove(i);
    }

    /**
     * Returns the amount of cards in the deck.
     * @return The size of the deck.
     */
    public int getSize(){
        return deck.size();
    }

    /**
     * creates the 4 wizard cards which are then added to the deck.
     */
    public void addWizard() {
        for (int i = 0; i < 4; i++) {
            Card wizard = new Card(Suit.non, 'w',15);
            deck.add(wizard);
        }
    }

    /**
     * Creates the 4 jester cards which are then added to the deck.
     */
    public void addJester(){
        for (int i = 0; i < 4; i++) {
            Card jester = new Card(Suit.non, 's',1);
            deck.add(jester);
        }
    }

    /**
     * Generates a normal 52 card deck and then adds four wizards and four jesters.
     */
    public void generateDeck() {
        //add the normal 52 cards to the deck
        for (int i = 0; i < numbers.length; i++) {
            Card spades = new Card(Suit.spades, values[i],numbers[i]);
            deck.add(spades);
            Card diamonds = new Card(Suit.diamonds, values[i],numbers[i]);
            deck.add(diamonds);
            Card clubs = new Card(Suit.clubs, values[i],numbers[i]);
            deck.add(clubs);
            Card hearts = new Card(Suit.hearts, values[i],numbers[i]);
            deck.add(hearts);
        }
addJester();
        addWizard();
isShuffled = false;
    }

    /**
     * To shuffle the deck of cards by changing the positions of the cards within the array
     * and swapping there randomly.
     */
    public void shuffle(){
        Random r = new Random();
        for(int i = deck.size()-1; i>0;i--){
            int jposition = r.nextInt(i+1);
            Card j= deck.get(jposition);
             Card temp = deck.get(i);
            deck.set(i,j);
            deck.set(jposition,temp);
        }
        isShuffled =true;
    }

    /**
     * Class used to manually check if all of the cards were generated and shuffled by visually looking if all the cards were there.
     */
    public void printDeck(){
        for (int i = 0; i < deck.size(); i++){
          System.out.println(deck.get(i).toString());
        }
        shuffle();
     for (int i = 0; i < deck.size(); i++){
         System.out.println(deck.get(i).toString());
     }
 }

    /**
     * Removes the cards from the deck until there is none left in the card array
     */
    public void delete(){
     do{
         deck.remove(0);
     }while(deck.size() > 0);
}

    /**
     * Checks to see if the deck is shuffled.
     * @return the isShuffled variable of true and false
     */
    public boolean isShuffled(){
        return isShuffled;
}
}