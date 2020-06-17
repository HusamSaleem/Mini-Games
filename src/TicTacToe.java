import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class TicTacToe extends Game {
	private boolean playerTurn;

	private int openSpots;
	List<Integer> remainingNumberSlots;
	private String playerMarker;
	private String computerMarker;
	private int playerIndexChoice;

	private String[][] board;


	public void startGame() {
		Random rand = new Random();
		int chanceToGoFirst = rand.nextInt(100);

		this.playerTurn = (chanceToGoFirst >= 50) ? true : false;
		this.openSpots = 9;
		this.playerMarker = "X";
		this.computerMarker = "O";

		board = new String[3][3];
		remainingNumberSlots = new ArrayList<Integer>();
		
		System.out.println();
		System.out.println();
		
		clearBoard();
		printScore();
		printBoard();
		
		System.out.println();

		while (openSpots > 0) {
			if (!playerTurn) {
				System.out.println("Computer Went First!\n");
				getComputerChoice();
				printBoard();
				playerTurn = true;
			}
			
			if (!getInput()) {
				System.out.println("Enter in a empty slot only");
				printBoard();
			} else {
				printBoard();
				
				if(checkWinner() != -1)
					break;
				
				if (openSpots > 0) {
					getComputerChoice();
					printBoard();
				}
			}
			
			if(checkWinner() != -1)
				break;
		}
		
		printScore();
		System.out.println("Would you like to play again? (Y/N)");

		String input = GameManager.scan.next();
		if (input.toLowerCase().equals("y"))
			startGame();
		else {
			GameManager.printOptions();
		}
	}

	private boolean getInput() throws InputMismatchException {
		System.out.println("Your turn!");
		System.out.println("Please enter a number slot that is empty (1-9) to place your marker");

		playerIndexChoice = GameManager.scan.nextInt();

		if (playerIndexChoice > 9 || playerIndexChoice < 1) {
			System.out.println("Enter a valid input please...");
		}

		if (placeMarker(playerIndexChoice, playerMarker)) {
			return true;
		}

		return false;
	}

	private void printBoard() {
		System.out.println();
		System.out.println();
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			System.out.print("|\t");
			for (int j = 0; j < 3; j++)
				System.out.print(board[i][j] + "\t|\t");

			System.out.println();
			System.out.println("-------------------------------------------------");
		}
	}

	private boolean placeMarker(int choice, String mark) {

		int i = (choice <= 3) ? 0 : (choice > 3 && choice <= 6) ? 1 : (choice > 6 && choice <= 9) ? 2 : -1;
		int j = (choice % 3 == 0) ? 2
				: (choice == 2 || choice == 5 || choice == 8) ? 1
						: (choice == 1 || choice == 4 || choice == 7) ? 0 : -1;

		if (this.board[i][j].equals(this.computerMarker) || this.board[i][j].equals(this.playerMarker))
			return false;

		this.board[i][j] = mark;
		remainingNumberSlots.remove((Object) choice);
		this.openSpots--;
		
		return true;
	}

	private void getComputerChoice() {
		Random rand = new Random();

		int randomNum = rand.nextInt(remainingNumberSlots.size());

		placeMarker(this.remainingNumberSlots.get(randomNum), computerMarker);
	}

	private void clearBoard() {
		int numCount = 1;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = "" + (numCount);
				this.remainingNumberSlots.add(numCount);
				numCount++;
			}
		}

		this.openSpots = 9;
	}

	private int checkWinner() {
		// Horizontal Checking
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])
						&& board[i][j].equals(playerMarker)) {
					System.out.println("Player Wins!");
					addPointToPlayer();
					return 0;
				} else if (board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])
						&& board[i][j].equals(computerMarker)) {
					System.out.println("Computer Wins!");
					addPointToComputer();
					return 1;
				}

				break;
			}
		}

		// Vertical Checking
		int verticalIndex = 0;
		for (int j = 0; j < board.length; j++) {
			if (board[verticalIndex][j].equals(board[verticalIndex + 1][j])
					&& board[verticalIndex][j].equals(board[verticalIndex + 2][j])
					&& board[verticalIndex][j].equals(playerMarker)) {
				System.out.println("Player Wins!");
				addPointToPlayer();
				return 0;
			} else if (board[verticalIndex][j].equals(board[verticalIndex + 1][j])
					&& board[verticalIndex][j].equals(board[verticalIndex + 2][j])
					&& board[verticalIndex][j].equals(computerMarker)) {
				System.out.println("Computer Wins!");
				addPointToComputer();
				return 1;
			}
		}
		
		// Diagonal Check
		if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals(playerMarker)) {
			System.out.println("Player Wins!");
			addPointToPlayer();
			return 0;
		} else if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals(computerMarker)) {
			System.out.println("Computer Wins!");
			addPointToComputer();
			return 1;
		}
		
		if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals(playerMarker)) {
			System.out.println("Player Wins!");
			addPointToPlayer();
			return 0;
		} else if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals(computerMarker)) {
			System.out.println("Computer Wins!");
			addPointToComputer();
			return 1;
		}
		
		if (openSpots == 0) {
			System.out.println("It's a tie!");
			return 2;
		}
		
		return -1;
	}
	
	// For test purposes
	public int checkWinnerTest(String[][] board, String marker1, String marker2) {
		// Horizontal Checking
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])
						&& board[i][j].equals(marker1)) {
					System.out.println("Player Wins!");
					//addPointToPlayer();
					return 0;
				} else if (board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2])
						&& board[i][j].equals(marker2)) {
					System.out.println("Computer Wins!");
					//addPointToComputer();
					return 1;
				}

				break;
			}
		}

		// Vertical Checking
		int verticalIndex = 0;
		for (int j = 0; j < board.length; j++) {
			if (board[verticalIndex][j].equals(board[verticalIndex + 1][j])
					&& board[verticalIndex][j].equals(board[verticalIndex + 2][j])
					&& board[verticalIndex][j].equals(marker1)) {
				System.out.println("Player Wins!");
				//addPointToPlayer();
				return 0;
			} else if (board[verticalIndex][j].equals(board[verticalIndex + 1][j])
					&& board[verticalIndex][j].equals(board[verticalIndex + 2][j])
					&& board[verticalIndex][j].equals(marker2)) {
				System.out.println("Computer Wins!");
				//addPointToComputer();
				return 1;
			}
		}
		
		// Diagonal Check
		if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals(marker1)) {
			System.out.println("Player Wins!");
			//addPointToPlayer();
			return 0;
		} else if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals(marker2)) {
			System.out.println("Computer Wins!");
			//addPointToComputer();
			return 1;
		}
		
		if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals(marker1)) {
			System.out.println("Player Wins!");
			//addPointToPlayer();
			return 0;
		} else if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals(marker2)) {
			System.out.println("Computer Wins!");
			//addPointToComputer();
			return 1;
		}
		
		System.out.println("It's a tie!");
		return 2;
	}

	public void addPointToPlayer() {
		playerTotalPoints++;
		GameManager.updatePlayerData();
	}

	public void addPointToComputer() {
		computerTotalPoints++;
		GameManager.updatePlayerData();
	}
	
	public void printScore() {
		System.out.println("------------ Score: ------------\nPlayer: " + playerTotalPoints + "\nComputer: " + computerTotalPoints + "\n");
	}
}