package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;
import edu.isu.cs.cs3308.structures.Node;

/**
 * A class to implement a singly linked list based off the List class
 *
 * @author Aaron Harvey
 * @param <E> any type of list
 */
public class SinglyLinkedList<E> implements List<E> {

	// Head node for the List
	protected Node<E> head = null;

	// Tail node for the List
	protected Node<E> tail = null;

	// Temporary node, added here for extension changing
	protected Node<E> tempNode = null;

	// Count of the nodes in the List
	protected int size = 0;

	/**
	 * Checks to see if a given element is null or not
	 * @param element the element to check
	 * @return true if not null, and false if null
	 */
	protected boolean checkElement(E element) {
		// may eventually throw an error or some message
		// but currently am just doing an error check
		return element != null;
	}

	/**
	 * Checks to see if a given index is within 0 and size
	 * @param index the index to check
	 * @return true if valid index, and false if invalid
	 */
	protected boolean checkIndex(int index) {
		return index < size && index >= 0;
	}

	/**
	 * Used to fix the head and tail of the list
	 * If a single node remains in the list, then head and tail are set to it
	 * If the size of the list is 0 then set head and tail to null
	 */
	protected void verifyList() {
		//DEBUG
//		System.out.println("first:" + first());
//		System.out.println("last:" + last());
//		System.out.println(head.getData());
//		System.out.println(tail.getData());
//		System.out.println("size:" + size);
//		printList();

		// if only one node in the list
		if (size == 1) {
			if (head != null) {
				tail = head;
			}
			else {
				head = tail;
			}
		}

		// else there are no nodes in the list
		if (size == 0) {
			head = null;
			tail = null;
		}

		// ADDED to make circular list easier
		if (size > 0) {
			verifyBoundary();
		}
	}

	/**
	 * Will set the Node values appropriately for an add action
	 * @param dataToAdd The data to store within the node
	 * @param atIndex Index to add the node at
	 */
	protected void nodeSetAdd(E dataToAdd, int atIndex) {
		//DEBUG
//		System.out.println("Node");

		// Create the node to add
		Node<E> addNode = new Node<>(dataToAdd);

		// if adding as the first Node
		if (atIndex == 0) {
			//DEBUG
//			System.out.println("add first " + dataToAdd);

			// if there is more than one node in the list
			if (size > 0) {
				// make the next of the new node to the original head
				addNode.setNext(head);
			}

			// change the head to the new node
			head = addNode;
		}

		// else if adding as the last Node
		else if (atIndex >= size) {
			//DEBUG
//			System.out.println("add last " + dataToAdd);

			// else make the original tail next to the tail to be
			tail.setNext(addNode);

			// change the tail to the new node
			tail = addNode;
		}

		// else inserts a Node at index in list
		else {
			//DEBUG
//			System.out.println("insert " + dataToAdd);

			// get the node before the one to be added
			Node<E> prevNode = getNode(atIndex-1);

			// set the correct next for the new node
			addNode.setNext(prevNode.getNext());

			// set the correct next for the previous node
			prevNode.setNext(addNode);
		}

		// increment size
		addSize();

		// fix the head and tail if single node in list
		verifyList();
	}

	/**
	 * Will set the Node values appropriately for a remove action
	 * @param atIndex Index of the node to remove
	 * @return The data within the node
	 */
	protected E nodeSetRemove(int atIndex) {
		// Create the node to add
		Node<E> removeNode = null;

		// if removing the first Node
		if (atIndex == 0) {
			removeNode = head;

			// if there are at least two nodes in the list
			if (size > 1) {
				// set the new head to next of original
				head = removeNode.getNext();
			}
		}

		// else removing a Node at index in list
		else {
			// get the node before the one to be removed
			Node<E> prevNode = getNode(atIndex-1);

			// get the node that will be removed
			removeNode = prevNode.getNext();

			// set the new connection with the node removed
			prevNode.setNext(removeNode.getNext());

			// else if removing the last Node
			if (atIndex >= size-1) {
				// set new tail node
				tail = prevNode;
			}
		}

		// null out the next of the removeNode
		removeNode.setNext(null);

		// decrement size
		subSize();

		// fix the head and tail if single node in list
		verifyList();

		// return the removed nodes data
		return removeNode.getData();
	}

	/**
	 * Get a node from the list given a specific index
	 * @param index The index within the list
	 * @return The node retrieved from the list
	 */
	protected Node<E> getNode(int index) {
		// get current head node to start from
		Node<E> seekNode = head;

		// seek through the list starting from the head
		for (int i = 0; i < index; i++) {
			seekNode = seekNode.getNext();
		}

		// return the desired Node from the list index
		return seekNode;
	}

	/**
	 * Adds 1 to the size value.
	 * I put this as a function if I ever needed to do other checks with it
	 */
	protected void addSize() {
		size++;
	}

	/**
	 * Subtracts 1 from the size value, and ensure it cannot go below 0
	 */
	protected void subSize() {
		size--;

		if (size < 0) {
			size = 0;
		}
	}

	/**
	 * ADDED to allow making a circular easy
	 * Will verify the tail goes to null for the SLL
	 */
	protected void verifyBoundary() {
		tail.setNext(null);
	}

	/**
	 * To get the data from the first node in the list
	 * @return The data within the head node, if none then null
	 */
	@Override
	public E first() {
		return (head != null) ? head.getData() : null;
	}

	/**
	 * To get the data from the last node in the list
	 * @return The data within the tail node, if none then null
	 */
	@Override
	public E last() {
		return (tail != null) ? tail.getData() : null;
	}

	/**
	 * Creates a node with the given element data to the end of the list
	 * @param element Data to store in the last node
	 */
	@Override
	public void addLast(E element) {
		// check if the element is not null
		if (checkElement(element)) {
			// check if the size is at least 1
			if (!isEmpty()) {
				// add the node to the end
				nodeSetAdd(element,size);
			}
			// else if size is 0 just add first
			else {
				addFirst(element);
			}
		}
	}

	/**
	 * Creates a node with the given element data to the beginning of the list
	 * @param element Data to store in the first node
	 */
	@Override
	public void addFirst(E element) {
		// check if the element is not null
		if (checkElement(element)) {
			// add the node to the beginning
			nodeSetAdd(element, 0);
		}
	}

	/**
	 * Removes the first node in the list
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E removeFirst() {
		// if the head Node is not null
		if (head != null) {
			return nodeSetRemove(0);
		}
		// else there is no head Node
		else {
			return null;
		}
	}

	/**
	 * Removes the last node in the list
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E removeLast() {
		// because of how remove works, check if there are at least 2 nodes
		if (size > 1) {
			return remove(size - 1);
		}

		// else just remove the first node
		else {
			return removeFirst();
		}
	}

	/**
	 * Create a node with the given element data, and insert that node
	 * into the list at the given index
	 * @param element Data to store in the node
	 * @param index Where in the list the node should be inserted
	 */
	@Override
	public void insert(E element, int index) {
		// if the element is not null
		if (checkElement(element)) {
			// just check if index is greater than 0
			if (index >= 0) {
				// if the index is the head
				if (index == 0) {
					addFirst(element);
				}

				// else if the index is the tail
				else if (index >= size) {
					addLast(element);
				}

				// else the index is some other node
				else {
					nodeSetAdd(element, index);
				}
			}
		}
	}

	/**
	 * Removes a given node from the list based on a given index
	 * @param index The index of the node in the list to remove
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E remove(int index) {
		// check if the index is a usable value
		if (checkIndex(index)) {
			// if the index is the head
			if (index == 0) {
				return removeFirst();
			}

			// else the index is some other node
			else {
				return nodeSetRemove(index);
			}
		}

		// if the index is invalid
		else {
			return null;
		}
	}

	/**
	 * Get the data within a node from the list at the given index
	 * @param index The index of the node in the list to retrieve
	 * @return Data stored within the node that is to be retrieved
	 */
	@Override
	public E get(int index) {
		// check if the index is a usable value
		if (checkIndex(index)) {
			// if the index is the head
			if (index == 0) {
				return head.getData();
			}

			// else if the index is the tail
			else if (index == size-1) {
				return tail.getData();
			}

			// else the index is some other node
			else {
				return getNode(index).getData();
			}
		}

		// if the index is invalid
		else {
			return null;
		}
	}

	/**
	 * The stored value which contains the number of nodes within the list
	 * @return The count of nodes in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines if the list is empty or not
	 * @return True if the list is empty, and false if size does not equal 0
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * MODIFIED this to be the spaced version instead of the newline from SLL
	 * Each value in the list will be printed out with spaces between them
	 * and then a newline at the end of the list.
	 */
	@Override
	public void printList() {
		// if there are nodes in the list
		if (!isEmpty()) {
			// get the head as the starting point
			tempNode = head;

			// iterate though the list, until we reach the end
			for (int i = 0; i < size; i++) {
				// print the current nodes data with a space after it
				if (i < size-1) {
					System.out.print(tempNode.getData() + "\n");
				}
				// else on the last value
				else {
					System.out.println(tempNode.getData());
				}

				// then get the next node to get data from
				tempNode = tempNode.getNext();
			}
		}
		// else there are no nodes in the list
		else {
			System.out.println("There is nothing in this list.");
		}

		// empty tempNode so there is no issues
		tempNode = null;
	}

	/**
	 * Finds a given item in the list, if not found or null then return -1
	 *
	 * @param item item to find in the list
	 * @return the index of the found item in the list
	 */
//	@Override
	public int indexOf(E item) {
		// if there are nodes in the list and item is not null
		if (!isEmpty() && item != null) {
			if (head.getData() == item) {
				return 0;
			}
			else if (tail.getData() == item) {
				return size-1;
			}
			else {
				// get the head as the starting point
				tempNode = head;

				// iterate though the list, until we reach the end
				for (int i = 0; i < size; i++) {
					//DEBUG
					System.out.println(i);
					System.out.println(tempNode.getData());
					System.out.println(tempNode.getData() == item);

					// if current node matches the item, then return index
					if (tempNode.getData() == item) {
						return i;
					}

					// get the next node to keep searching
					tempNode = tempNode.getNext();
				}
			}
		}

		// empty tempNode so there is no issues
		tempNode = null;

		// else there are no nodes in the list or item is null
		return -1;
	}
}
