package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

/**
 * This interface defines the required operations for all Lists in P6.
 * 
 * @author jfoley
 *
 * @param <T> the type of item inside the list.
 */
public interface P6List<T> {
	/**
	 * Delete the item at the front of the list.
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	public T removeFront();

	/**
	 * Delete the item at the back of the list.
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	public T removeBack();

	/**
	 * Delete the item at the specified index in the list.
	 * 
	 * @param index a number from 0 to size (excluding size).
	 * @return the value that was removed.
	 * @throws EmptyListError if the list is empty.
	 * @throws BadIndexError  if the index does not exist.
	 */
	public T removeIndex(int index);

	/**
	 * Add an item to the front of this list. The item should be at getIndex(0)
	 * after this call.
	 * 
	 * @param item the data to add to the list.
	 */
	public void addFront(T item);

	/**
	 * Add an item to the back of this list. The item should be at
	 * getIndex(size()-1) after this call.
	 * 
	 * @param item the data to add to the list.
	 */
	public void addBack(T item);

	/**
	 * Get the first item in the list.
	 * @return the item.
	 * @throws EmptyListError
	 */
	public T getFront();

	/**
	 * Get the last item in the list.
	 * @return the item.
	 * @throws EmptyListError
	 */
	public T getBack();

	/**
	 * Add an item before ``index`` in this list. Therefore,
	 * {@code addIndex(item, 0)} is the same as {@code addFront(item)}.
	 * 
	 * @param item  the data to add to the list.
	 * @param index the index at which to add the item.
	 */
	public void addIndex(T item, int index);

	/**
	 * Find the index-th element of this list.
	 * 
	 * @param index a number from 0 to size, excluding size.
	 * @return the value at index.
	 * @throws BadIndexError if the index does not exist.
	 */
	public T getIndex(int index);

	/**
	 * Calculate the size of the list.
	 * 
	 * @return the length of the list, or zero if empty.
	 */
	public int size();

	/**
	 * This is true if the list is empty. This is usually implemented by looking at
	 * size() but that's a bad idea for linked lists.
	 * 
	 * @return true if the list is empty.
	 */
	public boolean isEmpty();
}
