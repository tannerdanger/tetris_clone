/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

import actions.ScoreAlgorithm;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * creates the panel that the score will be displayed on.
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
public class ScorePanel extends DisplayPanel {

    /**
     * Random serial id.
     */
    private static final long serialVersionUID = -72595963340131651L;
    /** The default panel layout. */
    private static final GridLayout DEFAULT_LAYOUT = new GridLayout(4, 1);
    /** String that is used for the score label. */
    private static final String SCORE_STRING = "SCORE: ";
    /** String that is used for the level label. */
    private static final String LEVEL_STRING = "LEVEL: ";
    /** String that is used for the next level label. */
    private static final String NEXT_LVL_STRING = "NEXT: ";
    /** String that is used for the cleared lines label. */
    private static final String CLEARED_LINES_STRING = "LINES: ";


    // private Map<JLabel, JLabel> myLabelMap;

    /** A map that holds specific labels.  */
    private final Map<String, JLabel> myLabelMap;

    /** Score algorithm class that determines the score. */
    private final ScoreAlgorithm myScore;


    /**
     * Constructs the score panel.
     * @param theScale of the panel.
     */
    protected ScorePanel(final int theScale) {
        super(theScale, "Score");
        myScore = new ScoreAlgorithm();
        myLabelMap = new HashMap<>();
        setLayout(DEFAULT_LAYOUT);
        resetScore();
        addLabels();
    }


    /**
     * Resets the values of the ScoreAlgorithm object to default.
     */
    private void resetScore() {
        myScore.resetAll();
    }



    /**
     * Creates the labels used to display scores.
     */
    private void addLabels() {


        myLabelMap.clear();
        removeAll();


        //Score labels     
        myLabelMap.put(SCORE_STRING, 
                       new JLabel(String.valueOf(myScore.getTotalScore()),
                                  SwingConstants.CENTER));

        //Lines cleared labels
        myLabelMap.put(CLEARED_LINES_STRING, 
                       new JLabel(String.valueOf(myScore.getLinesCleared()), 
                                  SwingConstants.CENTER));

        //Level Labels
        myLabelMap.put(LEVEL_STRING, 
                       new JLabel(String.valueOf(myScore.getLevel()), SwingConstants.CENTER));

        ////Lines to next level 
        myLabelMap.put(NEXT_LVL_STRING, 
                       new JLabel(String.valueOf(myScore.getLinesToNextLevel()), 
                                  SwingConstants.CENTER));

        for (final String key : myLabelMap.keySet()) {
            add(new JLabel(key, SwingConstants.LEFT));
            add(myLabelMap.get(key));
        }


        revalidate();
        repaint();
    }

    /**
     * This method resets the game and begins a new one.
     */
    protected void startNewGame() {
        resetScore();
        updateLabels();
    }

    @Override
    public void updateTheme(final String theNewTheme) {
        super.updateTheme(theNewTheme);
        setLabelColor();
    }

    /**
     * Sets the color of the labels based off the active theme.
     */
    private void setLabelColor() {
        final Component[] componentList = getComponents();
        for (final Component c : componentList) {
            c.setForeground(getSecondaryThemeColor());

        }
    }

    /**
     * Updates score and labels when a block becomes frozen.
     */
    public void blockFrozen() {
        myScore.blockFrozen();
        updateLabels();
    }

    /**
     * Updates labels when there is a change to their values.
     */
    private void updateLabels() {

        myLabelMap.get(SCORE_STRING).setText(String.valueOf(myScore.getTotalScore()));

        myLabelMap.get(LEVEL_STRING).setText(String.valueOf(myScore.getLevel()));

        myLabelMap.get
        (CLEARED_LINES_STRING).setText(String.valueOf(myScore.getLinesCleared()));

        myLabelMap.get(NEXT_LVL_STRING).setText(String.valueOf(myScore.getLinesToNextLevel()));



    }
    /**
     * Adds new lines when lines are cleared.
     * @param theClearedLines number of lines cleared in last move.
     */
    public void newLines(final int theClearedLines) {
        myScore.addNewLines(theClearedLines);

    }

    /**
     * Gets the current game level.
     * @return the level.
     */
    public int getLevel() {
        return myScore.getLevel();
    }

    /**
     * Sets the current game level.
     * @param theLevel the new level.
     */
    public void setLevel(final int theLevel) {
        myScore.setLevel(theLevel);
    }

}
