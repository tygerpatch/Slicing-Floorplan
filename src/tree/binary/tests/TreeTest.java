package tree.binary.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tree.binary.Node;
import tree.binary.Tree;

/*
 * @author Todd Gerspacher
 */
public class TreeTest {

  @Test
  public void testInorder() {
    Node<String> A = new Node<String>("A");
    Node<String> B = new Node<String>("B");
    Node<String> C = new Node<String>("C");
    Node<String> D = new Node<String>("D");
    Node<String> E = new Node<String>("E");
    Node<String> F = new Node<String>("F");
    Node<String> G = new Node<String>("G");
    Node<String> H = new Node<String>("H");
    Node<String> I = new Node<String>("I");
    
    A.setLeftChild(B);
    A.setRightChild(C);
    
    B.setRightChild(D);
    
    C.setLeftChild(E);
    C.setRightChild(F);
    
    E.setLeftChild(G);
    
    F.setLeftChild(H);
    F.setRightChild(I);
    
    Tree<String> tree = new Tree<String>();
    tree.setRoot(A);

    assertThat(tree.inorder(), is("BDAGECHFI"));
  }
}
