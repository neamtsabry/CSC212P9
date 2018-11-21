package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;

	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}

	@Override
	public T removeFront() {
		// check if the list is empty
		checkNotEmpty();
		
		// if there's only one item in the list
		if(this.size()==1) {
			// save the that item
			T removed = start.value;
			
			// set both start and end to null 
			start = null;
			end = null;
			
			// return the removed item
			return removed;
		} else {
			// if it has more than one item
			
			// then we we will save the first item.
			T removed = start.value;
			
			// make sure that the item after is the new start
			start = start.after;
			
			// and if it's null, to make sure the item
			// before it is null
			if (start != null) {
				start.before = null;
			} 
			return removed;
		}
	}

	/*
	 * same as removeFront, O(1)
	 */
	@Override
	public T removeBack() {
		// see if the list is empty
		checkNotEmpty();

		// if there is one item in the list
		// just remove the first item
		if (this.size() == 1) {
			T removed = start.value;
			start = null;
			return removed;
		} else {
			// otherwise remove the end
			T removed = end.value;
			end = end.before;

			// make sure the end is not null
			if (end != null) {
				end.after = null;
			}
			return removed;
		}
	}

	/*
	 * This will take O(n) time because we need to access every time in the list
	 * with the for loop. If we think about the number of items in the list as n,
	 * and if this is the worst case scenario (that the index we want to remove is
	 * at the end), we will have O(n).
	 */
	@Override
	public T removeIndex(int index) {
		// check if list is empty
		checkNotEmpty();

		// if removing the first item
		if (index == 0) {
			return this.removeFront();
		}

		// if removing last item
		if (index == this.size()) {
			return this.removeBack();
		}

		// have a counter that starts at 0
		int at = 0;

		// go through all items in the list until you find
		// the desired index
		for (Node<T> current = start; current != null; current = current.after) {
			if (at == (index - 1)) {
				T removed = current.after.value;
				current.after = current.after.after;
				return removed;
			}
			at++;
		}

		// if you get here that means the index does not exist
		// so throw this error
		throw new BadIndexError();
	}

	/*
	 * O(1), we're just adding to the front
	 * 
	 * This method saves the start value, sets 
	 * the item after it to a new node with the item
	 * we would like to add and the item before it
	 * to be null (since it's at the beginning of the 
	 * list). 
	 */
	@Override
	public void addFront(T item) {
		Node<T> second = start;
		this.start = new Node<T>(item);
		start.after = second;
		start.before = null;
		if (second != null) {
			second.before = start;
		} else {
			end = start;
		}
	}

	/*
	 * same as addFront, O(1)
	 */
	@Override
	public void addBack(T item) {
		// make a new node
		Node<T> newNode = new Node<T>(item);

		// if list is empty
		if (this.end == null) {
			// just add item to front
			addFront(item);
		} else {
			// otherwise, make sure the end and
			// the new node are pointing to the right things

			// new node should be at the end so it
			// would be pointing to null right after it
			// and pointing back to the now previous end
			newNode.after = null;
			newNode.before = this.end;

			// and our end would be pointing at the new node
			this.end.after = newNode;

			// now the new node is the end
			this.end = newNode;
		}
	}

	/*
	 * O(n), we're going through all items in the list in worst case scenario
	 */
	@Override
	public void addIndex(T item, int index) {
		// if the list is empty
		if (this.isEmpty() || (index == 0)) {
			addFront(item);
		}

		// if index is out of bound
		if (index < 0 || index > this.size()) {
			throw new BadIndexError();
		}

		// if adding to the back
		if (index == this.size()) {
			addBack(item);
		}

		// if adding somewhere in the middle
		else {
			// have a counter
			int at = 0;

			// go through nodes
			for (Node<T> current = start; current != null; current = current.after) {
				if (at == (index - 1)) {
					// make a new node
					Node<T> c = new Node<>(item);

					// make sure we have the right pointers
					c.after = current.after;

					// c points at null and the current
					c.before = current;

					// current should be pointing at c
					current.after.before = c;

					// c should be current now
					current.after = c;

				}
				at++;
			}
		}
	}

	/*
	 * O(1)
	 */
	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;
	}

	/*
	 * O(1)
	 */
	@Override
	public T getBack() {
		// if list is empty
		checkNotEmpty();

		return end.value;
	}

	/*
	 * Going through all items, O(n)
	 */
	@Override
	public T getIndex(int index) {
		checkNotEmpty();

		int at = 0;

		for (Node<T> current = start; current != null; current = current.after) {
			if (at == (index)) {
				T found = current.value;
				return found;
			}
			at++;
		}
		throw new BadIndexError();
	}

	/*
	 * Here, we're going to go through all items O(n)
	 */
	@Override
	public int size() {
		int index = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			index++;
		}
		return index;
	}

	/*
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		// return if start is null 
		// list is empty if true
		return start == null;
	}

	/*
	 * O(1)
	 */
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of DoublyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
