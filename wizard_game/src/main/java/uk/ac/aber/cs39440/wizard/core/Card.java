package main.java.uk.ac.aber.cs39440.wizard.core;

public class Card {
    private char value;
    private Suit suit;
    private int number;

public Card() {

}
public Card(Card c){
    this.suit = c.getSuit();
    this.value = c.getValue();
    this.number = c.getNumber();
    }
public Card(Suit suit, char value, int number) {
        this.suit = suit;
        this.value = value;
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public char getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    public String toString(){
        if(suit == Suit.non){
           return value + "-";
        } else{
             return value + suit.toString();
        }

    }

}
