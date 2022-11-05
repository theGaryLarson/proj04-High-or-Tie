/**
 * main class to run the game, High or Tie
 */
public class Main {

    /**
     * entry point into the program
     * @param args optional command line arguments
     */
    public static void main(String[] args) {
        Game hot = new Game(4,4,1);
        hot.play();
    }
}
