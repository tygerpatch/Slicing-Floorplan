package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.Tree;

/**
 * This class extends from JPanel to handle the drawing of rooms
 * @author Todd Gerpacher
 */
public class RoomPanel extends JPanel {
  private Tree tree;

  /**
   * Constructor creates a new RoomPanel
   * @param tree the Tree to use when drawing the rooms
   */
  public RoomPanel(Tree tree) {
    this.tree = tree;
  }

  /**
   * Override’s JPanel’s paint method to draw rooms
   * @param g the Graphics object used in drawing rooms
   */
  public void paint(Graphics g) {
    tree.drawRooms((Graphics2D) g, 10, 10, 100, 100);
  }

  public static void main(String[] args) {
    Tree tree = new Tree();
    String inorder = "|-AB-|C-EFD";
    for(int i = 0; i < inorder.length(); i++) {
      tree.add(inorder.charAt(i));
    }
    RoomPanel panel = new RoomPanel(tree);
    JFrame frame = new JFrame("FileChooserPanel");
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
