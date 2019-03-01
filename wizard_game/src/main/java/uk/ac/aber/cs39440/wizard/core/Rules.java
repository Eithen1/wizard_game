package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;

public class Rules extends Round{
    private LinkedList<Player> players;
    public Player winner;


    public Rules(LinkedList<Player> players){
        this.players = players;
        winner = players.get(0);
    }
    public void numberRule(){
        for(int i = 0; i<players.size(); i++)
        if(winner.playCard.number < players.get(i).playCard.number){
            winner = players.get(i);
        }
    }

   public void suitRule(){
        for(int i=0; i<players.size(); i++){
            if((players.get(i).getPlayCard().suit == super.getTrump().suit && winner.getPlayCard().suit == super.getTrump().suit )|| winner.getPlayCard().suit == players.get(i).getPlayCard().suit){
                 numberRule();
               }
else if(players.get(i).getPlayCard().suit == super.getTrump().suit && winner.getPlayCard().suit != players.get(i).getPlayCard().suit){
                winner = players.get(i);
            }
        }
   }

   public void wizardRule(){
        for (int i=0; i<players.size(); i++){
            int n = 15;
            if(players.get(i).getPlayCard().number == n && winner.getPlayCard().number != n || winner == null){
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

   public void scoring(){
        for (int i=0; i< players.size(); i++){
            Player p = players.get(i);
            p.setScore(10*p.getTricksWon());
            if(p.tricksWon < p.bid || p.tricksWon > p.bid){
                int diff = Math.abs(p.getTricksWon() - p.bid);
                p.setScore(p.getScore() - (10*diff));
            }
            if(p.tricksWon == p.bid){
                p.setScore(p.getScore() + 20);
            }
        }
   }

}
