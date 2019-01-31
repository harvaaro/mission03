package edu.isu.cs.cs3308.structures;

public class NodeDouble<E> extends Node<E> {

	// Stores what the prev node is.
	private Node<E> prev;

	// Constructor for making a double node
	public NodeDouble(E data) {
		super(data);
	}

	/**
	 * Get what Node2 is stored as the prev in the List.
	 * @return The Node2 that is currently stored in the prev attribute
	 */
	public Node<E> getPrev() {
		return prev;
	}

	/**
	 * Set what Node2 should be stored as the prev in the List.
	 * @param prev The Node2 that should be the previous to current node
	 */
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
}
