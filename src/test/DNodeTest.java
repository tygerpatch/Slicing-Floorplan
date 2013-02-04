package test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import net.datastructures.DNode;
import net.datastructures.InvalidPositionException;

import org.junit.Test;

public class DNodeTest {

  @Test
  public void testParameterizedConstructor() {
    String element = "Hello World";
    
    DNode<String> node = new DNode<String>(null, null, null);
    node.setElement(element);
    
    try {
      node.element();
      fail("Expected InvalidPositionException");
    }
    catch(InvalidPositionException invalidPosition){
      assertThat(invalidPosition.getMessage(), is(equalTo("Position is not in a list!")));
    }
    
    DNode<String> prev = new DNode<String>(null, null, null);
    DNode<String> next = new DNode<String>(null, null, null);

    node = new DNode<String>(prev, next, null);
    node.setElement(element);

    assertThat(node.element(), is(equalTo(element)));
    assertThat(node.getPrev(), is(equalTo(prev)));
    assertThat(node.getNext(), is(equalTo(next)));
  }
}
