package tree.binary;

/*
 * @author Todd Gerspacher
 */
public class BinaryTree<E> extends tree.general.GeneralTree<E> {

  public String inorder() {
    return inorder((tree.binary.BinaryTreeNode<E>)root);
  }

  private String inorder(tree.binary.BinaryTreeNode<E> node) {
    StringBuilder stringBuilder = new StringBuilder();

    if(null != node) {
      stringBuilder.append(inorder(node.getLeftChild()));
      stringBuilder.append(node.getValue());
      stringBuilder.append(inorder(node.getRightChild()));
    }

    return stringBuilder.toString();
  }

}
