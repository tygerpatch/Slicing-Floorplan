package src;

import gui.RoomPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tree.BinaryTree;

/*
 *  Handles the drawing of the rooms as is described by the text file.
 *  @author Todd Gerspacher
 */
public class RoomsActionListener implements ActionListener {

  private JTextField textField;

  public RoomsActionListener(JTextField textField) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String filePath = textField.getText();
    // if tree is null, then don’t do anything
    if ((filePath != null) && (filePath.length() > 0)) {
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

      BinaryTree<Character> tree = FloorPlanReader.buildTree(readLine);
      
      JFrame frame = new JFrame("Rooms");
      frame.setContentPane(new RoomPanel(tree));
      frame.setSize(200, 200);
      frame.setVisible(true);
    }
  }
}
