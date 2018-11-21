package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;

	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}

	@Override
	public T removeFront() {
		return removeIndex(0);
	}

	// O(1) because we're not shifting anything
	@Override
	public T removeBack() {
		// if list is empty
		if (fill == 0) {
			throw new EmptyListError();
		}
		// otherwise get the last time
		T value = this.getIndex(fill - 1);
		fill--;
		
		// set it to null and return it
		this.array[fill] = null;
		return value;
	}

	// O(n)
	@Override
	public T removeIndex(int index) {
		// if list is empty
		if (fill == 0) {
			throw new EmptyListError();
		}

		// if index does not exist
		if (index >= fill) {
			throw new BadIndexError();
		}
		
		T removed = this.getIndex(index);
		fill--;
		
		// move items one step after until we get to 
		// the index
		for (int i = index; i < fill; i++) {
			this.array[i] = this.array[i + 1];
		}
		this.array[fill] = null;
		return removed;
	}
	
	/**
	 * Method that helps us make the growable list bigger
	 * when it runs out of space (it makes it twice 
	 * the original size)
	 */
	private void makeBigger() {
		// make a new array of a bigger size
		Object[] arr = new Object[this.array.length * 2];
		
		// add all items in old array to the new array
		for (int i = 0; i < fill; i++) {
			arr[i] = this.array[i];
		}
		
		// no save the new array to be our set list
		this.array = arr;
	}

	// O(n) because addIndex uses O(n) time
	@Override
	public void addFront(T item) {
		// check if the fill is bigger than the length 
		// of the array
		if (fill >= this.array.length) {
			makeBigger();
		}
		addIndex(item, 0);
	}

	// O(1)
	@Override
	public void addBack(T item) {
		// check if the fill is bigger than the length 
		// of the array
		if (fill >= this.array.length) {
			makeBigger();
		}
		this.array[fill++] = item;
	}

	// O(n) --> shifting items
	@Override
	public void addIndex(T item, int index) {
		// check if index is out of bounds
		if (index < 0) {
			throw new BadIndexError();
		}
		
		// make a bigger list if the fill is greater
		// than the length of the array
		if (fill >= this.array.length) {
			makeBigger();
		}
		
		// check if index is out of bounds 
		if (index > fill) {
			throw new BadIndexError();
		} else {
			// otherwise, move items one step behind 
			// to fit the new index
			for (int j = fill; j > index; j--) {
				array[j] = array[j - 1];
			}
			
			// set the item at the new index
			array[index] = item;
			fill++;
		}
	}

	// O(1), it's easy to get anything in this list
	@Override
	public T getFront() {
		// check if the list is empty
		if (fill == 0) {
			throw new EmptyListError();
		}
		return this.getIndex(0);
	}

	// O(1)
	@Override
	public T getBack() {
		// check if list is empty
		if (fill == 0) {
			throw new EmptyListError();
		}
		return this.getIndex(this.fill - 1);
	}

	/**
	 * Do not allow unchecked warnings in any other method. Keep the "guessing" the
	 * objects are actually a T here. Do that by calling this method instead of
	 * using the array directly.
	 * 
	 * O(1) -- Efficiency
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) {
		// check if index is out of bounds
		if (index < 0) {
			throw new BadIndexError();
		}
		if (index >= fill) {
			throw new BadIndexError();
		}
		return (T) this.array[index];
	}

	// O(1)
	@Override
	public int size() {
		return fill;
	}

	@Override
	public boolean isEmpty() {
		// returns if the fill is truly zero 
		return fill == 0;
	}

}
