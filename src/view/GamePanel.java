/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import model.Block;
import model.Point;

/**
 * This class creates the JPanel object that the game plays on.
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
public class GamePanel extends DisplayPanel {


    /**
     * Randomly generated serial id. 
     */
    private static final long serialVersionUID = -7234506973764490586L;

    /** Sets the base size that the scale will multiply by to define size of the panel. */
    private static final Dimension BASE_DIMENSION = new Dimension(10, 20);


    /** List that collects the data of the game board from the provided code. */
    private List<Block[]> myBoardData;

    /** Color that is used for the font on the board when a message appears. */
    private Color myPulseColor;

    /**
     * Constructs the game play area.
     * @param theScale the integer value that determines the scale of the components.
     */
    public GamePanel(final int theScale) {
        super();
        super.setBockMap();
        myBoardData = new ArrayList<Block[]>();
        myPulseColor = Color.RED;

        setPreferredSize(new Dimension(BASE_DIMENSION.width * theScale, 
                                       BASE_DIMENSION.height * theScale));

    }

    /**
     * This method sets the scale of the panel and the tetris pieces.
     * @param theScale int value to scale the default scale value by.
     */
    public void setScale(final int theScale) {
        myGameScale = theScale;
        setPreferredSize(new Dimension(BASE_DIMENSION.width * myGameScale, 
                                       BASE_DIMENSION.height * myGameScale));
    }

    /**
     * Overridden method to draw the tetris on this panel as the game progresses.
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(myGameScale));
        g2d.setFont(new Font("Arial", Font.BOLD, myGameScale));  
        g2d.setColor(myPulseColor);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);


        Image blockImage;

        for (int i = 0; i < myBoardData.size(); i++) {

            final Block[] activeBlocks = myBoardData.get(i);
            for (int k = 0; k < activeBlocks.length; k++) {

                final Point point = new Point(k * myGameScale, i * myGameScale);
                blockImage = super.getBlockImage(activeBlocks[k]);

                //TODO: Location 4a
                if (activeBlocks[k] != null) {
                    g2d.drawImage(blockImage, point.x(), 
                                  getHeight() - point.y() - myGameScale, 
                                  myGameScale, myGameScale, this);
                }
            }
        }

        if (myGamePaused) {

            g2d.drawString("GAME PAUSED", 
                           (int) (myGameScale + BASE_DIMENSION.getWidth()), 
                           getHeight() / HALF);

        } else if (myGameOver) {
            g2d.drawString("GAME OVER", 
                           (int) (myGameScale + BASE_DIMENSION.getWidth()), 
                           getHeight() / HALF);
        }
    }

    /**
     * Get the data on the gameboard.
     * @return the board data.
     */
    public List<Block[]> getMyBoardData() {
        return myBoardData;
    }

    /**
     * set the gameboard data. 
     * @param theBoardData the new list of blocks to represent that data.
     */
    public void setMyBoardData(final List<Block[]> theBoardData) {
        myBoardData = theBoardData;
    }

    /**
     * Sets the color for pulsing text.
     * @param theColor color of the text.
     */
    public void setPulseFontColor(final Color theColor) {
        myPulseColor = theColor;
    }

}