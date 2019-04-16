package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;

public class Rules{
    private final LinkedList<Player> players;
    private Player winner;
    private final Card trump;

    public Rules(LinkedList<Player> players, Card trump){
        this.players =players;
        winner = new Player();
        this.trump = trump;
    }

    public Rules(LinkedList<Player> players){
        this.players = players;
        winner = new Player();
        this.trump = new Card();
    }
    private void numberRule(Player p){
        if(winner.playCard.getNumber() < p.playCard.getNumber()){
            setWinner(p);
        }
    }

   private void suitRule(Player p){
            if(((p.getPlayCard().getSuit() == trump.getSuit()) && (winner.getPlayCard().getSuit() == trump.getSuit()) )|| (winner.getPlayCard().getSuit() == p.getPlayCard().getSuit())){
                 numberRule(p);
               }
else if(p.getPlayCard().getSuit() == trump.getSuit() && winner.getPlayCard().getSuit() != p.getPlayCard().getSuit()){
                setWinner(p);
            }

   }

   public void wizardRule(){
        for (int i=0; i<players.size(); i++){
            Player p = players.get(i);
            if(winner.getPlayCard() != null){
                if ((p.getPlayCard().getNumber() == 15 && winner.getPlayCard().getNumber() != 15) || winner.getPlayCard().getNumber() == 1) {
                    setWinner(p);
                } else if(winner.getPlayCard().getNumber() != 15) {
                    suitRule(p);
                }
            }
            else{
                setWinner(p);
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

    private void setWinner(Player winner) {
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
