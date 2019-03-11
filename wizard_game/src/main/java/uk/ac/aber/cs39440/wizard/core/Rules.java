package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;

public class Rules extends Round{
    private LinkedList<Player> players;
    public Player winner;
    public  Card trump;

    @Override
    public void setTrump(Card trump) {
        this.trump = trump;
    }

    public Rules(LinkedList<Player> players, Card trump){
        this.players = players;
        winner = new Player();
        this.trump = trump;
    }

    public Rules(LinkedList<Player> players){
        this.players = players;
        winner = new Player();
        this.trump = new Card();
    }
    public void numberRule(Player p){
        if(winner.playCard.getNumber() < p.playCard.getNumber()){
            winner = p;
        }
    }

   public void suitRule(Player p){
            if(((p.getPlayCard().getSuit() == trump.getSuit()) && (winner.getPlayCard().getSuit() == trump.getSuit()) )|| (winner.getPlayCard().getSuit() == p.getPlayCard().getSuit())){
                 numberRule(p);
               }
else if(p.getPlayCard().getSuit() == trump.getSuit() && winner.getPlayCard().getSuit() != p.getPlayCard().getSuit()){
                winner = p;
            }

   }

   public void wizardRule(){
        for (int i=0; i<players.size(); i++){
            Player p = players.get(i);
            if((p.getPlayCard().getValue() == 'w' && winner.getPlayCard().getValue() != 'w') || winner.getPlayCard() == null){
                winner = p;
            }
            else {
                suitRule(p);
            }
        }
   }

   public void scoringTrick(){
        wizardRule();
        winner.tricksWon++;
        System.out.println(winner.getTricksWon());
        winner  = new Player();
   }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void scoring(){
        for (int i=0; i< players.size(); i++){
            Player p = players.get(i);
            p.setScore(10*p.getTricksWon());
            if(p.getTricksWon() < p.getBid() || p.getTricksWon() > p.getBid()){
                int diff = Math.abs(p.getTricksWon() - p.getBid());
                p.setScore(p.getScore() - (10*diff));
            }
            if(p.getTricksWon() == p.getBid()){
                p.setScore(p.getScore() + 20);
            }
        }
   }

}
