package tree.binary.src;

import tree.general.src.GeneralTreeNode;

public class BinaryTreeNode<E> extends GeneralTreeNode<E> {

  // Keep references to children for quick access.
  private BinaryTreeNode<E> leftChild;
  private BinaryTreeNode<E> rightChild;

  public void addChild(GeneralTreeNode<E> node) {
    if(null == leftChild) {
      setLeftChild((BinaryTreeNode<E>)node);
    }
    else if(null == rightChild) {
      setRightChild((BinaryTreeNode<E>)node);
    }

    // Make it the user's responsibility to put child nodes in proper place.
  }

  public BinaryTreeNode<E> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(BinaryTreeNode<E> node) {
    leftChild = node;
    leftChild.setParent(this);
    children.add(node);
    // TODO: how to prevent extra nodes
    // Suppose user called addChild twice
  }

  public BinaryTreeNode<E> getRightChild() {
    return rightChild;
  }

  public void setRightChild(BinaryTreeNode<E> node) {
    rightChild = node;
    rightChild.setParent(this);
    children.add(node);
  }
}
