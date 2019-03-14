package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class AI extends Player {


    public AI() {
        super();
    }

public void randomSelect() {
    Random r = new Random();
    int i = r.nextInt(super.hand.size());
    super.setPlayCard(super.getCard(i));
}

    /**
     * Created List of Cards that match the Suit of the Trump Card in the Players Hand
     */
    public LinkedList<Card> ListOfTrumpSuit(Card trump){
        LinkedList<Card> TrumpPriority = new LinkedList<Card>();
        TrumpPriority.addAll(super.getHand());
        for(int i=0; i < TrumpPriority.size(); i++){
            Card c= TrumpPriority.get(i);
            if(c.getSuit() != trump.getSuit()){
                TrumpPriority.remove(i);
            }
        }
        return sortList(TrumpPriority);
    }

    public LinkedList<Card> ListOfOtherSuit(Card trump){
        LinkedList<Card> OtherPriority = new LinkedList<Card>();
        OtherPriority.addAll(super.getHand());
        for(int i=0; i < OtherPriority.size(); i++){
            Card c= OtherPriority.get(i);
            if(c.getSuit() == trump.getSuit()){
                OtherPriority.remove(i);
            }
        }

        return sortList(OtherPriority);
    }
public LinkedList<Card> sortList(LinkedList<Card> list){
        for(int i=0; i<list.size();i++){

            for(int j=0; j < list.size()-i; j++)
                if(list.get(j).getValue() > list.get(j+1).getValue()){
                swap(list.get(j),list.get(j+1));}
        }
        return list;
}

public void swap(Card c, Card d){
        Card temp = c;
        c= d;
        d= temp;
}
    public void ruleBasedSelect(Card trump){

        LinkedList<Card> OtherPriority = ListOfOtherSuit(trump);
        LinkedList<Card> TrumpPriority =ListOfTrumpSuit(trump);

      if(tricksWon != bid){
          super.setPlayCard(TrumpPriority.getFirst());
      }
    if(tricksWon == bid){
super.setPlayCard(OtherPriority.getLast());
    }
}



public  void randomBid(){
        Random r = new Random();
        int i = r.nextInt(super.hand.size());
        super.setBid(i);

}

}
