import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Tic Tac Endeavour
 * @author harrisonjeffs
 * @version 1.001
 *
 */

public class TicTacToe {
	static Scanner in;
	static String[] board;
	static String turn;
	//static String ANSI_RED = "\u001B[31m";
	//static String ANSI_BLACK = "\u001B[0m"; //colours, irrelevant until future use

	public static void main(String[] args) {
		in = new Scanner(System.in);
		board = new String[9];
		turn = "X";
		String winner = null;
		String author = "harrisonjeffs";
		String version = "v1.0";
		populateEmptyBoard();


		System.out.println("Tic Tac Endeavour " + version);
		System.out.println("by " + author);
		System.out.println("--------------------------------");
		System.out.println("                                ");
		printBoard();
		System.out.println("                                ");
		System.out.println("X will play first. Enter a slot number (1-9) to place X in:");	

		while (winner == null) {
			int numInput;
			try {
				numInput = in.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input. Try again");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again");
				continue;
			}
			
			if (board[numInput-1].equals(String.valueOf(numInput))) {
				board[numInput-1] = turn;
				if (turn.equals("X")) {
					turn = "O";
				} else {
					turn = "X";
				}
				printBoard();
				winner = checkWinner();
			} else {
				System.out.println("Slot's already taken champ");
				continue;
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("                                ");
			System.out.println("No one wins, which means you all lose. Play again!");
		} else {
			System.out.println("                                ");
			System.out.println(winner + " wins. Wicked");
		}
	}

	//the fun part
	static String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a+1))) {
				break;
			}
			else if (a == 8) return "draw";
		}

		System.out.println("                                ");
		System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
		return null;
	}

	// the 'gui' (garbage UI) aspect
	static void printBoard() {
	
		System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
	
	}

	// in time, i should be able to re-use the above assets to make a larger board (etc 4x4) with
	// the same logic, allowing for games that require more skill
	
	static void populateEmptyBoard() {
		for (int a = 0; a < 9; a++) {
			board[a] = String.valueOf(a+1);
		}
	}
}

// if i can work out how to randomize selection, i can most likely create an 'artificial' CPU opponent
// that would simply select a random square that is not yet selected by player (X) 
// This however would not be truly 'random' as the computer is not thinking logically about placement
// and would instead just be placing a O at pure random