import java.util.InputMismatchException;
import java.util.Scanner;

public class Gomoku extends Game {
    private GomokuBoard board = new GomokuBoard();

    Gomoku(){
        super.startGame();
    }

    int dropDisc(int iteration, int player){
        if(board.invalidColumnNum(chosenColumn) || board.invalidGridPosition(chosenRow)){
            System.out.println("row and column numbers must be between 1 and 19");
            return iteration;
        }

        if(player == 1){//player 1 turn
            if (board.dropDisc(new Disc(Color.RED), chosenColumn, chosenRow))
                return iteration+1;
        }else{ //player 2 turn
            if (board.dropDisc(new Disc(Color.YELLOW), chosenColumn, chosenRow))
                return iteration+1;
        }
        return iteration;
    }

    int getUserInput(Scanner input, int iteration, int playerTurn){
        System.out.print("choose grid to drop a disc (row column): ");
        chosenRow = input.nextInt();
        chosenColumn = input.nextInt();
        return dropDisc(iteration, playerTurn);
    }

    void printEndGame(int playerTurn, String coloredPlayer){
        System.out.println(coloredPlayer + "#####################");
        System.out.println("... Player " + playerTurn + ANSI_RESET + " WON");
        System.out.println(coloredPlayer + "#####################" + ANSI_RESET);
    }

    public boolean isWon(int column){
        return board.isWon(column);
    }

    public void displayBoard(){
        board.displayBoard();
    }

    public int totalGrids(){
        return 361;
    }
}
