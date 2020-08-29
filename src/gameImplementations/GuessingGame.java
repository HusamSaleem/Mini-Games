package gameImplementations;
import java.util.Random;

import abstractClasses.Game;
import driver.GameManager;

public class GuessingGame extends Game{

	
	public void gameLoop() {
		int[] randomArr = createRandomArray();
		randomArr = selectionSort(randomArr);

		System.out.println("Enter your lucky number!");

		int userNum = GameManager.scan.nextInt();

		if (binarySearch(randomArr, userNum)) {
			System.out.println("You successfully found a number in the randomly generated list!");
			addPointToPlayer();
			printScore();
			System.out.println("Would you like to try again? (Y/N)");
			
			String answer = GameManager.scan.next();
			if (answer.toLowerCase().equals(("y")))
					gameLoop();
			
			GameManager.printOptions();
		} else {
			System.out.println("You failed to guess a number :(");
			System.out.println("Would you like to try again? (Y/N)");
			
			String answer = GameManager.scan.next();
			if (answer.toLowerCase().equals(("y")))
					gameLoop();
			GameManager.printOptions();
		}
	}
	
	public static int[] createRandomArray() {
		Random rand = new Random();
		int[] arr = new int[rand.nextInt(100) + 1] ;

		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);
		

		return arr;
	}

	public static boolean binarySearch(int[] arr, int target) {
		return binarySearchCall(arr, target, 0, arr.length - 1);
	}

	public static boolean binarySearchCall(int[] arr, int target, int left, int right) {
		if (right >= left) {
			int middle = (right - left) / 2;
			middle += left;

			if (arr[middle] == target)
				return true;

			if (arr[middle] > target) {
				right = middle - 1;
				return binarySearchCall(arr, target, left, right);
			} else {
				left = middle + 1;
				return binarySearchCall(arr, target, left, right);
			}
		}

		return false;
	}

	public static int[] selectionSort(int[] arr) {
		int len = arr.length;

		boolean sorted = true;

		for (int i = 0; i < len - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				sorted = false;
				break;
			}
		}

		if (sorted)
			return arr;

		int minInd = 0;
		for (int j = 0; j < len; j++) {
			for (int x = j; x < len; x++) {
				if (arr[x] < arr[minInd]) {
					minInd = x;
				}
			}

			int tempNum = arr[j];
			arr[j] = arr[minInd];
			arr[minInd] = tempNum;
		}
		
		sorted = true;

		for (int i = 0; i < len - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				sorted = false;
				break;
			}
		}

		if (!sorted)
			selectionSort(arr);

		return arr;
	}
	
	public void addPointToPlayer() {
		playerTotalPoints++;
		GameManager.updatePlayerData();
	}

	public void addPointToComputer() {
		computerTotalPoints++;
		GameManager.updatePlayerData();
	}

	@Override
	public void printScore() {
		System.out.println("------------ Score: ------------\nPlayer: " + playerTotalPoints);
	}
}