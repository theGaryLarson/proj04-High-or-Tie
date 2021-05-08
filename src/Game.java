/**
 * represents the Game: "High or Tie" as created by Bill Barry
 * @author Gary Larson
 * @version 5/7/2021
 */
public class Game {
    /** amount of players playing the game */
    private int totalPlayers;
    /** max number of allowable strikes this game */
    private int maxStrikes;
    /** the delay between player turns (in seconds) */
    private int turnDelayInSecs;
    /** collection of players */
    private CircularLinkedList<Player> players;


    /**
     * constructs a game of "High or Tie"
     * @param totalPlayers total players in game
     * @param maxStrikes max number of allowable strikes this game
     * @param turnDelayInSecs the delay between player turns (in seconds)
     */
    public Game(int totalPlayers, int maxStrikes, int turnDelayInSecs) {
        if (totalPlayers < 1 || maxStrikes < 1 || turnDelayInSecs < 1) {
            throw new IllegalArgumentException("Must be more than one player, Max Strikes at least 1," +
                    "Delay must be 1 second or greater.");
        }
        players = new CircularLinkedList<>();
        this.totalPlayers = totalPlayers;
        this.maxStrikes = maxStrikes;
        this.turnDelayInSecs = turnDelayInSecs;

    }


    /**
     * starts and runs the game logic for "High or Tie"
     */
    public void play() {

    }
}
