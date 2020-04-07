package BoardGames;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class Game {
    protected int chosenColumn;
    protected int chosenRow;
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_RESET = "\u001B[0m";

    protected void startGame(){

        Scanner input = new Scanner(System.in);

        int iteration = 0;
        int nextIteration;
        int playerTurn;
        boolean endGame = false;
        String coloredPlayer = "";
        displayInstructions();
        displayBoard();
        while(!endGame) {
            try {
                playerTurn = (iteration % 2) + 1;
                coloredPlayer = (playerTurn == 1) ? ANSI_RED : ANSI_YELLOW;
                System.out.println(coloredPlayer + "Player " + playerTurn + ANSI_RESET + " turn.");
                nextIteration = getUserInput(input, iteration, playerTurn);
                if (iteration == nextIteration){
                    continue;
                }
                displayBoard();
                iteration = nextIteration;
                endGame = isWon(chosenColumn);
                if (endGame) {
                    printEndGame(playerTurn, coloredPlayer);
                } else if(iteration == totalGrids()){
                    System.out.println("#####################");
                    System.out.println("Game ended with TIE.");
                    System.out.println("#####################");
                    endGame = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
                input.nextLine();
                continue;
            }
        }
    }

    protected abstract int getUserInput(Scanner input, int iteration, int playerTurn);

    protected abstract void printEndGame(int playerTurn, String coloredPlayer);

    public abstract int dropDisc(int iteration, int player);

    public abstract boolean isWon(int column);

    public abstract void displayBoard();

    protected abstract int totalGrids();

    protected abstract void displayInstructions();

}
