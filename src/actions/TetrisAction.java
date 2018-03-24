/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.TetrisGUI;

/**
 * This class sets the actions for all of the different menu buttons.
 * @author  Tanner Brown
 * @version 28 Nov 2017
 */
public class TetrisAction extends AbstractAction implements ChangeListener, ActionListener {

    /**
     * Randomly generated serial id.
     */
    private static final long serialVersionUID = -8019500315706192646L;
    /** Default value representing level 1. */
    private static final int DEFAULT_LEVEL = 1;
    /** Default value representing level 3. */
    private static final int EASY_LEVEL = 3;
    /** Default value representing level 5. */
    private static final int MEDIUM_LEVEL = 5;
    /** Default value representing level 7. */
    private static final int HARD_LEVEL = 7;
    /** Default value representing level 10. */
    private static final int MAX_LEVEL = 10;

    /** A gui object. */
    private TetrisGUI myGUI;

    /** A map of actions. */
    private Map<String, Runnable> myActionsMap;

    /**
     * Empty private constructor.
     */
    private TetrisAction() {
        super();
        //Empty constructor.
    }

    /**
     * Constructor that accepts an object as its parameter.
     * @param theObject the object that is being sent in to have an action performed on.
     */
    public TetrisAction(final Object theObject) {
        this();
        setActionsMap();
        setComponent(theObject);
    }

    /**
     * Sets the component type based off what is sent in.
     * @param theObject object that is to be instantiated
     */
    private void setComponent(final Object theObject) {
        if (theObject instanceof TetrisGUI) {
            myGUI = (TetrisGUI) theObject;            
        }
    }


    /**
     * Sets the commands associated with each button source.
     */
    private void setActionsMap() {
        myActionsMap = new HashMap<>();
        myActionsMap.put("New", () -> myGUI.startNewGame());
        myActionsMap.put("Lig", () -> myGUI.setActiveTheme("light"));
        myActionsMap.put("Dar", () -> myGUI.setActiveTheme("dark"));
        myActionsMap.put("Chr", () -> myGUI.setActiveTheme("christmas"));
        myActionsMap.put("End", () -> myGUI.gameOver());
        myActionsMap.put("Set", () -> myGUI.changeKeys());
        myActionsMap.put("Qui", () -> myGUI.quitGame());
        myActionsMap.put("Def", () -> myGUI.setLevel(DEFAULT_LEVEL));
        myActionsMap.put("Eas", () -> myGUI.setLevel(EASY_LEVEL));
        myActionsMap.put("Med", () -> myGUI.setLevel(MEDIUM_LEVEL));
        myActionsMap.put("Har", () -> myGUI.setLevel(HARD_LEVEL));
        myActionsMap.put("Imp", () -> myGUI.setLevel(MAX_LEVEL));


    }


    /**
     * Invoked when an action occurs.
     *
     * @param theEvent
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {

        final String command = theEvent.getActionCommand().substring(0, 3);

        //System.out.println(command); //For testing

        if (myActionsMap.containsKey(command)) {
            myActionsMap.get(command).run();
        }
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param theEvent a ChangeEvent object
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {

        final JSlider scaleSlider = (JSlider) theEvent.getSource();     
        myGUI.changeScale(scaleSlider.getValue());
    }
}
