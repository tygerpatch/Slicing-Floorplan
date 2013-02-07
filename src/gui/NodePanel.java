package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.FloorPlanReader;
import tree.binary.Node;
import tree.binary.Tree;

/*
 *  This class extends from JPanel to handle drawing the nodes of the tree
 *  @author Todd Gerspacher
 */
public class NodePanel extends JPanel {
  private Tree<Character> tree;

  private static final int NODE_WIDTH = 20;
  private static final int NODE_HEIGHT = 20;

  public NodePanel(Tree<Character> tree) {
    this.tree = tree;
  }

  public void paint(Graphics graphics) {
    Node<Character> root = (Node<Character>)tree.getRoot();
    drawNode(graphics, 0, 0, root, null);
  }

  // Point is bottom of parent's node.  Used for drawing connector lines.
  private void drawNode(Graphics graphics, int x, int y, Node<Character> node, Point point) {
    if(null == node) {
      return;
    }

    int blanks = (int) Math.pow(2, tree.height(node)); // used for spacing nodes evenly
    char ch = node.getValue();

    if(('|' == ch) || ('-' == ch)) {
      // draw circle
      graphics.setColor(Color.YELLOW);
      graphics.fillOval(x + (blanks * NODE_WIDTH), y, NODE_WIDTH, NODE_HEIGHT);
      // fillOval(int x, int y, int width, int height)

      graphics.setColor(Color.BLACK);
      graphics.drawOval(x + (blanks * NODE_WIDTH), y, NODE_WIDTH, NODE_HEIGHT);
      // drawOval(int x, int y, int width, int height)
    }
    else {
      // drawSquare
      graphics.setColor(Color.GREEN);
      graphics.fillRect(x + (blanks * NODE_WIDTH), y, NODE_WIDTH, NODE_HEIGHT);
      // fillRect(int x, int y, int width, int height)

      graphics.setColor(Color.BLACK);
      graphics.drawRect(x + (blanks * NODE_WIDTH), y, NODE_WIDTH, NODE_HEIGHT);
      // drawRect(int x, int y, int width, int height)
    }

    // determine font and line spacing dimensions
    FontMetrics fontMetric = graphics.getFontMetrics();
    int charWidth = fontMetric.charWidth('X'); // 7
    int ascent = fontMetric.getAscent(); // 13

    graphics.drawString("" + node.getValue(), x + (blanks * NODE_WIDTH) + charWidth, y + ascent);

    if(null != point) {
      graphics.setColor(Color.BLACK);
      graphics.drawLine(x + (blanks * NODE_WIDTH) + (NODE_WIDTH/2), y, point.x, point.y);
    }

    // pass location of current node's bottom half
    point = new Point(x + (blanks * NODE_WIDTH) + (NODE_WIDTH/2), y + NODE_HEIGHT);

    drawNode(graphics, x, y + (NODE_HEIGHT * 2), node.getLeftChild(), point);
    drawNode(graphics, x + (blanks * NODE_WIDTH), y + (NODE_HEIGHT * 2), node.getRightChild(), point);
  }

  // tests the drawing of nodes
  public static void main(String[] args) {
    Tree<Character> tree = FloorPlanReader.buildTree("|-AB-|C-EFD");
    NodePanel panel = new NodePanel(tree);

    JFrame frame = new JFrame("Drawing Nodes test");
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
