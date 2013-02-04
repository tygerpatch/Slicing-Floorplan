package src;

import gui.MainPanel;
import gui.NodePanel;
import gui.RoomPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    Tree tree = new Tree();
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
