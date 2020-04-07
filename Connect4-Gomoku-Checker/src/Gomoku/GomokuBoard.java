package Gomoku;

import BoardGames.Column;
import BoardGames.Board;
import BoardGames.Disc;

public class GomokuBoard extends Board {

    GomokuBoard(){
        for (int i = 0; i < 19; i++) {
            columns.add(new GomokuColumn());
        }
        displayBoard();
    }

    protected int checkUp(Column col){
        int count = 0;
        int i = lastFilledGrid+1; // i = 1 to 20
        boolean discsMatch = true;
        while (i <= 19 && discsMatch) {
            if(col.getGrid(i).getDisc().equals(lastColor)){
                count++;
            }else {
                discsMatch = false;
            }
            i++;
        }
        return count;
    }

    public void displayBoard(){
        System.out.println("===================================================================================");
        for (int i = 0; i < 19; i++) {
            System.out.print((19-i) + "\t|\t");
            for (int j = 0; j < 19; j++) {
                System.out.print(columns.get(j).getGrids().get(18-i).toString() + "\t");
            }
            System.out.println();
        }
        System.out.println("===================================================================================");
        System.out.println("\t\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18\t19");
        System.out.println("===================================================================================");
    }

    public boolean dropDisc(Disc disc, int column){ return false; }

    public boolean dropDisc(Disc disc, int column, int row){
        GomokuColumn targetColumn = (GomokuColumn)columns.get(column-1);
        if(targetColumn.isGridOccupied(row)){
            System.out.println("The chosen grid is occupied! try another grid.");
            return false;
        }
        targetColumn.occupyGrid(disc, row);
        lastFilledGrid = row;
        lastColor = disc.getColor();
        return true;
    }

    protected boolean invalidColumnNum(int colNum){
        return (colNum < 1 || colNum > 19);
    }

    protected boolean invalidGridPosition(int gridPosition){
        return (gridPosition < 1 || gridPosition > 19);
    }

    public boolean isLineFormed(int discsNum){
        return (discsNum >= 5);
    }
}
