package tree.general;

import java.util.LinkedList;
import java.util.List;

/*
 * @author Todd Gerspacher
 */
public class Node<E> {

  protected Node<E> parent;

  public void setParent(Node<E> node) {
    parent = node;
  }

  public Node<E> getParent() {
    return parent;
  }

  protected E value;

  public void setValue(E value) {
    this.value = value;
  }

  public E getValue() {
    return value;
  }

  public Node() {
  }

  public Node(E value) {
    this.value = value;
  }

  protected List<Node<E>> children = new LinkedList<Node<E>>();

  public void addChild(Node<E> node) {
    children.add(node);
    node.setParent(this);
  }

  public boolean removeChild(Node<E> node) {
    if(children.remove(node)) {
      node.setParent(null);
      return true;
    }

    return false;
  }

  public List<Node<E>> getChildren() {
    return children;
  }

  // "Two nodes that are children of the same parent are siblings."
  public boolean isSiblingOf(Node<E> node) {
    return (this.parent == node.parent);
  }

  // "A node v is external if v has no children."
  public boolean isExternal() {
    return (children.size() == 0);
  }

  // "A node v is internal if it has one or more children."
  public boolean isInternal() {
    return (children.size() >= 1);
  }

  // "External nodes are also known as leaves."
  public boolean isLeaf() {
    return isExternal();
  }

  // "A node u is an ancestor of a node v if u = v or u is an ancestor of the parent of v."
  public boolean isAncestorOf(Node<E> node) {
    return ((this == node) || (this == node.getParent()));
  }

  // If node u is an ancestor of node v, then v is a descendent of u.
  public boolean isDescendentOf(Node<E> node) {
    return node.isAncestorOf(this);
  }
}
