/**
 * represents a player
 * @author Gary Larson
 * @version 5/7/2021
 */
public class Player {
    /** name of the player */
    private String name;
    /** total strikes during game */
    private int totalStrikes;

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
