package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tree.GeneralTree;
import tree.GeneralTreeNode;

/*
 * @author Todd Gerspacher
 */
public class TreeTest {

  @Test
  public void testGetSetRoot() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTree<String> tree = new GeneralTree<String>();

    assertThat(null == tree.getRoot(), is(true));

    tree.setRoot(root);
    assertThat(tree.getRoot(), is(root));
  }

  @Test
  public void testDepth() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();
    GeneralTreeNode<String> grandchild = new GeneralTreeNode<String>();

    root.addChild(child);
    child.addChild(grandchild);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(root);

    assertThat(tree.depth(root), is(0));
    assertThat(tree.depth(child), is(1));
    assertThat(tree.depth(grandchild), is(2));
  }

  @Test
  public void testLevel() {
    GeneralTreeNode<String> A = new GeneralTreeNode<String>();
    GeneralTreeNode<String> B = new GeneralTreeNode<String>();
    GeneralTreeNode<String> C = new GeneralTreeNode<String>();
    GeneralTreeNode<String> D = new GeneralTreeNode<String>();
    GeneralTreeNode<String> E = new GeneralTreeNode<String>();
    GeneralTreeNode<String> F = new GeneralTreeNode<String>();
    GeneralTreeNode<String> G = new GeneralTreeNode<String>();
    GeneralTreeNode<String> H = new GeneralTreeNode<String>();
    GeneralTreeNode<String> I = new GeneralTreeNode<String>();

    A.addChild(B);
    A.addChild(C);

    B.addChild(D);

    C.addChild(E);
    C.addChild(F);

    E.addChild(G);

    F.addChild(H);
    F.addChild(I);

    GeneralTree<String> tree = new GeneralTree<String>();
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
    GeneralTreeNode<String> parent = new GeneralTreeNode<String>();
    GeneralTreeNode<String> daughter = new GeneralTreeNode<String>();
    GeneralTreeNode<String> son = new GeneralTreeNode<String>();

    parent.addChild(son);
    parent.addChild(daughter);

    GeneralTreeNode<String> grandSon = new GeneralTreeNode<String>();
    GeneralTreeNode<String> grandDaughter = new GeneralTreeNode<String>();

    daughter.addChild(grandSon);
    daughter.addChild(grandDaughter);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(parent);

    assertThat(tree.height(parent), is(2));
    assertThat(tree.height(son), is(0));
    assertThat(tree.height(daughter), is(1));
    assertThat(tree.height(grandSon), is(0));
    assertThat(tree.height(grandDaughter), is(0));
  }

  @Test
  public void testPreorder() {
    GeneralTreeNode<String> R = new GeneralTreeNode<String>("R");
    GeneralTreeNode<String> A = new GeneralTreeNode<String>("A");
    GeneralTreeNode<String> B = new GeneralTreeNode<String>("B");
    GeneralTreeNode<String> C = new GeneralTreeNode<String>("C");
    GeneralTreeNode<String> D = new GeneralTreeNode<String>("D");
    GeneralTreeNode<String> E = new GeneralTreeNode<String>("E");
    GeneralTreeNode<String> F = new GeneralTreeNode<String>("F");

    A.addChild(C);
    A.addChild(D);
    A.addChild(E);

    B.addChild(F);

    R.addChild(A);
    R.addChild(B);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(R);

    assertThat(tree.preorder(), is("RACDEBF"));
  }

  @Test
  public void testPostorder() {
    GeneralTreeNode<String> R = new GeneralTreeNode<String>("R");
    GeneralTreeNode<String> A = new GeneralTreeNode<String>("A");
    GeneralTreeNode<String> B = new GeneralTreeNode<String>("B");
    GeneralTreeNode<String> C = new GeneralTreeNode<String>("C");
    GeneralTreeNode<String> D = new GeneralTreeNode<String>("D");
    GeneralTreeNode<String> E = new GeneralTreeNode<String>("E");
    GeneralTreeNode<String> F = new GeneralTreeNode<String>("F");

    A.addChild(C);
    A.addChild(D);
    A.addChild(E);

    B.addChild(F);

    R.addChild(A);
    R.addChild(B);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(R);

    assertThat(tree.postorder(), is("CDEAFBR"));
  }
}
