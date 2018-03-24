/*
 * TCSS 305 - Tetris
 * Fall 2017
 */
package view;

import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 * An object that associates values and commands with a keypress.
 * @author Tanner Brown
 * @version 8 Dec 2017
 *
 */
public class KeyControl {

    /** String used for labels. */
    private static final String DOTS_AND_A_SPACE = ":  ";
    /** This object's came. */
    private final String myName;
    /** The key that is allowed to be changed. */
    private Integer myChangableKey;
    /** The key that is default and unchangeable. */
    private final Integer myDefaultKey;
    /** The lable to display on the left side of the controls panel. */
    private final JLabel myLeftLabel;
    /** The label to display on the right side of the controls panel. */
    private final JLabel myRightLabel;
    /** Returns true if only one key is used for this command. */
    private final boolean mySingleKey;


    /**
     * Constructs objects that have multiple key controls associated with the same command. 
     * @param theName name of the command.
     * @param theChangableKey the key that is customizable.
     * @param theDefaultKey the key that is not customizable.
     */
    public KeyControl(final String theName, final Integer theChangableKey, 
                      final Integer theDefaultKey) {
        myName = theName;
        myChangableKey = theChangableKey;
        myDefaultKey = theDefaultKey;
        //PMD Acknowledged. Ignored for design reasons. 
        myLeftLabel = new JLabel(theName.toUpperCase() + DOTS_AND_A_SPACE); 
        myRightLabel = new JLabel();
        mySingleKey = false;
        setText();

    }
    /**
     * Constructs objects that have only one key controls associated with the same command. 
     * @param theName name of the command.
     * @param theChangableKey the key that is customizable.
     */
    public KeyControl(final String theName, final Integer theChangableKey) {
        myName = theName;
        myChangableKey = theChangableKey;
        myDefaultKey = theChangableKey;
        mySingleKey = true;
        //PMD Acknowledged. Ignored for design reasons. 
        myLeftLabel = new JLabel(theName.toUpperCase() + DOTS_AND_A_SPACE);
        myRightLabel = new JLabel();
        setText();
    }
    /**
     * Get the key that is changeable.
     * @return the changeable key.
     */
    public Integer getMyChangeableKey() {
        return myChangableKey;
    }

    /**
     * Set the changable key.
     * @param theNewKey new key value for the command.
     */
    public void setMyChangableKey(final Integer theNewKey) {
        myChangableKey = theNewKey;
        setText();
    }

    /**
     * Returns the name of the command.
     * @return the name.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Get the default, unchangable key.
     * @return the default key.
     */
    public Integer getMyDefaultKey() {
        return myDefaultKey;
    }

    /**
     * Get the left label that has the command's name.
     * @return the label.
     */
    public JLabel getLeftLabel() {
        setText();
        return myLeftLabel;
    }

    /**
     * Get the right label that has the key associated with the command.
     * @return the label.
     */
    public JLabel getRightLabel() {
        setText();
        return myRightLabel;
    }

    /**
     * Sets the text of the labels based on the values.
     */
    private void setText() {
        if (mySingleKey) {
            myRightLabel.setText(KeyEvent.getKeyText(myDefaultKey));
        } else {

            myRightLabel.setText(KeyEvent.getKeyText(myChangableKey) 
                                 + " / " + KeyEvent.getKeyText(myDefaultKey));
        }

    }

}
