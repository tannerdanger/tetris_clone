/*
 * TCSS 305 - Tetris
 * Fall 2017
 */
package view;

import actions.TetrisAction;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSlider;

/**
 * Creates top menu on the Frame.
 * @author Tanner Brown
 * @version 1 Dec 2017
 *
 */
public class MenuBar extends JMenuBar {

    /**
     * Randomly generated ID.
     */
    private static final long serialVersionUID = 4695539075864364234L;
    /** The minimum value of the slider. */
    private static final int MIN_THICK = 20;
    /** The max value of the slider. */
    private static final int MAX_THICK = 50;
    /** The spacing that shows number values on the slider. */
    private static final int TICK_SPACING = 5;
    /** The default value of the slider. */
    private static final int THICK_DEFAULT = 25;
    /** JMenu that contains the options components. */
    private JMenu myFileMenu;
    /** JMenu that contains the tools components. */
    private JMenu myOptionsMenu;
    /** JMenu that contains the help components. */
    private JMenu myHelpMenu;
    /** A gui object. */
    private final TetrisGUI myGui;


    /**
     * Basic constructor that builds the object.
     * @param theGui the gui that will be manipulated.
     */
    protected MenuBar(final TetrisGUI theGui) {
        super();
        myGui = theGui;
        build();
    }

    /**
     * Helper method builds the main components of the menu, then calls helper methods
     * that build the menu child components. 
     */
    private void build() {
        myFileMenu = new JMenu("File");
        myFileMenu.setMnemonic(KeyEvent.VK_F);
        myOptionsMenu = new JMenu("Options");
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);

        myHelpMenu = new JMenu("Help");
        myHelpMenu.setMnemonic(KeyEvent.VK_H);

        add(myFileMenu);
        buildFile();
        add(myOptionsMenu);
        buildOptions();
        add(myHelpMenu);
        buildHelp();
    }


    /**
     * Build the file menu components.
     */
    private void buildFile() {

        //Create components
        final JMenuItem startNewGameItem = new JMenuItem("New Game");
        final JMenuItem forceGameOver = new JMenuItem("End Game");
        final JMenuItem exitMenuItem = new JMenuItem("Quit");

        //Set mnemenimimononics
        startNewGameItem.setMnemonic(KeyEvent.VK_N);
        forceGameOver.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setMnemonic(KeyEvent.VK_Q);


        //Add to menu
        myFileMenu.add(startNewGameItem);
        myFileMenu.add(forceGameOver);
        myFileMenu.addSeparator();
        myFileMenu.add(exitMenuItem);

        //Add listeners
        exitMenuItem.addActionListener(new TetrisAction(myGui));
        forceGameOver.addActionListener(new TetrisAction(myGui));
        startNewGameItem.addActionListener(new TetrisAction(myGui));

    }

    /**
     * Builds the options menu in the menubar.
     */
    private void buildOptions() {
        //TODO: Location 3

        //Make set new keys button
        final JMenuItem setKeysItem = new JMenuItem("Set Controls...");
        myOptionsMenu.add(setKeysItem);
        setKeysItem.addActionListener(new TetrisAction(myGui));
        setKeysItem.setMnemonic(KeyEvent.VK_S);

        buildThemeButtons();
        myOptionsMenu.add(new JSeparator());
        buildSlider();     
        buildDifficulty();



    }

    /**
     * Helper method builds the size scaling slider.
     */
    private void buildSlider() {
        final JMenu setScaleMenu = new JMenu("Resize...");
        JSlider scaleSlider = new JSlider();
        scaleSlider = new JSlider(MIN_THICK, MAX_THICK, THICK_DEFAULT);
        scaleSlider.setMinorTickSpacing(TICK_SPACING);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setPaintTicks(true);
        setScaleMenu.add(scaleSlider);
        scaleSlider.addChangeListener(new TetrisAction(myGui));

        setScaleMenu.setMnemonic(KeyEvent.VK_R);
        myOptionsMenu.add(setScaleMenu);
    }

    /**
     * Helper method build the theme buttons.
     */
    private void buildThemeButtons() {

        //Make components
        final JMenu setLook = new JMenu("Theme...");
        final ButtonGroup themeGroup = new ButtonGroup();
        final JRadioButtonMenuItem lightTheme = new 
                        JRadioButtonMenuItem("Light Theme (Default)");
        final JRadioButtonMenuItem darkTheme = new 
                        JRadioButtonMenuItem("Dark Theme");

        final JRadioButtonMenuItem christmasTheme = new 
                        JRadioButtonMenuItem("Christmas Theme");

        //set mnemoniminoomonics
        setLook.setMnemonic(KeyEvent.VK_T);
        lightTheme.setMnemonic(KeyEvent.VK_L);
        darkTheme.setMnemonic(KeyEvent.VK_D);
        christmasTheme.setMnemonic(KeyEvent.VK_C);

        //Add components
        setLook.add(lightTheme);
        setLook.add(darkTheme);
        setLook.add(christmasTheme);
        themeGroup.add(lightTheme);
        themeGroup.add(darkTheme);
        themeGroup.add(christmasTheme);
        lightTheme.setSelected(true);

        //Add Listeners
        lightTheme.addActionListener(new TetrisAction(myGui));
        darkTheme.addActionListener(new TetrisAction(myGui));
        christmasTheme.addActionListener(new TetrisAction(myGui));
        myOptionsMenu.add(setLook);
    }

    /**
     * Helper method builds the difficulty buttons.
     */
    private void buildDifficulty() {

        final JMenu setDifficulty = new JMenu("Difficulty...");
        final ButtonGroup difficultyGroup = new ButtonGroup();
        final JRadioButtonMenuItem defLevel = new JRadioButtonMenuItem("Default - Level 1");
        final JRadioButtonMenuItem level3 = new JRadioButtonMenuItem("Easy - Level 3");
        final JRadioButtonMenuItem level5 = new JRadioButtonMenuItem("Medium - Level 5");
        final JRadioButtonMenuItem level7 = new JRadioButtonMenuItem("Hard - Level 7");
        final JRadioButtonMenuItem level10 = new JRadioButtonMenuItem("Impossible - Level 10");
        defLevel.addActionListener(new TetrisAction(myGui));
        level3.addActionListener(new TetrisAction(myGui));
        level5.addActionListener(new TetrisAction(myGui));
        level7.addActionListener(new TetrisAction(myGui));
        level10.addActionListener(new TetrisAction(myGui));
        setDifficulty.add(defLevel).setSelected(true);
        setDifficulty.add(level3);
        setDifficulty.add(level5);
        setDifficulty.add(level7);
        setDifficulty.add(level10);
        difficultyGroup.add(defLevel);
        difficultyGroup.add(level3);
        difficultyGroup.add(level5);
        difficultyGroup.add(level7);
        difficultyGroup.add(level10);

        setDifficulty.setMnemonic(KeyEvent.VK_D);
        defLevel.setMnemonic(KeyEvent.VK_1);
        level3.setMnemonic(KeyEvent.VK_3);
        level5.setMnemonic(KeyEvent.VK_5);
        level7.setMnemonic(KeyEvent.VK_7);
        level10.setMnemonic(KeyEvent.VK_0);
        //level11.setMnemonic(KeyEvent.VK_); 11?

        myOptionsMenu.add(setDifficulty);
    }

    /**
     * Helper method builds the components of the help menu.
     */
    private void buildHelp() {
        final JMenuItem aboutItem = new JMenuItem("About this game...");
        myHelpMenu.add(aboutItem);
        //TODO: Location 1

        aboutItem.setMnemonic(KeyEvent.VK_A);

        aboutItem.addActionListener(e -> {

            

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    new AboutFrame();
                }
            });
        });
    }


}