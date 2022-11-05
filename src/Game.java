import java.util.Iterator;
import java.util.Random;

/**
 * represents the Game: "High or Tie" as created by Bill Barry
 * @author Gary Larson
 * @version 5/7/2021
 */
public class Game {
    /** used to convert seconds to milliseconds */
    private static final int MS_FACTOR = 1000;
    /** amount of players playing the game */
    private final int totalPlayers;
    /** max number of allowable strikes this game */
    private final int maxStrikes;
    /** the delay between player turns (in seconds) */
    private final int turnDelayInMS;
    /** collection of players */
    private final CircularLinkedList<Player> players;
    /** generates dice rolls */
    private final Random rand;
    /** player with the highest score */
    private Player topPlayer;
    /** current high score */
    private int highScore;
    /** sound effects for the game */
    private final AudioClipManager clips;


    /**
     * constructs a game of "High or Tie"
     * @param totalPlayers total players in game
     * @param maxStrikes max number of allowable strikes this game
     * @param turnDelayInSecs the delay between player turns (in seconds)
     */
    public Game(int totalPlayers, int maxStrikes, int turnDelayInSecs) {
        if (totalPlayers < 2 || maxStrikes < 1 || turnDelayInSecs < 1) {
            throw new IllegalArgumentException("Must be more than one player, Max Strikes  of at least 1," +
                    "Delay must be 1 second or greater.");
        }
        players = new CircularLinkedList<>();
        this.totalPlayers = totalPlayers;
        this.maxStrikes = maxStrikes;
        turnDelayInMS = turnDelayInSecs * MS_FACTOR;
        highScore = 0;
        topPlayer = null;
        rand = new Random(1);
        clips = new AudioClipManager();
        addPlayers();

    }


    /**
     * starts and runs the game logic for "High or Tie"
     */
    public void play() {
        int roundCount = 0;
        //start of game
        Iterator<Player> iter = players.iterator();
        while(iter.hasNext()) {
            int pos = 1;
            roundCount++;
            //start of round
            printHeading(highScore, roundCount);
            while ( pos < players.size() + 1) {
                Player currPlayer = iter.next();
                System.out.printf("Current high = %d, ", highScore);

                if (currPlayer == topPlayer) {
                    System.out.printf("%s passes\n", currPlayer.getName());
                    clips.get(AudioClipManager.Fx.PASS).play();
                }
                else {
                    gameLogic(currPlayer);
                }
                pos++;
                try {
                    Thread.sleep(turnDelayInMS);
                }
                catch(InterruptedException e) {
                    //do nothing
                }
            }
            System.out.print("\n\n");
        }
        displayWinner(highScore);
    }


    /**
     * prints the heading for the given round
     * @param highScore flag for first round
     * @param roundCount tracks the number of rounds
     */
    private void printHeading(int highScore, int roundCount) {
        if (highScore == 0) {
            System.out.println("Start of game");
        }
        else {
            System.out.printf("New Round (%d)%n", roundCount);
        }
    }


    /**
     * adds player to the collection
     */
    private void addPlayers() {
        for (int i = 1; i < totalPlayers + 1; i++) {
            players.add(new Player("Player" + i));
        }
    }


    /**
     * the bulk of the game logic
     * @param currPlayer target player
     */
    private void gameLogic(Player currPlayer) {
        int currRoll = rand.nextInt(100) + 1;
        if (currRoll >= highScore) {
            topPlayer = currPlayer;
            highScore = currRoll;
            System.out.printf("%s rolled a %d\n", currPlayer.getName(), currRoll);
            clips.get(AudioClipManager.Fx.TURN).play();
        }
        else {
            currPlayer.addStrike();
            if(currPlayer.getStrikes() >= maxStrikes) {
                System.out.printf("%s rolled a %d, strike %d, out of the game!\n", currPlayer.getName(), currRoll,
                        currPlayer.getStrikes());
                clips.get(AudioClipManager.Fx.LOSS).play();
                players.remove(currPlayer);
            }
            else {
                System.out.printf("%s rolled a %d, strike %d\n", currPlayer.getName(), currRoll,
                        currPlayer.getStrikes());
               clips.get(AudioClipManager.Fx.STRIKE).play();
            }

        }
    }

    /**
     * displays the winner of the game
     * @param highScore winning score
     */
    private void displayWinner(int highScore) {
        System.out.printf("Winner is %s with a roll of %d!%n", players.get(0).getName(), highScore);
        clips.get(AudioClipManager.Fx.WIN).play();
    }
}



