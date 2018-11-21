package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

public class SinglyLinkedList<T> implements P6List<T>, Iterable<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.next;
		return before;
	}

	/*
	 * going through the items with for loop --> O(n)
	 */
	@Override
	public T removeBack() {
		checkNotEmpty();

		// Checks if there's only one item in the list
		if (start.next == null) {
			return this.removeFront();
		}

		// Where we have more than one item
		else {
			for (Node<T> current = start; current != null; current = current.next) {
				if (current.next.next == null) {
					T remove = current.next.value;
					current.next = null;
					return remove;
				}
			}
		}
		return null;
	}

	/*
	 * O(n) --> we're using for loop again
	 */
	@Override
	public T removeIndex(int index) {
		checkNotEmpty();

		// if removing the first item
		if (index == 0) {
			return this.removeFront();
		}

		// if removing last item
		if (index == this.size() - 1) {
			return this.removeBack();
		}

		int at = 0;

		for (Node<T> current = start; current != null; current = current.next) {
			if (at == (index - 1)) {
				T removed = current.next.value;
				current.next = current.next.next;
				return removed;
			}
			at++;
		}
		throw new BadIndexError();
	}

	/*
	 * O(1), because accessing first item
	 */
	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	/*
	 * O(n), going through items in the list if list is not empty
	 */
	@Override
	public void addBack(T item) {
		if (this.isEmpty()) {
			addFront(item);
		} else {
			for (Node<T> current = start; current != null; current = current.next) {
				if (current.next == null) {
					current.next = new Node<T>(item, null);
					break;
				}
			}
		}
	}

	/*
	 * O(n)
	 */
	@Override
	public void addIndex(T item, int index) {
		// if the list is empty
		if (this.isEmpty() || (index == 0)) {
			addFront(item);
		}

		// if the index is out of bounds
		if (index > (this.size()) || index < 0) {
			throw new BadIndexError();
		}

		// if index of item is after the last item
		if (index == (this.size())) {
			addBack(item);
		}

		// if adding somewhere in the middle
		else {
			int at = 0;

			for (Node<T> current = start; current != null; current = current.next) {
				if (at == (index - 1)) {
					Node<T> C = current.next;
					current.next = new Node<T>(item, C);
					break;
				}
				at++;
			}
		}
	}

	// O(1)
	@Override
	public T getFront() {
		if (start == null) {
			throw new EmptyListError();
		}
		return start.value;
	}

	// O(n)
	@Override
	public T getBack() {
		// if list is empty
		checkNotEmpty();

		// if we have one item in list
		if (start.next == null) {
			return start.value;
		}

		// every other condition
		for (Node<T> current = start; current != null; current = current.next) {
			if (current.next == null) {
				return current.value;
			}
		}
		throw new EmptyListError();
	}

	// O(n)
	@Override
	public T getIndex(int index) {
		checkNotEmpty();

		int at = 0;

		for (Node<T> current = start; current != null; current = current.next) {
			if (at == (index)) {
				T found = current.value;
				return found;
			}
			at++;
		}
		throw new BadIndexError();
	}

	// O(n) --> looping through list
	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	// O(1)
	@Override
	public boolean isEmpty() {
		return start == null;
	}

	/**
	 * Helper method to throw the right error for an empty state. O(1)
	 */
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * I'm providing this class so that SinglyLinkedList can be used in a for loop
	 * for {@linkplain ChunkyLinkedList}. This Iterator type is what java uses for
	 * {@code for (T x : list) { }} lops.
	 * 
	 * @author jfoley
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * 
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(SinglyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.next;
			return found;
		}
	}

	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for
	 * loop.
	 * 
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
}
