/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Block;
import model.Board;
import model.TetrisPiece;

/**
 * Creates, builds, and defines the behavior of much of the Tetris game GUI.
 * @author Tanner Brown
 * @version 1 Dec 2017
 */
public class TetrisGUI extends JFrame implements Observer {

    /**
     * Randomly generated ID for serialization.
     */
    private static final long serialVersionUID = 5311823647042117087L;

    /** The max level that can be reached. */
    private static final int MAX_LEVEL = 10;
    /** The default timer speed for the game. */
    private static final int DEFAULT_TIMER_DELAY = 650;
    /** The default timer speed for messages to display. */
    private static final int DEFAULT_MESSAGE_SPEED = 400;
    /** The speed at which the timer increases after each level increase. */
    private static final int TIMER_INCREASE = 50;
    /** Constant value for the default scale size. */
    private static final int DEFAULT_SCALE = 25;

    /** A game panel object. */
    private GamePanel myGamePanel;
    /** A piece preview panel object. */
    private PiecePreviewPanel myPiecePreviewPanel;
    /** A piece control panel object. */
    private ControlsPanel myControlsPanel;
    /** A piece score panel object. */
    private ScorePanel myScorePanel;
    /** A piece high-score panel object. */
    private HighScorePanel myHighScorePanel;
    /** A piece board object. */
    private final Board myGameBoard;


    /** Hashmap that associates a timer speed with a level. */
    private Map<Integer, Integer> myTimerMap;
    /** Timer that sets the game speed. */
    private Timer myGameTimer;
    /** Timer that acts when game is inactive.*/
    private Timer myMessageTimer;
    /** Returns true if the game is over. */
    private boolean myGameOver;
    /** Returns true if game is paused. */
    private boolean myIsPaused;
    /** The current game scale. */
    private final int myGameScale;

    //  private TetrisObserver theObserver;

    /**
     * Constructor that builds the Frame.
     */
    public TetrisGUI() {
        super();
        myGameBoard = new Board();
        myGameBoard.addObserver(this);

        //set defaults
        myGameScale = DEFAULT_SCALE;
        myIsPaused = false;
        myGameOver = true;
        buildMap();
        build();
        myGameBoard.newGame(); //TODO: Move this? Based on order of building the thing

    }


    /**
     * Method that calls to helper methods which build the GUI.
     */
    private void build() {

        addMenuBar();
        buildPanels();
        buildFrame();
        changeScale(myGameScale);
        myGameTimer = new Timer(DEFAULT_TIMER_DELAY, new TimerListener());
        myMessageTimer = new Timer(DEFAULT_MESSAGE_SPEED, new TimerListener());

    }

    /**
     * Helper method that builds the map that determines timer speed.
     */
    private void buildMap() {
        myTimerMap = new HashMap<Integer, Integer>();
        for (int i = 1; i <= MAX_LEVEL; i++) {
            myTimerMap.put(i, DEFAULT_TIMER_DELAY - (TIMER_INCREASE * i));
        }
    }

    /**
     * Builds and adds the menubar to the top of the frame.
     */
    private void addMenuBar() {
        final MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }


    /**
     * Builds the GUI frame.
     */
    private void buildFrame() {
        setName("TCSS 305 Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(false); //resize using slider
        pack();
        setVisible(true);
    }

    /**
     * Builds the panels.
     */
    private void buildPanels() {

        myGamePanel = new GamePanel(DEFAULT_SCALE);         
        myPiecePreviewPanel = new PiecePreviewPanel(DEFAULT_SCALE);

        final JFrame tempFrame = this;

        //Pass the game board so keylisteners can be added
        myControlsPanel = new ControlsPanel(DEFAULT_SCALE , myGameBoard, this);
        myControlsPanel.addKeyListeners(tempFrame); //Add listeners to game panel

        myScorePanel = new ScorePanel(DEFAULT_SCALE);
        myHighScorePanel = new HighScorePanel(DEFAULT_SCALE);



        //create temp panels for formatting
        final JPanel leftPanel = new JPanel(new BorderLayout());
        final JPanel topLeft = new JPanel(new GridLayout(1, 1, 0, 0));
        final JPanel midLeft = new JPanel(new FlowLayout());
        final JPanel botLeft = new JPanel(new GridLayout(1, 1, 0, 0));

        final JPanel rightPanel = new JPanel(new BorderLayout());
        final JPanel topRight = new JPanel(new GridLayout(1, 1, 0, 0));
        final JPanel midRight = new JPanel(new FlowLayout());
        final JPanel botRight = new JPanel(new GridLayout(1, 1, 0, 0));



        //creates test buttons
        //testButtons(leftPanel, rightPanel);

        //if not testing, enable these
        leftPanel.add(midLeft);
        rightPanel.add(midRight);

        final JPanel basePanel = (JPanel) getContentPane();

        basePanel.add(leftPanel, BorderLayout.WEST);
        basePanel.add(myGamePanel, BorderLayout.CENTER);
        basePanel.add(rightPanel, BorderLayout.EAST);

        leftPanel.add(topLeft, BorderLayout.NORTH);
        // leftPanel.add(midLeft);
        leftPanel.add(botLeft, BorderLayout.SOUTH);

        rightPanel.add(topRight, BorderLayout.NORTH);
        // rightPanel.add(midRight);
        rightPanel.add(botRight, BorderLayout.SOUTH);

        topLeft.add(myPiecePreviewPanel);
        botLeft.add(myControlsPanel);
        topRight.add(myScorePanel);
        botRight.add(myHighScorePanel);

    }



    //Below: change settings   
    /**
     * Allows user to change default keys.
     */
    public void changeKeys() {
        myGameTimer.stop();
        myControlsPanel.changeKeys();
        myGameTimer.start();
    }

    /**
     * Changes the scale of all of the components of the GUI.
     * @param theNewScale the new scale multiplier.
     */
    public void changeScale(final int theNewScale) {

        myGamePanel.setScale(theNewScale);
        myControlsPanel.setScale(theNewScale);
        myPiecePreviewPanel.setScale(theNewScale);
        myScorePanel.setScale(theNewScale);
        myHighScorePanel.setScale(theNewScale);
        myControlsPanel.setScale(theNewScale);
        pack();
    }

    /**
     * Sets the active theme for all of the GUI components.
     * @param theNewTheme to set the components to.
     */
    public void setActiveTheme(final String theNewTheme) {
        myGamePanel.updateTheme(theNewTheme);
        myControlsPanel.updateTheme(theNewTheme);
        myScorePanel.updateTheme(theNewTheme);
        myPiecePreviewPanel.updateTheme(theNewTheme);
        myHighScorePanel.updateTheme(theNewTheme);
    }

    /**
     * Starts a new game.
     */
    public void startNewGame() {

        myGameOver = false;
        myGameBoard.newGame();
        myPiecePreviewPanel.newGame();
        myScorePanel.newGame();
        myHighScorePanel.newGame();
        myGamePanel.newGame();
        myControlsPanel.newGame();             
        myGameTimer.start();
        myScorePanel.startNewGame();
    }

    /**
     * Sets the game into a game-over state.
     */
    public void gameOver() {

        myGameOver = true;
        myGameTimer.stop();
        myPiecePreviewPanel.gameOver();
        myScorePanel.gameOver();
        myHighScorePanel.gameOver();
        myGamePanel.gameOver();       
        myControlsPanel.gameOver();

        myMessageTimer.start();
    }

    /**
     * Quits the program.
     */
    public void quitGame() {
        myGameTimer.stop();
        final int response = JOptionPane.showConfirmDialog
                        (this, "Are you sure you want to quit the game?");
        if (0 == response) {
            setVisible(false);
            dispose();
        } else {
            myGameTimer.start();
        }

    }

    /**
     * Pauses the game.
     */
    protected void pause() {

        //If game is paused, un-pause game
        if (myIsPaused) {
            myIsPaused = false;
            myGamePanel.setPaused(false);
            myControlsPanel.setPaused(false);
            myMessageTimer.stop();
            myGameTimer.start();            
        } else { 

            myIsPaused = true;
            myGamePanel.setPaused(true);
            myControlsPanel.setPaused(true);
            myGameTimer.stop();
            myMessageTimer.start();
        }
    }

    /**
     * Get the game panel.
     * @return the game panel.
     */
    public GamePanel getGamePanel() {
        return myGamePanel;
    }

    /**
     * Get the next piece panel.
     * @return the panel.
     */
    public PiecePreviewPanel getMyPiecePreviewPanel() {
        return myPiecePreviewPanel;
    }
    /**
     * Get the controls panel.
     * @return the panel.
     */
    public ControlsPanel getControlsPanel() {
        return myControlsPanel;
    }
    /**
     * Get the score panel.
     * @return the panel.
     */
    public ScorePanel getScorePanel() {
        return myScorePanel;
    }

    /**
     * Perform a level check and adjust speed.
     * @param theLevel that determines the timer speed.
     */
    public void levelCheck(final int theLevel) {
        if (myTimerMap.containsKey(theLevel)) {
            myGameTimer.setDelay(myTimerMap.get(theLevel));
        }
    }
    /**
     * Sets the current level.
     * @param theLevel the new level.
     */
    public void setLevel(final int theLevel) {
        myScorePanel.setLevel(theLevel);
        levelCheck(theLevel);
    }

    /**
     * Helper method that updates the piece preview panel.
     * @param theObj the new Tetris Piece in object form.
     */
    private void newPiece(final Object theObj) {
        myPiecePreviewPanel.setNextPiece((TetrisPiece) theObj);
        myScorePanel.blockFrozen();
    }

    /**
     * Helper method that updates the board data.
     * @param theObj the board data in Object form.
     */
    private void updateBoardData(final Object theObj) {
        for (int i = 0; i < ((List<?>) theObj).size(); i++) {
            final Block[] tempBlocks = (Block[]) ((List<?>) theObj).get(i);
            myGamePanel.getMyBoardData().add(tempBlocks);
        }
    }



    @Override
    public void update(final Observable theObserv, final Object theObj) {
        if (!myGameOver) {

            //List of pieces
            if (theObserv instanceof Board && theObj instanceof List) {
                if (!myGameOver) {
                    myGamePanel.getMyBoardData().clear();
                }
                updateBoardData(theObj);


            }
            //Next piece
            if (theObj instanceof TetrisPiece) {               
                newPiece(theObj);
            }

            if (theObj instanceof Integer[]) {
                myScorePanel.newLines(((Integer[]) theObj).length);
                levelCheck(myScorePanel.getLevel());
            }
            //Is game over
            if (theObj instanceof Boolean) {
                myGamePanel.repaint();
                gameOver();
            }
        }

        if (!myGameOver) {
            myGamePanel.repaint();
            myPiecePreviewPanel.repaint();
        }
    }


    /**
     * Inner class that defines behavior of the timers. 
     * @author Tanner Brown
     * @version 6 Dec 2017
     *
     */
    private final class TimerListener implements ActionListener {

        /** String constant for red. */
        private static final String RED = "red";
        /** String constant for white. */
        private static final String WHITE = "white";

        /** The default color string for pulsing text.*/
        private String myPulseColor;
        /** The default color for pulsing text.*/
        private Color myNewColor;


        /**
         * Constructs the listener.
         */
        protected TimerListener() {
            myNewColor = Color.RED;
            myPulseColor = RED;
        }

        @Override
        public void actionPerformed(final ActionEvent theEvent) {

            if (theEvent.getSource().equals(myGameTimer)) {

                if (!myGameOver) {
                    myGameBoard.step();
                }

            } else if (theEvent.getSource().equals(myMessageTimer)) {
                switch (myPulseColor) {
                    case RED:
                        myNewColor = Color.WHITE;
                        myPulseColor = WHITE;
                        break;
                    case WHITE:
                        myNewColor = Color.RED;
                        myPulseColor = RED;
                        break;
                    default:
                        break;
                }
                myGamePanel.setPulseFontColor(myNewColor);
                myGamePanel.repaint();
            }
        }
    }
}
