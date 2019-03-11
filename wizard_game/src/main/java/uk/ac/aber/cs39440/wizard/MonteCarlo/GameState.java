package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

import main.java.uk.ac.aber.cs39440.wizard.core.Card;
import main.java.uk.ac.aber.cs39440.wizard.core.Deck;
import main.java.uk.ac.aber. cs39440.wizard.core.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {


    Deck cardsUsed = new Deck();
    ArrayList<Player> players;
    Player ai = new Player();
    Card trump = new Card();
    int wins = 0;
    int visitCount;


public GameState(){
    players = new ArrayList<>();
}

public GameState(Deck deck, ArrayList<Player> players, Card trump, Player ai){
    this.cardsUsed = deck;
    this.players = players;
    this.trump = trump;
    this.ai = ai;

}

public GameState(GameState state){
    this.players = new ArrayList<>(state.getPlayers());
    this.trump = state.getTrump();
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

    public Card getTrump() {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<GameState> getAllStates(){
    List<GameState> possibleStates= new ArrayList<>();
    for(int i=0; i <players.size(); i++)
        if(players.get(i).getPlayCard() == null){
        players.get(i).randomSelect();
        }
    return possibleStates;
    }

    public void incrementVisit(){
    this.visitCount++;
    }

    public void randomPlay()

    {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).randomSelect();
        }
    }


    public void addScore(int wins){
    if(this.wins != Integer.MIN_VALUE){
        this.wins += wins;
    }
    }

}
