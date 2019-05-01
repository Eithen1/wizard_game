package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import main.java.uk.ac.aber. cs39440.wizard.core.Player;


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
     * Give a list of all the states meaning the play card the player can make next turn.
     * @return list of all states the game could have next
     */
   public List<GameState> getAllStates() {

       List<GameState> possibleStates = new ArrayList<>();

       for (int i = 0; i < players.getFirst().getHand().size(); i++) {
           GameState newState = new GameState(this);
           LinkedList<Player> p = newState.getPlayers();
           newState.setPlayers(new LinkedList<>(p));
           for (int j = 0; j < p.size(); j++) {
               ArrayList<Card> hand = p.get(j).getHand();

               if (ai.getPlayerID() == p.get(j).getPlayerID()) {
                   p.get(j).setPlayCard(hand.get(i));
                   p.get(j).getHand().remove(i);
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
