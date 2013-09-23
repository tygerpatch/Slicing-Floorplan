package tree;

/*
 * @author Todd Gerspacher
 */
public class BinaryTree<E> extends tree.GeneralTree<E> {

  public String inorder() {
    return inorder((tree.BinaryTreeNode<E>)root);
  }

  private String inorder(tree.BinaryTreeNode<E> node) {
    StringBuilder stringBuilder = new StringBuilder();

    if(null != node) {
      stringBuilder.append(inorder(node.getLeftChild()));
      stringBuilder.append(node.getValue());
      stringBuilder.append(inorder(node.getRightChild()));
    }

    return stringBuilder.toString();
  }

}
