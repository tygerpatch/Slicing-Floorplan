package src;

import gui.NodePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tree.binary.Tree;

/*
 *  Handles drawing of the nodes in the tree in a JFrame.
 *  @author Todd Gerspacher
 */
public class NodesActionListener implements ActionListener {

  private Tree<Character> tree;

  public NodesActionListener(Tree<Character> tree) {
    this.tree = tree;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if tree is null, then don’t do anything
    if (tree == null) {
      return;
    }

    JFrame frame = new JFrame("Tree");
    frame.setContentPane(new NodePanel(tree));
    frame.setSize(200, 200);
    frame.setVisible(true);

    // NOTE: user can close this frame without terminating program
  }
}
