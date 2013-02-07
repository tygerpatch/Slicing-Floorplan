package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.datastructures.NodeQueue;
import src.FloorPlanReader;
import tree.binary.Tree;
import tree.binary.Node;

/**
 * This class extends from JPanel to handle the drawing of rooms
 * @author Todd Gerpacher
 */
public class RoomPanel extends JPanel {
  private Tree<Character> tree;
  private NodeQueue<Rectangle> queue; // used in drawing the rooms

  /**
   * Constructor creates a new RoomPanel
   *
   * @param tree
   *          the Tree to use when drawing the rooms
   */
  public RoomPanel(Tree<Character> tree) {
    this.tree = tree;
  }

  /**
   * Override’s JPanel’s paint method to draw rooms
   *
   * @param g
   *          the Graphics object used in drawing rooms
   */
  public void paint(Graphics g) {
    drawRooms((Graphics2D) g, 10, 10, 100, 100);
  }

  /**
   * This method is called to draw the rooms as is described by the tree
   *
   * @param g
   *          the Graphics2D to draw the rooms on
   * @param x
   *          the integer x coordinate of floor’s top left corner
   * @param y
   *          the integer y coordinate of floor’s top left corner
   * @param width
   *          the width of the floor
   * @param height
   *          the height of the floor
   */
  public void drawRooms(Graphics2D g, int x, int y, int width, int height) {
    // first create a new LinkedQueue
    // and push on a rectangular representation of the floor (without rooms)
    queue = new NodeQueue<Rectangle>();
    queue.enqueue(new Rectangle(x, y, width, height));

    drawRooms((Node<Character>)tree.getRoot(), g);
  }

  /**
   * This recursive draws the individual rooms using inorder traversal
   *
   * @param node
   *          the node used to determine whether to draw a cut or a room
   * @param g
   *          the Graphics2D object to draw on
   */
  private void drawRooms(Node<Character> node, Graphics2D g) {
    switch (node.getValue()) {
      case '|':
        drawVerticalCut();
        break;
      case '-':
        drawHorizontalCut();
        break;
      default:
        drawRoom(node, g);
        break;
    }

    // does node’s left child need to be drawn ?
    if (node.getLeftChild() != null) {
      drawRooms(node.getLeftChild(), g);
    }

    // does node’s right child need to be drawn ?
    if (node.getRightChild() != null) {
      drawRooms(node.getRightChild(), g);
    }
  }

  /**
   * This method draws a vertical cut on the floor plan
   */
  private void drawVerticalCut() {
    // First pop off the last rectangle (to be used in cut)
    Rectangle temp = (Rectangle) queue.dequeue();
    NodeQueue<Rectangle> q = new NodeQueue<Rectangle>();

    // Next determine the new two rooms new width value
    int newWidth = (int) (temp.getWidth() / 2);

    // remove all old rectangle shapes, so that the
    // the new rectangle shapes can become first and second in queue
    while (!queue.isEmpty()) {
      q.enqueue(queue.dequeue());
    }

    // push the first half of room onto queue
    queue.enqueue(new Rectangle((int) temp.getX(), (int) temp.getY(), newWidth, (int) temp.getHeight()));

    // push the second half of room onto queue
    queue.enqueue(new Rectangle((int) temp.getX() + newWidth, (int) temp.getY(), newWidth, (int) temp.getHeight()));

    // replace old rectangle shapes into queue
    while (!q.isEmpty()) {
      queue.enqueue(q.dequeue());
    }
  }

  /**
   * This method draws a horizontal cut on the floor plan
   */
  private void drawHorizontalCut() {
    // First pop off the last rectangle (to be used in cut)
    Rectangle temp = (Rectangle) queue.dequeue();
    NodeQueue<Rectangle> q = new NodeQueue<Rectangle>();

    // Next determine the new two rooms new height value
    int newHeight = (int) (temp.getHeight() / 2);

    // remove all old rectangle shapes, so that the
    // the new rectangle shapes can become first and second in queue
    while (!queue.isEmpty()) {
      q.enqueue(queue.dequeue());
    }

    // push the first half of room onto queue
    queue.enqueue(new Rectangle((int) temp.getX(), (int) temp.getY(), (int) temp.getWidth(), newHeight));

    // push the second half of room onto queue
    queue.enqueue(new Rectangle((int) temp.getX(), (int) temp.getY() + newHeight, (int) temp.getWidth(), newHeight));

    // replace old rectangle shapes into queue
    while (!q.isEmpty()) {
      queue.enqueue(q.dequeue());
    }
  }

  /**
   * This method draws the rectangular outline of the first room in queue And
   * then prints the node’s ch value in the center of the room
   *
   * @param node
   *          the Node object containing room’s char identifier
   * @param g
   *          the Graphics2D object to draw on
   */
  private void drawRoom(Node<Character> node, Graphics2D g) {
    Rectangle temp = (Rectangle) queue.dequeue();

    float x = (float) (temp.getX() + (temp.getWidth() / 2));
    float y = (float) (temp.getY() + (temp.getHeight() / 2));

    g.setColor(Color.LIGHT_GRAY);
    g.fill(temp);

    g.setColor(Color.BLACK);
    g.draw(temp);
    g.drawString("" + node.getValue(), x, y);
  }

  public static void main(String[] args) {
    Tree<Character> tree = FloorPlanReader.buildTree("|-AB-|C-EFD");
    RoomPanel panel = new RoomPanel(tree);
    JFrame frame = new JFrame("FileChooserPanel");
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
