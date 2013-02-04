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
  private Tree tree;
  private JTextField txtField;
  private JButton browse;
  private JButton rooms;
  private JButton nodes;

  public FloorPlan(JTextField txtField, JButton browse, JButton rooms, JButton nodes) {
    this.txtField = txtField;
    this.browse = browse;
    this.rooms = rooms;
    this.nodes = nodes;
    this.tree = new Tree();

    this.browse.addActionListener(new BrowseActionListener(txtField, tree));
    this.rooms.addActionListener(new RoomsActionListener(tree));
    this.nodes.addActionListener(new NodesActionListener(tree));
  }

  /**
   * This is the main method that runs the application from the console No
   * arguments are required to run application
   */
  public static void main(String[] args) {
    MainPanel mainPanel = new MainPanel();
    FloorPlan floorPlan = new FloorPlan(mainPanel.getTxtField(), mainPanel.getBrowse(), mainPanel.getRooms(), mainPanel.getNodes());

    JFrame frame = new JFrame("Floor Plan");
    frame.setContentPane(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
