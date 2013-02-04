package test;

import net.datastructures.Node;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;

/**
 * Class to test implementation of Singly Linked List class named Node.
 * @author Todd Gerspacher
 * @see net.datastructures.Node
*/
public class NodeTest {

  @Test
  public void testEmptyConstructor() {
    Node<String> node = new Node<String>();

    String element = node.getElement();
    assertThat(element, is(equalTo(null)));

    Node<String> next = node.getNext();
    assertThat(next, is(equalTo(null)));
  }

  @Test
  public void testParameterizedConstructor() {
    Node<String> node = new Node<String>("Hello World", new Node<String>());

    String element = node.getElement();
    assertThat(element, is(equalTo("Hello World")));

    Node<String> next = node.getNext();
    assertThat(next.getElement(), is(equalTo(null)));
    assertThat(next.getNext(), is(equalTo(null)));
  }
}
