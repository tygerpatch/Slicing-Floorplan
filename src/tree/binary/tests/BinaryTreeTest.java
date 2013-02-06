package tree.binary.tests;

import java.util.Stack;

import tree.binary.src.BinaryTree;
import tree.binary.src.BinaryTreeNode;

public class BinaryTreeTest {

  public static void main(String[] args) {
    String str = "|-AB-|C-EFD";
    char ch;
    BinaryTreeNode<Character> curr = null;
    BinaryTreeNode<Character> node = null;
    Stack<BinaryTreeNode<Character>> stack = new Stack<BinaryTreeNode<Character>>();
    System.out.println("start");
    for(int i = 0; i < str.length(); i++) {
      ch = str.charAt(i);
      node = new BinaryTreeNode<Character>();
      node.setValue(ch);
      if(null == curr) {
        curr = node;
      }
      else {
        while((null != curr.getLeftChild()) && (null != curr.getRightChild())) {
          curr = stack.pop();
        }
        if(null == curr.getLeftChild()) {
          curr.setLeftChild(node);
        }
        else {
          curr.setRightChild(node);
        }
        if(('|' == ch) || ('-' == ch)) {
          stack.push(curr);
          curr = node;
        }
      }
    }
    System.out.println("finished");
    BinaryTreeNode<Character> root = stack.pop();
    System.out.println("root's value = " + root.getValue());
    System.out.println("root's leftChild = " + root.getLeftChild().getValue());
    System.out.println("root's left grand Child = " + root.getLeftChild().getLeftChild().getValue());

    System.out.println("root's rightChild = " + root.getRightChild().getValue());
    System.out.println("root's right grand Child = " + root.getRightChild().getRightChild().getValue());

    BinaryTree<Character> tree = new BinaryTree<Character>();
    tree.setRoot(root);

    System.out.println("tree's preorder: " + tree.preorder());
    System.out.println("tree's postorder: " + tree.postorder());
    System.out.println("tree's inorder: " + tree.inorder());
  }
}
