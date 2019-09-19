package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;

public class PartialTreeList implements Iterable<PartialTree> {

	/**
	 * Inner class - to build the partial tree circular linked list
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;

		/**
		 * Next node in linked list
		 */
		public Node next;

		/**
		 * Initializes this node by setting the tree part to the given tree, and
		 * setting next part to null
		 * 
		 * @param tree
		 *            Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	/**
	 * Number of nodes in the CLL
	 */
	private int size;

	/**
	 * Initializes this list to empty
	 */
	public PartialTreeList() {
		rear = null;
		size = 0;
	}

	/**
	 * Adds a new tree to the end of the list
	 * 
	 * @param tree
	 *            Tree to be added to the end of the list
	 */
	public void append(PartialTree tree) {
		Node ptr = new Node(tree);
		if (rear == null) {
			ptr.next = ptr;
		} else {
			ptr.next = rear.next;
			rear.next = ptr;
		}
		rear = ptr;
		size++;
	}

	/**
	 * Removes the tree that is at the front of the list.
	 * 
	 * @return The tree that is removed from the front
	 * @throws NoSuchElementException
	 *             If the list is empty
	 */
	public PartialTree remove() throws NoSuchElementException {
		/* COMPLETE THIS METHOD */
		switch (size) {// eclipse was being dumb with if statement
		case 0:
			throw new NoSuchElementException("List Empty");
		}
		if (rear == rear.next) {// 1 node
			PartialTree tree = rear.tree;
			size = 0;
			rear = null;
			return tree;
		}
		PartialTree tree = rear.next.tree;
		if (tree == rear.tree) {
			size--;
			rear = null;
			return tree;
		}
		size--;
		rear.next = rear.next.next;
		return tree;
	}

	/**
	 * Removes the tree in this list that contains a given vertex.
	 * 
	 * @param vertex
	 *            Vertex whose tree is to be removed
	 * @return The tree that is removed
	 * @throws NoSuchElementException
	 *             If there is no matching tree
	 */
	public PartialTree removeTreeContaining(Vertex vertex) throws NoSuchElementException {
		/* COMPLETE THIS METHOD */
		if (size == 0 || rear == null) {
			throw new NoSuchElementException("Tree DNE");
		}
		if (rear == rear.next) {
			if (vertex.equals(rear.tree.getRoot())) {
				PartialTree y = rear.tree;
				rear = null;
				return y;
			}
		}
		Node prev = rear, current = rear.next;
		do {
			if (vertex.equals(current.tree.getRoot())) {
				PartialTree x = current.tree;
				prev.next = current.next;
				if (current == rear) {
					rear = prev;
				}
				return x;
			}
			prev = current;
			current = current.next;
		} while (prev != rear);
		if (current == rear && prev == rear) {
			throw new NoSuchElementException("Tree DNE");
		}
		PartialTree x = rear.tree;
		return x;
	}

	/*
	 * public static void TESTicle(PartialTreeList tree) { if (tree.rear ==
	 * null) { System.out.print("[]"); } else { Node ptr = tree.rear.next; while
	 * (ptr != tree.rear) { System.out.println(">" + ptr.tree + "||"); ptr =
	 * ptr.next; } System.out.println(">" + ptr.tree.toString() + ">"); }
	 * System.out.println(); }
	 */

	/**
	 * Gives the number of trees in this list
	 * 
	 * @return Number of trees
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an Iterator that can be used to step through the trees in this
	 * list. The iterator does NOT support remove.
	 * 
	 * @return Iterator for this list
	 */
	public Iterator<PartialTree> iterator() {
		return new PartialTreeListIterator(this);
	}

	private class PartialTreeListIterator implements Iterator<PartialTree> {

		private PartialTreeList.Node ptr;
		private int rest;

		public PartialTreeListIterator(PartialTreeList target) {
			rest = target.size;
			ptr = rest > 0 ? target.rear.next : null;
		}

		public PartialTree next() throws NoSuchElementException {
			if (rest <= 0) {
				throw new NoSuchElementException();
			}
			PartialTree ret = ptr.tree;
			ptr = ptr.next;
			rest--;
			return ret;
		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return (rest != 0);
		}

	}
}
