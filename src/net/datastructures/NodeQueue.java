package net.datastructures;

import net.datastructures.exceptions.EmptyQueueException;
import net.datastructures.interfaces.Queue;

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

  /** @return String representation of Queue, ex. (1, 2, 3) */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");

    if (!isEmpty()) {
      Node head = this.head;
      do {
        stringBuilder.append(head.getElement());
        if (head != tail) {
          stringBuilder.append(", ");
        }
        head = head.getNext();
      } while (head != null);
    }

    stringBuilder.append(")");
    return stringBuilder.toString();
  }

  /**
   * Prints information about an operation and the queue.
   * @param operation operation performed
   * @param element element returned by the operation
   * @return information about the operation performed, the element returned by
   *         the operation and the content of the stack after the operation.
   */
  public static void status(Queue queue, String operation, Object element) {
    System.out.println("---------------------------------");
    System.out.println(operation);
    System.out.println("Returned: " + element);
    System.out.println("size = " + queue.size() + ", " + (queue.isEmpty() ? "empty" : "not empty"));
    System.out.println("Queue: " + queue);
  }

  /**
   * Test program that performs a series of operations on on a queue and prints
   * the operation performed, the returned element and the content of the stack
   * after each operation.
   */
  public static void main(String[] args) {
    Queue<Integer> queue = new NodeQueue<Integer>();
    status(queue, "New empty queue", null);

    queue.enqueue(5);
    status(queue, "enqueue(5)", null);

    queue.enqueue(3);
    status(queue, "enqueue(3)", null);

    queue.enqueue(7);
    status(queue, "enqueue(7)", null);

    Object obj = queue.dequeue();
    status(queue, "dequeue()", obj);

    queue.enqueue(9);
    status(queue, "enqueue(9)", null);

    obj = queue.dequeue();
    status(queue, "dequeue()", obj);

    obj = queue.front();
    status(queue, "front()", obj);
  }
}
