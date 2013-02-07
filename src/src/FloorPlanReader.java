package src;

import java.util.Stack;

import tree.binary.Tree;
import tree.binary.Node;

/*
 * @author Todd Gerspacher
 */
public class FloorPlanReader {
  public static Tree<Character> buildTree(String str) {
    char ch;
    Node<Character> curr = null;
    Node<Character> node = null;
    Stack<Node<Character>> stack = new Stack<Node<Character>>();
    for(int i = 0; i < str.length(); i++) {
      ch = str.charAt(i);
      node = new Node<Character>();
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
    Node<Character> root = stack.size() > 0 ? stack.pop() : curr;
    Tree<Character> tree = new Tree<Character>();
    tree.setRoot(root);

    return tree;
  }
}
