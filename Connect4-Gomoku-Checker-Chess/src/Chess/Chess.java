package Chess;

import BoardGames.Game;
import BoardGames.Position;

import java.util.HashMap;
import java.util.Scanner;

public class Chess extends Game {
    ChessBoard board = new ChessBoard();

    public Chess(){
        super.startGame();
    }

    protected int getUserInput(Scanner input, int iteration, int playerTurn){
        System.out.print("choose a disc to be moved (from-to): ");
        String line = input.nextLine();
        String[] commandParts = line.split(" "); System.out.println(commandParts.length);
        String[] fromTo = line.split("-");
        if (commandParts.length == 1 &&
                fromTo.length == 2 &&
                fromTo[0].length() == 2 &&
                fromTo[1].length() == 2){

            int fromCol = charToInt(fromTo[0].charAt(0));
            int fromRow = getInt(fromTo[0].charAt(1));
            int toCol = charToInt(fromTo[1].charAt(0));
            int toRow = getInt(fromTo[1].charAt(1));
            if (fromCol == -1 || fromRow == -1 || toCol == -1 || toRow == -1){
                System.out.println("Invalid input!");
                return iteration;
            }
            return dropDisc(iteration, playerTurn, new Position(fromRow, fromCol), new Position(toRow, toCol));
        } else if (line.split(" ").length == 3 &&
                commandParts[0].equals("replace") &&
                commandParts[1].length() == 2 &&
                isDiscName(commandParts[2])){

                int pawnCol = charToInt(commandParts[1].charAt(0));
                int pawnRow = getInt(commandParts[1].charAt(1));
                if (pawnCol == -1 || pawnRow == -1){
                    System.out.println("Invalid input!");
                    return iteration;
                }
                return replacePawn(iteration, playerTurn, new Position(pawnRow, pawnCol), commandParts[2]);
        } else {
            System.out.println("Invalid input!");
            return iteration;
        }
    }

    private int charToInt(char c){
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

    private int getInt(char c){
        if(c < '1' || c > '8'){
            return -1;
        }
        return c - '0';
    }

    private boolean isDiscName(String name){
        return (name.equals("pawn") || name.equals("rook") || name.equals("knight") ||
                name.equals("queen") || name.equals("bishop"));
    }

    public int dropDisc(int iteration, int playerTurn, Position from, Position to){
        return board.makePlay(iteration, playerTurn, from, to);
    }

    public int replacePawn(int iteration, int playerTurn, Position from, String discName){
        return board.replacePawn(iteration, playerTurn, from, discName);
    }

    protected void printEndGame(int playerTurn, String coloredPlayer){
        System.out.println(coloredPlayer + "#####################");
        System.out.println("... Player " + playerTurn + Game.ANSI_RESET + " WON");
        System.out.println(coloredPlayer + "#####################" + Game.ANSI_RESET);
    }

    public int dropDisc(int iteration, int player){
        return 0;
    }

    public boolean isWon(int column){
        return board.isEndGame();
    }

    public void displayBoard(){
        board.displayBoard();
    }

    protected int totalGrids(){
        return 64;
    }

    protected void displayInstructions(){
        System.out.println("Welcome To Chess Game - console version. Here are some instructions");
        System.out.println("- Provide the position of the piece which you want to move" +
                "followed by the position you want to move the piece to" +
                "separated by '-', for example, 'A2-A4'");
        System.out.println("- In order to replace a pawn by another piece," +
                "use the command replace, for example, 'replace B8 rook'.");
        System.out.println("- The game ends when one of the kings is killed.");
    }
}
