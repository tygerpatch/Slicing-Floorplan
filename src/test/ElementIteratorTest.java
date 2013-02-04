package test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import net.datastructures.ElementIterator;
import net.datastructures.NodePositionList;
import net.datastructures.PositionList;

import org.junit.Test;

public class ElementIteratorTest {

  @Test
  public void testDummy() {
    NodePositionList<String> list = new NodePositionList<String>();
    assertThat(list.isEmpty(), is(equalTo(true)));

    ElementIterator<String> iterator = new ElementIterator<String>(list);
    assertThat(iterator.hasNext(), is(equalTo(false)));

    try {
      iterator.remove();
      fail("Expected UnsupportedOperationException");
    }
    catch(UnsupportedOperationException unsupportedException) {
      assertThat(unsupportedException.getMessage(), is(equalTo("remove")));
    }

    try {
      iterator.next();
      fail("Expected NoSuchElementException");
    }
    catch(NoSuchElementException noSuchElement) {
      assertThat(noSuchElement.getMessage(), is(equalTo("No next element")));
    }
  }

  // TODO: test with a non empty NodePositionList list
}
