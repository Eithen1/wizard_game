package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import main.java.uk.ac.aber. cs39440.wizard.core.Player;
import main.java.uk.ac.aber.cs39440.wizard.core.Rules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameState {


    Deck cardsUsed = new Deck();
    LinkedList<Player> players;
    Player ai = new Player();
    int wins = 0;
    int visitCount;


public GameState(){
    players = new LinkedList<>();
}

public GameState(Deck deck, LinkedList<Player> players, Player ai){
    this.cardsUsed = deck;
    this.players = players;
    this.ai = ai;

}

public GameState(GameState state){
    this.players = new LinkedList<>(state.getPlayers());
    this.visitCount = state.getVisitCount();
    this.wins = state.getWins();
    this.ai = state.getAi();
}

    public Player getAi() {
        return ai;
    }

    public void setAi(Player ai) {
        this.ai = ai;
    }

    public Deck getCardsUsed() {
        return cardsUsed;
    }

    public void setCardsUsed(Deck cardsUsed) {
        this.cardsUsed = cardsUsed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public List<GameState> getAllStates(){
    List<GameState> possibleStates= new ArrayList<>();
    for(int i=0; i <players.size(); i++)
        if(players.get(i).getPlayCard() == null){
        players.get(i).randomSelect();
        }
        Rules r = new Rules(players);
    r.scoringTrick();
   if( r.getWinner() == ai){
       wins++;
   }
    return possibleStates;
    }

    public void incrementVisit(){
    this.visitCount++;
    }



    public void addScore(int wins){
    if(this.wins != Integer.MIN_VALUE){
        this.wins += wins;
    }
    }

}
