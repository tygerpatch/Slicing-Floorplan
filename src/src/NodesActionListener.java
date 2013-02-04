package src;

import gui.NodePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// Handles drawing of the nodes in the tree in a JFrame.
public class NodesActionListener implements ActionListener {

  private Tree tree;

  public NodesActionListener(Tree tree) {
    this.tree = tree;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if tree is null, then don’t do anything
    if (tree == null) {
      return;
    }

    JFrame frame = new JFrame("Tree");
    frame.setContentPane(new NodePanel(tree.getNodeInfoList(), 25));
    frame.setSize(200, 200);
    frame.setVisible(true);

    // NOTE: user can close this frame without terminating program
  }
}
