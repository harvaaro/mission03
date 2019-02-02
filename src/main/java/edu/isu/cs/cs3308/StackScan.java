package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.Stack;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;

/**
 *
 * @author Isaac Griffith
 * @author Aaron Harvey
 */
public class StackScan {

    /**
     * Tests whether the given stack contains the provided element.
     * Implementation should use a queue to scan the stack and reconstruct it
     * when done.
     *
     * @param <E> Type of elements stored in the stack
     * @param stack Stack to be scanned.
     * @param element Element to search the stack for.
     * @return True if the given stack is not null and contains the given
     * element. Returns false if both the stack and element are not null and the
     * stack does not contain the element, if either the stack or element is
     * null, or if the stack is emtpy.
     */
    public static <E> boolean scanStack(final Stack<E> stack, E element) {
        // temp list to store values for checking
        LinkedQueue<E> scanQueue = new LinkedQueue<>();

        // if either the stack or element is null, or if the stack is emtpy return false
        if ((stack == null || element == null) || stack.isEmpty()) {
            return false;
        }

        // else if both the stack and element are not null and the
        // stack does not contain the element, then return false else return true
        else {
            // boolean to store whether a match was found or not
            boolean searchFound = false;

            // get the current size of the list
            int listSize = stack.size();

            // make a temp size for looping
            int tempSize = listSize;

            // transfer the stack to a temp queue to search
            while (tempSize > 0) {
                // get the first element from the list
                E tempElement = stack.pop();

                // check the first index to see if it matched the element
                if (tempElement == element) {
                    searchFound = true;
                }

                // add element to the queue
                scanQueue.offer(tempElement);

                // iterate through the list
                tempSize--;
            }

            // reverse the list back now that we are done.
            scanQueue.reverse();

            // reget the size needed for looping
            tempSize = listSize;

            // input that queue back into the stack
            while (tempSize > 0) {
                // add element to the queue
                stack.push(scanQueue.poll());

                // iterate through the list
                tempSize--;
            }

            // return the result of the scan
            return searchFound;
        }
    }
}
