/**
 * represents a player
 * @author Gary Larson
 * @version 5/7/2021
 */
public class Player {
    /** name of the player */
    private final String name;
    /** total strikes during game */
    private int totalStrikes;


    /**
     * constructs a player
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
    }


    /**
     * the player's name
     * @return the player's name
     */
    public String getName() {
        return name;
    }


    /**
     * increase player's strike count by one.
     */
    public void addStrike() {
        totalStrikes++;
    }


    /**
     * the total strikes of player
     * @return total strikes
     */
    public int getStrikes() {
        return totalStrikes;
    }

}
