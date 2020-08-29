package driver;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import abstractClasses.Game;
import gameImplementations.GuessingGame;
import gameImplementations.RockPaperScissors;
import gameImplementations.TicTacToe;

public class GameManager {
	public static Scanner scan = new Scanner(System.in);
	private static HashMap<String, int[]> accounts = new HashMap<String, int[]>();
	
	static TicTacToe ticTacToeGame = new TicTacToe();
	static RockPaperScissors rockPaperScissorsGame = new RockPaperScissors();
	static GuessingGame guessGame = new GuessingGame();

	public static void main(String[] args) {
		mainMenu();
		printOptions();
	}

	private static void mainMenu() {
		System.out.println("1. Create an account");
		System.out.println("2. Log-in");
		System.out.println("3. Print Account Names");
		System.out.println("4. Exit");

		try {
			int inp = scan.nextInt();

			if (inp == 1) {
				getPlayerName();
			} else if (inp == 2) {
				System.out.println("Enter your account's name");

				String name = scan.next();

				if (accounts.containsKey(name.toLowerCase())) {
					int[] accountData = accounts.get(name.toLowerCase());

					Game.setPlayerPoints(accountData[0]);
					Game.setComputerPoints(accountData[1]);
					Game.setPlayerName(name);

					System.out.println("Logged in as: " + name);
					printOptions();
				} else {
					System.out.println("No account exists with that username");
					mainMenu();
				} 
			} else if (inp == 3) {
				printAccountNames();
				mainMenu();
			}
			else if (inp == 4) {
				System.exit(0);
			} else {
				System.out.println("Invalid choice");
				mainMenu();
			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid choice, try again...");
			mainMenu();
		}
	}

	public static void printOptions() {
		System.out.println("1. Tic-Tac-Toe");
		System.out.println("2. Rock Paper Scissors");
		System.out.println("3. Guess to see if a number exists in a randomly generated array");
		System.out.println("4. Log out");
		System.out.println("Enter a number for the option you want");

		getInput();
	}

	private static void getInput() {
		int num = scan.nextInt();

		if (num == 1) {
			ticTacToeGame.startGame();
		} else if (num == 2) {
			rockPaperScissorsGame.gameLoop();
		} else if (num == 3) {
			guessGame.gameLoop();
		}

		else if (num == 4) {
			Game.setPlayerName("");
			Game.setPlayerPoints(0);
			Game.setComputerPoints(0);
			mainMenu();
		} else {
			System.out.println("Invalid choice");
			getInput();
		}

	}

	private static void getPlayerName() {
		System.out.println("Please enter your name!");

		String playerName = scan.next();
		Game.setPlayerName(playerName);
		System.out.println("Welcome " + playerName + "!");
		accounts.put(playerName.toLowerCase(), new int[] { 0, 0 });
		
		printOptions();
	}
	
	public static void updatePlayerData() {
		if (accounts.containsKey(Game.playerName.toLowerCase())) { 
			int[] newData = {Game.playerTotalPoints, Game.computerTotalPoints};
			accounts.replace(Game.playerName.toLowerCase(), newData);
			
		} else {
			System.out.println("Failed to save data");
		}
	}
	
	private static void printAccountNames() {
		if (accounts.size() == 0) {
			System.out.print("No accounts exist\n");
			return;
		}
		
		int id = 0;
		for (Map.Entry<String, int[]> map : accounts.entrySet()) { 
            System.out.println("Account ID: " + id++ + ", Username: " + map.getKey());
		}
		
		System.out.println();
	}
}