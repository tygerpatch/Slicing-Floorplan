package net.datastructures;

import java.util.Iterator;

/**
 * Realization of a PositionList using a doubly-linked list of nodes.
 * @author Michael Goodrich, Natasha Gelfand, Roberto Tamassia, Eric Zamore
 */
public class NodePositionList<E> implements PositionList<E> {
  protected int numberOfElements;     // Number of elements in the list
  protected DNode<E> header, trailer; // Special sentinels

  /** Constructor that creates an empty list; O(1) time */
  public NodePositionList() {
    numberOfElements = 0;
    header = new DNode<E>(null, null, null);    // create header
    trailer = new DNode<E>(header, null, null); // create trailer
    header.setNext(trailer);                    // make header and trailer point to each other
  }

  /**
   * Checks if position is valid for this list and converts it to DNode if it is valid; O(1) time
   */
  protected DNode<E> checkPosition(Position<E> position) throws InvalidPositionException {
    if (position == null) {
      throw new InvalidPositionException("Null position passed to NodeList");
    }

    if (position == header) {
      throw new InvalidPositionException("The header node is not a valid position");
    }

    if (position == trailer) {
      throw new InvalidPositionException("The trailer node is not a valid position");
    }

    try {
      DNode<E> temp = (DNode<E>) position;

      if ((temp.getPrev() == null) || (temp.getNext() == null)) {
        throw new InvalidPositionException("Position does not belong to a valid NodeList");
      }

      return temp;
    }
    catch (ClassCastException e) {
      throw new InvalidPositionException("Position is of wrong type for this list");
    }
  }

  /** Returns the number of elements in the list; O(1) time */
  public int size() {
    return numberOfElements;
  }

  /** Returns whether the list is empty; O(1) time */
  public boolean isEmpty() {
    return (numberOfElements == 0);
  }

  /** Returns the first position in the list; O(1) time */
  public Position<E> first() throws EmptyListException {
    if (isEmpty()) {
      throw new EmptyListException("List is empty");
    }

    return header.getNext();
  }

  /** Returns the last position in the list; O(1) time */
  public Position<E> last() throws EmptyListException {
    if (isEmpty()) {
      throw new EmptyListException("List is empty");
    }

    return trailer.getPrev();
  }

  /** Returns the position before the given one; O(1) time */
  public Position<E> prev(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    DNode<E> v = checkPosition(position);
    DNode<E> prev = v.getPrev();

    if (prev == header) {
      throw new BoundaryViolationException("Cannot advance past the beginning of the list");
    }

    return prev;
  }

  /** Returns the position after the given one; O(1) time */
  public Position<E> next(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    DNode<E> v = checkPosition(position);
    DNode<E> next = v.getNext();

    if (next == trailer) {
      throw new BoundaryViolationException("Cannot advance past the end of the list");
    }

    return next;
  }

  /**
   * Insert the given element before the given position, returning the new
   * position; O(1) time
   */
  public void addBefore(Position<E> position, E element) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    numberOfElements++;
    DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
    v.getPrev().setNext(newNode);
    v.setPrev(newNode);
  }

  /**
   * Insert the given element after the given position, returning the new
   * position; O(1) time
   */
  public void addAfter(Position<E> position, E element) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    numberOfElements++;
    DNode<E> newNode = new DNode<E>(v, v.getNext(), element);
    v.getNext().setPrev(newNode);
    v.setNext(newNode);
  }

  /**
   * Insert the given element at the beginning of the list, returning the new
   * position; O(1) time
   */
  public void addFirst(E element) {
    numberOfElements++;
    DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
    header.getNext().setPrev(newNode);
    header.setNext(newNode);
  }

  /**
   * Insert the given element at the end of the list, returning the new
   * position; O(1) time
   */
  public void addLast(E element) {
    numberOfElements++;
    DNode<E> oldLast = trailer.getPrev();
    DNode<E> newNode = new DNode<E>(oldLast, trailer, element);
    oldLast.setNext(newNode);
    trailer.setPrev(newNode);
  }

  /** Remove the given position from the list; O(1) time */
  public E remove(Position<E> position) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    numberOfElements--;
    DNode<E> vPrev = v.getPrev();
    DNode<E> vNext = v.getNext();
    vPrev.setNext(vNext);
    vNext.setPrev(vPrev);
    E vElem = v.element();
    // unlink the position from the list and make it invalid
    v.setNext(null);
    v.setPrev(null);
    return vElem;
  }

  /**
   * Replace the element at the given position with the new element and return
   * the old element; O(1) time
   */
  public E set(Position<E> position, E element) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    E oldElt = v.element();
    v.setElement(element);
    return oldElt;
  }

  /** Returns an iterator of all the elements in the list. */
  public Iterator<E> iterator() {
    return new ElementIterator<E>(this);
  }

  /** Returns an iterable collection of all the nodes in the list. */
  public Iterable<Position<E>> positions() { // create a list of posiitons
    PositionList<Position<E>> P = new NodePositionList<Position<E>>();
    if (!isEmpty()) {
      Position<E> p = first();
      while (true) {
        P.addLast(p); // add position p as the last element of list P
        if (p == last()) {
          break;
        }
        p = next(p);
      }
    }
    return P; // return P as our Iterable object
  }

  // Convenience methods
  /** Returns whether a position is the first one; O(1) time */
  public boolean isFirst(Position<E> position) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    return v.getPrev() == header;
  }

  /** Returns whether a position is the last one; O(1) time */
  public boolean isLast(Position<E> position) throws InvalidPositionException {
    DNode<E> v = checkPosition(position);
    return v.getNext() == trailer;
  }

  /** Swap the elements of two give positions; O(1) time */
  public void swapElements(Position<E> a, Position<E> b) throws InvalidPositionException {
    DNode<E> pA = checkPosition(a);
    DNode<E> pB = checkPosition(b);
    E temp = pA.element();
    pA.setElement(pB.element());
    pB.setElement(temp);
  }

  /** Returns a textual representation of a given node list using for-each */
  public static <E> String forEachToString(PositionList<E> positionList) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    int i = positionList.size();
    for (E elem : positionList) {
      stringBuilder.append(elem);  // implicit cast of the element to String
      i--;
      if (i > 0) {
        stringBuilder.append(", ");  // separate elements with a comma
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  /** Returns a textual representation of a given node list */
  public static <E> String toString(PositionList<E> positionList) {
    Iterator<E> iterator = positionList.iterator();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    while (iterator.hasNext()) {
      stringBuilder.append(iterator.next());  // implicit cast of the next element to String
      if (iterator.hasNext()){
        stringBuilder.append(", ");
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  /** Returns a textual representation of the list */
  public String toString() {
    return toString(this);
  }
}
