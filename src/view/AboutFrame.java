/*
 * TCSS 305 - Tetris
 * Fall 2017
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

/**
 * This class creates an external frame that displays instructions and important info.
 * 
 * @author Tanner Brown
 * @version 7 Dec 2017
 *
 */
public class AboutFrame extends JFrame {
    
    /** Randomly generaed serial ID. */
    private static final long serialVersionUID = -148033926718571423L;

    /**
     * Basic constructor calls to build helper methods.
     */
    public AboutFrame() {
        super();        
        build();       
    }
    
    /**
     * Method that builds the frame. Calls to helper methods to create components.
     */
    private void build() {
        final JPanel basePanel = (JPanel) getContentPane();
        basePanel.setLayout(new CardLayout(0, 0));
        
        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        basePanel.add(tabbedPane);
        
        buildInstructions(tabbedPane);
        buildScoring(tabbedPane);
        buildAbout(tabbedPane);
        
        pack();
        setVisible(true);
        setResizable(false);
        setName("About");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
    }
    
    /**
     * Builds the instructions tab.
     * @param theTabbedPane the tabbed container this panel sits in.
     */
    private void buildInstructions(final JTabbedPane theTabbedPane) {

        
        final JPanel instructionsPanel = new JPanel(new BorderLayout(0, 0));
        theTabbedPane.addTab("Instructions", null, instructionsPanel, null);
        
        final JPanel instructionsImagePanel = new JPanel(new BorderLayout(0, 0));
        instructionsPanel.add(instructionsImagePanel, BorderLayout.EAST);
        
        //Add Image
        final JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("imgs/img1.png"));
        instructionsImagePanel.add(imageLabel);
        
        final JTextPane txtPanel = new JTextPane();
        txtPanel.setText("+ Control falling Tetriminos to "
                        + "create horizontal lines and clear them away."
                        + "\r\n\r\n"
                        + "+ If you allow blocks to stack up to the "
                        + "top of the screen the game is over!\r\n\r\n+"
                        + " Every 5 cleared lines increases the level,"
                        + " which increases the difficulty.\r\n\r\n"
                        + "Tips:\r\n\r\n"
                        + "+ You can set the starting difficulty and "
                        + "controls in the Options menu.\r\n\r\n+ Even "
                        + "after a Tetrimino has landed, for a very "
                        + "short time you can still\r\nmove the piece "
                        + "left or right, or even rotate it. However, "
                        + "this can't be\r\ndone acter a Tetrimino has been hard dropped.");
        
        instructionsPanel.add(txtPanel, BorderLayout.WEST);         
              
    }
    
    /**
     * Builds the tabbed panel that displays scoring info.
     * @param theTabbedPane the tabbed pane this panel will sit in.
     */
    private void buildScoring(final JTabbedPane theTabbedPane) {
        
        final JPanel scoringPanel = new JPanel(new BorderLayout(0, 0));
        theTabbedPane.addTab("Scoring", null, scoringPanel, null);

        
        final JPanel algorithmPanel = new JPanel(new GridLayout(5, 5, 0, 0));
        scoringPanel.add(algorithmPanel, BorderLayout.CENTER);
        
        addAlgorithmLabels(algorithmPanel);
        
        
        final JTextPane textPanel = new JTextPane();
        scoringPanel.add(textPanel, BorderLayout.NORTH);
        textPanel.setText("+ 4 points are given for every "
                        + "block that is placed on the "
                        + "gamefield\r\n\r\n+ Points are added "
                        + "for each cleared line, based on the "
                        + "current level\r\n\r\n+ Clearing more "
                        + "lines at once results in a higher score multiplier");
    }
    
    /**
     * Adds labels to the algorithm display panel.
     * @param thePanel the algorithm panel the labels are added to.
     */
    private void addAlgorithmLabels(final JPanel thePanel) {
        
        //I know this is gross. If I had more time I would have just
        //Created a loop that essentially applies score algorithm and
        //adds a label based off all values. 
        
        final JLabel emptyLabel = new JLabel();
        thePanel.add(emptyLabel);
        
        final JLabel lblNewLabel1 = new JLabel("Single Line");
        thePanel.add(lblNewLabel1);
        
        final JLabel lblNewLabelNum2 = new JLabel("Double Line");
        thePanel.add(lblNewLabelNum2);
        
        final JLabel lblNewLabelNum3 = new JLabel("Triple Line");
        thePanel.add(lblNewLabelNum3);
        
        final JLabel lblNewLabelNum6 = new JLabel("4 Lines (Tetris)");
        thePanel.add(lblNewLabelNum6);
        
        final JLabel lblNewLabelNum4 = new JLabel("Level 1:");
        thePanel.add(lblNewLabelNum4);
        
        final JLabel lblNewLabelNum8 = new JLabel("40");
        thePanel.add(lblNewLabelNum8);
        
        final JLabel lblNewLabelNum14 = new JLabel("100");
        thePanel.add(lblNewLabelNum14);
        
        final JLabel lblNewLabelNum10 = new JLabel("300");
        thePanel.add(lblNewLabelNum10);
        
        final JLabel lblNewLabelNum20 = new JLabel("1200");
        thePanel.add(lblNewLabelNum20);
        
        final JLabel lblNewLabelNum7 = new JLabel("Level 2:");
        thePanel.add(lblNewLabelNum7);
        
        final JLabel lblNewLabelNum15 = new JLabel("80");
        thePanel.add(lblNewLabelNum15);
        
        final JLabel lblNewLabelNum19 = new JLabel("200");
        thePanel.add(lblNewLabelNum19);
        
        final JLabel lblNewLabelNum12 = new JLabel("600");
        thePanel.add(lblNewLabelNum12);
        
        final JLabel lblNewLabel = new JLabel("2400");
        thePanel.add(lblNewLabel);
        
        final JLabel lblNewLabelNum17 = new JLabel("Level 3:");
        thePanel.add(lblNewLabelNum17);
        
        final JLabel lblNewLabelNum16 = new JLabel("120");
        thePanel.add(lblNewLabelNum16);
        
        final JLabel lblNewLabelNum18 = new JLabel("3OO");
        thePanel.add(lblNewLabelNum18);
        
        final JLabel lblNewLabelNum11 = new JLabel("900");
        thePanel.add(lblNewLabelNum11);
        
        final JLabel lblNewLabelNum9 = new JLabel("3600");
        thePanel.add(lblNewLabelNum9);
        
        final JLabel lblNewLabelNum13 = new JLabel("Level 10:");
        thePanel.add(lblNewLabelNum13);
        
        final JLabel lblNewLabelNum22 = new JLabel("400");
        thePanel.add(lblNewLabelNum22);
        
        final JLabel lblNewLabelNum21 = new JLabel("1000");
        thePanel.add(lblNewLabelNum21);
        
        final JLabel lblNewLabelNum23 = new JLabel("3000");
        thePanel.add(lblNewLabelNum23);
        
        final JLabel lblNewLabelNum24 = new JLabel("12000");
        thePanel.add(lblNewLabelNum24);
    }
    
    /**
     * Helper method that builds the about panel.
     * @param theTabbedPane the container that the about panel sits in.
     */
    private void buildAbout(final JTabbedPane theTabbedPane) {
        
        final JTextPane aboutPanel = new JTextPane();
        aboutPanel.setText("Created by: Tanner Brown\r\n\r\n"
                        + "Original design by: Alexey Pajitnov - 1984"
                        + "\r\n\r\nCreated for: TCSS - 305 "
                        + "at the University of Washington - Tacoma");
        theTabbedPane.addTab("About This Program", null, aboutPanel, null);
        
    }
        


        
        
    
    

}
