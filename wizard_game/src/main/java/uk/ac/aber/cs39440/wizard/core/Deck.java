package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    ArrayList<Card> deck = new ArrayList<>();
    char values[] = {'2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a'};


    public ArrayList<Card> getDeck() {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Card getCard(int i){
        return deck.get(i);
    }

    public void removeCard(int i){
        deck.remove(i);
    }
    public int getSize(){
        return deck.size();
    }

    public void addwizard() {
        for (int i = 0; i < 4; i++) {
            Card wizard = new Card(Suit.non, 'w');
            deck.add(wizard);
        }
    }

    public void addJester(){
        for (int i = 0; i < 4; i++) {
            Card jester = new Card(Suit.non, 'S');
            deck.add(jester);
        }
    }
    public void generateDeck() {
        //add the normal 52 cards to the deck
        for (int i = 0; i < values.length; i++) {
            Card spades = new Card(Suit.spades, values[i]);
            deck.add(spades);
            Card diamonds = new Card(Suit.diamonds, values[i]);
            deck.add(diamonds);
            Card clubs = new Card(Suit.clubs, values[i]);
            deck.add(clubs);
            Card hearts = new Card(Suit.hearts, values[i]);
            deck.add(hearts);
        }
addJester();
        addwizard();

    }

    public void shuffle(){
        Random r = new Random();
        for(int i = deck.size()-1; i>0;i--){
            int jposition = r.nextInt(i+1);
            Card j= deck.get(jposition);
             Card temp = deck.get(i);
            deck.set(i,j);
            deck.set(jposition,temp);
        }
    }
 public void printDeck(){
        for (int i = 0; i < deck.size(); i++){
          System.out.println(deck.get(i).toString());
        }
        shuffle();
     for (int i = 0; i < deck.size(); i++){
         System.out.println(deck.get(i).toString());
     }
 }


}