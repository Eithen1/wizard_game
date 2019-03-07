package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;
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


public void ruleBasedSelect(){
    ArrayList<Card> priority = new ArrayList<>();
      priority.addAll( super.getHand());

}
public  void randomBid(){
        Random r = new Random();
        int i = r.nextInt(super.hand.size());
        super.setBid(i);

}

}
