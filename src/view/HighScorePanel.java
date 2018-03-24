/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

/**
 * Creates a Jpanel that shows the top scores.
 * @author Tanner Brown
 * @version 1 Dec 2018
 *
 */
public class HighScorePanel extends DisplayPanel {

    
    /**
     * Random id.
     */
    private static final long serialVersionUID = 3487737997669908539L;

    /**
     * Basic constructor.
     * @param theScale the scale of the panel.
     */
    protected HighScorePanel(final int theScale) {
        super(theScale, "High Score");
        //TODO: Location 2
        /*
         * Note to instructor if they are wondering why this is here...
         * I planned to use this and create a serializable object that
         * can represent the user's score and do all kinds of cool things
         * with it. unfortunately, time didn't allow for this, but I'm keeping
         * the panel so I can do that later on my own as a personal addition.
         */
        
    }

}


