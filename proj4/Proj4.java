/**
 * Project 4
 * Game that reads in 5 and 6 letter words from a .txt file and then jumbles
 * them up. The user is then allowed to guess the word, and can also ask 
 * for a hint if they need one. Contains an additional method called shuffle() that 
 * will shuffle strings.
 *
 * @author Samantha Montgomery
 * @version 4
 */

import java.util.*;
import java.text.*;
import java.io.*;

public class Proj4 {
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		Scanner inFile = new Scanner(new File("words56.txt"));
		Random r = new Random();
		int size = Integer.parseInt(inFile.nextLine());
		
		String [] words = new String[size];
		for (int i = 0; i < size; i++) {
			words[i] = inFile.nextLine();
		}
		
		char gameContinue = 'y';
		int points = 10; //sets up out current points variable
		int total = 0; //how many points the user has total
		
		String currentPuzzle = words[r.nextInt(size)]; // this picks our random word
		String currentPuzzleShuffle = shuffle(currentPuzzle); //this is a string variable for the shuffled word
		
		do { // start of our jumble game do while loop
			
			//System.out.println("Current puzzle unscramble: " + currentPuzzle);
			System.out.println("Current puzzle: " + currentPuzzleShuffle); //this prints out the scrambled word
			System.out.println("Current points for word: " + points);
			
			char choice = 'a'; //our choice variable for what option the user picks
			
			while (choice != 'g' && choice != 'n' && choice != 'h' && choice != 'q') { //repeat until the user enters a valid choice
				System.out.println("Enter (g)uess, (n)ew word, (h)int, or (q)uit: ");
				choice = (s.nextLine().toLowerCase().charAt(0));
				
				if (choice != 'g' && choice != 'n' && choice != 'h' && choice != 'q') { //if the user types in a wrong choice print this
					System.out.println("Please enter a valid answer.");
				}
			}
			
			switch (choice) {
			case 'g': //this is a guess
				System.out.println("What is the guess? ");
				String guess = s.nextLine();
				
				if (guess.equals(currentPuzzle)) {
					System.out.println("You guessed it!");
					System.out.println("You recieved " + points + " points for that word."); //prints out how many points they got (for that word)
					currentPuzzle = words[r.nextInt(size)]; //pick random
					total += points;
					currentPuzzleShuffle = shuffle(currentPuzzle); //shuffle
					points = 10; //set points for current word back to 10
				} else {
					System.out.println("Nope, sorry.");
					
					if (points != 0) //if our points don't equal zero
					{
					points -= 1; //subtract one for guessing incorrectly
					}
				}
				break;
			case 'n': //generate a new word
				System.out.println("The word was " + currentPuzzle + ".");
				currentPuzzle = words[r.nextInt(size)]; //pick random
				currentPuzzleShuffle = shuffle(currentPuzzle); //shuffle
				points = 10; //set points for current word back to 10
				break;
			case 'h': //give the user a hint
				int randHint = r.nextInt(currentPuzzle.length()); //position of hint
				char hint = currentPuzzle.charAt(randHint); //character of hint
				System.out.println("The character '" + hint + "' is at postition " + (randHint+1));
				points /= 2;
				break;
			case 'q': //exit the game
				System.out.println("Total score: " + total);
				gameContinue = 'n';
				break;
			}
			
			System.out.println(" "); //nice blank line aesthetic
			
		} while (gameContinue == 'y');
		
		//closing our connections
		inFile.close();
		s.close();
	}
	
	//new method that will shuffle strings
	public static String shuffle(String shuffleWord) { //takes in a string for the parameter
		
	    char[] letters = shuffleWord.toCharArray(); //this is turning our string into a char array called 'letters'
	    
	    for (int i = 0; i < letters.length; i++) { //this loop goes as many times as the length of our letters array (a.k.a the number of letters in our orig. word)
	    		
	    		//this generates a random integer from a range of 0 to the length of array (but doesn't include last # b/c that would be out of bounds for our array)
	        int randomIndex = (int)(Math.random() * letters.length); 
	        
	        //sets the character at index i to a temporary variable
	        char temp = letters[i]; 
	        
	        //this changes our char array at this index i to a different character from the random index/# we generated earlier
	        letters[i] = letters[randomIndex]; 
	        
	        //takes our random index in the char array and sets it to our temporary guy from earlier
	        letters[randomIndex] = temp; 
	        
	    }
	    return new String(letters); //returns string in a scrambled fashion
	}
}
