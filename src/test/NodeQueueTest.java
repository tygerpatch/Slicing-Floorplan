package test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import net.datastructures.NodeQueue;
import net.datastructures.Queue;

import org.junit.Test;

/**
 * Class to test Singly Linked List based Queue class named NodeQueue.
 * Basically just converted the output of NodeQueue's main method into individual tests.
 * @author Todd Gerspacher
 * @see net.datastructures.Node
*/
public class NodeQueueTest {

  @Test
  public void testEmptyQueue() {
    Queue<Integer> queue = new NodeQueue<Integer>();
    helper(queue, 0, true, "()");
  }

  @Test
  public void testEnqueue() {
    Queue<Integer> queue = new NodeQueue<Integer>();

    queue.enqueue(5);
    helper(queue, 1, false, "(5)");

    queue.enqueue(3);
    helper(queue, 2, false, "(5, 3)");

    queue.enqueue(7);
    helper(queue, 3, false, "(5, 3, 7)");
  }

  @Test
  public void testDequeue() {
    Queue<Integer> queue = new NodeQueue<Integer>();
    queue.enqueue(5);
    queue.enqueue(3);
    queue.enqueue(7);

    assertThat(queue.dequeue(), is(equalTo(5)));
    helper(queue, 2, false, "(3, 7)");

    queue.enqueue(9);
    helper(queue, 3, false, "(3, 7, 9)");

    assertThat(queue.dequeue(), is(equalTo(3)));
    helper(queue, 2, false, "(7, 9)");
  }

  @Test
  public void testFront() {
    Queue<Integer> queue = new NodeQueue<Integer>();

    queue.enqueue(5);
    queue.enqueue(3);
    queue.enqueue(7);
    queue.dequeue();
    queue.enqueue(9);
    queue.dequeue();

    assertThat(queue.front(), is(equalTo(7)));
    helper(queue, 2, false, "(7, 9)");
  }

  private void helper(Queue queue, int size, boolean isEmpty, String str) {
    assertThat(queue.size(), is(equalTo(size)));
    assertThat(queue.isEmpty(), is(equalTo(isEmpty)));
    assertThat(queue.toString(), is(equalTo(str)));
  }
}
