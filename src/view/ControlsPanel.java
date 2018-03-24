/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;


import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Board;

/**
 * This class creates a JPanel that displays the current controls and defines their behavior. 
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
public class ControlsPanel extends DisplayPanel {


    /**
     * Random serial ID.
     */
    private static final long serialVersionUID = 2447054469232012818L;

    /** Default dimension for the panel. */
    private static final GridLayout DEFAULT_LAYOUT = new GridLayout(6, 1);
    /** Default string for left commands. */
    private static final String LEFT = "left";
    /** Default string for right commands. */
    private static final String RIGHT = "right";
    /** Default string for rotate commands. */
    private static final String ROTATE = "rotate";
    /** Default string for drop commands. */
    private static final String DROP = "drop";
    /** Default string for down commands. */
    private static final String DOWN = "down";
    /** Default string for pause commands. */
    private static final String PAUSE = "pause";

    /** The game board object. */
    private final Board myGameBoard;
    /** An object representing the move left command. */
    private KeyControl myLeft;
    /** An object representing the move right command. */
    private KeyControl myRight;
    /** An object representing the rotate command. */
    private KeyControl myRotate;
    /** An object representing the drop command. */
    private KeyControl myDrop;
    /** An object representing the move down command. */
    private KeyControl myDown;
    /** An object representing the pause command. */
    private KeyControl myPause;

    /** Hashmap that contains each of the movement key objects. */
    private final Map<String, KeyControl> myKeyObjectMap;

    /** A Tetris GUI object. */
    private final TetrisGUI myGui;

    /** Map that runs a method based off which key is pressed. */
    private Map<Integer, Runnable> myCommandMap;



    /**
     * Constructor that builds the control panel.
     * @param theScale the scale of the components.
     * @param theBoard Board object being passed in.
     * @param theGUI the TetrisGUI object being passed in
     */
    protected ControlsPanel(final int theScale, final Board theBoard, final TetrisGUI theGUI) {

        super(theScale, "Controls");
        myKeyObjectMap = new HashMap<>();
        myGui = theGUI;
        myGameBoard = theBoard;
        setDefaultKeys();        
        setLabels();
        setLayout(DEFAULT_LAYOUT);

    }

    @Override
    public void updateTheme(final String theNewTheme) {
        super.updateTheme(theNewTheme);
        setLabelColor();
    }
    
    /**
     * Method that updates the color labels when the theme changes.
     */
    private void setLabelColor() {
        final Component[] componentList = getComponents();
        for (final Component c : componentList) {
            c.setForeground(getSecondaryThemeColor());
        }
    }

    /**
     * Sets the labels displaying the game controls to default values.
     */
    private void setLabels() {

        for (final String key : myKeyObjectMap.keySet()) {
            JLabel tempLabel = (JLabel) myKeyObjectMap.get(key).getLeftLabel();
            tempLabel.setHorizontalTextPosition(JLabel.RIGHT);
            tempLabel.setForeground(getSecondaryThemeColor());
            add(tempLabel);
            tempLabel = (JLabel) myKeyObjectMap.get(key).getRightLabel();
            tempLabel.setHorizontalTextPosition(JLabel.CENTER);
            tempLabel.setForeground(getSecondaryThemeColor());
            add(tempLabel);
        }



    }

    /**
     * Updates the labels displaying the game controls.
     */
    private void updateLabels() {

        final Component[] componentList = getComponents();
        for (final Component c : componentList) {

            remove(c);

        }
        for (final String key : myKeyObjectMap.keySet()) {
            JLabel tempLabel = (JLabel) myKeyObjectMap.get(key).getLeftLabel();
            tempLabel.setHorizontalTextPosition(JLabel.RIGHT);
            tempLabel.setForeground(getSecondaryThemeColor());
            add(tempLabel);
            tempLabel = (JLabel) myKeyObjectMap.get(key).getRightLabel();
            tempLabel.setHorizontalTextPosition(JLabel.CENTER);
            tempLabel.setForeground(getSecondaryThemeColor());
            add(tempLabel);
        }
    }


    /**
     * Initializes the hashmap that contains the key commands and sets default values.
     */
    private void setDefaultKeys() {

        myLeft = new KeyControl(LEFT, KeyEvent.VK_A, KeyEvent.VK_LEFT);
        myRight = new KeyControl(RIGHT, KeyEvent.VK_D, KeyEvent.VK_RIGHT);
        myRotate = new KeyControl(ROTATE, KeyEvent.VK_W, KeyEvent.VK_UP);
        myDrop = new KeyControl(DROP, KeyEvent.VK_SPACE);
        myDown = new KeyControl(DOWN, KeyEvent.VK_S, KeyEvent.VK_DOWN);
        myPause = new KeyControl(PAUSE, KeyEvent.VK_P);

        myKeyObjectMap.put(myLeft.getMyName(), myLeft);
        myKeyObjectMap.put(myRight.getMyName(), myRight);
        myKeyObjectMap.put(myRotate.getMyName(), myRotate);
        myKeyObjectMap.put(myDrop.getMyName(), myDrop);
        myKeyObjectMap.put(myDown.getMyName(), myDown);
        myKeyObjectMap.put(myPause.getMyName(), myPause);

        //Figured this out BEFORE teacher example....
        myCommandMap = new HashMap<>();


        //left
        myCommandMap.put(myLeft.getMyDefaultKey(), () -> myGameBoard.left()); 
        //left arrow KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        // myCommandMap.put(KeyEvent.VK_LEFT, () -> myGameBoard.left());
        myCommandMap.put(myLeft.getMyChangeableKey(), () -> myGameBoard.left());
        //right
        myCommandMap.put(myRight.getMyChangeableKey(), () -> myGameBoard.right()); //d keycode:
        //right arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myRight.getMyDefaultKey(), () -> myGameBoard.right()); 

        //rotate
        myCommandMap.put(myRotate.getMyChangeableKey(), () -> myGameBoard.rotateCW());
        //UP arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myRotate.getMyDefaultKey(), () -> myGameBoard.rotateCW());
        //  myCommandMap.put(KeyEvent.VK_E, () -> myGameBoard.rotateCCW()); 

        //down
        myCommandMap.put(myDown.getMyDefaultKey(), () -> myGameBoard.down()); //s keycode:
        //down arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myDown.getMyChangeableKey() , () -> myGameBoard.down()); 

        //drop
        myCommandMap.put(myDrop.getMyDefaultKey(), () -> myGameBoard.drop()); 

        //pause
        myCommandMap.put(myPause.getMyChangeableKey(), () -> myGui.pause());
    }

    /**
     * Method that attaches key listeners to components.
     * @param theComponent to add a listener to.
     */
    
    protected void addKeyListeners(final JFrame theComponent) {

        theComponent.addKeyListener(new KeyControls());
    }

    /**
     * Method that allows user to change default control keys.
     */
    public void changeKeys() {
        final Object[] possibilities = {LEFT, RIGHT, ROTATE, DROP, DOWN};
        final String keyTitle = "Change keys";
        boolean loop = true;
        String newKey = null;
        String oldKey = null;
        int newKeyInt = 0;
        //int oldKeyInt = 0;

        while (loop) {
            oldKey = (String) 
                            JOptionPane.showInputDialog
                            (null, "What control would you like to\n"
                                            + "\"change the key of?\"",
                                            keyTitle,
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            possibilities, null
                                            );

            if (null == oldKey) {
               
                loop = false;
                break;
            }

            newKey = (String) 
                            JOptionPane.showInputDialog
                            (null,  "What would key would you like\n"
                                            + "\"to set to the  '"
                                            + oldKey + "'  control?\"",
                                            keyTitle,
                                            JOptionPane.PLAIN_MESSAGE,
                                            null, null, null
                                            );

            if (null == newKey) {
                
                loop = false;
                break;
            }

            newKeyInt = KeyEvent.getExtendedKeyCodeForChar(newKey.charAt(0));
            if (myKeyObjectMap.keySet().contains(oldKey)) {
                myKeyObjectMap.get(oldKey).setMyChangableKey(newKeyInt);
                
                editKeys();
                setLabels();
                updateLabels();
                loop = false;
                
            }

        }




    }


    /**
     * Method that allows user to change keys.
     * @param theOldKey the old keycode that will be changed.
     * @param theNewKey the new keycode that will be used.
     */
    protected void editKey(final int theOldKey, final int theNewKey) {
        final Runnable tempRunnable = myCommandMap.get(theOldKey);
        myCommandMap.get(theOldKey);
        myCommandMap.remove(theOldKey);
        myCommandMap.put(theNewKey, tempRunnable);
        setLabels();
    }
    /**
     * Method that changes the mapped keys.
     */
    protected void editKeys() {
        myCommandMap.clear();
        //left
        myCommandMap.put(myLeft.getMyDefaultKey(), () -> myGameBoard.left()); 
        //left arrow KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        // myCommandMap.put(KeyEvent.VK_LEFT, () -> myGameBoard.left());
        myCommandMap.put(myLeft.getMyChangeableKey(), () -> myGameBoard.left());
        //right
        myCommandMap.put(myRight.getMyChangeableKey(), () -> myGameBoard.right()); //d keycode:
        //right arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myRight.getMyDefaultKey(), () -> myGameBoard.right()); 

        //rotate
        myCommandMap.put(myRotate.getMyChangeableKey(), () -> myGameBoard.rotateCW());
        //UP arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myRotate.getMyDefaultKey(), () -> myGameBoard.rotateCW());
        //  myCommandMap.put(KeyEvent.VK_E, () -> myGameBoard.rotateCCW()); 

        //down
        myCommandMap.put(myDown.getMyDefaultKey(), () -> myGameBoard.down()); //s keycode:
        //down arrow keycode KEEP THIS AS IS AND DON'T CHANGE. ITS PERMANENT.
        myCommandMap.put(myDown.getMyChangeableKey() , () -> myGameBoard.down()); 

        //drop
        myCommandMap.put(myDrop.getMyDefaultKey(), () -> myGameBoard.drop()); 

        //pause
        myCommandMap.put(myPause.getMyChangeableKey(), () -> myGui.pause());
    }



    /**
     * Inner class that adds the controls to the gameboard.
     */
    protected class KeyControls extends KeyAdapter {



        @Override
        public void keyPressed(final KeyEvent theEvent) {
            super.keyPressed(theEvent);


            //final String key = KeyEvent.getKeyText(theEvent.getKeyCode());
            final int key = theEvent.getKeyCode();
            if (!(myGamePaused || myGameOver) && myCommandMap.containsKey(key)) {

                myCommandMap.get(key).run();

            } else if (myGamePaused && theEvent.getKeyCode() == KeyEvent.VK_P) {
                myCommandMap.get(key).run();
            }
        }

    }
}
