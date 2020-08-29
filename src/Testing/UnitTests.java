package Testing;
import java.util.Random;

import gameImplementations.GuessingGame;
import gameImplementations.RockPaperScissors;
import gameImplementations.TicTacToe;

public class UnitTests {

	public static void main(String[] args) {
		startTest();
	}
	
	public static void startTest() {
		// Binary Search 
		System.out.println("Case 1a: " + ((binarySearchTest(new int[] {1,5,10,15,20,25,30,35,40}, 10) == true) ? "Passed" : "Failed")); // Should be True
		System.out.println("Case 1b: " + ((binarySearchTest(new int[] {1,5,10,15,20,25,30,35,40}, -1) == false) ? "Passed" : "Failed")); // Should be False
		System.out.println("Case 1c: " + ((binarySearchTest(new int[] {1,5,10,15,20,25,30,35,40}, 40) == true) ? "Passed" : "Failed")); // Should be False
		System.out.println("Case 1d: " + ((binarySearchTest(new int[] {1,5,10,15,20,25,30,35,40}, 1) == true) ? "Passed" : "Failed")); // Should be False
		System.out.println("Case 1e: " + ((binarySearchTest(new int[] {-1,50,2000,3322,50102,23012031,401239123,999999}, -1) == true) ? "Passed" : "Failed")); // Should be False
		
		System.out.println();
		
		// Selection Sort Tests
		System.out.println("Case 2a: " + ((arraySortingTest(new int[] {1,5,10,15,20,25,30,35,40}) == true) ? "Passed" : "Failed")); // Should be True
		System.out.println("Case 2b: " + ((arraySortingTest(new int[] {-1,0,9,-50,302,-500,20,33}) == true) ? "Passed" : "Failed")); // Should be True
		System.out.println("Case 2c: " + ((arraySortingTest(new int[] {44,33,22,11,10,9,9,8,7,6,-1}) == true) ? "Passed" : "Failed")); // Should be True
		System.out.println("Case 2d: " + ((arraySortingTest(new int[] {123,5,213,7,14,7,134,7,234,14,1,67,3546,2,431,4,6723,42}) == true) ? "Passed" : "Failed")); // Should be True
		System.out.println("Case 2e: " + ((arraySortingTest(createRandomArray()) == true) ? "Passed" : "Failed")); // Should be True
		
		System.out.println();
		
		//Rock Paper Scissors winner check
		System.out.println("Case 3a: " + ((rockPaperScissorsWinnerTest("Rock", "Paper") == 1) ? "Passed" : "Failed")); // Should be 1
		System.out.println("Case 3b: " + ((rockPaperScissorsWinnerTest("Rock", "Scissors") == 0) ? "Passed" : "Failed")); // Should be 0
		System.out.println("Case 3c: " + ((rockPaperScissorsWinnerTest("Rock", "Rock") == 2) ? "Passed" : "Failed")); // Should be 2
		System.out.println("Case 3d: " + ((rockPaperScissorsWinnerTest("Paper", "Scissors") == 1) ? "Passed" : "Failed")); // Should be 1
		System.out.println("Case 3e: " + ((rockPaperScissorsWinnerTest("Scissors", "Paper") == 0) ? "Passed" : "Failed")); // Should be 0
		
		System.out.println();
		
		// Tic Tac Toe Winner Test
		String[][] board = {{"1", "2", "3"}, 
							 {"4", "5", "6"},
							 {"7", "8", "9"}};
		
		String[][] board1 = {{"X", "O", "X"}, 
				 			{"X", "5", "O"},
				 			{"X", "O", "O"}};
		
		String[][] board2 = {{"O", "X", "X"}, 
	 						{"X", "O", "O"},
	 						{"X", "X", "O"}};
		
		String[][] board3 = {{"O", "X", "O"}, 
							{"X", "O", "O"},
							{"X", "O", "X"}};
		
		String[][] board4 = {{"O", "O", "O"}, 
							{"X", "X", "O"},
							{"X", "O", "X"}};
		
		String[][] board5 = {{"O", "O", "X"}, 
							{"O", "X", "X"},
							{"X", "O", "X"}};
		
		System.out.println("Case 4a: " + ((ticTacToeWinnerTest(board1, "X", "O") == 0) ? "Passed" : "Failed")); // Should be 0
		System.out.println("Case 4b: " + ((ticTacToeWinnerTest(board2, "X", "O") == 1) ? "Passed" : "Failed")); // Should be 1
		System.out.println("Case 4c: " + ((ticTacToeWinnerTest(board3, "X", "O") == 2) ? "Passed" : "Failed")); // Should be 2
		System.out.println("Case 4d: " + ((ticTacToeWinnerTest(board4, "X", "O") == 1) ? "Passed" : "Failed")); // Should be 1
		System.out.println("Case 4e: " + ((ticTacToeWinnerTest(board5, "X", "O") == 0) ? "Passed" : "Failed")); // Should be 0
	}
	
	public static boolean binarySearchTest(int[] arr, int target) {
		if (GuessingGame.binarySearchCall(arr, target, 0, arr.length - 1)) {
			return true;
		} else {
			 return false;
		}
	}
	
	public static boolean arraySortingTest(int[] arr) {
		int[] newArr = GuessingGame.selectionSort(arr);
		
		for (int i = 0; i < newArr.length -1; i++) {
			if (newArr[i] > newArr[i+1]) {
				return false;
			}
		}
		
		return true;
	}
	
	// 0 = First choice won
	// 1 = second second won
	// 2 = tie
	public static int rockPaperScissorsWinnerTest(String choice1, String choice2) {
		RockPaperScissors rock = new RockPaperScissors();
		
		return rock.checkWinnerTest(choice1, choice2);
	}
	
	// 0 = First choice won
	// 1 = second second won
	// 2 = tie
	public static int ticTacToeWinnerTest(String[][] board, String marker1, String marker2) {
		TicTacToe tic = new TicTacToe();
		
		return tic.checkWinnerTest(board, marker1, marker2);
	}
	
	public static int[] createRandomArray() {
		Random rand = new Random();
		int[] arr = new int[rand.nextInt(100)];

		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);
		

		return arr;
	}
}