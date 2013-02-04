package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BrowseActionListener implements ActionListener {

  private JTextField textField;
  private Tree tree;

  public BrowseActionListener(JTextField textField, Tree tree) {
    this.textField = textField;
    this.tree = tree;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    JFileChooser fileChooser = new JFileChooser();

    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      textField.setText(fileChooser.getSelectedFile().toString());

      // next call method that handles the creation of the tree
      getTree();
    }
  }

  /** This method handles the creation of floor plan tree */
  private void getTree() {
    String str;

    // handle the reading of file in a try/catch block
    // in case of input/output errors (like file not found)
    try {
      BufferedReader reader = new BufferedReader(new FileReader(textField.getText()));

      str = reader.readLine();

      for (int i = 0; i < str.length(); i++) {
        tree.add(str.charAt(i));
      }
    }
    catch (IOException io) {
      JOptionPane.showMessageDialog(null, io.getMessage());
    }
  }

}
