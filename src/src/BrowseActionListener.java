package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tree.binary.Tree;

/*
 * @author Todd Gerspacher
 */
public class BrowseActionListener implements ActionListener {

  private JTextField textField;
  private Tree<Character> tree;

  public BrowseActionListener(JTextField textField, Tree<Character> tree) {
    this.textField = textField;
    this.tree = tree;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    JFileChooser fileChooser = new JFileChooser("./other");

    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      textField.setText(fileChooser.getSelectedFile().toString());

      String readLine;

      try {
        FileReader fileReader = new FileReader(textField.getText());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
          readLine = bufferedReader.readLine();
          bufferedReader.close();
          fileReader.close();
        }
        catch(IOException ioException) {
          JOptionPane.showMessageDialog(null, ioException.getMessage());
          return;
        }
      }
      catch(FileNotFoundException fileNotFoundException) {
        JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage());
        return;
      }

      tree = FloorPlanReader.buildTree(readLine);
    }
  }
}
