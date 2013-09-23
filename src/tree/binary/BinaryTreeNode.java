package tree.binary;

/*
 * @author Todd Gerspacher
 */
public class BinaryTreeNode<E> extends tree.general.GeneralTreeNode<E> {

  // Keep references to children for quick access.
  private tree.binary.BinaryTreeNode<E> leftChild;
  private tree.binary.BinaryTreeNode<E> rightChild;

  public BinaryTreeNode() {
  }

  public BinaryTreeNode(E value) {
    this.value = value;
  }
  
  @Override
  public void addChild(tree.general.GeneralTreeNode<E> node) {
    throw new UnsupportedOperationException("For Binary Trees, you must use either setLeftChild or setRightChild.");
    // Make it the user's responsibility to put child nodes in proper place.
    // That is, it's ambiguous which subtree a grandchild should go.
  }

  @Override
  public boolean removeChild(tree.general.GeneralTreeNode<E> node) {
    super.removeChild(node);
    
    if(leftChild == node) {
      leftChild = null;
      return true;
    }
    
    if(rightChild == node) {
      rightChild = null;
      return true;
    }

    return false;
  }
  
  public tree.binary.BinaryTreeNode<E> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(tree.binary.BinaryTreeNode<E> node) {
    if(leftChild != null) {
      children.remove(leftChild);
    }
    
    super.addChild(node);
    leftChild = node;
  }

  public tree.binary.BinaryTreeNode<E> getRightChild() {
    return rightChild;
  }

  public void setRightChild(tree.binary.BinaryTreeNode<E> node) {
    if(rightChild != null) {
      children.remove(rightChild);
    }
    
    super.addChild(node);
    rightChild = node;    
  }
}
