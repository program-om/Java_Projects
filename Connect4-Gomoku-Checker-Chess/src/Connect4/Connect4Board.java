package Connect4;

import BoardGames.Board;
import BoardGames.Column;
import BoardGames.Disc;

public class Connect4Board extends Board {

    Connect4Board(){
        for (int i = 0; i < 7; i++) {
            columns.add(new Connect4Column());
        }
        displayBoard();
    }

    @Override
    protected int checkUp(Column col){
        return 0;
    }

    public void displayBoard(){
        System.out.println("=====================");
        System.out.println(" 1  2  3  4  5  6  7");
        System.out.println("=====================");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(" " + columns.get(j).getGrids().get(5-i).toString() + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    public boolean dropDisc(Disc disc, int column){
        //place disc of player x into top grid of column
        Connect4Column targetColumn = (Connect4Column)columns.get(column-1);

        if (targetColumn.isFull()){//for gomoku, check if position is occupied
            System.out.println("column "+column+ " is full. Choose another column.");
            return false;
        }else {
            lastFilledGrid = targetColumn.dropDisc(disc);
            lastColor = disc.getColor();
            return true;
        }
    }

    public boolean dropDisc(Disc disc, int column, int row){ return false; }

    protected boolean invalidColumnNum(int colNum){
        return (colNum < 1 || colNum > 7);
    }

    protected boolean invalidGridPosition(int gridPosition){
        return (gridPosition < 1 || gridPosition > 6);
    }

    public boolean isLineFormed(int discsNum){
        return (discsNum >= 4);
    }

    int getBoardHeight(){
        return 6;
    }

    int getBoardWidth(){
        return 7;
    }
}
