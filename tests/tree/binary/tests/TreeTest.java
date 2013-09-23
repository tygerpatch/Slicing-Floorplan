package tree.binary.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tree.binary.BinaryTreeNode;
import tree.binary.BinaryTree;

/*
 * @author Todd Gerspacher
 */
public class TreeTest {

  @Test
  public void testInorder() {
    BinaryTreeNode<String> A = new BinaryTreeNode<String>("A");
    BinaryTreeNode<String> B = new BinaryTreeNode<String>("B");
    BinaryTreeNode<String> C = new BinaryTreeNode<String>("C");
    BinaryTreeNode<String> D = new BinaryTreeNode<String>("D");
    BinaryTreeNode<String> E = new BinaryTreeNode<String>("E");
    BinaryTreeNode<String> F = new BinaryTreeNode<String>("F");
    BinaryTreeNode<String> G = new BinaryTreeNode<String>("G");
    BinaryTreeNode<String> H = new BinaryTreeNode<String>("H");
    BinaryTreeNode<String> I = new BinaryTreeNode<String>("I");

    A.setLeftChild(B);
    A.setRightChild(C);

    B.setRightChild(D);

    C.setLeftChild(E);
    C.setRightChild(F);

    E.setLeftChild(G);

    F.setLeftChild(H);
    F.setRightChild(I);

    BinaryTree<String> tree = new BinaryTree<String>();
    tree.setRoot(A);

    assertThat(tree.inorder(), is("BDAGECHFI"));
  }
}
