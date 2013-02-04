import java.util.NoSuchElementException;

/**
 Class to represent a Queue in a Linked List.  
 Queues order elements in a first in first out (FIFO).
 @author Tim Margush, commented by Todd Gerspacher
*/
public class LinkedQueue // implements Queue // commented out by Todd Gerspacher
{
 /**
  Class to represent Links in Queue.  
  Each Link stores an Object data and a reference to the next link
  @author Tim Margush, commented by Todd Gerspacher
 */ 
 private class Link
 {
  public Object data;
  public Link next;

  /**
   Constructor creates a new Link with the given Object data
    and reference to the next Link
   @param d Object data to store in link 
   @param n the next Link in Queue
  */
  public Link(Object d, Link n)
  {
   data = d;
   next = n;
  }
 }

 private Link first = null;
 private Link last = null;
 private int size = 0;

 /**
   Method to push (add) an Object to the end of the LinkedQueue.
   Method name changed from add to push by Todd Gerspacher
   @param o the Object to added to Queue
 */	
 public void push(Object o)
 {   
  if (last==null)
   last = first = new Link(o, null);  // first element added to Queue
  else 
   last = last.next = new Link(o, null); 

  size++;
 }

 /**
   Method to pop (remove) the first element in the LinkedQueue and return its stored data.
   Method name changed from remove to pop by Todd Gerspacher
   @throws NoSuchElementException if the Queue is empty
   @return Object the Object stored in the first element
 */
 public Object pop() throws NoSuchElementException
 {
  // test for empty queue
  if (first==null)
   throw new NoSuchElementException("Queue is empty");

  Object temp = first.data; // temporary store first element's data
  first = first.next; // move first reference to the next element

  if (first == null) // last element pop'd ?
   last = null; // release last's Link reference

  size--;

  return temp;
 }

 /**
  Method to return the data stored in the first element of Queue
  Does not remove the first element from Queue
  @throws NoSuchElementException if Queue is empty
  @return Object the stored data in the first element 
 */
 public Object front() throws NoSuchElementException
 {
  if (first==null)
   throw new NoSuchElementException("Queue is empty");

  return first.data;
 }

 /**
  Method to determine if Queue is empty
  @return boolean true iff first element is empty
 */
 public boolean isEmpty()
 {
  return first==null;
 }

 /**
  Method to determine number of elements stored in Queue
  @return int size of Queue
 */
 public int size()
 {
  return size;
 }
}