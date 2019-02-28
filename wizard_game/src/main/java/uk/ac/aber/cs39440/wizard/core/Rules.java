package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;

public class Rules extends Game{
    private LinkedList<Player> players;
Player winner;
    public Rules(LinkedList<Player> players){
        this.players = players;
        winner = new Player();
    }
    public void numberRule(){
        for(int i = 0; i<players.size(); i++)
        if(winner.playCard.number < players.get(i).playCard.number){
            winner = players.get(i);
        }
    }

   public void suitRule(){
        for(int i=0; i<players.size(); i++){
            if(players.get(i).getPlayCard().suit == trump.suit && winner.getPlayCard().suit == trump.suit || winner.getPlayCard().suit == players.get(i).getPlayCard().suit){
                 numberRule();
               }
else if(players.get(i).getPlayCard().suit == trump.suit && winner.getPlayCard().suit != players.get(i).getPlayCard().suit){
                winner = players.get(i);
            }
        }
   }

   public void wizardRule(){
        for (int i=0; i<players.size(); i++){
            char n = 'w';
            if(players.get(i).getPlayCard().value == n || winner.getPlayCard().value != n){
                winner = players.get(i);
            }
            else {
                suitRule();
            }
        }
   }


   public void scoringTrick(){
        wizardRule();
        winner.tricksWon++;
        System.out.println(winner.tricksWon);
   }
}
