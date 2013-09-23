package tree.binary.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import tree.binary.Node;

/*
 * @author Todd Gerspacher
 */
public class NodeTest {
  
  @Test
  public void testSetRightChild() {
    Node<String> parent = new Node<String>();
    Node<String> rightChild = new Node<String>();
    
    parent.setRightChild(rightChild);
    assertThat(parent.getRightChild(), is(rightChild));
    assertThat(parent.getChildren().size(), is(1));
    
    Node<String> leftChild = new Node<String>();
    parent.setRightChild(leftChild);
    assertThat(parent.getRightChild(), is(leftChild));
    assertThat(parent.getChildren().size(), is(1));
  }

  @Test
  public void testSetLeftChild() {
    Node<String> parent = new Node<String>();
    Node<String> leftChild = new Node<String>();
    
    parent.setLeftChild(leftChild);

    assertThat(parent.getLeftChild(), is(leftChild));
    assertThat(parent.getChildren().size(), is(1));
        
    Node<String> rightChild = new Node<String>();    
    parent.setLeftChild(rightChild);
    
    assertThat(parent.getLeftChild(), is(rightChild));
    assertThat(parent.getChildren().size(), is(1));
  }

  @Test
  public void testAddChild() {
    Node<String> parent = new Node<String>();
    Node<String> child = new Node<String>();

    try {
      parent.addChild(child);
      fail("Expected an UnsupportedOperationException");
    }
    catch(UnsupportedOperationException unsupportedOperation) {
     assertThat(unsupportedOperation.getMessage(), is("For Binary Trees, you must use either setLeftChild or setRightChild.")); 
    }
  }
  
  @Test
  public void testRemoveChild() {
    Node<String> parent = new Node<String>();
    Node<String> leftChild = new Node<String>();
    Node<String> rightChild = new Node<String>();
    
    parent.setLeftChild(leftChild);
    parent.setRightChild(rightChild);

    assertThat(parent.getLeftChild(), is(leftChild));
    assertThat(parent.getRightChild(), is(rightChild));
    assertThat(parent.getChildren().size(), is(2));

    assertThat(parent.removeChild(leftChild), is(true));
    assertThat(null == leftChild.getParent(), is(true));
    assertThat(null == parent.getLeftChild(), is(true));
    assertThat(parent.getChildren().size(), is(1));
  }  
}
