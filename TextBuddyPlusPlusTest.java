import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyPlusPlusTest {

 TextBuddyPlusPlus tester;
 
 @Test
 public void testSort() {
  
  // initialise new textbuddy++
  tester = new TextBuddyPlusPlus("mytestfile");
  
  // check intial file is empty
  assertEquals("mytestfile.txt is empty\n", tester.display());
	 
  // adding of test case items to the list
  tester.addAndWriteToFile("g");
  tester.addAndWriteToFile("9");
  tester.addAndWriteToFile("a");
  tester.addAndWriteToFile("3");

  // check that the items have been added properly to the list
  assertEquals("1. g\n2. 9\n3. a\n4. 3\n", tester.display());
  
  /** Expected output of the sorted list: 3, 9, a, g
   * 
   *  check that the list is not sorted
   */
  assertNotEquals("1. 3\n2. 9\n3. a\n4. g\n", tester.display());
  
  tester.sort();
  
  // check that the output is same as expected output
  assertEquals("1. 3\n2. 9\n3. a\n4. g\n", tester.display());
  
 }
 
 @Test
 public void testSearch(){
	 
	  // initialise new textbuddy++
	  tester = new TextBuddyPlusPlus("mytestfile");
	  
	  // check intial file is empty
	  assertEquals("mytestfile.txt is empty\n", tester.display());
	  
	  
	  tester.addAndWriteToFile("little 1");
	  tester.addAndWriteToFile("litle 1");
	  tester.addAndWriteToFile("little brown fox");
	  tester.addAndWriteToFile("big black fox");
	  tester.addAndWriteToFile("big brown box");
	  tester.addAndWriteToFile("little black box");

	  // check that the items have been added properly to the list
	  assertEquals("1. little 1\n2. litle 1\n3. little brown fox\n"
	  		+ "4. big black fox\n5. big brown box\n6. little black box\n", tester.display());
	  
	// check that blah keyword is not found
		  assertEquals("blah not found in mytestfile.txt\n", tester.search("blah"));
		  
		  // check little keyword 
		  assertEquals("little 1\nlittle brown fox\nlittle black box\n", tester.search("little"));
		
		  // check fox keyword
		  assertEquals("little brown fox\nbig black fox\n", tester.search("fox"));
 }
 
 }
