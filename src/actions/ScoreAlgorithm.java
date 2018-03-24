/*
 * TCSS 305 - Tetris
 * Fall 2017
 */
package actions;

import java.util.HashMap;
import java.util.Map;


//add score = line combo * level
//add 4 points when an item is frozen
//Increment a level each 5 lines cleared

/**
 * This class contains the methods for determing score based on a specific algorithm.
 * @author Tanner Brown
 * @version 8 Dec 2017
 *
 */
public class ScoreAlgorithm {

    /** Default value for values that start at 0. */
    private static final int DEFAULT_ZERO = 0;
    /** Default value for values that start at 1. */
    private static final int DEFAULT_ONE = 1;
    /** Default value for values that start at 1. */
    private static final int DEFAULT_TWO = 2;
    /** Default value for values that start at 3. */
    private static final int DEFAULT_THREE = 3;
    /** Default value for values that start at 1. */
    private static final int DEFAULT_FOUR = 4;
    /** Default value for values that start at 5. */
    private static final int DEFAULT_FIVE = 5;
    /** Default value multiplier for a one line combo. */
    private static final int DEFAULT_ONE_COMBO = 40;
    /** Default value multiplier for a two line combo. */
    private static final int DEFAULT_TWO_COMBO = 100;
    /** Default value multiplier for a three line combo. */
    private static final int DEFAULT_THREE_COMBO = 300;
    /** Default value multiplier for a four line combo. */
    private static final int TETRIS = 1200;
    /** The players current level. */
    private int myLevel;
    /** The players next level. */
    private int myNextLevel;
    /** The user's score. */
    private int myTotalScore;
    /** The total number of lines cleared. */
    private int myClearedLines;
    /** The number of lines until the user reaches the next level. */
    private int myLinesToNextLevel;
    /** A map that determines score based on lines cleared at once. */
    private final Map<Integer, Integer> myComboMap;

    /**
     * Basic constructor.
     */
    public ScoreAlgorithm() {
        myComboMap = new HashMap<Integer, Integer>();
        build();
    }
    /**
     * Helper method builds default values.
     */
    private void build() {
        myComboMap.put(DEFAULT_ONE, DEFAULT_ONE_COMBO);
        myComboMap.put(DEFAULT_TWO, DEFAULT_TWO_COMBO);
        myComboMap.put(DEFAULT_THREE, DEFAULT_THREE_COMBO);
        myComboMap.put(DEFAULT_FOUR, TETRIS);
        myLevel = DEFAULT_ONE;
        myNextLevel = myLevel + DEFAULT_ONE;
        myTotalScore = DEFAULT_ZERO;
        myClearedLines = DEFAULT_ZERO;
        myLinesToNextLevel = DEFAULT_FIVE;

    }
    /**
     * Resets all values to default values.
     */
    public void resetAll() {
        myLevel = DEFAULT_ONE;
        myNextLevel = myLevel + DEFAULT_ONE;
        myTotalScore = DEFAULT_ZERO;
        myClearedLines = DEFAULT_ZERO;
        myLinesToNextLevel = DEFAULT_FIVE;

    }

    /**
     * When new lines are cleared, this method updates score and counter.
     * @param theClearedLines the number of new lines cleared.
     */
    public void addNewLines(final int theClearedLines) {
        calculateScore(theClearedLines); //Calculate score based off previously existing lines
        calculateLevels(theClearedLines);

    }

    /**
     * Calculates the current level as well as the amount of lines to be cleared
     * until the next level is reached.
     * 
     * @param theClearedLines the number of lines that have been cleared.
     */
    private void calculateLevels(final int theClearedLines) {

        for (int i = 0; i < theClearedLines; i++) {
            myLinesToNextLevel--;
            if (myLinesToNextLevel == DEFAULT_ZERO) {
                myLinesToNextLevel = DEFAULT_FIVE;
                myLevel++;
            }
        }

        myClearedLines = myClearedLines + theClearedLines; //Update lines cleared total
    }

    /**
     * Method calculates score based off current values.
     * @param theClearedLines the new number of lines cleared in latest move.
     */
    private void calculateScore(final int theClearedLines) {

        final int tempScore = myComboMap.get(theClearedLines) * myLevel;

        myTotalScore = myTotalScore + tempScore;



    }

    /**
     * Updates the score to reflect a block being frozen.
     */
    public void blockFrozen() {
        myTotalScore = myTotalScore + (DEFAULT_FIVE - DEFAULT_ONE);
    }

    /**
     * Getter for current level.
     * @return the current level.
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * Sets the game level.
     * @param theLevel the new level.
     */
    public void setLevel(final int theLevel) {
        myLevel = theLevel;
        myNextLevel = myLevel + DEFAULT_ONE;
    }

    /**
     * Get the next level.
     * @return the next level.
     */
    public int getNextLevel() {
        return myNextLevel;
    }

    /**
     * Get the total user score this game.
     * @return the score.
     */
    public int getTotalScore() {
        return myTotalScore;
    }

    /**
     * get the number of lines until the user levels up.
     * @return the number until next level up.
     */
    public int getLinesToNextLevel() {
        return myLinesToNextLevel;
    }

    /**
     * Get the number of lines cleared.
     * @return number of lines cleared.
     */
    public int getLinesCleared() {
        return myClearedLines;
    }

}
