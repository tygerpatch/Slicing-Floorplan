package tree;

/*
 * @author Todd Gerspacher
 */
public class BinaryTreeNode<E> extends tree.GeneralTreeNode<E> {

  // Keep references to children for quick access.
  private tree.BinaryTreeNode<E> leftChild;
  private tree.BinaryTreeNode<E> rightChild;

  public BinaryTreeNode() {
  }

  public BinaryTreeNode(E value) {
    this.value = value;
  }

  @Override
  public void addChild(tree.GeneralTreeNode<E> node) {
    throw new UnsupportedOperationException("For Binary Trees, you must use either setLeftChild or setRightChild.");
    // Make it the user's responsibility to put child nodes in proper place.
    // That is, it's ambiguous which subtree a grandchild should go.
  }

  @Override
  public boolean removeChild(tree.GeneralTreeNode<E> node) {
    super.removeChild(node);

    if (leftChild == node) {
      leftChild = null;
      return true;
    }

    if (rightChild == node) {
      rightChild = null;
      return true;
    }

    return false;
  }

  public tree.BinaryTreeNode<E> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(tree.BinaryTreeNode<E> node) {
    if (leftChild != null) {
      children.remove(leftChild);
    }

    super.addChild(node);
    leftChild = node;
  }

  public tree.BinaryTreeNode<E> getRightChild() {
    return rightChild;
  }

  public void setRightChild(tree.BinaryTreeNode<E> node) {
    if (rightChild != null) {
      children.remove(rightChild);
    }

    super.addChild(node);
    rightChild = node;
  }
}
