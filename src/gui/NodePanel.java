package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.NodeInfo;
import src.NodeInfoList;
import src.Tree;

/**
 * This class extends from JPanel to handle drawing the nodes of the tree
 * @author Todd Gerspacher
*/
public class NodePanel extends JPanel {
  private NodeInfoList list;
  private final int SHAPE_SIZE;
  private final int HALF_SIZE;

  /**
   * Constructor creates a NodePanel object
   * 
   * @param list the NodeInfoList containing all the NodeInfo’s to use
   * @param size the size of the shape’s to use in drawing
   */
  public NodePanel(NodeInfoList list, int size) {
    this.list = list;
    SHAPE_SIZE = size;
    HALF_SIZE = (int) size / 2;
  }

  /**
   * Overrides JPanel’s paint to draw the tree’s nodes
   * 
   * @param g the Graphics object to draw on
   */
  public void paint(Graphics g) {
    ListIterator<NodeInfo> it = list.listIterator(0);
    int num;

    // iterate through list, drawing nodes
    while (it.hasNext()) {
      num = it.next().num();

      // (http://www.wvutech.edu/mclark/Data%20Structures/Assignments%202004/Extra%20Credit%20Assignment.htm)
      draw(num, num * 2, g);
      draw(num, (num * 2) + 1, g);
    }
  }

  /**
   * This method does the actual drawing of the nodes
   * 
   * @param from the int value of which NodeInfo’s number to draw from
   * @param to the int value of which NodeInfo’s number to draw to
   * @param g the Graphics object to draw on
   */
  private void draw(int from, int to, Graphics g) {
    NodeInfo parent = list.get(from);
    NodeInfo child = list.get(to);

    if (child == null)
      return;

    int x1 = parent.x() * SHAPE_SIZE + SHAPE_SIZE;
    int y1 = parent.y() * SHAPE_SIZE + SHAPE_SIZE;
    int x2 = child.x() * SHAPE_SIZE + SHAPE_SIZE;
    int y2 = child.y() * SHAPE_SIZE + SHAPE_SIZE;

    g.setColor(Color.BLACK);
    g.drawLine(x1, y1, x2, y2);

    if (Tree.isCut(parent.ch()))
      drawCircle("" + parent.ch(), x1, y1, g);
    else
      drawSquare("" + parent.ch(), x1, y1, g);

    if (Tree.isCut(child.ch()))
      drawCircle("" + child.ch(), x2, y2, g);
    else
      drawSquare("" + child.ch(), x2, y2, g);
  }

  /**
   * Method draws a filled in green square around the specified coordinate And
   * then places a string in the middle of square
   * 
   * @param str the String to be placed in middle of square
   * @param x the x-coordinate of square’s top left corner
   * @param y the y-coordinate of square’s top left corner
   * @param g the Graphics object to draw on
   */
  private void drawSquare(String str, int x, int y, Graphics g) {
    g.setColor(Color.GREEN);
    g.fillRect(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);

    g.setColor(Color.BLACK);
    g.drawRect(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);

    g.drawString(str, x, y);
  }

  /**
   * Method draws a filled in yellow circle around the specified coordinate And
   * then places a string in the middle of circle
   * 
   * @param str the String to be placed in middle of circle
   * @param x the x-coordinate of circle’s top left corner
   * @param y the y-coordinate of circle’s top left corner
   * @param g the Graphics object to draw on
   */
  private void drawCircle(String str, int x, int y, Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillOval(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);

    g.setColor(Color.BLACK);
    g.drawOval(x - HALF_SIZE, y - HALF_SIZE, SHAPE_SIZE, SHAPE_SIZE);

    g.drawString(str, x, y);
  }

  // tests the drawing of nodes
  public static void main(String[] args) {
    Tree tree = new Tree();
    String str = "|-AB-|C-EFD";

    for (int i = 0; i < str.length(); i++) {
      tree.add(str.charAt(i));
    }

    JPanel panel = new NodePanel(tree.getNodeInfoList(), 25);

    JFrame frame = new JFrame("Drawing Nodes test");
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
