package tree.binary;

public class Tree<E> extends tree.general.Tree<E> {

  public String inorder() {
    return inorder((tree.binary.Node<E>)root);
  }

  private String inorder(tree.binary.Node<E> node) {
    StringBuilder stringBuilder = new StringBuilder();

    if(null != node) {
      stringBuilder.append(inorder(node.getLeftChild()));
      stringBuilder.append(node.getValue());
      stringBuilder.append(inorder(node.getRightChild()));
    }

    return stringBuilder.toString();
  }

}
