package src;

import gui.NodePanel;
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

import tree.binary.Tree;

/*
 *  Handles drawing of the nodes in the tree in a JFrame.
 *  @author Todd Gerspacher
 */
public class NodesActionListener implements ActionListener {

  private JTextField textField;

  public NodesActionListener(JTextField textField) {
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

      Tree<Character> tree = FloorPlanReader.buildTree(readLine);
      
      JFrame frame = new JFrame("Nodes");
      frame.setContentPane(new NodePanel(tree));
      frame.setSize(200, 200);
      frame.setVisible(true);
    }
  }
  
}
