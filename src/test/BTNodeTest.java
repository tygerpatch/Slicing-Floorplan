package test;

import net.datastructures.BTNode;
import net.datastructures.BTPosition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class BTNodeTest {
  
  @Test
  public void testEmptyConstructor() {
    BTNode<String> node = new BTNode<String>();
    assertThat(node.element(), is(equalTo(null)));
    assertThat(node.getLeft(), is(equalTo(null)));
    assertThat(node.getRight(), is(equalTo(null)));
    assertThat(node.getParent(), is(equalTo(null)));
  }
  
  @Test
  public void testGetSetElement() {
    BTNode<String> node = new BTNode<String>();
    String element = "Hello World";
    node.setElement(element);
    assertThat(node.element(), is(equalTo(element)));
  }
  
  @Test
  public void testGetSetLeft() {
    BTNode<String> node = new BTNode<String>();
    BTPosition<String> left = new BTNode<String>();
    node.setLeft(left);
    assertThat(node.getLeft(), is(equalTo(left)));
  }
  
  @Test
  public void testGetSetRight() {
    BTNode<String> node = new BTNode<String>();
    BTPosition<String> right = new BTNode<String>();
    node.setRight(right);
    assertThat(node.getRight(), is(equalTo(right)));
  }

  @Test
  public void testGetSetParent() {
    BTNode<String> node = new BTNode<String>();
    BTPosition<String> parent = new BTNode<String>();
    node.setParent(parent);
    assertThat(node.getParent(), is(equalTo(parent)));
  }

  // 
  @Test
  public void testParameterizedConstructor() {
    BTPosition<String> left = new BTNode<String>();
    BTPosition<String> right = new BTNode<String>();
    BTPosition<String> parent = new BTNode<String>();

    String element = "Hello World";
    BTNode<String> node = new BTNode<String>(element, parent, left, right);

    assertThat(node.element(), is(equalTo(element)));
    assertThat(node.getLeft(), is(equalTo(left)));
    assertThat(node.getRight(), is(equalTo(right)));
    assertThat(node.getParent(), is(equalTo(parent)));
  }
}
