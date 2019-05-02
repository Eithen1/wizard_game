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

    /**
     * Finds the player whose play card has the largest number
     * @param p the player to compare to the winner
     */
    private void numberRule(Player p){
        if(winner.playCard.getNumber() < p.playCard.getNumber()){
            setWinner(p);
        }
    }

    /**
     * Finds out if the player match the trump or dealers suit and whose is better if the same then move onto the number rule
     * @param p the player to compare to the winner
     */
   private void suitRule(Player p){
            if(((p.getPlayCard().getSuit() == trump.getSuit()) && (winner.getPlayCard().getSuit() == trump.getSuit()) )|| (winner.getPlayCard().getSuit() == p.getPlayCard().getSuit())){
                 numberRule(p);
               }
if(p.getPlayCard().getSuit() == trump.getSuit() && winner.getPlayCard().getSuit() != p.getPlayCard().getSuit()){
                setWinner(p);
            }

   }

    /**
     * Checks to see if the player has a wizard card and if not compares to the winner if they dont have a wizard
     */
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

    /**
     * Gives the winner of the rules one more point towards their trick won
     */
   public void scoringTrick(){
        wizardRule();
        winner.tricksWon++;
        winner  = new Player();
   }

    public Player getWinner() {
        return winner;
    }

    private void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Gives the player a score based of how many trick that have won and what their bid was.
     */
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
