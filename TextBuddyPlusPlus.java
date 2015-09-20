import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TextBuddyPlusPlus {
  
  private static final String MESSAGE_ADDED = "added to %s: \"%s\"\n";
  private static final String MESSAGE_CLEAR = "all content deleted from %s\n";
  private static final String MESSAGE_DELETE = "delete from %s: \"%s\"\n";
  private static final String MESSAGE_INVALID_FORMAT = "invalid command\n";
  private static final String MESSAGE_INVALID_ID = "invalid item ID\n";
  private static final String MESSAGE_EMPTY = "%s is empty\n";
  private static final String MESSAGE_DISPLAY = "%d. %s\n";
  private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use\n";
  private static final String MESSAGE_SORT = "%s has been sorted\n";
  private static final String MESSAGE_NOT_FOUND = "%s not found in %s\n";
  
  private static final String FILENAME = "mytextfile";
  
  // These are the possible command types
  enum COMMANDS {
    DISPLAY, ADD, DELETE, CLEAR, SORT, SEARCH, EXIT
  };
  
  // Filename of output text file
  private String fileName;
  
  // This stores the inputs
  private final ArrayList<String> list = new ArrayList<String>();
  
  private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
  public static void main(String[] args) {
    TextBuddyPlusPlus textBuddyPlusPlus;
    
    /** if file name stated by user, follows what user input
      * else name is mytextfile.txt
      */
    if (args.length == 0) {
      textBuddyPlusPlus = new TextBuddyPlusPlus();
    } else {
      textBuddyPlusPlus = new TextBuddyPlusPlus(args[0]);
    }
    
    textBuddyPlusPlus.run();
  }
  
  public TextBuddyPlusPlus() {
    fileName = FILENAME.concat(".txt");
    print(String.format(MESSAGE_WELCOME, fileName));
  }
  
  public TextBuddyPlusPlus(String args) {
    fileName = args.concat(".txt");
    print(String.format(MESSAGE_WELCOME, fileName));
  }
  
  /**
   * Bulk of program. Program will terminate on exit command.
   */
  public void run() {
    
    while (true) {
      
      try {
        // read in the string input by user
        String userCommand = in.readLine();
        
        // parses the string into a function that determines the command to be done
        executeCommand(userCommand);
        
      } catch (Exception e) {
        print(MESSAGE_INVALID_FORMAT);
      }
    }
  }
  
  public void executeCommand(String userCommand) {
    // Save the first input as the command and the second input as the parameter
    String[] cmd = userCommand.trim().split(" ", 2);
    
    // Check for command according to the list of possible command types    
    switch (COMMANDS.valueOf(cmd[0].toUpperCase())) {
      
      case DISPLAY :
        print(display());
        break;
        
      case ADD :
        print(addAndWriteToFile(cmd[1]));
        break;
        
      case DELETE :
        print(delete(cmd[1]));
        break;
        
      case CLEAR :
        print(clear());
        break;
        
      case SORT :
        print(sort());
        break;
        
      case SEARCH :
        print(search(cmd[1]));
        break;
        
      case EXIT :
        System.exit(0);
        break;
        
      default:
        print(MESSAGE_INVALID_FORMAT);
    }
  }
  
  /**
   * Writes the list to the file.
   * 
   */
  private void writeToFile() {
    try {
      FileWriter fileStream = new FileWriter(fileName);
      BufferedWriter file = new BufferedWriter(fileStream);
      
      for (String storedItem : list) {
        file.write(storedItem);
        file.newLine();
      }
      
      file.flush();
      file.close();
    } catch (IOException e) {
      System.out.println("Error during writing");
    }
  }
  
  /**
   * Prints out the list.
   * 
   * @return Returns the numbered list.
   */
  public String display() {
    StringBuffer output = new StringBuffer();
    if (list.isEmpty()) {
      output.append(String.format(MESSAGE_EMPTY, fileName));
    } else {
      for (int i = 0; i < list.size(); i++) {
        output.append(String.format(MESSAGE_DISPLAY, (i + 1),list.get(i)));
      }
    }
    return output.toString();
  }
  
  /**
   * Adds an item to the list and saves to file.
   * 
   * @return "item added" message
   */
  public String addAndWriteToFile(String item) {
    list.add(item);
    writeToFile();
    return String.format(MESSAGE_ADDED, fileName, item);
  }
  
  /**
   * Deletes an item from the list and saves to file. 
   * 
   * Checks if list is empty or if item index is out of bounds.
   * 
   * @return "message deleted" message
   */
  public String delete(String indexOfItem) {
    
    int id = Integer.parseInt(indexOfItem);
    
    if (id > 0 && list.size() >= id) { // Check if ID is valid
      int index = id - 1; // 0 based indexing list
      String item = list.get(index);
      list.remove(index);
      writeToFile();
      return String.format(MESSAGE_DELETE, fileName, item);
      
    } else if (list.isEmpty()) {
      return String.format(MESSAGE_EMPTY, fileName);
    } else {
      return String.format(MESSAGE_INVALID_ID);
    }
  }
  
  /**
   * Clears the list and saves to file.
   * 
   * @return cleared message
   */
  public String clear() {
    list.clear();
    writeToFile();
    return String.format(MESSAGE_CLEAR, fileName);
  }
  
  /**
   * Sorts the list and saves to file.
   * 
   * @return sorted message
   */
  public String sort() {
    Collections.sort(list);
    writeToFile();
    return String.format(MESSAGE_SORT, fileName);
  }
  
  /**
   * Searches the list for the keyword.
   * 
   * @return items with the keyword in the name
   */
  public String search(String keyword) {
    
    StringBuffer output = new StringBuffer();
    
    // iterate through all the elements to look for the keyword
    for(int i=0; i < list.size(); i++) {
      if (list.get(i).contains(keyword)) {
        output.append(list.get(i));
        output.append("\n");
      }
    }
    
    // message if keyword is not found in the list
    if(output.length() == 0) {
      output.append(String.format(MESSAGE_NOT_FOUND, keyword, fileName));
    }
    
    return output.toString();
  }
  
  private void print(String str){
    System.out.print(str);
  }
  
}