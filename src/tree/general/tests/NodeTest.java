package tree.general.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import tree.general.Node;

public class NodeTest {

  @Test
  public  void testAddChild() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);
    
    assertThat(child.getParent(), is(root));
    
    List<Node<String>> children = root.getChildren();
    assertThat(children.size(), is(1));
    assertThat(children.get(0), is(child));
  }
  
  @Test
  public  void testRemoveChild() {
    Node<String> root = new Node<String>();        
    Node<String> child = new Node<String>();
    
    assertThat(root.removeChild(child), is(false));
    
    root.addChild(child);
    
    assertThat(root.removeChild(child), is(true));
    assertThat(null == child.getParent(), is(true));
    assertThat(root.getChildren().size(), is(0));
  }
    
  @Test
  public void testIsSiblingOf() {
    Node<String> root = new Node<String>();
    Node<String> firstChild = new Node<String>();
    Node<String> secondChild = new Node<String>();

    root.addChild(firstChild);
    root.addChild(secondChild);

    assertThat(firstChild.isSiblingOf(secondChild), is(true));
    assertThat(secondChild.isSiblingOf(firstChild), is(true));
    assertThat(root.isSiblingOf(firstChild), is(false));
    assertThat(root.isSiblingOf(secondChild), is(false));
  }

  @Test
  public void testIsExternal() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);

    assertThat(root.isExternal(), is(false));
    assertThat(child.isExternal(), is(true));
  }

  @Test
  public void testIsInternal() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);

    assertThat(root.isInternal(), is(true));
    assertThat(child.isInternal(), is(false));
  }

  @Test
  public void testIsLeaf() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);

    assertThat(root.isLeaf(), is(false));
    assertThat(child.isLeaf(), is(true));
  }

  @Test
  public void testIsAncestorOf() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);

    assertThat(root.isAncestorOf(root), is(true));
    assertThat(root.isAncestorOf(child), is(true));

    assertThat(child.isAncestorOf(root), is(false));
    assertThat(child.isAncestorOf(child), is(true));
  }

  @Test
  public void testIsDescendentOf() {
    Node<String> root = new Node<String>();
    Node<String> child = new Node<String>();
    
    root.addChild(child);

    assertThat(root.isDescendentOf(root), is(true));
    assertThat(root.isDescendentOf(child), is(false));

    assertThat(child.isDescendentOf(root), is(true));
    assertThat(child.isDescendentOf(child), is(true));
  }
}
