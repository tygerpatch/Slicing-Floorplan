package tree.binary.src;

import tree.general.src.GeneralTree;
import tree.general.src.GeneralTreeNode;

public class BinaryTree<E> extends GeneralTree<E> {

  public String inorder() {
    return inorder((BinaryTreeNode<E>)root);
  }

  private String inorder(BinaryTreeNode<E> node) {
    StringBuilder stringBuilder = new StringBuilder();

    if(null != node) {
      stringBuilder.append(inorder(node.getLeftChild()));
      stringBuilder.append(node.getValue());
      stringBuilder.append(inorder(node.getRightChild()));
    }

    return stringBuilder.toString();
  }

}
