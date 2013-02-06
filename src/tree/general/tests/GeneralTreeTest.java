package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import tree.general.src.GeneralTreeNode;
import tree.general.src.GeneralTree;

public class GeneralTreeTest {

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
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> firstChild = new GeneralTreeNode<String>();
    GeneralTreeNode<String> secondChild = new GeneralTreeNode<String>();

    root.addChild(firstChild);
    root.addChild(secondChild);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(root);

    assertThat(tree.level(root), is(0));
    assertThat(tree.level(firstChild), is(1));
    assertThat(tree.level(secondChild), is(1));
  }

  @Test
  public void testHeight() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();
    GeneralTreeNode<String> grandchild = new GeneralTreeNode<String>();

    root.addChild(child);
    child.addChild(grandchild);

    GeneralTree<String> tree = new GeneralTree<String>();
    tree.setRoot(root);

    assertThat(tree.height(root), is(2));
    assertThat(tree.height(child), is(1));
    assertThat(tree.height(grandchild), is(0));
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

    String result = tree.postorder();
    String expected = "CDEAFBR";
    assertThat(result, is(expected));
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

    String result = tree.preorder();
    String expected = "RACDEBF";
    assertThat(result, is(expected));
  }
}
