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

import tree.binary.BinaryTree;

/*
 * @author Todd Gerspacher
 */
public class BrowseActionListener implements ActionListener {

  private JTextField textField;

  public BrowseActionListener(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    JFileChooser fileChooser = new JFileChooser("./other");

    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      textField.setText(fileChooser.getSelectedFile().toString());
    }
  }
}
