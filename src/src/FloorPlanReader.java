package src;

import java.util.Stack;

import tree.binary.BinaryTree;
import tree.binary.BinaryTreeNode;

/*
 * @author Todd Gerspacher
 */
public class FloorPlanReader {
  public static BinaryTree<Character> buildTree(String str) {
    char ch;
    BinaryTreeNode<Character> curr = null;
    BinaryTreeNode<Character> node = null;
    Stack<BinaryTreeNode<Character>> stack = new Stack<BinaryTreeNode<Character>>();
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
    BinaryTreeNode<Character> root = stack.size() > 0 ? stack.pop() : curr;
    BinaryTree<Character> tree = new BinaryTree<Character>();
    tree.setRoot(root);

    return tree;
  }
}
