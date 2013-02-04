package src;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class is used to hold a list of NodeInfo objects
 * @author Todd Gerspacher
*/
public class NodeInfoList extends LinkedList<NodeInfo> {
  /**
   * This method overrides the LinkedList’s get method To allow user to retrieve
   * a NodeInfo object based on it’s number
   * 
   * @param num the number of the NodeInfo object to get
   * @return NodeInfo the desired NodeInfo with requested number, If the
   *         NodeInfo object was not found in list, then a null is returned
   */
  public NodeInfo get(int num) {
    // create a temporary NodeInfo object to use in NodeInfo’s equals method
    NodeInfo tmp = new NodeInfo(0, 0, '?', num);

    if (contains(tmp)) {
      return super.get(indexOf(tmp));
    }

    return null;
  }

  /**
   * This method overrides LinkedList’s corresponding add method to ensure That
   * their no NodeInfo’s with the same number
   * 
   * @param obj the NodeInfo object to be potentially added to list
   * @return true if NodeInfo was added, false otherwise
   */
  public boolean add(NodeInfo obj) {
    if (!contains(obj)) {
      return super.add(obj);
    }

    return false;
  }

  /**
   * This method overrides LinkedList’s corresponding add method to ensure That
   * their no NodeInfo’s with the same number Method also eliminates users
   * ability to place NodeInfo object anywhere in list
   * 
   * @param indx
   * @param obj the NodeInfo object to be potentially added to list
   */
  public void add(int indx, NodeInfo obj) {
    add(obj);
  }

  /**
   * This method overrides LinkedList’s corresponding add method to ensure That
   * their no NodeInfo’s with the same number
   * 
   * @param obj the NodeInfo object to be potentially added to list
   */
  public void addFirst(NodeInfo obj) {
    add(obj);
  }

  /**
   * This method overrides LinkedList’s corresponding add method to ensure That
   * their no NodeInfo’s with the same number
   * 
   * @param obj the NodeInfo object to be potentially added to list
   */
  public void addLast(NodeInfo obj) {
    add(obj);
  }

  /**
   * This method allows the NodeInfoList to be represented as a String
   * 
   * @return String the String representation of the NodeInfoList
   */
  public String toString() {
    String str = "["; // inclose NodeInfo’s in square brackets
    ListIterator<NodeInfo> it = listIterator(0);

    // iterate through NodeInfo’s, concatenating their String representations
    while (it.hasNext()) {
      str = str + it.next().toString() + ",";
    }

    // determine if a ‘,’ needs to be removed
    if (str.charAt(str.length() - 1) == ',') {
      str = str.substring(0, str.length() - 1);
    }

    str = str + "]";

    return str;
  }

  /**
   * This method tests creating a NodeInfoList, attempting to add a NodeInfo
   * With a duplicate number, and also displaying NodeInfoList in a String
   */
  public static void main(String[] args) {
    NodeInfoList list = new NodeInfoList();

    list.add(new NodeInfo(1, 2, 'a', 1));
    list.add(new NodeInfo(3, 4, 'b', 2));
    list.add(new NodeInfo(5, 6, 'c', 1));

    System.out.println("List : " + list + '\n');
    System.out.println("NodeInfo with a number of 1 : " + list.get(1) + '\n');
  }
}
