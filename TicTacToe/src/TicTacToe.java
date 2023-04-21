import java.util.Scanner;

public class TicTacToe {
	
	// Takes string input and makes sure it is an integer
	public static int stringToInteger(Scanner scnr, int max) {
		int answer = 0; // int variable meant to hold the users inputted answer
		String userInput;
		//While loop continues until user inputs a number
		   	while (answer == 0) {
		   		userInput = scnr.nextLine(); // Gets user input and stores it in a string variable.
				try 
		   		{
					answer = Integer.parseInt(userInput); // Takes user input and stores it in the variable "answer".
		    	}  
		    	catch (NumberFormatException e)  // If user inputs something that isn't a number.
		    	{ 
		    		System.out.println("That was not a number, try again");
		    	}
				if (answer <= 0 || answer > max ) {
					System.out.println("Please enter a number from 1 - " + max);
					answer = 0;
				}
		   	}
		   	return answer;
	}
	
	public static boolean playerWins(boolean win, int turn) {
		win = true;
			if (turn % 2 == 1) {
				System.out.println("Player 1 wins");
			} else {
				System.out.println("Player 2 wins");
			}
		return win;
		}
	
	public static boolean winCondition(int[] board, int turn) {
		boolean win = false;
		for (int i = 0; i < 9; i++) {
			
			// Checks that the space is occupied
			if (board[i] != 0) {
					// Checks for row win condition using left column
					if (i == 0 || i == 3 || i == 6) {
						if (board[i] == board[i+1] && board[i] == board[i+2]) {
								win = playerWins(win, turn);
							}
						}
			
					// Checks for column win condition using top row
					if (i < 3) {
						if (board[i] == board[i+3] && board[i] == board[i+6]) {
							win = playerWins(win, turn);
						}
					}
			
					// Checks for diagonal win condition using top left corner
					if (i == 0) {
						if (board[i] == board[i+4] && board[i] == board[i+8]) {
							win = playerWins(win, turn);
						}
					}
			
					// Checks for diagonal win condition using top right corner
					if (i == 2) {
						if (board[i] == board[i+2] && board[i] == board[i+4]) {
							win = playerWins(win, turn);
						}
					}
				}
			}
		return win;
		}
	
	public static String num2token(int num) {
		if (num == 0) {
			return " ";
		}
		if (num == 1) {
			return "X";
		}
		
		return "O";
	}

	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in); // Scanner variable for recieving user input
		int yOrN;
		int turn = 1; // To keep track of who's turn it is
		int x = 1; // Holds the value 1, which is placed in the array to represent " x "
		int o = 2; // Holds the value 2, which is placed in the array to represent " o "
		int xOrO; // Holds x or o depending on whos turn it is
		int index; // Where the player makes their move
		int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // An int array variable is declared with 9 indexes each containing the value 0
		boolean gameOver = false;
		boolean playAgain = false;
		
		while (!gameOver) {
			
			if (playAgain) {
				turn = 1; // To keep track of who's turn it is
				
				// Replaces all values of board array with 0, effectively clearing the board
				for (int i = 0; i < 9; i++) {
					board[i] = 0;
				}
				
				playAgain = false;
			}
			
			// Prints the board (should be empty)
			System.out.println(num2token(board[0]) + " | " + 
			num2token(board[1]) + " | " + num2token(board[2])+ "\n" + num2token(board[3]) + " | " + 
			num2token(board[4]) + " | " + num2token(board[5])+ "\n"+ num2token(board[6]) + " | " + 
			num2token(board[7]) + " | " + num2token(board[8])+ "\n");
			
			// The game loop
			game: while(turn <= 9) {
				
			// If turn is odd, it is player 1's turn, else, it is player 2's turn
						if (turn % 2 == 1) {
							System.out.println("It is player 1's turn.");
							xOrO = x; // Player one is x
						} else {
							System.out.println("It is player 2's turn.");
							xOrO = o; // Player two is o
						}
						
						System.out.println("Enter a number from 1 - 9 to make your move."); // (1 - 9 because people don't count zero)
						index = stringToInteger(scnr, 9) - 1; // The player makes their move ( -1 due to indexes starting at 0)
						
						// Checks if the index is already occupied or not
						if(board[index] == 0) {
							board[index] = xOrO; // An x or an o is placed on the board depednding on who's turn it is
						} else {
							System.out.println("That space is occupied, try again.");
							turn--; // Sets the turn back so that the same player can go again.
						}
						
						// Prints the board
						System.out.println(num2token(board[0]) + " | " + 
								num2token(board[1]) + " | " + num2token(board[2])+ "\n" + num2token(board[3]) + " | " + 
								num2token(board[4]) + " | " + num2token(board[5])+ "\n"+ num2token(board[6]) + " | " + 
								num2token(board[7]) + " | " + num2token(board[8])+ "\n");
			
						// The win condition loop, if no win, game checks for a tie
						if (winCondition(board, turn)) {
							break game;
						}
						
						turn++; // Turn increments by one to change turns
							
							// Checks for tie on last turn
							if (turn == 10) {
								System.out.println("The game has ended in a tie");
							}
			
			} // For loop
			
			System.out.println("Would you like to play again? \n1. Yes \n2. No");
			
			yOrN = stringToInteger(scnr, 2);
			
			if (yOrN == 1) {
				playAgain = true;
			} else {
				gameOver = true;
			}
		
		} // While loop

	}

}
