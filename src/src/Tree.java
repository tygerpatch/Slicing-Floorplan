package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.datastructures.NodeQueue;

/**
 * This class will be used to represent the binary tree used in the Floor Plan program.
 * @author Todd Gerspacher
*/
public class Tree {
  private int visited = 0;            // used in generating NodeInfoList
  private NodeQueue<Rectangle> queue; // used in drawing the rooms

  /**
   * This class will represent the nodes in the tree
   * @author Todd Gerspacher
   */
  private static class Node {
    public Node left;
    public char ch;
    public Node right;

    /**
     * Constructor creates a new Node object
     * @param left the Node’s left child
     * @param ch the char value to store in Node
     * @param right the Node’s right child
     */
    public Node(Node left, char ch, Node right) {
      this.left = left;
      this.ch = ch;
      this.right = right;
    }
  }

  private Node root; // reference to the root node of the tree

  /**
   * This method will determine if a given ch value denotes a cut ‘-’ is a
   * horizontal cut and ‘|‘ is a vertical cut
   * 
   * @param ch the char to be tested
   * @return true if char represents a cut, false otherwise
   */
  public static boolean isCut(char ch) {
    return ((ch == '|') || (ch == '-'));
  }

  /**
   * This method allows will determine a creates A new node with the given ch value
   * @param ch the char value created Node should have
   */
  public void add(char ch) {
    if (root == null) {
      root = new Node(null, ch, null);
    }
    else {
      root = add(root, ch);
    }
  }

  /**
   * This potentially recursive method does an inorder traversal through The
   * tree to find the next available position in the tree and Then creates and
   * add’s a new node with the given ch value
   * 
   * @param parent the potential parent of the Node being added
   * @param ch the char value created Node should have
   */
  private Node add(Node parent, char ch) {
    Node temp;

    if (isCut(parent.ch)) {
      if (parent.left == null) {
        return new Node(new Node(null, ch, null), parent.ch, parent.right);
      }

      if (parent.left != (temp = add(parent.left, ch))) {
        return new Node(temp, parent.ch, parent.right);
      }

      if (parent.right == null) {
        return new Node(parent.left, parent.ch, new Node(null, ch, null));
      }

      if (parent.right != (temp = add(parent.right, ch))) {
        return new Node(parent.left, parent.ch, temp);
      }
    }

    return parent;
  }

  /**
   * This method returns the string represenetion of this FPTree object (using
   * preorder traversal).
   * 
   * For example if the following was this FPTree, with / and \ representing
   * references to child nodes,
   * 
   * - / \ | C / \ A B
   * 
   * then this toString method method will return the following string:
   * 
   * root : -\n +left : root : |\n ++left : root : A\n ++right : root : B\n
   * +right : root : C\n
   * 
   * NOTE: The newline character is represented here, but will not be display in
   * returned String. NOTE: +'s represent depth of the node. For example, the
   * root has no +'s because it has a depth of 0.
   * 
   * @return String representation of this FPTree object
   */
  public String toString() {
    if (root == null) {
      return "";
    }

    return toString(root, "+");
  }

  /**
   * This method does the actual conversion from FPTree to its String
   * representation.
   * 
   * If the root is not null, then this method: First concatenates "root : "
   * with the node's value and also '\n' (newline) Next if the node's left
   * reference is not null, it concatenates its String representation If the
   * node's right reference is not null either, it is concatenated to the String
   * also.
   * 
   * Finally the method returns concatenated String.
   * 
   * NOTE: If the Node has a null reference value, then its returned String is
   * "".
   * 
   * @param n
   *          Node to be represented as a String
   * @param depthRep
   *          String value to use to represent Node's depth
   * @return String representation of given Node
   */
  private String toString(Node node, String depthRep) {
    String str = "root : " + node.ch + '\n';

    if (node.left != null) {
      str = str + depthRep + "left : ";
      str = str + toString(node.left, depthRep + depthRep.charAt(0));
    }

    if (node.right != null) {
      str = str + depthRep + "right : ";
      str = str + toString(node.right, depthRep + depthRep.charAt(0));
    }

    return str;
  }

  /**
   * This method creates a NodeInfoList of containing all the node’s in the tree
   * (author: Michael T. Goodrich, title: Roberto Tamassia, Data Structures &
   * Algorithms in Java, publisher: John Wiley & Sons, Inc., edition: Fourth
   * edition, page number: 273)
   * 
   * @return NodeInfoList the created NodeInfoList
   */
  public NodeInfoList getNodeInfoList() {
    visited = 0;
    return nodeInfos(root, 0, 1);
  }

  /**
   * Method that actually creates the NodeInfo for each node in the FPTree.
   * 
   * @param node
   *          the Node to find NodeInfo for
   * @param depth
   *          Number of ancestors for the given node, excluding the node itself
   * @param num
   *          Number to use to represent the given node
   * 
   *          (author: Michael T. Goodrich, title: Roberto Tamassia, Data
   *          Structures & Algorithms in Java, publisher: John Wiley & Sons,
   *          Inc., edition: Fourth edition, page number: 273)
   *          (http://www.wvutech
   *          .edu/mclark/Data%20Structures/Assignments%202004
   *          /Extra%20Credit%20Assignment.htm)
   */
  private NodeInfoList nodeInfos(Node node, int depth, int num) {
    NodeInfoList list = new NodeInfoList();
    NodeInfoList subtree;

    // create NodeInfoList of the left subtree ?
    if (node.left != null) {
      subtree = nodeInfos(node.left, depth + 1, num * 2);
      list.addAll(subtree);
    }

    list.add(new NodeInfo(visited++, depth, node.ch, num));

    // create NodeInfoList of the right subtree ?
    if (node.right != null) {
      subtree = nodeInfos(node.right, depth + 1, (num * 2) + 1);
      list.addAll(subtree);
    }

    return list;
  }

  /**
   * This method is called to draw the rooms as is described by the tree
   * @param g the Graphics2D to draw the rooms on
   * @param x the integer x coordinate of floor’s top left corner
   * @param y the integer y coordinate of floor’s top left corner
   * @param width the width of the floor
   * @param height the height of the floor
   */
  public void drawRooms(Graphics2D g, int x, int y, int width, int height) {
    // first create a new LinkedQueue
    // and push on a rectangular representation of the floor (without rooms)
    queue = new NodeQueue<Rectangle>();
    queue.enqueue(new Rectangle(x, y, width, height));

    drawRooms(root, g);
  }

  /**
   * This recursive draws the individual rooms using inorder traversal
   * 
   * @param node the node used to determine whether to draw a cut or a room
   * @param g the Graphics2D object to draw on
   */
  private void drawRooms(Node node, Graphics2D g) {
    switch (node.ch) {
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
    if (node.left != null) {
      drawRooms(node.left, g);
    }

    // does node’s right child need to be drawn ?
    if (node.right != null) {
      drawRooms(node.right, g);
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
   * @param node the Node object containing room’s char identifier
   * @param g the Graphics2D object to draw on
   */
  private void drawRoom(Node node, Graphics2D g) {
    Rectangle temp = (Rectangle) queue.dequeue();

    float x = (float) (temp.getX() + (temp.getWidth() / 2));
    float y = (float) (temp.getY() + (temp.getHeight() / 2));

    g.setColor(Color.LIGHT_GRAY);
    g.fill(temp);

    g.setColor(Color.BLACK);
    g.draw(temp);
    g.drawString("" + node.ch, x, y);
  }

  /**
   * this method tests the creation of tree objects
   * 
   * Test #1 Given floor plan : "-AB" Purpose : Ability to do a basic floor
   * plane with one cut
   * 
   * Test #2 Given floor plan : "-ABC" Purpose : Ability to identify room
   * identifiers (which cannot hold child nodes).
   * 
   * Test #3 Given floor plan : "|-AB-|C-EFD" Purpose : Ability to do a complex
   * floor plan with multiple room identifiers and cuts
   */
  public static void main(String[] args) {
    String tests[] = new String[3];

    tests[0] = "-AB"; // basic floor plane with one cut
    tests[1] = "-ABC"; // a room identifier cannot be used as a cut
    tests[2] = "|-AB-|C-EFD"; // multiple room identifiers and cuts

    Tree tree;

    for (int i = 0; i < tests.length; i++) {
      tree = new Tree();

      for (int j = 0; j < tests[i].length(); j++) {
        tree.add(tests[i].charAt(j));
      }

      System.out.println("TEST # " + (i + 1) + '\n' + tree);
    }
  }
}
