import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyPlusPlusTest {

 TextBuddyPlusPlus tester = new TextBuddyPlusPlus("mytestfile");
 
 @Test
 public void testSort() {
  
  // check intial file is empty
  assertEquals("mytestfile.txt is empty\n", tester.display());
	 
  tester.addAndWriteToFile("g");
  tester.addAndWriteToFile("9");
  tester.addAndWriteToFile("a");
  tester.addAndWriteToFile("3");

  // check that the items have been added properly to the list
  assertEquals("1. g\n2. 9\n3. a\n4. 3\n", tester.display());
  
 }
 
 }
