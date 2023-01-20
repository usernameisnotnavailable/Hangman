import java.util.Arrays;
import java.util.Scanner;
import java.util.*;



public class Hangman {

    public static Scanner scan = new Scanner(System.in);
    

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        
        char guess = ' ';
        int numberOfWrongGuesses = 0;
        char[] wrongGuessHolder = new char[7];
        
        String choosenWord = randomWord();

        char[] choosenWordInArray = new char[choosenWord.length()];
        for (int i = 0; i < choosenWord.length(); i++) {
            choosenWordInArray[i] = choosenWord.charAt(i);  /* Convert string to array */
        }
        char[] placeHolder = new char[choosenWordInArray.length];
        for (int i = 0; i < placeHolder.length; i++) {
            placeHolder[i] ='_';
        }

        System.out.println(choosenWord);
        System.out.println(choosenWordInArray.length);

        

        System.out.println("LET THE GAME BEGIN!! HAHAHAHA!");

         
        System.out.println(gallows[numberOfWrongGuesses]);
        printPlaceholders(placeHolder);
        printMissedGuesses(numberOfWrongGuesses, wrongGuessHolder);
        
        
        while (numberOfWrongGuesses != 6) {
            guess = takingGuess();                                                                

            
            if (checkGuess(guess, choosenWordInArray) == false){                        /* Checks if the guess is correct runs if wrong*/
                numberOfWrongGuesses += 1;
                wrongGuessHolder[numberOfWrongGuesses] = guess;                         /*Stores misses */
                storeMissedGuesses(guess, numberOfWrongGuesses, wrongGuessHolder);
            }
            System.out.println(gallows[numberOfWrongGuesses]); 
            placeHolder = Arrays.copyOf(returnPlaceholder(guess, choosenWordInArray, placeHolder), choosenWordInArray.length);  
            printPlaceholders(placeHolder);                             /*Placeholder function*/
            printMissedGuesses(numberOfWrongGuesses, wrongGuessHolder);
            checkWin(choosenWordInArray, placeHolder);

        }

        System.out.println("\n" + gallows[numberOfWrongGuesses]);
        placeHolder = Arrays.copyOf(returnPlaceholder(guess, choosenWordInArray,placeHolder), choosenWordInArray.length);
        printPlaceholders(placeHolder);
        printMissedGuesses(numberOfWrongGuesses, wrongGuessHolder);
        System.out.println("\nSorry, you loose!");
        System.out.println("\n The word was: " + choosenWord);
        
        
    }

    public static String randomWord() {
        double random = Math.random()*64;
        int randomInt = (int)random;
        return words[randomInt];
    }

    public static boolean checkGuess(char guess, char[] choosenWordInArray) {

        for (int i = 0; i < choosenWordInArray.length; i++) {
            if (Character.compare(choosenWordInArray[i], guess) == 0 ) {
                    return true;
                }
            }
           return false;
    }


    public static char[] returnPlaceholder(char guess, char[] choosenWordInArray, char[] placeHolder) {
        
        for (int i = 0; i < choosenWordInArray.length; i++){
            if (Character.compare(choosenWordInArray[i], guess) == 0 ){
                placeHolder[i] = choosenWordInArray[i];

            } else if (Character.compare(choosenWordInArray[i], '_') == 0 ) {
                placeHolder[i] = '_';
            } 
        }
        
        return placeHolder;
    }

    public static void printPlaceholders(char [] placeHolder) {  
        
        System.out.print("\nWord: \t");
            for (int i = 0; i < placeHolder.length; i++) {                   
                System.out.print(placeHolder[i]);
            }
        } 

        public static char takingGuess() {      /* Taking user input and converts to char */
            System.out.print("\nGuess: ");
            char guess  = scan.next().charAt(0);
                return guess;
            
        }
        

        public static char[] storeMissedGuesses(char guess, int numberOfWrongGuesses, char[] wrongGuessHolder) {

            wrongGuessHolder[numberOfWrongGuesses] = guess;
            
            return wrongGuessHolder;
        }

        public static void printMissedGuesses(int numberOfWrongGuesses, char[] wrongGuessHolder) {
            System.out.print("\nMisses: ");
            for (int i = 0; i <= numberOfWrongGuesses; i++){
                System.out.print(wrongGuessHolder[i] + " ");
            }
        }
        public static void checkWin(char[] choosenWordInArray, char[] placeHolder) {
            int counter = 0;
            for (int i = 0; i < choosenWordInArray.length; i++) {
                if (Character.compare(choosenWordInArray[i],placeHolder[i]) == 0) {
                        counter += 1;
                }
            }
            if (choosenWordInArray.length == counter) {
                System.out.println("\n Congrats!! You win!!");
                System.exit(0);
            }
        }

}
