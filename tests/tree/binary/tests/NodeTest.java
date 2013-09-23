package tree.binary.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import tree.binary.BinaryTreeNode;

/*
 * @author Todd Gerspacher
 */
public class NodeTest {

  @Test
  public void testSetRightChild() {
    BinaryTreeNode<String> parent = new BinaryTreeNode<String>();
    BinaryTreeNode<String> rightChild = new BinaryTreeNode<String>();

    parent.setRightChild(rightChild);
    assertThat(parent.getRightChild(), is(rightChild));
    assertThat(parent.getChildren().size(), is(1));

    BinaryTreeNode<String> leftChild = new BinaryTreeNode<String>();
    parent.setRightChild(leftChild);
    assertThat(parent.getRightChild(), is(leftChild));
    assertThat(parent.getChildren().size(), is(1));
  }

  @Test
  public void testSetLeftChild() {
    BinaryTreeNode<String> parent = new BinaryTreeNode<String>();
    BinaryTreeNode<String> leftChild = new BinaryTreeNode<String>();

    parent.setLeftChild(leftChild);

    assertThat(parent.getLeftChild(), is(leftChild));
    assertThat(parent.getChildren().size(), is(1));

    BinaryTreeNode<String> rightChild = new BinaryTreeNode<String>();
    parent.setLeftChild(rightChild);

    assertThat(parent.getLeftChild(), is(rightChild));
    assertThat(parent.getChildren().size(), is(1));
  }

  @Test
  public void testAddChild() {
    BinaryTreeNode<String> parent = new BinaryTreeNode<String>();
    BinaryTreeNode<String> child = new BinaryTreeNode<String>();

    try {
      parent.addChild(child);
      fail("Expected an UnsupportedOperationException");
    }
    catch (UnsupportedOperationException unsupportedOperation) {
      assertThat(unsupportedOperation.getMessage(), is("For Binary Trees, you must use either setLeftChild or setRightChild."));
    }
  }

  @Test
  public void testRemoveChild() {
    BinaryTreeNode<String> parent = new BinaryTreeNode<String>();
    BinaryTreeNode<String> leftChild = new BinaryTreeNode<String>();
    BinaryTreeNode<String> rightChild = new BinaryTreeNode<String>();

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
