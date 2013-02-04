package net.datastructures;

/**
 * Realization of a queue by means of a singly-linked list of nodes.
 * All operations are performed in constant time.
 * @author Roberto Tamassia
 * @See net.datastructure.Queue
 */
public class NodeQueue<E> implements Queue<E> {

  protected Node<E> head, tail;
  protected int size; // Keeps track of number of elements in queue

  /** Creates an empty queue. */
  public NodeQueue() {
    head = null;
    tail = null;
    size = 0;
  }

  /** Returns the number of elements in the queue. */
  public int size() {
    return size;
  }

  /** Returns true if the queue is empty, false otherwise. */
  public boolean isEmpty() {
    if ((head == null) && (tail == null)) {
      return true;
    }

    return false;
  }

  /**
   * Inspects the element at the front of the queue.
   * @return element at the front of the queue.
   * @exception EmptyQueueException if the queue is empty.
   */
  public E front() throws EmptyQueueException {
    if (size == 0) {
      throw new EmptyQueueException("Queue is empty.");
    }

    return head.getElement();
  }

  /** Inserts an element at the rear of the queue. */
  public void enqueue(E element) {
    Node<E> node = new Node<E>();
    node.setElement(element);
    node.setNext(null);

    // Special Case: Empty Queue
    if (size == 0) {
      head = node;
    }
    else {
      // add node at the tail of the list
      tail.setNext(node);
    }

    // update the reference to the tail node
    tail = node;
    size++;
  }

  /**
   * Removes the element at the front of the queue.
   * @return element removed.
   * @exception EmptyQueueException if the queue is empty.
   */
  public E dequeue() throws EmptyQueueException {
    if (size == 0) {
      throw new EmptyQueueException("Queue is empty.");
    }

    E element = head.getElement();
    head = head.getNext();
    size--;

    // Special Case: Empty Queue
    if (size == 0) {
      tail = null;
    }

    return element;
  }

  public String toString() {
    String s = "";
    s += "(";
    if (!isEmpty()) {
      Node p = head;
      do {
        s += p.getElement();
        if (p != tail)
          s += ", ";
        p = p.getNext();
      } while (p != null);
    }
    s += ")";
    return s;
  }

  /**
   * Prints information about an operation and the queue.
   * @param op operation performed
   * @param element element returned by the operation
   * @return information about the operation performed, the element returned by
   *         the operation and the content of the stack after the operation.
   */
  public static void status(Queue Q, String op, Object element) {
    System.out.println("---------------------------------");
    System.out.println(op);
    System.out.println("Returned: " + element);
    String emptyStatus;
    if (Q.isEmpty())
      emptyStatus = "empty";
    else
      emptyStatus = "not empty";
    System.out.println("size = " + Q.size() + ", " + emptyStatus);
    System.out.println("Queue: " + Q);
  }

  /**
   * Test program that performs a series of operations on on a queue and prints
   * the operation performed, the returned element and the content of the stack
   * after each operation.
   */
  public static void main(String[] args) {
    Object o;
    Queue<Integer> A = new NodeQueue<Integer>();
    status(A, "New empty queue", null);
    A.enqueue(5);
    status(A, "enqueue(5)", null);
    A.enqueue(3);
    status(A, "enqueue(3)", null);
    A.enqueue(7);
    status(A, "enqueue(7)", null);
    o = A.dequeue();
    status(A, "dequeue()", o);
    A.enqueue(9);
    status(A, "enqueue(9)", null);
    o = A.dequeue();
    status(A, "dequeue()", o);
    o = o = A.front();
    status(A, "front()", o);
  }
}
