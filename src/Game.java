import java.util.Random;

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
    private int turnDelayInMS;
    /** collection of players */
    private CircularLinkedList<Player> players;
    /** generates dice rolls */
    private Random rand = new Random();
    /** player with highest score */
    Player topPlayer = null;
    /** current high score */
    int highScore;
    /**  */
    Sound win = new Sound("sounds\\winner.wav");
    Sound strike = new Sound("sounds\\strike_out.wav");
    Sound eliminated = new Sound("sounds\\elimination.wav");

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
        turnDelayInMS = turnDelayInSecs * 1000;
        highScore = 0;
        addPlayers();

    }


    /**
     * starts and runs the game logic for "High or Tie"
     */
    public void play() {
        int roundCount = 0;
        //start of game
        while(players.size() > 1) {
            int pos = 1;
            roundCount++;
            //start of round
            printHeading(highScore, roundCount);
            while ( pos < players.size() + 1) {
                Player currPlayer = players.get(pos);
                System.out.print(String.format("Current high = %d, ", highScore));

                if (currPlayer == topPlayer) {
                    System.out.print(String.format("%s passes\n", currPlayer.getName()));
                }
                else {
                    gameLogic(currPlayer);
                }
                pos++;
                try {
                    Thread.sleep(turnDelayInMS);
                }
                catch(InterruptedException e) {
                    //todo: is it always better to handle ourselves or is this a case to use @throws?
                }

            }
            System.out.println();
            System.out.println();
        }
        displayWinner(highScore);
    }



    private void printHeading(int highScore, int roundCount) {
        if (highScore == 0) {
            System.out.println("Start of game");
        }
        else {
            System.out.println(String.format("New Round (%d)", roundCount));
        }
    }


    private void addPlayers() {
        for (int i = 1; i < totalPlayers + 1; i++) {
            players.add(new Player("Player" + i));
        }
    }


    private void gameLogic(Player currPlayer) {
        int currRoll = rand.nextInt(100) + 1;
        if (currRoll >= highScore) {
            topPlayer = currPlayer;
            this.highScore = currRoll;
            System.out.print(String.format("%s rolled a %d\n", currPlayer.getName(), currRoll));
        }
        else {
            currPlayer.addStrike();
            if(currPlayer.getStrikes() >= maxStrikes) {
                System.out.print(String.format("%s rolled a %d, strike %d, out of the game!\n", currPlayer.getName(), currRoll,
                        currPlayer.getStrikes()));
                eliminated.play();
                players.remove(currPlayer);
            }
            else {
                System.out.print(String.format("%s rolled a %d, strike %d\n", currPlayer.getName(), currRoll,
                        currPlayer.getStrikes()));
            }

        }
    }


    private void displayWinner(int highscore) {
        System.out.println(String.format("Winner is %s with a roll of %d!", players.get(1).getName(), highscore));
        win.play();
    }
}



