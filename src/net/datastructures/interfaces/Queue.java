package net.datastructures.interfaces;

import net.datastructures.exceptions.EmptyQueueException;

// The following interface was taken from page 206 of the book
// "Data Structures & Algorithms in Java" (4th edition) by Michael T. Goodrich & Roberto Tamassia

/**
 * Interface for a queue: a collection of elements that are inserted
 * and removed according to the first-in first-out principle.
 *
 * @author Michael T. Goodrich
 * @author Natasha Gelfand
 * @author Mark Handy
 * @author Roberto Tamassia
 * @see EmptyQueueException
*/
public interface Queue<E> {

  /** Returns the number of elements in the queue. */
  public int size();

  /** Returns true if the queue is empty, false otherwise. */
  public boolean isEmpty();

  /**
  * Inspects the element at the front of the queue.
  * @return element at the front of the queue.
  * @exception EmptyQueueException if the queue is empty.
  */
  public E front() throws EmptyQueueException;

  /** Inserts an element at the rear of the queue. */
  public void enqueue (E element);

  /**
  * Removes the element at the front of the queue.
  * @return element removed.
  * @exception EmptyQueueException if the queue is empty.
  */
  public E dequeue() throws EmptyQueueException;
}