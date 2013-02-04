package src;

/**
 * This class represents the nodes of the floor plan tree on a graph 
 * NOTE: uses integer for coordinates
 * 
 * @author Todd Gerspacher
 */
public class NodeInfo {
  private int x;
  private int y;
  private char ch;
  private int num;

  /**
   * Constructor creates a new NodeInfo object with the given arguments
   * 
   * @param x
   *          the int x coordinate of node on graph
   * @param y
   *          the int y coordinate of node on graph
   * @param ch
   *          the char value stored in node
   * @param num
   *          the number of the nodes, The root node will have a number of 1
   *          It’s left child’s number will be its parent * 2 It’s right child’s
   *          number will be its parent * 2 and then + 1
   */
  public NodeInfo(int x, int y, char ch, int num) {
    this.x = x;
    this.y = y;
    this.ch = ch;
    this.num = num;
  }

  /**
   * This method returns the x coordinate of where to display Node on graph
   * 
   * @param int the x-coordinate of node
   */
  public int x() {
    return x;
  }

  /**
   * This method returns the y coordinate of where to display Node on graph
   * 
   * @param int the y-coordinate of node
   */
  public int y() {
    return y;
  }

  /**
   * This method returns the char value stored in node
   * 
   * @param ch the char value
   */
  public char ch() {
    return ch;
  }

  /**
   * This method returns the number of the node
   * 
   * @param int the number of the node
   */
  public int num() {
    return num;
  }

  /**
   * This method overrides Object’s equals method This method is called to
   * determine if a given Object Is an instance of NodeInfo and if its number is
   * the same as this NodeInfo’s number
   * 
   * @param obj the Object that is being tested
   */
  public boolean equals(Object obj) {
    NodeInfo tmp;

    if (obj instanceof NodeInfo) {
      tmp = (NodeInfo) obj;
      return (num == tmp.num());
    }

    return false;
  }

  /**
   * This method allows a NodeInfo object to be represented as a String
   * 
   * @param String the String representation of this NodeInfo object
   */
  public String toString() {
    return new String("(" + x + "," + y + ") " + ch + " #" + num);
  }
}
