package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();
    final char[] values = {'2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a'};
final int[] numbers = {2,3,4,5,6,7,8,9,10,11,12,13,14};
Boolean isShuffled;


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

    public void addWizard() {
        for (int i = 0; i < 4; i++) {
            Card wizard = new Card(Suit.non, 'w',15);
            deck.add(wizard);
        }
    }

    public void addJester(){
        for (int i = 0; i < 4; i++) {
            Card jester = new Card(Suit.non, 's',1);
            deck.add(jester);
        }
    }
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
 public void reset(){

      generateDeck();
      shuffle();
 }

public void delete(){
        for(int i=0; i <= deck.size(); i++){
            deck.remove(i);
        }
}

public boolean isShuffled(){
        return isShuffled;
}
}