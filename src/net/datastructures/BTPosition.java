package net.datastructures;

/**
 * Interface for a node of a binary tree.
 * It maintains an element, a parent node, a left node, and a right node.
 *
 *  @author Michael Goodrich
 */
public interface BTPosition<E> extends Position<E> {
  public void setElement(E o);
  public BTPosition<E> getLeft(); 
  public void setLeft(BTPosition<E> v); 
  public BTPosition<E> getRight(); 
  public void setRight(BTPosition<E> v); 
  public BTPosition<E> getParent(); 
  public void setParent(BTPosition<E> v);
}

