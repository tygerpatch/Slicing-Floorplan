package net.datastructures;

import java.util.Iterator;

/**
 * An implementation of the BinaryTree interface by means of a linked structure.
 * This class serves as a superclass for the BinarySearchTree implementation.
 *
 * This design decision was made to emphasize the conceptual relationship that a BinarySearchTree is a specialized LinkedBinaryTree.
 * An unwanted side-effect of this is that the {@link #size() size} method returns the number of total nodes 
 * whereas the {@link BinarySearchTree#size() size} method in the {@link BinarySearchTree BinarySearchTree} class returns
 * the number of internal nodes only.
 *
 * For this reason, the the {@link #size size} variable instead of the {@link #size() size} method is used within this class.
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 * @see BinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
  protected BTPosition<E> root; // reference to the root
  protected int size; // number of nodes

  /** Creates an empty binary tree. */
  public LinkedBinaryTree() {
    root = null; // start with an empty tree
    size = 0;
  }

  /** Returns the number of nodes in the tree. */
  public int size() {
    return size;
  }

  /** Returns whether the tree is empty. */
  public boolean isEmpty() {
    return (size == 0);
  }

  /** Returns whether a node is internal. */
  public boolean isInternal(Position<E> position) throws InvalidPositionException {
    checkPosition(position); // auxiliary method
    return (hasLeft(position) || hasRight(position));
  }

  /** Returns whether a node is external. */
  public boolean isExternal(Position<E> position) throws InvalidPositionException {
    return !isInternal(position);
  }

  /** Returns whether a node is the root. */
  public boolean isRoot(Position<E> position) throws InvalidPositionException {
    checkPosition(position);
    return (position == root());
  }

  /** Returns whether a node has a left child. */
  public boolean hasLeft(Position<E> position) throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(position);
    return (vv.getLeft() != null);
  }

  /** Returns whether a node has a right child. */
  public boolean hasRight(Position<E> position) throws InvalidPositionException {
    BTPosition<E> temp = checkPosition(position);
    return (temp.getRight() != null);
  }

  /** Returns the root of the tree. */
  public Position<E> root() throws EmptyTreeException {
    if (root == null) {
      throw new EmptyTreeException("The tree is empty");
    }
    return root;
  }

  /** Returns the left child of a node. */
  public Position<E> left(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    BTPosition<E> temp = checkPosition(position);
    Position<E> leftPos = temp.getLeft();
    if (leftPos == null) {
      throw new BoundaryViolationException("No left child");
    }
    return leftPos;
  }

  /** Returns the right child of a node. */
  public Position<E> right(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    BTPosition<E> temp = checkPosition(position);
    Position<E> rightPos = temp.getRight();
    if (rightPos == null) {
      throw new BoundaryViolationException("No right child");
    }
    return rightPos;
  }

  /** Returns the parent of a node. */
  public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    BTPosition<E> temp = checkPosition(position);
    Position<E> parentPos = temp.getParent();
    if (parentPos == null) {
      throw new BoundaryViolationException("No parent");
    }
    return parentPos;
  }

  /** Returns an iterable collection of the children of a node. */
  public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
    PositionList<Position<E>> children = new NodePositionList<Position<E>>();
    if (hasLeft(position)) {
      children.addLast(left(position));
    }
    if (hasRight(position)) {
      children.addLast(right(position));
    }
    return children;
  }

  /** Returns an iterable collection of the tree nodes. */
  public Iterable<Position<E>> positions() {
    PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
    if (size != 0)
      preorderPositions(root(), positions); // assign positions in preorder
    return positions;
  }

  /** Returns an iterator of the elements stored at the nodes */
  public Iterator<E> iterator() {
    Iterable<Position<E>> positions = positions();
    PositionList<E> elements = new NodePositionList<E>();
    for (Position<E> pos : positions) {
      elements.addLast(pos.element());
    }
    return elements.iterator(); // An iterator of elements
  }

  /** Replaces the element at a node. */
  public E replace(Position<E> position, E obj) throws InvalidPositionException {
    BTPosition<E> temp = checkPosition(position);
    E element = position.element();
    temp.setElement(obj);
    return element;
  }

  // Additional accessor method
  /** Return the sibling of a node */
  public Position<E> sibling(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
    BTPosition<E> temp = checkPosition(position);
    BTPosition<E> parentPos = temp.getParent();
    if (parentPos != null) {
      BTPosition<E> sibPos;
      BTPosition<E> leftPos = parentPos.getLeft();
      if (leftPos == temp) {
        sibPos = parentPos.getRight();
      }
      else {
        sibPos = parentPos.getLeft();
      }
      if (sibPos != null) {
        return sibPos;
      }
    }
    throw new BoundaryViolationException("No sibling");
  }

  // Additional update methods
  /** Adds a root node to an empty tree */
  public Position<E> addRoot(E element) throws NonEmptyTreeException {
    if (!isEmpty()) {
      throw new NonEmptyTreeException("Tree already has a root");
    }
    size = 1;
    root = createNode(element, null, null, null);
    return root;
  }

  /** Inserts a left child at a given node. */
  public Position<E> insertLeft(Position<E> position, E element) throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(position);
    Position<E> leftPos = vv.getLeft();
    if (leftPos != null) {
      throw new InvalidPositionException("Node already has a left child");
    }
    BTPosition<E> ww = createNode(element, vv, null, null);
    vv.setLeft(ww);
    size++;
    return ww;
  }

  /** Inserts a right child at a given node. */
  public Position<E> insertRight(Position<E> position, E element) throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(position);
    Position<E> rightPos = vv.getRight();
    if (rightPos != null) {
      throw new InvalidPositionException("Node already has a right child");
    }
    BTPosition<E> w = createNode(element, vv, null, null);
    vv.setRight(w);
    size++;
    return w;
  }

  /** Removes a node with zero or one child. */
  public E remove(Position<E> v) throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    BTPosition<E> leftPos = vv.getLeft();
    BTPosition<E> rightPos = vv.getRight();
    if (leftPos != null && rightPos != null)
      throw new InvalidPositionException("Cannot remove node with two children");
    BTPosition<E> ww; // the only child of v, if any
    if (leftPos != null)
      ww = leftPos;
    else if (rightPos != null)
      ww = rightPos;
    else
      // v is a leaf
      ww = null;
    if (vv == root) { // v is the root
      if (ww != null)
        ww.setParent(null);
      root = ww;
    }
    else { // v is not the root
      BTPosition<E> uu = vv.getParent();
      if (vv == uu.getLeft()) {
        uu.setLeft(ww);
      }
      else {
        uu.setRight(ww);
      }
      if (ww != null) {
        ww.setParent(uu);
      }
    }
    size--;
    return v.element();
  }

  /** Attaches two trees to be subtrees of an external node. */
  public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    if (isInternal(v))
      throw new InvalidPositionException("Cannot attach from internal node");
    if (!T1.isEmpty()) {
      BTPosition<E> r1 = checkPosition(T1.root());
      vv.setLeft(r1);
      r1.setParent(vv); // T1 should be invalidated
    }
    if (!T2.isEmpty()) {
      BTPosition<E> r2 = checkPosition(T2.root());
      vv.setRight(r2);
      r2.setParent(vv); // T2 should be invalidated
    }
  }

  /** Swap the elements at two nodes */
  public void swapElements(Position<E> position1, Position<E> position2) throws InvalidPositionException {
    BTPosition<E> btPosition1 = checkPosition(position1);
    BTPosition<E> btPosition2 = checkPosition(position2);
    E element = position2.element();
    btPosition2.setElement(position1.element());
    btPosition1.setElement(element);
  }

  /**
   * Expand an external node into an internal node with two external node
   * children
   */
  public void expandExternal(Position<E> position, E left, E right) throws InvalidPositionException {
    if (!isExternal(position)) {
      throw new InvalidPositionException("Node is not external");
    }
    insertLeft(position, left);
    insertRight(position, right);
  }

  /**
   * Remove an external node v and replace its parent with v's sibling
   */
  public void removeAboveExternal(Position<E> position) throws InvalidPositionException {
    if (!isExternal(position)) {
      throw new InvalidPositionException("Node is not external");
    }
    if (isRoot(position)) {
      remove(position);
    }
    else {
      Position<E> temp = parent(position);
      remove(position);
      remove(temp);
    }
  }

  // Auxiliary methods
  /** If v is a good binary tree node, cast to BTPosition, else throw exception */
  protected BTPosition<E> checkPosition(Position<E> position) throws InvalidPositionException {
    if (position == null || !(position instanceof BTPosition)) {
      throw new InvalidPositionException("The position is invalid");
    }
    return (BTPosition<E>) position;
  }

  /** Creates a new binary tree node */
  protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
    return new BTNode<E>(element, parent, left, right);
  }

  /**
   * Creates a list storing the the nodes in the subtree of a node, ordered
   * according to the preorder traversal of the subtree.
   */
  protected void preorderPositions(Position<E> position, PositionList<Position<E>> list) throws InvalidPositionException {
    list.addLast(position);
    if (hasLeft(position)) {
      preorderPositions(left(position), list); // recurse on left child
    }
    if (hasRight(position)) {
      preorderPositions(right(position), list); // recurse on right child
    }
  }

  /**
   * Creates a list storing the the nodes in the subtree of a node, ordered
   * according to the inorder traversal of the subtree.
   */
  protected void inorderPositions(Position<E> position, PositionList<Position<E>> list) throws InvalidPositionException {
    if (hasLeft(position)) {
      inorderPositions(left(position), list); // recurse on left child
    }
    list.addLast(position);
    if (hasRight(position)) {
      inorderPositions(right(position), list); // recurse on right child
    }
  }
}
