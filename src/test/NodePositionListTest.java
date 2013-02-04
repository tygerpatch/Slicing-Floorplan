package test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import net.datastructures.EmptyListException;
import net.datastructures.InvalidPositionException;
import net.datastructures.NodePositionList;
import net.datastructures.Position;

import org.junit.Test;

public class NodePositionListTest {

  @Test
  public void testEmptyList() {
    NodePositionList<String> list = new NodePositionList<String>();
    assertThat(list.size(), is(equalTo(0)));
    assertThat(list.isEmpty(), is(equalTo(true)));

    try {
      list.first();
      fail("Expected EmptyListException");
    }
    catch(EmptyListException emptyList) {
      assertThat(emptyList.getMessage(), is(equalTo("List is empty")));
    }

    try {
      list.last();
      fail("Expected EmptyListException");
    }
    catch(EmptyListException emptyList) {
      assertThat(emptyList.getMessage(), is(equalTo("List is empty")));
    }

    try {
      list.prev(null);
      fail("Expected InvalidPositionException");
    }
    catch(InvalidPositionException invalidPosition) {
      assertThat(invalidPosition.getMessage(), is(equalTo("Null position passed to NodeList")));
    }

    try {
      list.next(null);
      fail("Expected InvalidPositionException");
    }
    catch(InvalidPositionException invalidPosition) {
      assertThat(invalidPosition.getMessage(), is(equalTo("Null position passed to NodeList")));
    }

    try {
      list.addBefore(null, null);
      fail("Expected InvalidPositionException");
    }
    catch(InvalidPositionException invalidPosition) {
      assertThat(invalidPosition.getMessage(), is(equalTo("Null position passed to NodeList")));
    }

    try {
      list.addAfter(null, null);
      fail("Expected InvalidPositionException");
    }
    catch(InvalidPositionException invalidPosition) {
      assertThat(invalidPosition.getMessage(), is(equalTo("Null position passed to NodeList")));
    }

    assertThat(list.toString(), is(equalTo("[]")));
    assertThat(NodePositionList.toString(list), is(equalTo("[]")));
    assertThat(NodePositionList.forEachToString(list), is(equalTo("[]")));
  }
  
  // TODO: more methods to test
}
