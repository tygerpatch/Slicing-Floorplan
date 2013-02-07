package src;

import gui.MainPanel;

import javax.swing.JFrame;
import javax.swing.JTextField;

import tree.binary.Tree;

/**
 * Program request text file containing floor plan to use.
 * Floor plan is read in and stored as a binary tree.
 * User can then selected to graphically display tree or display the rooms that the floor plan represents.
 * @author Todd Gerspacher
 */
public class FloorPlan {
  /**
   * This is the main method that runs the application from the console.
   * No arguments are required to run application
   */
  public static void main(String[] args) {
    MainPanel mainPanel = new MainPanel();
    // Note: MainPanel has its own main method for testing how it looks.
    // Therefore I had to use a separate class.  Plus FloorPlan is more intuitive as a starting point for a project titled FloorPlan.

    Tree<Character> tree = new Tree<Character>();
    JTextField txtField = mainPanel.getTxtField();

    mainPanel.setBrowseActionListener(new BrowseActionListener(txtField, tree));
    mainPanel.setRoomsActionListener(new RoomsActionListener(tree));
    mainPanel.setNodesActionListener(new NodesActionListener(tree));

    JFrame frame = new JFrame("Floor Plan");
    frame.setContentPane(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
