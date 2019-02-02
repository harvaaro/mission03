package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

/**
 * A linked queue that is based off of a Queue
 *
 * @author Aaron Harvey
 * @param <E> any type of queue
 */
public class LinkedQueue<E> implements Queue<E> {

	// create our DLL to use for the stack
	protected DoublyLinkedList<E> theList = new DoublyLinkedList<>();

	/**
	 * Add a new element onto the queue at the end
	 * @param element data to add to end, unless it is null
	 */
	@Override
	public void offer(E element) {
		theList.addLast(element);
	}

	/**
	 * Remove the value at the beginning of the queue
	 * @return The value at the beginning of the queue
	 */
	@Override
	public E poll() {
		return theList.removeFirst();
	}

	/**
	 * See the value at the beginning without removing it
	 * @return The value at the beginning of the queue
	 */
	@Override
	public E peek() {
		return theList.first();
	}

	/**
	 * Get how many elements are in the queue
	 * @return Number of elements in queue
	 */
	@Override
	public int size() {
		return theList.size();
	}

	/**
	 * Determine if the queue list is empty or not
	 * @return True if the queue list is empty, else is false
	 */
	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}

	/**
	 * Transfer all the data from one queue to another
	 * this also reverses the order of the elements
	 * @param to The queue to transfer to unless it is null
	 */
	@Override
	public void transfer(Queue<E> to) {
		// if the stack is not null or empty
		if (to != null && this.size() > 0) {
			// temporary linkedstack to help with reversal
			LinkedStack<E> tempLinkStack = new LinkedStack<>();

			// loop until all elements are transferred
			while(this.size() > 0) {
				tempLinkStack.push(this.poll());
			}

			// loop until all elements are transferred
			while(tempLinkStack.size() > 0) {
				to.offer(tempLinkStack.pop());
			}
		}
	}

	/**
	 *Reverse the order of the elements of the current queue
	 */
	@Override
	public void reverse() {
		// create 2 temporary stacks to use for reversing
		LinkedQueue<E> temp1 = new LinkedQueue<>();
		LinkedQueue<E> temp2 = new LinkedQueue<>();

		// make reverse order in temp1
		this.transfer(temp1);

		// make normal order in temp2
		temp1.transfer(temp2);

		// send back to original as reversed
		temp2.transfer(this);

	}

	/**
	 * Copy the other queue to the end of the this queue.
	 * @param other queue whose contents are to be merged
	 * onto the bottom of this queue.
	 */
	@Override
	public void merge(Queue<E> other) {
		if (other != null) {
			// size of the other list to merge
			int listSize = other.size();

			// loop until all elements are transferred
			for(int i = 0; i < listSize; i++) {
				// remove element temporarly
				E tempElement = other.poll();

				// insert into both lists
				other.offer(tempElement);
				this.offer(tempElement);
			}
		}
	}

	/**
	 * Prints out the content of the queue list
	 */
	@Override
	public void printQueue() {
		theList.printList();
	}
}
