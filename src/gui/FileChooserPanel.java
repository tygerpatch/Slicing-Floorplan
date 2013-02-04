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
 * @author Todd
 *
 */
public class FileChooserPanel extends JPanel {
  private JTextField txtField;
  private JButton browse;

  public FileChooserPanel() {
    setLayout(new GridLayout(1, 3));

    // this JTextField will display the path of file choosen
    txtField = new JTextField(10);
    txtField.setEditable(false);

    // this JButton will allow user to search through directory
    browse = new JButton("...");
    // browse.addActionListener(this);

    add(new JLabel(" Floor Plan file:"));
    add(txtField);
    add(browse);
  }

  // TODO: Remove this method, implemented to support existing code
  public JTextField getTextField() {
    return txtField;
  }

  // TODO: Remove this method, implemented to support existing code
  public JButton getButton() {
    return browse;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("FileChooserPanel");
    frame.setContentPane(new FileChooserPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
