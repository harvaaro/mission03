package edu.isu.cs.cs3308.structures.impl;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 *
 * @author Aaron Harvey
 */
public class AaronSinglyLinkedListTest {
	//	/	/	/	/
	// Initial setup
	//	/	/	/	/
	private SinglyLinkedList<Integer> fixture;
//	private DoublyLinkedList<Integer> fixture;

	private boolean testIndexOf = false;

	private ArrayList<Integer> verifyList;
	private String newln = System.lineSeparator();
	private String sepln = newln + "###### ###### ######";
	private int rndBound = 10;
	private String testing = newln + "Testing --\t";
	private String shouldBe = newln + "Should be:\t";
	private String listSize = newln + "List size:\t";
	private String testBroken = newln + "BROKEN\t :";
	private String testFailed = "FAILED: ";
	private String testPassed = "PASSED: ";
	private String failedAfter = " failed after: ";
	private String failedStart = " which came after: ";
	private String failedSizer = " size is: ";
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
		fixture = new SinglyLinkedList<>();
//		fixture = new DoublyLinkedList<>();

		verifyList = new ArrayList<>();
	}

	@After
	public void tearDown() {
	}

	//	/	/	/	/
	// Test methods
	//	/	/	/	/

	private void testNormal(String methodString, Object testMethod) {
		String showRemoval = (whichRemove != "") ? failedAfter + whichRemove : "";
		String showPrint = "";

		if (trueListSize > 0) {
			showPrint = printResult;
		}

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

	private void methodLoopChoice(int orderIndex, boolean doAdd) {
		Random rnd = new Random();
		int addValue = rnd.nextInt(10) + rndBound;
		rndBound += 10;

		if (orderIndex == 0) {
			if (doAdd) {
				fixture.addFirst(addValue);
				verifyList.add(0, addValue);
				tempListSize++;
			} else {
				fixture.removeFirst();
				verifyList.remove(0);
				tempListSize--;
			}
		} else if (orderIndex == 1) {
			if (doAdd) {
				fixture.addLast(addValue);
				verifyList.add(verifyList.size(), addValue);
				tempListSize++;
			} else {
				fixture.removeLast();
				verifyList.remove(verifyList.size() - 1);
				tempListSize--;
			}
		} else if (orderIndex == 2) {
			if (doAdd) {
				fixture.insert(addValue, 0);
				verifyList.add(0, addValue);
				tempListSize++;
			} else {
				fixture.remove(0);
				verifyList.remove(0);
				tempListSize--;
			}
		} else if (orderIndex == 3) {
			if (doAdd) {
				fixture.insert(addValue, 1);
				if (1 > trueListSize) {
					verifyList.add(0, addValue);
					tempListSize++;
				} else {
					verifyList.add(1, addValue);
					tempListSize++;
				}
			} else {
				fixture.remove(1);
				if (1 < trueListSize) {
					verifyList.remove(1);
					tempListSize--;
				}
			}
		} else if (orderIndex == 4) {
			if (doAdd) {
				fixture.insert(addValue, 2);
				if (2 > trueListSize) {
					verifyList.add(1, addValue);
					tempListSize++;
				} else {
					verifyList.add(2, addValue);
					tempListSize++;
				}
			} else {
				fixture.remove(2);
				if (2 < trueListSize) {
					verifyList.remove(2);
					tempListSize--;
				}
			}
		} else if (orderIndex == 5) {
			if (doAdd) {
				fixture.insert(addValue, 3);
				if (3 > trueListSize) {
					verifyList.add(2, addValue);
					tempListSize++;
				} else {
					verifyList.add(3, addValue);
					tempListSize++;
				}
			} else {
				fixture.remove(3);
				if (3 < trueListSize) {
					verifyList.remove(3);
					tempListSize--;
				}
			}
		} else if (orderIndex == 6) {
			if (doAdd) {
				fixture.insert(addValue, 4);
				if (4 > trueListSize) {
					verifyList.add(3, addValue);
					tempListSize++;
				} else {
					verifyList.add(4, addValue);
					tempListSize++;
				}
			} else {
				fixture.remove(4);
				if (4 < trueListSize) {
					verifyList.remove(4);
					tempListSize--;
				}
			}
		} else if (orderIndex == 7) {
			if (doAdd) {
				fixture.insert(addValue, 5);
				if (5 > trueListSize) {
					verifyList.add(4, addValue);
					tempListSize++;
				} else {
					verifyList.add(5, addValue);
					tempListSize++;
				}
			} else {
				fixture.remove(5);
				if (5 < trueListSize) {
					verifyList.remove(5);
					tempListSize--;
				}
			}
		} else {
			if (doAdd) {
				fixture.insert(addValue, -1);
//				verifyList.add(-1, addValue);
			} else {
				fixture.remove(-1);
//				verifyList.remove(-1);
			}
		}
	}

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
				for (int i = 0; i < tempListSize; i++) {
					trueResult = i; //TODO check for -1 maybe
					testNormal("indexOf(" + listValues[i] + ")", fixture.indexOf(listValues[i]));
				}
			}
		}
	}

	private void checkAddRemove(int checkListSize) {
		// add appropriate amounts for checking
		for (int i = 0; i < checkListSize - 1; i++) {
			methodLoopChoice(1, true);
		}

		int insertAmounts = checkListSize + 4;

		String[] addOrder = {"addFirst()", "addLast", "insert(0)", "insert(1)",
				"insert(2)", "insert(3)", "insert(4)", "insert(5)", "insert(-1)"};
		String[] removeOrder = {"removeFirst()", "removeLast()", "remove(0)", "remove(1)",
				"remove(2)", "remove(3)", "remove(4)", "remove(5)", "remove(-1)"};

		System.out.println(sepln + newln + "List Size " + checkListSize + sepln);

		if (checkListSize == 0) {
			checkSize();
		} else {
			for (int i = 0; i < insertAmounts; i++) {
				whichInsert = addOrder[i];
				whichRemove = "";
				System.out.println(sepln + newln + whichInsert + whichRemove + sepln);

				methodLoopChoice(i, true);
//		if (i != 8) { tempListSize++; }
				checkSize();

				whichRemove = removeOrder[i];
				System.out.println(sepln + newln + whichInsert + whichRemove + sepln);

				methodLoopChoice(i, false);
//		if (i != 8) { tempListSize++; }
				checkSize();
			}
		}
	}

	//	/	/	/	/
	// Actual tests
	//	/	/	/	/

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize0() {
		totalTally = 15;

		checkAddRemove(0);
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize1() {
		totalTally = 117;

		checkAddRemove(1);
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize2() {
		totalTally = 134;

		checkAddRemove(2);
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize3() {
		totalTally = 14;

		checkAddRemove(3);
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize4() {
		totalTally = 14;

		checkAddRemove(4);
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void ListSize5() {
		totalTally = 14;

		checkAddRemove(5);
	}

}