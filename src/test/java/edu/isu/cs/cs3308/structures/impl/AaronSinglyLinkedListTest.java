package edu.isu.cs.cs3308.structures.impl;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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

	private ArrayList<Integer> verifyList;

	private String newln = System.lineSeparator();
	private String sepln = newln + "###### ###### ######";
	private int value1 = 10;
	private int value2 = 20;
	private int value3 = 30;
	private int value4 = 40;
	private int value5 = 50;
	private int valueInsert = 12345;

	private int[] addValues = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

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
	private int testIndexNeg = -1;
	private int testIndex0 = 0;
	private int testIndex1 = 1;
	private int testIndex2 = 2;
	private int testIndex3 = 3;
	private int testIndex4 = 4;
	private int testIndex5 = 5;
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

		System.out.println(testing+methodString+shouldBe+trueResult+listSize+trueListSize);
		assertEquals( testFailed + (totalTally-passedTally) + "\t" +
				testPassed + passedTally + " of " + totalTally +
				failedList + failedSizer + trueListSize + showPrint +
				testBroken + methodString + showRemoval +
				failedStart + whichInsert
				, trueResult, testMethod);
		passedTally++;
		System.out.println(testPassed + "\t" + passedTally + " of " + totalTally);
	}

	private void testPrint(int... printList) {
		System.out.println(testing+"printList()"+shouldBe+trueResult+listSize+trueListSize);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		fixture.printList();
		System.out.flush();
		System.setOut(old);
		String output = baos.toString();
		if (trueListSize == 0) {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, trueResult, output.trim());
		}
		else if (trueListSize == 1) {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln, output.trim());
		}
		else if (trueListSize == 2) {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln, output.trim());
		}
		else if (trueListSize == 3) {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln, output.trim());
		}
		else if (trueListSize == 4) {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln +
					printList[3] + newln, output.trim());
		}
		else {
			assertNotEquals(testFailed + (totalTally-passedTally) + "\t" + testPassed + passedTally +
					" of " + totalTally, printList[0] + newln +
					printList[1] + newln + printList[2] + newln +
					printList[3] + newln + printList[4] + newln, output.trim());
		}
		passedTally++;
		System.out.println(testPassed + "\t" + passedTally + " of " + totalTally);
	}

	private void checkSize0() {
		trueListSize = 0;

		System.out.println("\nCHECKING:\tResult values for a List of size 0.");
		trueResult = trueListSize;
		testNormal("size()",						fixture.size());
		trueResult = true;
		testNormal("isEmpty()",					fixture.isEmpty());
		trueResult = null;
		testNormal("first()",					fixture.first());
		testNormal("last()",						fixture.last());
		testNormal("get("+testIndexNeg+")",		fixture.get(testIndexNeg));
		testNormal("get("+testIndex0+")",		fixture.get(testIndex0));
		testNormal("get("+testIndex1+")",		fixture.get(testIndex1));
		testNormal("removeFirst()",				fixture.removeFirst());
		testNormal("removeLast()",				fixture.removeLast());
		testNormal("remove("+testIndexNeg+")",	fixture.remove(testIndexNeg));
		testNormal("remove("+testIndex0+")",		fixture.remove(testIndex0));
		testNormal("remove("+testIndex1+")",		fixture.remove(testIndex1));
		trueResult = -1;
		testNormal("indexOf("+valueInsert+")",	fixture.indexOf(valueInsert));

		trueResult = "empty";
		testPrint();
	}

	private void checkSize1(int firstValue) {
		trueListSize = 1;

		int[] printList = { firstValue };
		printResult = newln + printList[0];

		System.out.println("\nCHECKING:\tResult values for a List of size 1.");
		trueResult = trueListSize;
		testNormal("size()",						fixture.size());
		trueResult = false;
		testNormal("isEmpty()",					fixture.isEmpty());
		trueResult = firstValue;
		testNormal("first()",					fixture.first());
		testNormal("last()",						fixture.last());
		testNormal("get("+testIndex0+")",		fixture.get(testIndex0));
		trueResult = null;
		testNormal("get("+testIndexNeg+")",		fixture.get(testIndexNeg));
		testNormal("get("+testIndex1+")",		fixture.get(testIndex1));
		trueResult = 0;
		testNormal("indexOf("+firstValue+")",	fixture.indexOf(firstValue));

		trueResult = printResult;
		testPrint(printList);
	}

	private void checkSize2(int firstValue, int secondValue) {
		trueListSize = 2;

		int[] printList = { firstValue, secondValue };
		printResult = newln + printList[0] + newln + printList[1];

		System.out.println("\nCHECKING:\tResult values for a List of size 2.");
		trueResult = trueListSize;
		testNormal("size()",						fixture.size());
		trueResult = false;
		testNormal("isEmpty()",					fixture.isEmpty());
		trueResult = firstValue;
		testNormal("first()",					fixture.first());
		testNormal("get("+testIndex0+")",		fixture.get(testIndex0));
		trueResult = secondValue;
		testNormal("last()",						fixture.last());
		testNormal("get("+testIndex1+")",		fixture.get(testIndex1));
		trueResult = null;
		testNormal("get("+testIndexNeg+")",		fixture.get(testIndexNeg));
		testNormal("get("+testIndex2+")",		fixture.get(testIndex2));
		trueResult = 0;
		testNormal("indexOf("+firstValue+")",	fixture.indexOf(firstValue));
		trueResult = 1;
		testNormal("indexOf("+secondValue+")",	fixture.indexOf(secondValue));

		trueResult = printResult;
		testPrint(printList);
	}

	private void methodLoopChoice(int orderIndex, boolean doAdd) {
		Random rnd = new Random();
		int addValue = rnd.nextInt(10)+rndBound;
		rndBound += 10;

		if (orderIndex == 0) {
			if (doAdd) {
				fixture.addFirst(addValue);
				verifyList.add(0, addValue);
				tempListSize++;
			}
			else {
				fixture.removeFirst();
				verifyList.remove(0);
				tempListSize--;
			}
		}
		else if (orderIndex == 1) {
			if (doAdd) {
				fixture.addLast(addValue);
				verifyList.add(verifyList.size(), addValue);
				tempListSize++;
			}
			else {
				fixture.removeLast();
				verifyList.remove(verifyList.size()-1);
				tempListSize--;
			}
		}
		else if (orderIndex == 2) {
			if (doAdd) {
				fixture.insert(addValue, 0);
				verifyList.add(0, addValue);
				tempListSize++;
			}
			else {
				fixture.remove(0);
				verifyList.remove(0);
				tempListSize--;
			}
		}
		else if (orderIndex == 3) {
			if (doAdd) {
				fixture.insert(addValue, 1);
				if (1 > trueListSize) {
					verifyList.add(0, addValue);
					tempListSize++;
				}
				else {
					verifyList.add(1, addValue);
					tempListSize++;
				}
			}
			else {
				fixture.remove(1);
				if (1 < trueListSize) {
					verifyList.remove(1);
					tempListSize--;
				}
			}
		}
		else if (orderIndex == 4) {
			if (doAdd) {
				fixture.insert(addValue, 2);
				if (2 > trueListSize) {
					verifyList.add(1, addValue);
					tempListSize++;
				}
				else {
					verifyList.add(2, addValue);
					tempListSize++;
				}
			}
			else {
				fixture.remove(2);
				if (2 < trueListSize) {
					verifyList.remove(2);
					tempListSize--;
				}
			}
		}
		else if (orderIndex == 5) {
			if (doAdd) {
				fixture.insert(addValue, 3);
				if (3 > trueListSize) {
					verifyList.add(2, addValue);
					tempListSize++;
				}
				else {
					verifyList.add(3, addValue);
					tempListSize++;
				}
			}
			else {
				fixture.remove(3);
				if (3 < trueListSize) {
					verifyList.remove(3);
					tempListSize--;
				}
			}
		}
		else if (orderIndex == 6) {
			if (doAdd) {
				fixture.insert(addValue, 4);
				if (4 > trueListSize) {
					verifyList.add(3, addValue);
					tempListSize++;
				}
				else {
					verifyList.add(4, addValue);
					tempListSize++;
				}
			}
			else {
				fixture.remove(4);
				if (4 < trueListSize) {
					verifyList.remove(4);
					tempListSize--;
				}
			}
		}
		else if (orderIndex == 7)  {
			if (doAdd) {
				fixture.insert(addValue, 5);
				if (5 > trueListSize) {
					verifyList.add(4, addValue);
					tempListSize++;
				}
				else {
					verifyList.add(5, addValue);
					tempListSize++;
				}
			}
			else {
				fixture.remove(5);
				if (5 < trueListSize) {
					verifyList.remove(5);
					tempListSize--;
				}
			}
		}
		else{
			if (doAdd) {
				fixture.insert(addValue, -1);
				verifyList.add(-1, addValue);
			}
			else {
				fixture.remove(-1);
				verifyList.remove(-1);
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
		}
		else if (trueListSize > 1) {
			lastTrueValue = listValues[listValues.length-1];
		}
		else {
			lastTrueValue = null;
		}

		trueResult = trueListSize;
		testNormal("size()",						fixture.size());
		trueResult = (trueListSize == 0);
		testNormal("isEmpty()",					fixture.isEmpty());
		trueResult = (trueListSize > 0) ? listValues[0] : null;
		testNormal("first()",					fixture.first());
		testNormal("get("+0+")",					fixture.get(0));
		trueResult = lastTrueValue;
		testNormal("last()",						fixture.last());
		testNormal("get("+(trueListSize-1)+")",	fixture.get(trueListSize-1));
		trueResult = null;
		testNormal("get("+(-1)+")",				fixture.get(-1));
		testNormal("get("+trueListSize+")",		fixture.get(trueListSize));

		if (trueListSize > 2) {
			for (int i = 1; i < trueListSize; i++) {
				trueResult = listValues[i];
				testNormal("get("+i+")",			fixture.get(i));
			}
		}

		if (trueListSize == 0) {
			trueResult = null;
			testNormal("removeFirst()",			fixture.removeFirst());
			testNormal("removeLast()", 			fixture.removeLast());
			testNormal("remove(" + (-1) + ")",	fixture.remove(-1));
			testNormal("remove(" + 0 + ")",		fixture.remove(0));
			testNormal("remove(" + 1 + ")",		fixture.remove(1));
			trueResult = -1;
			testNormal("indexOf(" + addValues[0] + ")", fixture.indexOf(addValues[0]));
		}
		else {
			for (int i = 0; i < tempListSize; i++) {
				trueResult = i; //TODO check for -1 maybe
				testNormal("indexOf(" + listValues[i] + ")", fixture.indexOf(listValues[i]));
			}
		}

		trueResult = printResult;
		testPrint(listValues);
	}

	private void checkAddRemove(int checkListSize) {
		// add appropriate amounts for checking
		for(int i = 0; i < checkListSize-1; i++) {
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
		}
		else {
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
		totalTally = 14;

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

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void A00_Size0() {
		totalTally = 14;

		System.out.println(sepln + newln + "List Size " + 0 + sepln);
		checkSize0();
	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void A01_Size1() {
		totalTally = 290;

		System.out.println(sepln + newln + "List Size " + 1 + sepln);
		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value1);
		checkSize1(value1);

		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize0();

		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value1);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize0();

		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize1(value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize0();

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value1);
		checkSize1(value1);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize0();

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value1);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize0();

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize1(value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize0();

		whichInsert = "insert(-1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1,-1);
		checkSize0();

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 0);
		checkSize1(value1);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize0();

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 0);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize0();

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 0);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize1(value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize0();

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 1);
		checkSize1(value1);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize0();

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 1);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize0();

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize1(value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize0();

	}

	/**
	 * Test of new, of class SinglyLinkedList.
	 */
	@Test
	public void A02_Size2() {
		fixture.addLast(value1);

		totalTally = 354;

		System.out.println(sepln + newln + "List Size " + 2 + sepln);
		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value2);
		checkSize2(value2, value1);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize1(value1);

		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value2);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize1(value2);

		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize2(value1, value2);
		whichRemove = "remove(2)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(2);
		checkSize2(value1, value2);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize1(value2);

		whichInsert = "addFirst()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addFirst(value1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value2);
		checkSize2(value1, value2);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize1(value2);

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value1);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize1(value2);

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize2(value2, value1);
		whichRemove = "remove(2)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(2);
		checkSize2(value2, value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize1(value1);

		whichInsert = "addLast()";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.addLast(value2);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);

		whichInsert = "insert(-1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2,-1);
		checkSize1(value1);

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 0);
		checkSize2(value2, value1);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize1(value1);

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 0);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize1(value2);

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 0);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize2(value1, value2);
		whichRemove = "remove(2)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(2);
		checkSize2(value1, value2);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize1(value2);

		whichInsert = "insert(0)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 0);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 1);
		checkSize2(value1, value2);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize1(value2);

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 1);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize1(value2);

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 1);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize2(value2, value1);
		whichRemove = "remove(2)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(2);
		checkSize2(value2, value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize1(value1);

		whichInsert = "insert(1)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 1);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);

		whichInsert = "insert(2)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 2);
		checkSize2(value1, value2);
		whichRemove = "removeFirst()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeFirst();
		checkSize1(value2);

		whichInsert = "insert(2)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 2);
		whichRemove = "removeLast()";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.removeLast();
		checkSize1(value2);

		whichInsert = "insert(2)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value1, 2);
		whichRemove = "remove(-1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(-1);
		checkSize2(value2, value1);
		whichRemove = "remove(2)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(2);
		checkSize2(value2, value1);
		whichRemove = "remove(0)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(0);
		checkSize1(value1);

		whichInsert = "insert(2)";
		whichRemove = "";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.insert(value2, 2);
		whichRemove = "remove(1)";
		System.out.println(sepln + newln + whichInsert + whichRemove + sepln);
		fixture.remove(1);
		checkSize1(value1);
	}


}
