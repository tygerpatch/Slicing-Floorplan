package net.datastructures;

/**
 * Node of a singly linked list, which stores references to its element and to the next node in the list.
 *
 * @author Natasha Gelfand
 * @author Roberto Tamassia
 * @author Michael Goodrich
 */
public class Node<E> {

  private E element;

  public E getElement() {
    return element;
  }

  public void setElement(E element) {
    this.element = element;
  }

  private Node<E> next;

  public Node<E> getNext() {
    return next;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }

  /** Creates a node with null references to its element and next node. */
  public Node() {
    this(null, null);
  }

  /** Creates a node with the given element and next node. */
  public Node(E element, Node<E> next) {
    this.element = element;
    this.next = next;
  }
}

