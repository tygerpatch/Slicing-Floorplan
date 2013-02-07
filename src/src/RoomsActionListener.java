package src;

import gui.RoomPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tree.binary.Tree;

/*
 *  Handles the drawing of the rooms as is described by the text file.
 *  @author Todd Gerspacher
 */
public class RoomsActionListener implements ActionListener {

  private Tree<Character> tree;

  public RoomsActionListener(Tree<Character> tree) {
    this.tree = tree;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if tree is null, then don�t do anything
    if (tree == null) {
      return;
    }

    JFrame frame = new JFrame("Rooms");
    frame.setContentPane(new RoomPanel(tree));
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
