import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;
import java.util.*;

public class xkcdpwgen {
 
   
  public static void main(String[] args) throws FileNotFoundException, IOException {

    ArrayList<String> password = new ArrayList<String>();
   
    int numOfLine = 0;
    try (BufferedReader read = new BufferedReader(new FileReader("words.txt"))) {
      while (read.readLine() != null) {
        numOfLine++;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
   
    BufferedReader read = new BufferedReader(new FileReader("words.txt"));
    ArrayList<String> listOfWords = new ArrayList<String>();
   
    for (int i = 0; i < numOfLine; i++) {
      listOfWords.add(read.readLine());
    }
    read.close();  
   
    // defaults
    int words = 4;
    int caps = 0;
    int numbers = 0;
    int symbols = 0;
    String help = "usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\r\n"
        + "                \r\n" + "Generate a secure, memorable password using the XKCD method\r\n"
        + "                \r\n" + "optional arguments:\r\n"
        + "    -h, --help            show this help message and exit\r\n"
        + "    -w WORDS, --words WORDS\r\n"
        + "                          include WORDS words in the password (default=4)\r\n"
        + "    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\r\n"
        + "                          (default=0)\r\n" + "    -n NUMBERS, --numbers NUMBERS\r\n"
        + "                          insert NUMBERS random numbers in the password\r\n"
        + "                          (default=0)\r\n" + "    -s SYMBOLS, --symbols SYMBOLS\r\n"
        + "                          insert SYMBOLS random symbols in the password\r\n"
        + "                          (default=0)";
   
   
   
    // arg buffer
    for (int num = 0; num < args.length; num += 2) {
      if (args[num].equals("-w") || args[num].equals("--words")) {
        words = Integer.parseInt(args[num + 1]);
      }
      if (args[num].equals("-c") || args[num].equals("--caps")) {
        caps = Integer.parseInt(args[num + 1]);
       
      }
      if (args[num].equals("-s") || args[num].equals("--symbols")) {
        symbols = Integer.parseInt(args[num + 1]);
      }
      if (args[num].equals("-n") || args[num].equals("--numbers")) {
        numbers = Integer.parseInt(args[num + 1]);
      }
      if (args[num].equals("-h") || args[num].equals("--help")) {
        words = 0;
        System.out.println(help);
      }
    }
     
    // produces random words for a given amount of times
    for (int i = 0; i < words; i++) {
      Random genrandWord = new Random();
      int getRandWord = genrandWord.nextInt(numOfLine);
      String randWord = listOfWords.get(getRandWord);
      password.add(randWord);

    }

 //   capitalizes random words
    for (int i = 0; i < caps; i++) {
      Random genrandWord = new Random();
      int max = password.size() - 1, min = 0;
      int getRandWord = genrandWord.nextInt(max + 1 - min) + min;
      String randWord = password.get(getRandWord);
      String upRandWord = randWord.substring(0,1).toUpperCase() + randWord.substring(1);
      password.set(getRandWord, upRandWord);
    }
 
    // adds random numbers
    ArrayList<String> nums = new ArrayList<String>();
    nums.add("1");
    nums.add("2");
    nums.add("3");
    nums.add("4");
    nums.add("5");
    nums.add("6");
    nums.add("7");
    nums.add("8");
    nums.add("9");
    nums.add("0");
 
    for (int i = 0; i < numbers; i++) {
      Random randNum = new Random();
      int max = 9, min = 0;
      int randNumGen = randNum.nextInt(max + 1 - min) + min;
      String getRandNum = (nums.get(randNumGen));
      int randIndexOfList = randNum.nextInt(password.size());
      password.add(randIndexOfList, getRandNum);
    }

    // picks a random symbol
     ArrayList<String> syms = new ArrayList<String>();
    syms.add("!");
    syms.add("@");
    syms.add("#");
    syms.add("$");
    syms.add("%");
    syms.add("^");
    syms.add("&");
    syms.add("*");
    syms.add("?");
    syms.add("<");
    syms.add(">");

    for (int i = 0; i < symbols; i++) {
      Random randSym = new Random();
      int max = 11, min = 0;
      int randSymGen = randSym.nextInt(max + 1 - min) + min;
      String getRandSym = syms.get(randSymGen);
      int randAnotherIndexOfList = randSym.nextInt(password.size());
      password.set(randAnotherIndexOfList, getRandSym);
}
    System.out.print(String.join("", password));
}
}


