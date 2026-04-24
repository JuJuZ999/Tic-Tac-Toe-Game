package ticTacToeGame;

import java.util.Scanner;

public class TicTacToeGame {
	
	//Handle non-numeric inputs without crashing
	public static int getInt(Scanner keyboard) {
		while(true) {
			try {
				return keyboard.nextInt();
				
			}
			catch(Exception nonInt){
				System.out.println("Please enter a number between 1-9: ");
				keyboard.next();
			}
		}
	}
	
	//Display board
	static void displayBoard(char[][] board) {
		for(int i = 0; i < 3; i++) {
			System.out.println(" " + board[i][0] + " | " + 
		board[i][1] + " | " + board[i][2]);
			if(i<2) {
				System.out.println("---|---|---");
			}
		}
	}
	
	//Check if there's a winner
	static char checkWinner(char[][] board) {
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return board[i][0];
			}
			else if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				return board[0][i];
			}
		}
		
		if((board[0][0] == board[1][1] && board[1][1] == board[2][2] || 
				board[2][0] == board[1][1] && board[1][1] == board[0][2]) && board[1][1] != ' ') {
			return board[1][1];
		}
		
		return ' ';
	}
	
	//Check if there's a tie
	static boolean checkTie(char[][] board) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		//Welcome message and instructions
		System.out.println("Welcome to Tic-Tac-Toe!"
				+ "\n\nInstructions:"
				+ "\nTwo players take turns playing X's and O's on a 3*3 board."
				+ "\nThe first player to get 3 in a row (horizontally, vertically, or diagonally) wins!"
				+ "\n\nThe board is labeled as follows:"
				+ "\n 1 | 2 | 3"
				+ "\n---|---|---"
				+ "\n 4 | 5 | 6"
				+ "\n---|---|---"
				+ "\n 7 | 8 | 9"
				+ "\n\nTo place your X or O, enter the number of the cell you want."
				+ "\nFor example, entering 1 places your piece in the top-left corner.\n");
		
		//Initializing board and starting player
		char[][] board = {
				{' ', ' ',' '},
				{' ', ' ',' '},
				{' ', ' ',' '},
		};
		
		char currentPlayer = 'X';
		
		Scanner keyboard = new Scanner(System.in);
		
		//Game Loop
		while(true) {
			
			displayBoard(board);
			
			//Prompt Player for cell choice
			System.out.println("It's player " + currentPlayer + "'s turn."
					+ "\nPlease enter the number of the cell you want to place your " + currentPlayer);
			
			//Get user input
			int choice = getInt(keyboard);
			//Validate user input
			while(choice > 9 || choice < 1) {
				System.out.print("Please enter a number between 1-9: ");
				choice = getInt(keyboard);
			}
			//Convert to row and column indexes
			int row = (choice - 1) / 3;
			int col = (choice - 1) % 3;
			
			//Check whether the cell is already occupied
			while(board[row][col] != ' ') {
				System.out.print("Already occupied! Please choose another cell: ");
				choice = getInt(keyboard);
				row = (choice - 1) / 3;
				col = (choice - 1) % 3;
			}
			board[row][col] = currentPlayer;
			
			//Check for winner and tie
			char winner = checkWinner(board);
			if(winner != ' ') {
				System.out.println("Player " + winner + " wins! Thank you for Playing!");
				break;
			}
			
			boolean tie = checkTie(board);
			if(tie == true) {
				System.out.print("We have a tie! Thank you for Playing!");
				break;
			}
			
			//Switch Player
			if(currentPlayer == 'X') {
				currentPlayer = 'O';
			}
			else {
				currentPlayer = 'X';
			}
			
		}
		
	}

}
