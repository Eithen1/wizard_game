package main.java.uk.ac.aber.cs39440.wizard.core;
/**
 * Card.java 1.0 2019/04/30
 *
 * Copyright (c) 2019 Aberystwyth University.
 * All rights reserved.
 *
 *
 * Main
 *
 * @author Eithen Howard
 * @version 1.0
 * @date 2019-04-30
 */

/**
 * To construct the cards to be used in the deck
 */
public class Card {
    private char value;
    private Suit suit;
    private int number;

    /**
     * Initial Constructor Card Class
     */
    public Card() {

}

    /**
     *  Create a Copy of the Card using the values of the inputted Card
     * @param c
     */
    public Card(Card c){
    this.suit = c.getSuit();
    this.value = c.getValue();
    this.number = c.getNumber();
    }

    /**
     * Constructor Class for Card
     * @param suit can be either heart,spade,diamond,club or non
     * @param value A value of 1-10 j,q,k, wizard and jester
     * @param number Gives a value for the card to be used in methods
     */
    public Card(Suit suit, char value, int number) {
        this.suit = suit;
        this.value = value;
        this.number = number;
    }

    /**
     * Gets the suit of Card
     * @return The suit of the Card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the Value of the Card
     * @return The value of the Card
     */
    public char getValue() {
        return value;
    }

    /**
     * Get the number of the Card
     * @return The Number of the Card
     */
    public int getNumber() {
        return number;
    }

    /**
     * A toString method that shows the value and suit of the card
     * @return value and suit in string form
     */
    public String toString(){
        if(suit == Suit.non){
           return value + "-";
        } else{
             return value + suit.toString();
        }

    }

}
