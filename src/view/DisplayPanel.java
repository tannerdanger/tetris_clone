/*
 * TCSS 305 - Tetris
 * Fall 2017
 */


package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.Block;

/**
 * Parent class that helps define behavior for all of the different panels on the board.
 * 
 * @author Tanner Brown
 * @version 6 Dec 2017
 */
public class DisplayPanel extends JPanel {

    /** Default value for dividing sizes in half. */
    protected static final int HALF = 2;
    /** The value of 4. Thanks, checkstyle. */
    private static final int THE_VALUE_THAT_SHALL_DIVIDE_THE_SCALE = 4;
    /**
     * Randomly generated serialization ID.
     */
    private static final long serialVersionUID = 2593149535529574155L;
    /** Default base dimension. */
    private static final Dimension BASE_DIMENSION = new Dimension(4, 4);
    /** String representation of the light theme. */
    private static final String LIGHT_THEME = "light";
    /** String representation of the dark theme. */
    private static final String DARK_THEME = "dark";
    /** String representation of the christmas theme. */
    private static final String CHRISTMAS_THEME = "christmas";
    /** Default string for the font name. */
    private static final String FONT_STRING = "SansSerrif";
    /** Default layout. */
    private static final GridLayout DEFAULT_LAYOUT = new GridLayout(1, 1);
    /** Default Christmas red color. */
    private static final Color RED_COLOR = new Color(220,  61, 42);
    /** Default Christmas green color. */
    private static final Color GREEN_COLOR = new Color(0,  179, 44);

    /** The scale of the components in this game. */
    protected int myGameScale;
    /** The font. */
    protected Font myFont;
    /** True if game is paused. */
    protected boolean myGamePaused;
    /** True if game is over. */
    protected Boolean myGameOver;
    /** Current active theme. */
    private String myActiveTheme;

    /** Block map that defines a color based on the type of block. */
    private Map<Block, String> myBlockColorMap;

    /**
     * Separate constructor for the GamePanel class.
     */
    protected DisplayPanel() {
        super();
        myGameOver = false;
        myGamePaused = false;
        myActiveTheme = LIGHT_THEME;
        build();
        setBackground(Color.LIGHT_GRAY);
        setLayout(DEFAULT_LAYOUT);
    }

    /**
     * Constructor for all panels besides the main game panel.
     * @param theScale the scale of the panel.
     * @param theTitle the title of the panel.
     *
     */
    protected DisplayPanel(final int theScale, final String theTitle) {
        this();
        setPreferredSize(new Dimension(BASE_DIMENSION.width 
                                       * (theScale / THE_VALUE_THAT_SHALL_DIVIDE_THE_SCALE 
                                                       + theScale), 
                                       BASE_DIMENSION.height 
                                       * (theScale / THE_VALUE_THAT_SHALL_DIVIDE_THE_SCALE 
                                                       + theScale)));

        setMyFont(new Font(FONT_STRING, Font.PLAIN, myGameScale / HALF));
        setBoarders(theTitle);

    }
    /**
     * Helper method.
     */
    private void build() {
        
    }

    /** Sets components for a new game. */
    public void newGame() {
        myGameOver = false;
        myGamePaused = false;
    }

    /**
     * Sets the boarders around the panels. 
     * @param theTitle of the panel. 
     */
    private void setBoarders(final String theTitle) {


        final Border border = 
                        BorderFactory.createStrokeBorder(new BasicStroke(3), Color.YELLOW);
        final TitledBorder title = BorderFactory.createTitledBorder(
                                                                    border, theTitle);
        title.setTitleJustification(TitledBorder.CENTER);

        setBorder(title);
    }


    /**
     * Sets the game paused.
     * @param theIsPaused if the game is paused or not.
     */
    protected void setPaused(final boolean theIsPaused) {
        myGamePaused = theIsPaused;
    }

    /**
     * Sets if the game is over.
     */
    protected void gameOver() {
        myGameOver = true;
    }

    /**
     * Updates the active theme.
     * @param theNewTheme the new theme to be activated.
     */
    public void updateTheme(final String theNewTheme) {
        myActiveTheme = theNewTheme;
        setBackground(getPrimaryThemeColor());
    }

    /**
     * Sets the color of the panels based off active theme.
     * @return the primary color.
     */
    protected Color getPrimaryThemeColor() {
        Color color = Color.black; //black just incase an error.

        if (CHRISTMAS_THEME.equals(myActiveTheme)) {
            color = RED_COLOR;
        } else if (LIGHT_THEME.equals(myActiveTheme)) {
            color = Color.LIGHT_GRAY;
        } else if (DARK_THEME.equals(myActiveTheme)) {
            color = Color.DARK_GRAY;

        }
        return color;
    }

    /**
     * Sets the color of the font based off active theme.
     * @return the primary color.
     */
    protected Color getSecondaryThemeColor() {
        Color color = Color.black; //black just incase an error.

        if (CHRISTMAS_THEME.equals(myActiveTheme)) {
            color = GREEN_COLOR;
        } else if (LIGHT_THEME.equals(myActiveTheme)) {
            color = Color.BLACK;
        } else if (DARK_THEME.equals(myActiveTheme)) {
            color = Color.YELLOW;
        }
        return color;
    }

    /**
     * Sets the active theme.
     * @param theActiveTheme the new active theme.
     */
    protected void setMyActiveTheme(final String theActiveTheme) {
        myActiveTheme = theActiveTheme;
    }

    /**
     * This method creates a hashmap that associates each blocktype with a string.
     */
    public void setBockMap() {
        myBlockColorMap = new HashMap<>();
        myBlockColorMap.put(Block.I, "I");
        myBlockColorMap.put(Block.J, "J");
        myBlockColorMap.put(Block.L, "L");
        myBlockColorMap.put(Block.O, "O");
        myBlockColorMap.put(Block.S, "S");
        myBlockColorMap.put(Block.T, "T");
        myBlockColorMap.put(Block.Z, "Z");
    }

    /**
     * Sets the scale of the child panels.
     * @param theNewScale the new scale.
     */
    public void setScale(final int theNewScale) {
        myGameScale = theNewScale;
        setMyFont(new Font(FONT_STRING, Font.PLAIN, myGameScale / HALF - HALF));

        final Dimension tempDimension = new Dimension();

        tempDimension.setSize(BASE_DIMENSION.width 
                              * (theNewScale / THE_VALUE_THAT_SHALL_DIVIDE_THE_SCALE 
                                              + theNewScale), 
                              BASE_DIMENSION.height 
                              * (theNewScale / THE_VALUE_THAT_SHALL_DIVIDE_THE_SCALE 
                                              + theNewScale));
        setSize(tempDimension);
        setPreferredSize(tempDimension);


        final Component[] componentList = getComponents();
        for (final Component c : componentList) {

            if (c instanceof JLabel) {
                c.setFont(myFont);
            }         
        }

    }

    /**
     * This method determines the graphic that will represent the pieces based off
     * the current scale size, the blocks shape and the current active theme.
     * @param theBlock the block type being passed in.
     *
     * @return The block image for specified piece.
     */
    public Image getBlockImage(final Block theBlock) {
        Image tempImage = null;
        tempImage = Toolkit.getDefaultToolkit().getImage
                        ("imgs/" + myActiveTheme + "/" 
                                        + myBlockColorMap.get(theBlock) + ".png");

        return tempImage;
    }


    /**
     * Gets the current font.
     * @return the font.
     */
    public Font getMyFont() {
        return myFont;
    }


    /**
     * Sets the current font.
     * @param theFont the new font.
     */
    public void setMyFont(final Font theFont) {
        myFont = theFont;
    }
}
