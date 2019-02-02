package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.NodeDouble;

/**
 * A class to implement a doubly linked list based off the singly linked list class
 *
 * @author Aaron Harvey
 * @param <E> any type of list
 */
public class DoublyLinkedList<E> extends SinglyLinkedList<E> {

	// Head node for the List
//	protected NodeDouble<E> head = null;

	// Tail node for the List
//	protected NodeDouble<E> tail = null;

	// Temporary node, added here for extension changing
//	protected NodeDouble<E> tempNode = null;

	@Override
	protected void nodeSetAdd(E dataToAdd, int atIndex) {
		//DEBUG
//		System.out.println("NodeDouble");

		// Create the node to add
		NodeDouble<E> addNode = new NodeDouble<>(dataToAdd);

		// if adding as the first Node
		if (atIndex == 0) {
			//DEBUG
//			System.out.println("add first " + dataToAdd);

			// if there is more than one node in the list
			if (size > 0) {
				// make the next of the new node to the original head
				addNode.setNext(head);

				// make original head point back to new node
				((NodeDouble<E>) head).setPrev(addNode);
			}

			// change the head to the new node
			head = addNode;
		}

		// else if adding as the last Node
		else if (atIndex >= size) {
			//DEBUG
//			System.out.println("add last " + dataToAdd);

			// make the original tail next to the tail to be
			tail.setNext(addNode);

			// make the new node point to the original tail
			addNode.setPrev((NodeDouble<E>) tail);

			// change the tail to the new node
			tail = addNode;
		}

		// else inserts a Node at index in list
		else {
			//DEBUG
//			System.out.println("insert " + dataToAdd + " index: " + atIndex);

			// get the node before the one to be added
			NodeDouble<E> prevNode = getNode(atIndex-1);

			// set the correct next for the new node
			addNode.setNext(prevNode.getNext());

			// set the correct prev for the new node
			addNode.setPrev(prevNode);

			// set the following node to point back to new node
			((NodeDouble<E>) addNode.getNext()).setPrev(addNode);

			// set the correct next for the previous node
			prevNode.setNext(addNode);
		}

		// increment size
		addSize();

		// fix the head and tail if single node in list
		verifyList();
	}

	@Override
	protected E nodeSetRemove(int atIndex) {
		// Create the node to add
		NodeDouble<E> removeNode = null;

		// if removing the first Node
		if (atIndex == 0) {
			removeNode = (NodeDouble<E>) head;

			// if there are at least two nodes in the list
			if (size > 1) {
				// set the new head to next of original
				head = removeNode.getNext();
			}
		}

		// else if removing the last Node
		else if (atIndex >= size - 1) {
			// get the node before the one to be removed
			NodeDouble<E> prevNode = ((NodeDouble<E>) tail).getPrev();

			// get the node that will be removed
			removeNode = (NodeDouble<E>) tail;

			// set the new connection with the node removed
//			prevNode.setNext(removeNode.getNext());

			// set new tail node
			tail = prevNode;
		}

		// else removing a Node at index in list
		else {
			// get the node before the one to be removed
			NodeDouble<E> prevNode = getNode(atIndex-1);

			// get the node that will be removed
			removeNode = (NodeDouble<E>) prevNode.getNext();

			// set the following node to point back to new node
			((NodeDouble<E>) removeNode.getNext()).setPrev(prevNode);

			// set the new connection with the node removed
			prevNode.setNext(removeNode.getNext());
		}

		// null out the next of the removeNode
		removeNode.setPrev(null);
		removeNode.setNext(null);

		// decrement size
		subSize();

		// fix the head and tail if single node in list
		verifyList();

		// return the removed nodes data
		return removeNode.getData();
	}

	@Override
	protected NodeDouble<E> getNode(int index) {
		// get current head node to start from
		NodeDouble<E> seekNode;

		// if in the first half of the list then use next
		if (index <= size/2) {
			// get current head node to start from
			seekNode = (NodeDouble<E>) head;

			// seek through the list starting from the head
			for (int i = 0; i < index; i++) {
				seekNode = (NodeDouble<E>) seekNode.getNext();
			}
		}
		// else in the second half of list then use prev
		else {
			// get current tail node to start from
			seekNode = (NodeDouble<E>) tail;

			// seek through the list starting from the head
			for (int i = size-1; i > index; i--) {
				seekNode = seekNode.getPrev();
			}
		}

		// return the desired Node from the list index
		return seekNode;
	}

	@Override
	protected void verifyBoundary() {
		((NodeDouble<E>) head).setPrev(null);
		tail.setNext(null);
	}
}
