package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tree.general.Tree;
import tree.general.Node;

/*
 * @author Todd Gerspacher
 */
public class TreeTest {

  @Test
  public void testGetSetRoot() {
    Node<String> root = new Node<String>();
    Tree<String> tree = new Tree<String>();

    assertThat(null == tree.getRoot(), is(true));

    tree.setRoot(root);
    assertThat(tree.getRoot(), is(root));
  }

  @Test
  public void testDepth() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    Node<String> grandchild = new Node<String>();

    root.addChild(child);
    child.addChild(grandchild);

    Tree<String> tree = new Tree<String>();
    tree.setRoot(root);

    assertThat(tree.depth(root), is(0));
    assertThat(tree.depth(child), is(1));
    assertThat(tree.depth(grandchild), is(2));
  }

  @Test
  public void testLevel() {
    Node<String> A = new Node<String>();
    Node<String> B = new Node<String>();
    Node<String> C = new Node<String>();
    Node<String> D = new Node<String>();
    Node<String> E = new Node<String>();
    Node<String> F = new Node<String>();
    Node<String> G = new Node<String>();
    Node<String> H = new Node<String>();
    Node<String> I = new Node<String>();

    A.addChild(B);
    A.addChild(C);

    B.addChild(D);

    C.addChild(E);
    C.addChild(F);

    E.addChild(G);

    F.addChild(H);
    F.addChild(I);

    Tree<String> tree = new Tree<String>();
    tree.setRoot(A);

    assertThat(tree.level(A), is(0));

    assertThat(tree.level(B), is(1));
    assertThat(tree.level(C), is(1));

    assertThat(tree.level(D), is(2));
    assertThat(tree.level(E), is(2));
    assertThat(tree.level(F), is(2));

    assertThat(tree.level(G), is(3));
    assertThat(tree.level(H), is(3));
    assertThat(tree.level(I), is(3));
  }

  @Test
  public void testHeight() {
    Node<String> parent = new Node<String>();
    Node<String> daughter = new Node<String>();
    Node<String> son = new Node<String>();

    parent.addChild(son);
    parent.addChild(daughter);

    Node<String> grandSon = new Node<String>();
    Node<String> grandDaughter = new Node<String>();

    daughter.addChild(grandSon);
    daughter.addChild(grandDaughter);

    Tree<String> tree = new Tree<String>();
    tree.setRoot(parent);

    assertThat(tree.height(parent), is(2));
    assertThat(tree.height(son), is(0));
    assertThat(tree.height(daughter), is(1));
    assertThat(tree.height(grandSon), is(0));
    assertThat(tree.height(grandDaughter), is(0));
  }

  @Test
  public void testPreorder() {
    Node<String> R = new Node<String>("R");
    Node<String> A = new Node<String>("A");
    Node<String> B = new Node<String>("B");
    Node<String> C = new Node<String>("C");
    Node<String> D = new Node<String>("D");
    Node<String> E = new Node<String>("E");
    Node<String> F = new Node<String>("F");

    A.addChild(C);
    A.addChild(D);
    A.addChild(E);

    B.addChild(F);

    R.addChild(A);
    R.addChild(B);

    Tree<String> tree = new Tree<String>();
    tree.setRoot(R);

    assertThat(tree.preorder(), is("RACDEBF"));
  }

  @Test
  public void testPostorder() {
    Node<String> R = new Node<String>("R");
    Node<String> A = new Node<String>("A");
    Node<String> B = new Node<String>("B");
    Node<String> C = new Node<String>("C");
    Node<String> D = new Node<String>("D");
    Node<String> E = new Node<String>("E");
    Node<String> F = new Node<String>("F");

    A.addChild(C);
    A.addChild(D);
    A.addChild(E);

    B.addChild(F);

    R.addChild(A);
    R.addChild(B);

    Tree<String> tree = new Tree<String>();
    tree.setRoot(R);

    assertThat(tree.postorder(), is("CDEAFBR"));
  }
}
