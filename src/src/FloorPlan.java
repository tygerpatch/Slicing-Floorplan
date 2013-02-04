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
public class FloorPlan implements ActionListener {
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
    this.rooms.addActionListener(this);
    this.nodes.addActionListener(this);
  }

  /**
   * This method is called whenever the user clicks on one of the three JButtons:
   *
   * The one to search for the text file to use in program The one to
   * graphically display the nodes of the floor plan tree The one to display the
   * rooms as is described by the text file
   *
   * @param ActionEvent contains source of who called this method
   */
  public void actionPerformed(ActionEvent evt) {
    // get the reference of the object that triggered the event
    Object obj = evt.getSource();

//    // if that object was the browse button
//    // then call a method that deals with looking for a file to use
//    if (obj == browse) {
//      selectFile();
//    }

    // if that object was the rooms button
    // then call method to display rooms in a separate JFrame
    if (obj == rooms) {
      drawRooms();
    }

    // if the object was the nodes button
    // then call method to display nodes in a separate JFrame
    if (obj == nodes) {
      drawNodes();
    }
  }

  /**
   * This method handles the drawing of the nodes in the tree in a JFrame
   */
  private void drawNodes() {
    // if tree is null, then don’t do anything
    if (tree == null) {
      return;
    }

    JFrame frame = new JFrame("Tree");
    frame.setContentPane(new NodePanel(tree.getNodeInfoList(), 25));
    frame.setSize(200, 200);
    frame.setVisible(true);

    // NOTE: user can close this frame without terminating program
  }

  /**
   * This method handles the drawing of the rooms as is described by the text file.
   */
  private void drawRooms() {
    // if tree is null, then don’t do anything
    if (tree == null) {
      return;
    }

    JFrame frame = new JFrame("Rooms");
    frame.setContentPane(new RoomPanel(tree));
    frame.setSize(200, 200);
    frame.setVisible(true);

    // NOTE: user can close this frame without terminating program
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
