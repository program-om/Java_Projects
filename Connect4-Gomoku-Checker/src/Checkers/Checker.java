package Checkers;

import BoardGames.*;

import java.util.HashMap;
import java.util.Scanner;

public class Checker extends Game {
    private CheckerBoard board = new CheckerBoard();

    public Checker(){
        super.startGame();
    }

    public Checker(String test){}

    public int dropDisc(int iteration, int player){
        if(board.invalidColumnNum(chosenColumn) || board.invalidGridPosition(chosenRow)){
            System.out.println("row and column numbers must be between 1 and 19");
            return iteration;
        }

        return iteration;
    }

    public int dropDisc(int iteration, int playerTurn, Position from, Position to){
        return board.makePlay(iteration, playerTurn, from, to);
    }

    protected int getUserInput(Scanner input, int iteration, int playerTurn){
        System.out.print("choose a disc to be moved (from-to): ");
        String line = input.nextLine();
        String[] fromTo = line.split("-");
        if (fromTo.length != 2 || fromTo[0].length() != 2 || fromTo[1].length() != 2 ) {
            System.out.println("Invalid input!");
            return iteration;
        }

        int fromCol = charToInt(fromTo[0].charAt(0));
        int fromRow = getInt(fromTo[0].charAt(1));
        int toCol = charToInt(fromTo[1].charAt(0));
        int toRow = getInt(fromTo[1].charAt(1));
        if (fromCol == -1 || fromRow == -1 || toCol == -1 || toRow == -1){
            System.out.println("Invalid input!");
            return iteration;
        }
        return dropDisc(iteration, playerTurn, new Position(fromRow, fromCol), new Position(toRow, toCol));
    }

    public int charToInt(char c){
        HashMap<Character, Integer> charToInt = new HashMap<Character, Integer>();
        Character ch = 'A';
        Integer i = 1;
        while(i < 9){
            charToInt.put(ch, i);
            ch++;
            i++;
        }
        Integer val = charToInt.get(c);
        if (val != null){ return val; }
        return -1;
    }

    public int getInt(char c){
        if(c < '1' || c > '8'){
            return -1;
        }
        return c - '0';
    }

    protected void printEndGame(int playerTurn, String coloredPlayer){
        System.out.println(coloredPlayer + "#####################");
        System.out.println("... Player " + playerTurn + Game.ANSI_RESET + " WON");
        System.out.println(coloredPlayer + "#####################" + Game.ANSI_RESET);
    }

    public boolean isWon(int column){
        return board.isWon(column);
    }

    public void displayInstructions(){
        System.out.println("Welcome to Checkers Game!" +
                "Rows are represented by numbers from 1 to 8. Columns represented by letters from A to H." +
                "\nInput format is (from position)-(to position) e.g. A3-B4. " +
                "The game will end if one player run out of discs or a player can not move any of his/her discs");
    }

    public void displayBoard(){
        board.displayBoard();
    }

    public int totalGrids(){
        return 0;
    }
}
