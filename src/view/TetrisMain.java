/*
 * TCSS 305 - Tetris
 * Fall 2017
 */

package view;

import java.awt.EventQueue;

/**
 * Launches the Tetris Game GUI.
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
final class TetrisMain {

    /**
     * Empty constructor to prevent instantiation. 
     */
    private TetrisMain() {
        //Empty Private Constructor.
    }

    /**
     * Main method to launch GUI.
     * @param theArgs the default arguments value.
     */
    public static void main(final String[] theArgs) {


        launchGUI();

    }

    /**
     * Launches the GUI.
     */
    private static void launchGUI() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                new TetrisGUI();
            }
        });
    }



}
