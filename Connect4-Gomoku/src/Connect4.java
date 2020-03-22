import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect4 extends Game {
    private Connect4Board board = new Connect4Board();

    Connect4(){
        super.startGame();
    }

    int dropDisc(int iteration, int player){
        if(chosenColumn < 1 || chosenColumn > 7){
            System.out.println("column number must be between 1 and 7");
            return iteration;
        }

        if(player == 1){//player 1 turn
            if (this.board.dropDisc(new Disc(Color.RED), chosenColumn))
                return iteration+1;
        }else{ //player 2 turn
            if (this.board.dropDisc(new Disc(Color.YELLOW), chosenColumn))
                return iteration+1;
        }
        return iteration;
    }

    int getUserInput(Scanner input, int iteration, int playerTurn){
        System.out.print("choose column to drop a disc (1 to 7): ");
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
        return 42;
    }
}
