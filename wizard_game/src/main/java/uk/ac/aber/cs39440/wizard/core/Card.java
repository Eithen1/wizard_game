package main.java.uk.ac.aber.cs39440.wizard.core;

public class Card {
    char value;
    Suit suit;
    int number;

public Card(){

}
    public Card(Suit suit, char value, int number) {
        this.suit = suit;
        this.value = value;
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        if(suit == Suit.non){
           return value + "-";
        } else{
             return value + suit.toString();
        }

    }

}
