package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import main.java.uk.ac.aber. cs39440.wizard.core.Player;
import main.java.uk.ac.aber.cs39440.wizard.core.Suit;


import java.util.*;

public class GameState {


    private static Deck cardsUsed = new Deck();
  private   LinkedList<Player> players;
   private Player ai = new Player();
    private int wins = ai.getTricksWon();
   int simWins = 0;
    int visitCount;
  Card trump;

public GameState(){
    players = new LinkedList<>();
}

public GameState(Deck deck, LinkedList<Player> players, Player ai, Card trump){
    cardsUsed = deck;
    this.players  = new LinkedList<Player>();
    for(int i =0; i<players.size(); i++){
             this.players.add(new Player(players.get(i)));
    }
    this.ai = new Player(ai);
    this.trump = trump;
}


    /**
     * A class the copy the game state
     * @param state the new copied game state.
     */
    public GameState(GameState state){
    this.players = new LinkedList<Player>();
    for(int i =0; i<state.players.size(); i++){
        this.players.add(new Player(state.players.get(i)));
    }
    this.visitCount = state.getVisitCount();
    this.wins = state.getWins();
    this.ai = state.getAi();
    this.trump = state.getTrump();
}

    public Card getTrump() {
        return trump;
    }

    public int getSimWins() {
        return simWins;
    }

    public Player getAi() {
        return ai;
    }

    public int getWins() {
        return wins;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    /**
     * Method use the prune the search space by only using the cards that are valid to play as the childs.
     * @return Array of cards that are legal to play in the game.
     */
    public ArrayList<Card> validCards(){
        for(int i=0; i < players.size(); i++){
            if(players.get(i).getPlayerID() == ai.getPlayerID()){
               Player player =  players.get(i);
               if(player.getPlayerID() == players.get(0).getPlayerID()){
                   return player.getHand();
               }
               else{
                   if(containsCard(player) == true){
                       ArrayList<Card> valid = new ArrayList<>();
                       for(int j= player.getHand().size() ; j > 0; j--){
                           if(player.getHand().get(j-1).getSuit() == trump.getSuit() || player.getHand().get(j-1).getSuit() == players.get(0).getPlayCard().getSuit() || player.getHand().get(j-1).getSuit() == Suit.non){
                               valid.add(player.getHand().get(j-1));
                           }
                       }
                       return valid;
                   }
                   else {
                       return player.getHand();
                   }
               }
            }
        }
        return null;
}

    /**
     * Checks that the hand of the player has a card that is valid to use in the game
     * @param p the players who cards we are checking
     * @return a boolean showing if it does contain any valid cards.
     */
    private boolean containsCard(Player p){

        if(p != players.get(0)){
            int j=0;
            do {
                for (int i = 0; i < p.getHand().size(); i++) {
                    Card c = p.getHand().get(i);
                    if (c.getSuit() == trump.getSuit() || c.getSuit() == players.get(0).getPlayCard().getSuit() || c.getSuit() == Suit.non
                            ) {
                        return true;
                    }
                    j++;
                }
            }while (j < p.getHand().size()) ;
        }
        return false;
    }
    /**
     * Give a list of all the states meaning the play card the player can make next turn.
     * @return list of all states the game could have next
     */
   public List<GameState> getAllStates() {

       List<GameState> possibleStates = new ArrayList<>();
       ArrayList<Card> hand = new ArrayList<>(validCards());
       for (int i = 0; i < hand.size(); i++) {
           GameState newState = new GameState(this);
           LinkedList<Player> p = newState.getPlayers();
           newState.setPlayers(new LinkedList<>(p));
           for (int j = 0; j < p.size(); j++) {

               if (ai.getPlayerID() == p.get(j).getPlayerID()) {
                   p.get(j).setPlayCard(hand.get(i));
                   p.get(j).getHand().remove(hand.get(i));
                   possibleStates.add(newState);
               }
           }
       }


       return possibleStates;
   }


    public void incrementVisit(){
    this.visitCount++;
    }

    public void setAIAfterRound(){
    for(int i=0; i < players.size(); i++){
        if(ai.getPlayerID() == players.get(i).getPlayerID()){
            ai =players.get(i);
        }
    }
    }

    /**
     * Give a score to how well the simulation went by how far away from the bid it was.
     * @return int of score given
     */
    public int winsSim(){
    int i = ai.getPlayerID();
    setAIAfterRound();
    for(int j=0; j<players.size(); j++) {
        if(i == players.get(j).getPlayerID())
        if (players.get(j).getTricksWon() == players.get(j).getBid()) {
            return 5;
        } else if (players.get(j).getTricksWon() < players.get(j).getBid() + 2 && players.get(j).getTricksWon() > players.get(j).getBid()) {
            return 3;
        } else if (players.get(j).getTricksWon() > players.get(j).getBid() - 2 && players.get(j).getTricksWon() < players.get(j).getBid()) {
            return 2;
        } else {
            return 1;
        }
    }
    return Integer.parseInt(null);
    }

    /**
     * Add one more score to the wins
     * @param wins wins plus one
     */
    public void addScore(int wins){
    if(this.wins != Integer.MIN_VALUE){
        this.simWins += wins;
    }
    }


}
