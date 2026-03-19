import java.util.ArrayList;
import java.util.Scanner;

public class MyNLP {

  // Instance Variables that store dictionary data
  private ArrayList<String> wordList;
  private ArrayList<String> partOfSpeechList;
  private ArrayList<String> definitionList;

  /**
   * Student Developed Method
   * Constructor that creates the MyNLP object and loads
   * dictionary data from the text file into ArrayLists.
   */
  public MyNLP() {
    parseTextFile("dictionary.txt");
  }

  /**
   * Reads a dictionary text file and separates each line
   * into a word, part of speech, and definition using the "|" symbol.
   * The parsed data is stored in ArrayLists.
   * @param fileName name of the dictionary file being read
   */
  public void parseTextFile(String fileName) {

    // Convert file into a list of lines
    ArrayList<String> lines = FileReader.toStringList(fileName);

    // Temporary lists to store parsed data
    ArrayList<String> wordList = new ArrayList<String>();
    ArrayList<String> partOfSpeechList = new ArrayList<String>();
    ArrayList<String> definitionList = new ArrayList<String>();

    // Loop through each line of the file
    for (String line : lines) {

      // Find positions of the separators "|"
      int firstPipe = line.indexOf("|");
      int secondPipe = line.indexOf("|", firstPipe + 1);
      int thirdPipe = line.indexOf("|", secondPipe + 1);

      // Extract the word, part of speech, and definition
      String word = line.substring(0, firstPipe);
      String partOfSpeech = line.substring(firstPipe + 1, secondPipe);
      String definition = line.substring(secondPipe + 1, thirdPipe);

      // Store the extracted data into lists
      wordList.add(word);
      partOfSpeechList.add(partOfSpeech);
      definitionList.add(definition);
    }

    // Assign parsed lists to instance variables
    this.wordList = wordList;
    this.partOfSpeechList = partOfSpeechList;
    this.definitionList = definitionList;
  }

  /**
   * Breaks a user's sentence into individual lowercase words
   * using a Natural Language Processing technique.
   * @param userInput the sentence entered by the user
   * @return ArrayList containing each parsed word from the input
   */
  public ArrayList<String> parseUserInput(String userInput) {

    ArrayList<String> wordsList = new ArrayList<String>();

    // Split the input string by spaces
    for (String word : userInput.split(" ")) {
      wordsList.add(word.toLowerCase());
    }

    return wordsList;
  }

  /**
   * Searches the dictionary ArrayLists for a word
   * and prints its part of speech and definition if it exists.
   * @param word the word being searched in the dictionary
   */
  public void findWord(String word) {

    // Loop through dictionary words
    for (int i = 0; i < wordList.size(); i++) {

      // If the word matches, print its information
      if (wordList.get(i).equals(word)) {
        System.out.println("\nWord: " + wordList.get(i));
        System.out.println("Part of Speech: " + partOfSpeechList.get(i));
        System.out.println("Definition: " + definitionList.get(i));
        return;
      }
    }

    // If the word is not found
    System.out.println("\nWord not found in dictionary.");
  }

  /**
   * Prompts the user to enter a word, processes the input,
   * and displays dictionary results based on the words entered.
   */
  public void prompt() {

    Scanner input = new Scanner(System.in);

    System.out.println("Welcome to my NLP Dictionary!");
    System.out.print("Enter a word: ");

    // Read user input
    String userInput = input.nextLine();

    // Parse the input into individual words
    ArrayList<String> userWords = parseUserInput(userInput);

    // Look up each word in the dictionary
    for (String word : userWords) {
      findWord(word);
    }

    System.out.println("\nGoodbye!");
    input.close();
  }

  /**
   * Prints a summary showing the first dictionary entry
   * to confirm that the file was parsed correctly.
   */
  public void printSummary() {

    System.out.println("Here's my NLP summary:");

    System.out.println(wordList.get(0));
    System.out.println(partOfSpeechList.get(0));
    System.out.println(definitionList.get(0));
  }

}