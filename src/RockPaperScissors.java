import java.util.InputMismatchException;
import java.util.Random;

public class RockPaperScissors extends Game {
	
	public void gameLoop() {
		printScore();
		
		while (true) {
			String playerChoice = getInput();
			String computerChoice = getComputerChoice();
			
			if (checkWinner(playerChoice, computerChoice) != -1) {
				printScore();
				
				System.out.println("Do you want to play again? (Y/N)");
				String inp = GameManager.scan.next();
				
				if (!inp.toLowerCase().equals(("y"))) {
					GameManager.printOptions();
					break;
				}
			}
		}
	}
	
	private String getInput() throws InputMismatchException{
		System.out.println("Rock | Paper | Scissors");
		System.out.println("Type an option");
		
		String input = GameManager.scan.next();
		
		if (input.toLowerCase().equals("rock") || input.toLowerCase().equals("paper") || input.toLowerCase().equals("scissors")) {
			return input; 
		} else {
			System.out.println("Enter a valid input please");
			getInput();
		}
		
		return null;
	}
	
	private String getComputerChoice() {
		String[] choices = {"Rock", "Paper", "Scissors"};
		
		Random rand = new Random();
		String choice =  choices[rand.nextInt(choices.length)];
		System.out.println("Computer chose: " +  choice);
		
		return  choice;
	}
	
	public void printScore() {
		System.out.println("------------ Score: ------------\nPlayer: " + playerTotalPoints + "\nComputer: " + computerTotalPoints + "\n");
	}
	
	public int checkWinner(String playerChoice, String computerChoice) {
		
		if (playerChoice.toLowerCase().equals(computerChoice.toLowerCase())) {
			System.out.println("\nIt's a tie!");
			return 2;
		}
		
		if (playerChoice.toLowerCase().equals("rock") && computerChoice.toLowerCase().equals("scissors")) {
			System.out.println("\nPlayer Wins!");
			addPointToPlayer();
			return 0;
		} else if (playerChoice.toLowerCase().equals("paper") && computerChoice.toLowerCase().equals("rock")) {
			System.out.println("\nPlayer Wins!");
			addPointToPlayer();
			return 0;
		} else if (playerChoice.toLowerCase().equals("scissors") && computerChoice.toLowerCase().equals("paper")) {
			System.out.println("\nPlayer Wins!");
			addPointToPlayer();
			return 0;
		}
		
		if (computerChoice.toLowerCase().equals("rock") && playerChoice.toLowerCase().equals("scissors")) {
			System.out.println("\nComputer Wins!");
			addPointToComputer();
			return 1;
		} else if (computerChoice.toLowerCase().equals("paper") && playerChoice.toLowerCase().equals("rock")) {
			System.out.println("\nComputer Wins!");
			addPointToComputer();
			return 1;
		} else if (computerChoice.toLowerCase().equals("scissors") && playerChoice.toLowerCase().equals("paper")) {
			System.out.println("\nComputer Wins!");
			addPointToComputer();
			return 1;
		}
		
		return -1;
	}

	public int checkWinnerTest(String playerChoice, String computerChoice) {
		
		if (playerChoice.toLowerCase().equals(computerChoice.toLowerCase())) {
			System.out.println("\nIt's a tie!");
			return 2;
		}
		
		if (playerChoice.toLowerCase().equals("rock") && computerChoice.toLowerCase().equals("scissors")) {
			System.out.println("\nPlayer Wins!");
			
			return 0;
		} else if (playerChoice.toLowerCase().equals("paper") && computerChoice.toLowerCase().equals("rock")) {
			System.out.println("\nPlayer Wins!");

			return 0;
		} else if (playerChoice.toLowerCase().equals("scissors") && computerChoice.toLowerCase().equals("paper")) {
			System.out.println("\nPlayer Wins!");

			return 0;
		}
		
		if (computerChoice.toLowerCase().equals("rock") && playerChoice.toLowerCase().equals("scissors")) {
			System.out.println("\nComputer Wins!");

			return 1;
		} else if (computerChoice.toLowerCase().equals("paper") && playerChoice.toLowerCase().equals("rock")) {
			System.out.println("\nComputer Wins!");

			return 1;
		} else if (computerChoice.toLowerCase().equals("scissors") && playerChoice.toLowerCase().equals("paper")) {
			System.out.println("\nComputer Wins!");

			return 1;
		}
		
		return -1;
	}
	
	public void addPointToPlayer() {
		playerTotalPoints++;
		GameManager.updatePlayerData();
	}

	public void addPointToComputer() {
		computerTotalPoints++;
		GameManager.updatePlayerData();
	}
	
}