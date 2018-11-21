package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

/**
 * This is a data structure that has an array inside each node of a Linked List.
 * Therefore, we only make new nodes when they are full. Some remove operations
 * may be easier if you allow "chunks" to be partially filled.
 * 
 * @author jfoley
 * @param <T> - the type of item stored in the list.
 */
public class ChunkyLinkedList<T> implements P6List<T> {
	private int chunkSize;
	private SinglyLinkedList<FixedSizeList<T>> chunks;

	public ChunkyLinkedList(int chunkSize) {
		this.chunkSize = chunkSize;
		chunks = new SinglyLinkedList<>();
		chunks.addBack(new FixedSizeList<>(chunkSize));
	}

	@Override
	public T removeFront() {
		// check if the chunk is empty
		if (this.isEmpty()) {
			// throw empty list error
			throw new EmptyListError();
		} else {
			// we first go and get the first chunk
			FixedSizeList<T> firstChunk = this.chunks.getFront();
			T value = firstChunk.removeFront();
			if (firstChunk.isEmpty()) {
				this.chunks.removeFront();
			}
			return value;
		}
	}

	// O(n) because we're just accessing last item
	// and using get back method that is also O(n)
	@Override
	public T removeBack() {
		// check if the chunk is empty
		if (this.isEmpty()) {
			// throw empty list error
			throw new EmptyListError();
		} else {
			// we first go and get the first chunk
			FixedSizeList<T> lastChunk = this.chunks.getBack();
			T value = lastChunk.removeBack();
			if (lastChunk.isEmpty()) {
				this.chunks.removeBack();
			}
			return value;
		}
	}

	/*
	 * O(n^2)
	 * 
	 * 1) O(n) because we're using a for loop that iterates over the list and uses
	 * the methods size and remove index.
	 * 
	 * 2) Because of these two methods together, we will also have O(2n) ( see
	 * FixedSizedList )
	 * 
	 * Together, and because they're nested within each other, O(n^2)
	 */
	@Override
	public T removeIndex(int index) {
		// check if the chunk is empty
		if (this.isEmpty()) {
			// throw empty list error
			throw new EmptyListError();
		} else {
			// have a start value of 0 (a counter)
			int start = 0;

			// go through the chunks in the linked list
			for (FixedSizeList<T> chunk : this.chunks) {
				// calculate bounds of this chunk.
				int end = start + chunk.size();

				// Check whether the index should be in this chunk:
				if (start <= index && index < end) {
					return chunk.removeIndex(index - start);
				}

				// update bounds of next chunk.
				start = end;
			}
		}
		throw new BadIndexError();
	}

	// O(1)
	// all used methods are O(1)
	@Override
	public void addFront(T item) {
		// we first go and get the first chunk
		FixedSizeList<T> firstChunk = this.chunks.getFront();

		// if the first chunk has no empty spacre for our item
		if (firstChunk.size() == chunkSize) {
			// make a new chunk
			FixedSizeList<T> newChunk = new FixedSizeList<T>(chunkSize);
			// add chunk to the front
			chunks.addFront(newChunk);
			// add item to front of the chunk
			newChunk.addFront(item);
		} else {
			// if it's not full, just add it to the front of the front chunk
			firstChunk.addFront(item);
		}
	}

	/*
	 * O(n) because we're using methods (except for size and fixed sized list add
	 * back) from singly linked list that take up O(n) time
	 */
	@Override
	public void addBack(T item) {
		if (this.chunks.isEmpty()) {
			this.chunks.addBack(new FixedSizeList<T>(this.chunkSize));
		}
		// we first go and get the last chunk
		FixedSizeList<T> lastChunk = this.chunks.getBack();

		// if the last chunk has no empty spacre for our item
		if (lastChunk.size() == chunkSize) {
			// make a new chunk
			FixedSizeList<T> newChunk = new FixedSizeList<T>(chunkSize);
			// add chunk to the back
			chunks.addBack(newChunk);
			// add item to back of the chunk
			newChunk.addBack(item);
		} else {
			// if it's not full, just add it to the back of the last chunk
			lastChunk.addBack(item);
		}
	}

	/*
	 * 1) O(n) --> for loop that iterates through the list
	 * 
	 * 2) O(n) --> method add index from SLL (also remember that it is nested within
	 * the for loop)
	 * 
	 * TOTAL O(n^2)
	 */
	@Override
	public void addIndex(T item, int index) {
		// error debug print statement
		// System.err.println(">>AddIndex: "+index+" in size="+size());
		
		// check if list is empty 
		if (this.isEmpty()) {
			// throw empty list error
			this.addFront(item);
		} 
		else {
			// have a start value of 0 (a counter)
			int chunkNo = 0;
			
			// initialize start value at 0 
			int start = 0;
			
			// go through the chunks in the linked list
			for (FixedSizeList<T> chunk : this.chunks) {
				// calculate bounds of this chunk.
				int end = start + chunk.size();
				
				// Check whether the index should be in this chunk:
				if (start <= index && index <= end) {
					// if the chunk is full
					if (chunk.size() == chunkSize) {
						// make new chunk because there is no space
						FixedSizeList<T> newChunk = new FixedSizeList<T>(chunkSize);

						// add it to our list
						chunks.addIndex(newChunk, chunkNo + 1);
						if (index == end) {
							newChunk.addFront(item);
						} else {
							// add item to the middle of chunk:
							newChunk.addFront(chunk.removeBack());
							chunk.addIndex(item, index - start);
						}
					}

					else {
						// just add an item to the chunk
						chunk.addIndex(item, (index - start));
					}
					return;
				}
				// increment the chunk number
				chunkNo++;

				// set start to end so it can go to the next chunk
				start = end;
			}
			// if we get here, that means the index does not exist
			throw new BadIndexError();
		}
	}

	/*
	 * O(1) --> calls (only once) methods that are only called once
	 */
	@Override
	public T getFront() {
		return this.chunks.getFront().getFront();
	}

	/*
	 * O(n) --> get back from singly linked list requires O(n) time
	 */
	@Override
	public T getBack() {
		return this.chunks.getBack().getBack();
	}

	/*
	 * O(n) because of the for loop that goes through the list
	 */
	@Override
	public T getIndex(int index) {
		// if it's empty
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		// start a counter
		int start = 0;

		// go through every chunk in the linked list
		for (FixedSizeList<T> chunk : this.chunks) {
			// calculate bounds of this chunk.
			int end = start + chunk.size();

			// Check whether the index should be in this chunk:
			if (start <= index && index < end) {
				return chunk.getIndex(index - start);
			}

			// update bounds of next chunk.
			start = end;
		}
		// if we get here, that means the index does not exist
		throw new BadIndexError();
	}

	/*
	 * O(n) due to for loop
	 */
	@Override
	public int size() {
		int total = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			total += chunk.size();
		}
		return total;
	}

	/*
	 * O(1), calls only once
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
}
