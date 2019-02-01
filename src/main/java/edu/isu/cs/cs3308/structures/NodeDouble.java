package edu.isu.cs.cs3308.structures;

import org.checkerframework.checker.signature.qual.MethodDescriptor;

/**
 * Node class needed for the Double List
 *
 * @author Aaron Harvey
 * @param <E>
 */
public class NodeDouble<E> extends Node<E>{

	// Stores what the prev node is.
	private NodeDouble<E> prev;

	// Stores what the next node is.
	private NodeDouble<E> next;

	/**
	 * Constructor with data parameter
	 * @param data Whatever data the Node should store
	 */
	public NodeDouble(E data) {
		super(data);
	}

	/**
	 * Get what Node is stored as the prev in the List.
	 * @return The Node that is currently stored in the prev attribute
	 */
	public NodeDouble<E> getPrev() {
		return prev;
	}

	/**
	 * Set what Node should be stored as the prev in the List.
	 * @param prev The Node that should be the previous to current node
	 */
	public void setPrev(NodeDouble<E> prev) {
		this.prev = prev;
	}

	/**
	 * Get what Node is stored as the next in the List.
	 * @return The Node that is currently stored in the next attribute
	 */
	public NodeDouble<E> getNext() {
		return next;
	}

	/**
	 * Set what Node should be stored as the next in the List.
	 * @param next The Node that should be the next to current node
	 */
	public void setNext(NodeDouble<E> next) {
		this.next = next;
	}
}
