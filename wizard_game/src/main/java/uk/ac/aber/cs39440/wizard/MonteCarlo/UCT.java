package main.java.uk.ac.aber.cs39440.wizard.MonteCarlo;

public class UCT {
    private static UCT ourInstance = new UCT();

    public static UCT getInstance() {
        return ourInstance;
    }

    private UCT() {
    }
}
