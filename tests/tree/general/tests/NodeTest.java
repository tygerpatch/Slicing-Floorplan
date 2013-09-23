package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import tree.general.GeneralTreeNode;

/*
 * @author Todd Gerspacher
 */
public class NodeTest {

  @Test
  public void testAddChild() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(child.getParent(), is(root));

    List<GeneralTreeNode<String>> children = root.getChildren();
    assertThat(children.size(), is(1));
    assertThat(children.get(0), is(child));
  }

  @Test
  public void testRemoveChild() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    assertThat(root.removeChild(child), is(false));

    root.addChild(child);

    assertThat(root.removeChild(child), is(true));
    assertThat(null == child.getParent(), is(true));
    assertThat(root.getChildren().size(), is(0));
  }

  @Test
  public void testIsSiblingOf() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> firstChild = new GeneralTreeNode<String>();
    GeneralTreeNode<String> secondChild = new GeneralTreeNode<String>();

    root.addChild(firstChild);
    root.addChild(secondChild);

    assertThat(firstChild.isSiblingOf(secondChild), is(true));
    assertThat(secondChild.isSiblingOf(firstChild), is(true));
    assertThat(root.isSiblingOf(firstChild), is(false));
    assertThat(root.isSiblingOf(secondChild), is(false));
  }

  @Test
  public void testIsExternal() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(root.isExternal(), is(false));
    assertThat(child.isExternal(), is(true));
  }

  @Test
  public void testIsInternal() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(root.isInternal(), is(true));
    assertThat(child.isInternal(), is(false));
  }

  @Test
  public void testIsLeaf() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(root.isLeaf(), is(false));
    assertThat(child.isLeaf(), is(true));
  }

  @Test
  public void testIsAncestorOf() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(root.isAncestorOf(root), is(true));
    assertThat(root.isAncestorOf(child), is(true));

    assertThat(child.isAncestorOf(root), is(false));
    assertThat(child.isAncestorOf(child), is(true));
  }

  @Test
  public void testIsDescendentOf() {
    GeneralTreeNode<String> root = new GeneralTreeNode<String>();
    GeneralTreeNode<String> child = new GeneralTreeNode<String>();

    root.addChild(child);

    assertThat(root.isDescendentOf(root), is(true));
    assertThat(root.isDescendentOf(child), is(false));

    assertThat(child.isDescendentOf(root), is(true));
    assertThat(child.isDescendentOf(child), is(true));
  }
}
