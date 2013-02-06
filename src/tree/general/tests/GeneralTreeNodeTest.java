package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import tree.general.src.GeneralTreeNode;

public class GeneralTreeNodeTest {

  private GeneralTreeNode<String> root, child;

  @Before
  public void setUp() {
    root = new GeneralTreeNode<String>();
    child = new GeneralTreeNode<String>();

    root.addChild(child);
  }

  @Test
  public void testIsSiblingOf() {
    GeneralTreeNode<String> firstChild = child;
    GeneralTreeNode<String> secondChild = new GeneralTreeNode<String>();

    root.addChild(secondChild);

    assertThat(firstChild.isSiblingOf(secondChild), is(true));
    assertThat(secondChild.isSiblingOf(firstChild), is(true));
    assertThat(root.isSiblingOf(firstChild), is(false));
    assertThat(root.isSiblingOf(secondChild), is(false));
  }

  @Test
  public void testIsExternal() {
    assertThat(root.isExternal(), is(false));
    assertThat(child.isExternal(), is(true));
  }

  @Test
  public void testIsInternal() {
    assertThat(root.isInternal(), is(true));
    assertThat(child.isInternal(), is(false));
  }

  @Test
  public void testIsLeaf() {
    assertThat(root.isLeaf(), is(false));
    assertThat(child.isLeaf(), is(true));
  }

  @Test
  public void testIsAncestorOf() {
    GeneralTreeNode<String> alias = root;

    assertThat(root.isAncestorOf(root), is(true));
    assertThat(root.isAncestorOf(alias), is(true));
    assertThat(root.isAncestorOf(child), is(true));

    assertThat(child.isAncestorOf(root), is(false));
    assertThat(child.isAncestorOf(alias), is(false));
    assertThat(child.isAncestorOf(child), is(true));
  }

  @Test
  public void testIsDescendentOf() {
    GeneralTreeNode<String> alias = root;

    assertThat(root.isDescendentOf(root), is(true));
    assertThat(root.isDescendentOf(alias), is(true));
    assertThat(root.isDescendentOf(child), is(false));

    assertThat(child.isDescendentOf(root), is(true));
    assertThat(child.isDescendentOf(alias), is(true));
    assertThat(child.isDescendentOf(child), is(true));
  }
}
