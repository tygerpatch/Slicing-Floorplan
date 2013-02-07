package tree.general;

/*
 * @author Todd Gerspacher
 */
public class Tree<E> {
  // Allow subclasses to work with root.  That way they can customize GeneralTree methods.
  protected Node<E> root;

  public void setRoot(Node<E> node) {
    root = node;
  }

  public Node<E> getRoot() {
    return root;
  }

  // "If v is the root, then the depth of v is 0.
  // Otherwise, the depth of v is one plus the depth of the parent of v."
  public int depth(Node<E> node) {
    if (node == root) {
      return 0;
    }

    return 1 + depth(node.getParent());
  }

  public int level(Node<E> node) {
    int level = 0;
    Node<E> temp = node;
    while(null != temp.getParent()) {
      temp = temp.getParent();
      level++;
    }
    
    return level;
  }

  // "If v is an external node, then the height of v is 0.
  // Otherwise, the height of v is one plus the maximum height of a child of v."
  public int height(Node<E> node) {
    if (node.isExternal()) {
      return 0;
    }

    int height, maxHeight = 0;

    for (Node<E> child : node.getChildren()) {
      height = height(child);

      if (height > maxHeight) {
        maxHeight = height;
      }
    }

    return 1 + maxHeight;
  }

  public String preorder() {
    // start with the root node
    return preorder(root);
  }

  private String preorder(Node<E> node) {
    // visit node first
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(node.getValue());

    // perform preorder traversal on each subtree, from left to right
    for (Node<E> subtree : node.getChildren()) {
      stringBuilder.append(preorder(subtree));
    }

    return stringBuilder.toString();
  }

  public String postorder() {
    return postorder(getRoot());
  }

  private String postorder(Node<E> node) {
    StringBuilder stringBuilder = new StringBuilder();

    // visit children first, from left to right
    for (Node<E> child : node.getChildren()) {
      stringBuilder.append(postorder(child));
    }

    // visit root last
    stringBuilder.append(node.getValue());

    return stringBuilder.toString();
  }
}
