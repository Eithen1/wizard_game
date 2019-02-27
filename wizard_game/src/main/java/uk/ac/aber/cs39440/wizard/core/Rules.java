package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;

public class Rules extends Game{
    private LinkedList<Player> players = new LinkedList<>();
Player winner
    public Rules(LinkedList<Player> players){
        this.players = players;
    }
    public void numberRule(){
        winner =players.get(0);
        for(int i = 0; i<players.size(); i++)
        if(winner.playCard.number < players.get(i).playCard.number){
            winner = players.get(i);
        }
    }

   public void suitRule(){
        for(int i=0; i<players.size(); i++){
            if(players.get(i).getPlayCard().getSuit() != players.get(0).getPlayCard().getSuit()) {
            if(players.get(i).getPlayCard().suit == trump.suit){
                 numberRule();
               }

            }
        }
   }

}
