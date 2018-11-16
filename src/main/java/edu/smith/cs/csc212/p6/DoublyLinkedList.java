package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;



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
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public void addFront(T item) {
		throw new P6NotImplemented();
	}

	@Override
	public void addBack(T item) {
		throw new P6NotImplemented();
	}

	@Override
	public void addIndex(T item, int index) {
		throw new P6NotImplemented();
	}

	@Override
	public T getFront() {
		throw new P6NotImplemented();
	}

	@Override
	public T getBack() {
		throw new P6NotImplemented();
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public int size() {
		throw new P6NotImplemented();
	}

	@Override
	public boolean isEmpty() {
		throw new P6NotImplemented();
	}
	
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
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
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
