package main.java.uk.ac.aber.cs39440.wizard.core;


import java.util.LinkedList;


public class Game {

    private Deck deck;
    private final LinkedList<Player> players = new LinkedList<>();
    private Round r ;

    /**
     * Setup how many players are in the game and if there are AI or Human players
     */
    public void setupPlayers() {
        Player player1 = new Player();
        player1.setAI(false);
        players.add(player1);
        player1.setPlayerID(1);
        for (int i = 2; i < 4; i++) {
            Player computer = new Player();
            computer.setPlayerID(i);
            players.add(computer);
        }
    }

    /**
     * Sets ups the shuffled deck and starts the round with players and deck.
     */
    public void gameSetup() {
        deck = new Deck();
        deck.generateDeck();
        deck.shuffle();
        setupPlayers();
        r = new Round(deck, players);
    }

    /**
     * Repopulates the deck with cards that are then shuffles and a new round is started
     */
    public void reSetup() {
        deck.delete();
        deck.generateDeck();
        deck.shuffle();
        r = new Round(deck,players);
        r.roundSetup();
    }

    /**
     * Plays the game for 5 round and scores after each round.
     */
    public void playGame() {
        Rules rules = new Rules(players);
        r.playRound();
        rules.scoring();
        for (int i = 0; i < 3; i++) {
            i++;
            reSetup();
            r.playRound();
            rules.scoring();
        }
    }

    /**
     * The main class to setup, play the game and decide the winner.
     */
    public void play() {

        gameSetup();
        playGame();
        winner();
    }

    /**
     * Decides the winner of the game based on there score.
     */
    public void winner() {
        Player winner = new Player();
        for (int i = 0; i < players.size(); i++) {
            if (winner.getScore() < players.get(i).getScore()) {
                winner = players.get(i);
            }
        }
        if (!winner.isAI) {
            System.out.println("You Win!!!");
        } else {
            System.out.println("You Lose!!!");
        }
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public Round getR() {
        return r;
    }
}
