/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * creates the next piece window.
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
public class PiecePreviewPanel extends DisplayPanel {

    /**
     * Randomly generated ID for serialization.
     */
    private static final long serialVersionUID = 1207733560089475884L;


    /** The tetris piece which will be displayed in the panel. */
    private TetrisPiece myNextPiece;


    /** The type of Tetris Piece that is displayed in the panel. */
    private Block myNextPieceType;

    /**
     * Constructor for Piece Preview Panel object.
     * @param theScale the scale multiplier of the panel.
     */
    protected PiecePreviewPanel(final int theScale) {
        super(theScale, "Next Piece");
        super.setBockMap();
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        int xCoordinate = 0;
        int yCoordinate = 0;

        if (!myGameOver && myNextPiece != null) {



            final int blockSize = myGameScale; 
            final int halfBlock = blockSize / HALF;
            final Graphics2D g2d = (Graphics2D) theGraphics;
            final Block block = myNextPiece.getBlock();
            final Image blockImage = getBlockImage(block);


            final Point[] points = myNextPiece.getPoints();

            for (int i = 0; i < points.length; i++) {


                final Point nextPoint = myNextPiece.getPoints()[i];

                if (Block.O == myNextPieceType) {
                    yCoordinate = blockSize * nextPoint.y() + halfBlock;
                    xCoordinate = blockSize * nextPoint.x() + halfBlock;
                } else if (Block.I == myNextPieceType) {
                    xCoordinate = blockSize * nextPoint.x() + halfBlock;
                    yCoordinate = blockSize * nextPoint.y() + halfBlock;
                } else {
                    xCoordinate = blockSize * nextPoint.x() + blockSize;
                    yCoordinate = (getHeight() - HALF)  
                                    - (nextPoint.y() * blockSize) - blockSize - halfBlock;
                }

                g2d.drawImage(blockImage, 
                              xCoordinate, yCoordinate, 
                              blockSize, blockSize, this);

            }
        }
    }



    /**
     * Sets the next tetris piece to display in the preview window.
     * @param theNextPiece the next tetris piece to enter the game.
     */
    public void setNextPiece(final TetrisPiece theNextPiece) {
        myNextPiece = theNextPiece;
        myNextPieceType = theNextPiece.getBlock();
    }
}
