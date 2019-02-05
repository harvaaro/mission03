package edu.isu.cs.cs3308.structures.impl;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * This is a series of tests to ensure that a linked list passes all needed checks
 * It will loop through from size 0 to 5, doing every type of insert with every
 * type of remove being tested on each of those inserts. Then at each point will
 * test to see if all of the get calls return correctly like, first, last, etc.
 *
 * If there is an error at any stage start with the lowest size count errors first.
 * So if Size 0 has an error fix that issue before moving on. Once a size passes all
 * tests then you can move onto to any issues the next size has.
 *
 * This set of tests should work for any linked list type. Single, Double, Circular, etc
 * I also have a boolean to allow you to test the indexOf method as well.
 * @author Aaron Harvey
 */
public class AaronSinglyLinkedListTest {
	//	/	/	/	/
	// Initial setup
	//	/	/	/	/
	private SinglyLinkedList<Integer> fixture;
//	private DoublyLinkedList<Integer> fixture;

	private boolean testIndexOf = true;

	private LinkedList<Integer> verifyList;
	private String newln = System.lineSeparator();
	private String sepln = newln + "###### ###### ######";
	private int rndBound = 10;
	private String testing = newln + "Testing --\t";
	private String shouldBe = newln + "Should be:\t";
	private String listSize = newln + "List size:\t";
	private String testBroken = newln + "BROKEN\t :";
	private String testFailed = "FAILED: ";
	private String testPassed = "PASSED: ";
	private String failedList = newln + "printList:";
	private String whichInsert;
	private String whichRemove;
	private Object trueResult;
	private String printResult;
	private int tempListSize = 0;
	private int trueListSize = 0;
	private int passedTally = 0;
	private int totalTally = 0;

	public AaronSinglyLinkedListTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		freshList();
	}

	@After
	public void tearDown() {
	}

	//	/	/	/	/
	// Test methods
	//	/	/	/	/

	/**
	 * Resets the needed variables for each iteration in the checkaddremove
	 */
	private void freshList() {
		fixture = new SinglyLinkedList<>();
//		fixture = new DoublyLinkedList<>();

		verifyList = new LinkedList<>();
		tempListSize = 0;
		trueListSize = 0;
		rndBound = 10;
	}

	/**
	 * Runs the appropriate test with error log as needed.
	 * Will also output a debug of each step.
	 * @param methodString The method that is currently being tested
	 * @param testMethod The result of the actual method call
	 */
	private void testNormal(String methodString, Object testMethod) {
		String failedAfter = " failed after: ";
		String failedStart = " which came after: ";
		String failedSizer = " size is: ";
		String showRemoval = failedAfter + whichRemove;
		String showPrint = "";

		// incase there is an error have the list to display
		if (trueListSize > 0) {
			showPrint = printResult;
		}

		// run assert tests and increase the tally of passed tests
		System.out.println(testing + methodString + shouldBe + trueResult + listSize + trueListSize);
		assertEquals(testFailed + (totalTally - passedTally) + "\t" +
						testPassed + passedTally + " of " + totalTally +
						failedList + failedSizer + trueListSize + showPrint +
						testBroken + methodString + showRemoval +
						failedStart + whichInsert
				, trueResult, testMethod);
		passedTally++;
		System.out.println(testPassed + "\t" + passedTally + " of " + totalTally);
	}

	/**
	 * This tests that the list will print out correctly based on the correct system new line character
	 * TODO this method probably can be simplified
	 * @param printList This is an optional int array that stores the values needed for comparison
	 */
	private void testPrint(int... printList) {
		System.out.println(testing + "printList()" + shouldBe + trueResult + listSize + trueListSize);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		fixture.printList();
		System.out.flush();
		System.setOut(old);
		String output = baos.toString();
		if (trueListSize == 0) {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, trueResult, output.trim());
		} else if (trueListSize == 1) {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln, output.trim());
		} else if (trueListSize == 2) {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln, output.trim());
		} else if (trueListSize == 3) {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln, output.trim());
		} else if (trueListSize == 4) {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln +
					printList[3] + newln, output.trim());
		} else {
			assertNotEquals(testFailed + (totalTally - passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln +
					printList[3] + newln + printList[4] + newln, output.trim());
		}
		passedTally++;
		System.out.println(testPassed + "\t" + passedTally + " of " + totalTally);
	}

	/**
	 * Handles all of the add and remove functions needed for each loop.
	 * It will do the add/remove to both the real list and the verification list to compare to.
	 * Also will increment the random number for the int array after each iteration.
	 * @param orderIndex Which method operation is needed for either add or remove
	 * @param doAdd Determines whether True to add or False to remove
	 */
	private void methodLoopChoice(int orderIndex, boolean doAdd) {
		Random rnd = new Random();
		int addValue = rnd.nextInt(10) + rndBound;
		rndBound += 10;

		if (orderIndex == 8) {
			if (doAdd) {
				fixture.insert(addValue, -1);
			} else {
				fixture.remove(-1);
			}
		}
		else {
			if (doAdd) {
				if (orderIndex == 0) {
					fixture.addFirst(addValue);
					verifyList.addFirst(addValue);
				} else if (orderIndex == 1) {
					fixture.addLast(addValue);
					verifyList.addLast(addValue);
					System.out.println("addinglast");
				} else if (orderIndex == 2) {
					fixture.insert(addValue, 0);
					verifyList.add(0, addValue);
				} else if (orderIndex == 3) {
					fixture.insert(addValue, 1);
					if (verifyList.size() > 1) verifyList.add(1, addValue);
					else verifyList.addLast(addValue);
				} else if (orderIndex == 4) {
					fixture.insert(addValue, 2);
					if (verifyList.size() > 2) verifyList.add(2, addValue);
					else verifyList.addLast(addValue);
				} else if (orderIndex == 5) {
					fixture.insert(addValue, 3);
					if (verifyList.size() > 3) verifyList.add(3, addValue);
					else verifyList.addLast(addValue);
				} else if (orderIndex == 6)  {
					fixture.insert(addValue, 4);
					if (verifyList.size() > 4) verifyList.add(4, addValue);
					else verifyList.addLast(addValue);
				} else {
					fixture.insert(addValue, 5);
					if (verifyList.size() > 5) verifyList.add(5, addValue);
					else verifyList.addLast(addValue);
				}
				tempListSize++;
			}
			else {
				if (orderIndex == 0) {
					fixture.removeFirst();
					verifyList.removeFirst();
					tempListSize--;
				} else if (orderIndex == 1) {
					fixture.removeLast();
					verifyList.removeLast();
					tempListSize--;
				} else if (orderIndex == 2) {
					fixture.remove(0);
					verifyList.remove(0);
					tempListSize--;
				} else if (orderIndex == 3) {
					fixture.remove(1);
					if (verifyList.size() > 1) {
						verifyList.remove(1);
						tempListSize--;
					}
				} else if (orderIndex == 4) {
					fixture.remove(2);
					if (verifyList.size() > 2) {
						verifyList.remove(2);
						tempListSize--;
					}
				} else if (orderIndex == 5) {
					fixture.remove(3);
					if (verifyList.size() > 3) {
						verifyList.remove(3);
						tempListSize--;
					}
				} else if (orderIndex == 6)  {
					fixture.remove(4);
					if (verifyList.size() > 4) {
						verifyList.remove(4);
						tempListSize--;
					}
				} else {
					fixture.remove(5);
					if (verifyList.size() > 5) {
						verifyList.remove(5);
						tempListSize--;
					}
				}
			}
		}
	}

	/**
	 * Verifies all of the get or retrieve methods that need to work at each stage.
	 * After each add or remove these methods are compared to ensure it changes
	 * how the list is supposed to change and that each value is appropriately assigned.
	 */
	private void checkSize() {
		trueListSize = tempListSize;
		printResult = "";

		int[] listValues = new int[trueListSize];

		for (int i = 0; i < listValues.length; i++) {
			listValues[i] = verifyList.get(i);
			printResult += newln + listValues[i];
		}

		System.out.println(newln + "CHECKING:\tResult values for a List of size " + trueListSize);

		Object lastTrueValue;
		if (trueListSize == 1) {
			lastTrueValue = listValues[0];
		} else if (trueListSize > 1) {
			lastTrueValue = listValues[listValues.length - 1];
		} else {
			lastTrueValue = null;
		}

		trueResult = trueListSize;
		testNormal("size()", fixture.size());
		trueResult = (trueListSize == 0);
		testNormal("isEmpty()", fixture.isEmpty());
		trueResult = (trueListSize > 0) ? listValues[0] : null;
		testNormal("first()", fixture.first());
		testNormal("get(" + 0 + ")", fixture.get(0));
		trueResult = lastTrueValue;
		testNormal("last()", fixture.last());
		testNormal("get(" + (trueListSize - 1) + ")", fixture.get(trueListSize - 1));
		trueResult = null;
		testNormal("get(" + (-1) + ")", fixture.get(-1));
		testNormal("get(" + trueListSize + ")", fixture.get(trueListSize));

		if (trueListSize > 2) {
			for (int i = 1; i < trueListSize; i++) {
				trueResult = listValues[i];
				testNormal("get(" + i + ")", fixture.get(i));
			}
		}

		trueResult = printResult;
		testPrint(listValues);

		if (trueListSize == 0) {
			trueResult = null;
			testNormal("removeFirst()", fixture.removeFirst());
			testNormal("removeLast()", fixture.removeLast());
			testNormal("remove(" + (-1) + ")", fixture.remove(-1));
			testNormal("remove(" + 0 + ")", fixture.remove(0));
			testNormal("remove(" + 1 + ")", fixture.remove(1));
			if (testIndexOf) {
				trueResult = -1;
				testNormal("indexOf(" + 10 + ")", fixture.indexOf(10));
			}
		} else {
			if (testIndexOf) {
				for (int i = 0; i < trueListSize; i++) {
					trueResult = i;
					testNormal("indexOf(" + listValues[i] + ")", fixture.indexOf(listValues[i]));
				}
			}
		}
	}

	/**
	 * Iterates through every possible add and remove function and calls those methods.
	 * @param checkListSize The size of the list to test
	 */
	private void checkAddRemove(int checkListSize) {
		int insertAmounts = checkListSize + 4;
		int removeAmounts = checkListSize + 4;

		String[] addOrder = {"addFirst()", "addLast()", "insert(0)", "insert(1)",
				"insert(2)", "insert(3)", "insert(4)", "insert(5)", "insert(-1)"};
		String[] removeOrder = {"removeFirst()", "removeLast()", "remove(0)", "remove(1)",
				"remove(2)", "remove(3)", "remove(4)", "remove(5)", "remove(-1)"};

		System.out.println(sepln + newln + "List Size " + checkListSize + sepln);

		if (checkListSize == 0) {
			checkSize();
		} else {
			// inserts
			for (int i = 0; i < insertAmounts; i++) {
				freshList();

				// add appropriate amounts for checking
				for (int p = 0; p < checkListSize - 1; p++) {
					methodLoopChoice(1, true);
				}

				// removes
				for (int r = 0; r < removeAmounts; r++) {
					if (r < 6) {
						whichInsert = addOrder[i];
						whichRemove = "";
						System.out.println(sepln + newln + whichInsert + whichRemove + sepln);

						methodLoopChoice(i, true);
						checkSize();
					}

					if (i != 8) {
						whichRemove = removeOrder[r];
						System.out.println(sepln + newln + whichInsert + whichRemove + sepln);

						methodLoopChoice(r, false);
						checkSize();
					}
				}
			}
		}
	}

	//	/	/	/	/
	// Actual tests
	//	/	/	/	/

	/**
	 * Test for a list with a size of 0
	 * In this method it tests mostly gets and some removes on an empty list.
	 */
	@Test
	public void ListSize0() {
		if (testIndexOf) {
			totalTally = 15;
		}
		else {
			totalTally = 14;
		}

		checkAddRemove(0);
	}

	/**
	 * Test for a list with a size of 1
	 */
	@Test
	public void ListSize1() {
		if (testIndexOf) {
			totalTally = 585;
		}
		else {
			totalTally = 525;
		}

		checkAddRemove(1);
	}

	/**
	 * Test for a list with a size of 2
	 */
	@Test
	public void ListSize2() {
		if (testIndexOf) {
			totalTally = 870;
		}
		else {
			totalTally = 672;
		}

		checkAddRemove(2);
	}

	/**
	 * Test for a list with a size of 3
	 */
	@Test
	public void ListSize3() {
		if (testIndexOf) {
			totalTally = 1113;
		}
		else {
			totalTally = 931;
		}

		checkAddRemove(3);
	}

	/**
	 * Test for a list with a size of 4
	 */
	@Test
	public void ListSize4() {
		if (testIndexOf) {
			totalTally = 1384;
		}
		else {
			totalTally = 1280;
		}

		checkAddRemove(4);
	}

	/**
	 * Test for a list with a size of 5
	 */
	@Test
	public void ListSize5() {
		if (testIndexOf) {
			totalTally = 1586;
		}
		else {
			totalTally = 1560;
		}

		checkAddRemove(5);
	}

}