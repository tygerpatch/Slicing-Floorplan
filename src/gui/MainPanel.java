package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class does not perform any actions.
 * It is just a GUI for letting the user choose the floor plan file.
 * Also has buttons for showing Rooms and Nodes
 * @author Todd
 *
 */
public class MainPanel extends JPanel {
  private JTextField txtField;
  private JButton browse;
  private JButton rooms;
  private JButton nodes;

  public MainPanel() {
    JPanel fileChooserPanel = new JPanel();
    fileChooserPanel.setLayout(new GridLayout(1, 3));

    // this JTextField will display the path of file choosen
    txtField = new JTextField(10);
    txtField.setEditable(false);

    // this JButton will allow user to search through directory
    browse = new JButton("...");
    // browse.addActionListener(this);

    fileChooserPanel.add(new JLabel(" Floor Plan file:"));
    fileChooserPanel.add(txtField);
    fileChooserPanel.add(browse);
    
    JPanel drawPanel = new JPanel(new GridLayout(1, 5));

    // this JButton will allow user to display the rooms
    //  as described by the given text file
    rooms = new JButton("ROOMS");
    // rooms.addActionListener(this);

    // this JButton will allow user to display the nodes
    //  of the binary floor plan tree
    nodes = new JButton("NODES");
    // nodes.addActionListener(this);
      
    drawPanel.add(new JLabel(""));
    drawPanel.add(rooms);
    drawPanel.add(new JLabel(""));
    drawPanel.add(nodes );
    drawPanel.add(new JLabel(""));

    setLayout(new BorderLayout());
    add(fileChooserPanel, BorderLayout.NORTH);
    add(drawPanel);
  }

  // TODO: Remove this method, implemented to support existing code
  public JTextField getTxtField() {
    return txtField;
  }

  // TODO: Remove this method, implemented to support existing code
  public JButton getBrowse() {
    return browse;
  }

  // TODO: Remove this method, implemented to support existing code
  public JButton getRooms() {
    return rooms;
  }

  // TODO: Remove this method, implemented to support existing code
  public JButton getNodes() {
    return nodes;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("FileChooserPanel");
    frame.setContentPane(new MainPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}