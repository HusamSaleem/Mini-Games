package abstractClasses;
import Interfaces.ScoreSystem;

public abstract class Game implements ScoreSystem {
	public static String playerName;
	public static int playerTotalPoints;
	public static int computerTotalPoints;
	
	public Game() {
		playerTotalPoints = 0;
		computerTotalPoints = 0;
	}
	
	
	public abstract void addPointToPlayer();
	
	public abstract void addPointToComputer();
	
	public abstract void printScore();
	
	public final int getPlayerPoints() {
		return playerTotalPoints;
	}
	
	public final int getComputerPoints() {
		return computerTotalPoints;
	}
	
	public static void setPlayerName(String name) {
		playerName = name;
	}
	
	public static void setPlayerPoints(int points) {
		playerTotalPoints = points;
	}
	
	public static void setComputerPoints(int points) {
		computerTotalPoints = points;
	}
	
}
