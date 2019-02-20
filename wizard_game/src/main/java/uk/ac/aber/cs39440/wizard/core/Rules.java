package main.java.uk.ac.aber.cs39440.wizard.core;

import java.util.ArrayList;

public class Rules{
    private ArrayList<Player> players = new ArrayList<>();

    public Rules(ArrayList<Player> players){
        this.players = players;
    }
    public void valueRule(){
       Player winner =players.get(0);
        for(int i = 0; i<players.size(); i++)
        if(winner.playCard.value < players.get(i).playCard.value){
            winner = players.get(i);
        }
    }

}
