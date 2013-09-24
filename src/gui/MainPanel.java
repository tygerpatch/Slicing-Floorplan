package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class does not perform any actions.
 * It is just a GUI for letting the user choose the floor plan file.
 * Also has buttons for showing Rooms and Nodes
 * @author Todd Gerspacher
 */
public class MainPanel extends JPanel {
  private JTextField txtField;
  private JButton browse;
  private JButton rooms;
  private JButton nodes;

  private static final int rows = 1, cols = 3;

  public MainPanel() {
    JPanel fileChooserPanel = new JPanel();
    fileChooserPanel.setLayout(new GridLayout(rows, cols));

    // this JTextField will display the path of file choosen
    txtField = new JTextField(10);
    txtField.setEditable(false);

    // this JButton will allow user to search through directory
    browse = new JButton("...");

    fileChooserPanel.add(new JLabel(" Floor Plan file:"));
    fileChooserPanel.add(txtField);
    fileChooserPanel.add(browse);

    JPanel drawPanel = new JPanel(new GridLayout(1, 5));

    // JButton to display the rooms as described by the given text file.
    rooms = new JButton("ROOMS");

    // JButton to display the nodes of the binary floor plan tree.
    nodes = new JButton("NODES");

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

  public void setBrowseActionListener(ActionListener actionListener) {
    browse.addActionListener(actionListener);
  }

  public void setRoomsActionListener(ActionListener actionListener) {
    rooms.addActionListener(actionListener);
  }

  public void setNodesActionListener(ActionListener actionListener) {
    nodes.addActionListener(actionListener);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("FileChooserPanel");
    frame.setContentPane(new MainPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
