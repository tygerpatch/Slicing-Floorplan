package test;

import java.util.NoSuchElementException;

import net.datastructures.Position;
import net.datastructures.PositionList;

public class ElementIteratorTest {

}

//protected PositionList<E> list; // the underlying list
//protected Position<E> cursor;   // the next position
//
///** Creates an element iterator over the given list. */
//public ElementIterator(PositionList<E> L) {
//  list = L;
//  cursor = (list.isEmpty())? null : list.first();
//}
//
///** Returns whether the iterator has a next object. */
//public boolean hasNext() {
//  return (cursor != null);
//}
//
///** Returns the next object in the iterator. */
//public E next() throws NoSuchElementException {
//  if (cursor == null)
//    throw new NoSuchElementException("No next element");
//  E toReturn = cursor.element();
//  cursor = (cursor == list.last())? null : list.next(cursor);
//  return toReturn;
//}
//
///** Throws an {@link UnsupportedOperationException} in all cases,
// * because removal is not a supported operation in this iterator.
// */
//public void remove() throws UnsupportedOperationException {
//  throw new UnsupportedOperationException("remove");
//}
