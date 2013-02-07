package tree.binary;

public class Node<E> extends tree.general.Node<E> {

  // Keep references to children for quick access.
  private tree.binary.Node<E> leftChild;
  private tree.binary.Node<E> rightChild;

  public Node() {
  }

  public Node(E value) {
    this.value = value;
  }
  
  @Override
  public void addChild(tree.general.Node<E> node) {
    throw new UnsupportedOperationException("For Binary Trees, you must use either setLeftChild or setRightChild.");
    // Make it the user's responsibility to put child nodes in proper place.
    // That is, it's ambiguous which subtree a grandchild should go.
  }

  @Override
  public boolean removeChild(tree.general.Node<E> node) {
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
  
  public tree.binary.Node<E> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(tree.binary.Node<E> node) {
    if(leftChild != null) {
      children.remove(leftChild);
    }
    
    super.addChild(node);
    leftChild = node;
  }

  public tree.binary.Node<E> getRightChild() {
    return rightChild;
  }

  public void setRightChild(tree.binary.Node<E> node) {
    if(rightChild != null) {
      children.remove(rightChild);
    }
    
    super.addChild(node);
    rightChild = node;    
  }
}
