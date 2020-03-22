import java.util.ArrayList;

public abstract class Board {
    public ArrayList<Column> columns = new ArrayList<Column>();
    protected int lastFilledGrid;
    protected Color lastColor;

    Board(){ }

    public boolean isWon(int chosenColumn){
        return (checkHorizontal(chosenColumn) || checkVertical(chosenColumn) || checkDiagonal(chosenColumn));
    }

    private boolean checkHorizontal(int columnNum){
        int countLeft = checkHorizontalLeft(columnNum-1);
        int countRight = checkHorizontalRight(columnNum+1);
        return isLineFormed(countLeft+ 1 + countRight);
    }

    private int checkHorizontalLeft(int columnNum){

        if(invalidColumnNum(columnNum)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(lastFilledGrid)){
            return 0;
        }
        if(column.getGrid(lastFilledGrid).getDisc().equals(lastColor)){
            return 1 + checkHorizontalLeft(columnNum-1);
        }
        return 0;
    }

    private int checkHorizontalRight(int columnNum){
        if(invalidColumnNum(columnNum)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(lastFilledGrid)){
            return 0;
        }
        if(column.getGrid(lastFilledGrid).getDisc().equals(lastColor)){
            return 1 + checkHorizontalLeft(columnNum+1);
        }
        return 0;
    }

    private boolean checkVertical(int columnNum){
        int countDiscs = checkUp(columns.get(columnNum-1)) + 1
                + checkDown(columns.get(columnNum-1));
        return isLineFormed(countDiscs);
    }

    abstract int checkUp(Column col);

    private int checkDown(Column col){
        int count = 0;
        int i = lastFilledGrid-1; // i = 0 to 5
        boolean discsMatch = true;
        while (i > 0 && discsMatch) {
            if(col.getGrid(i).getDisc().equals(lastColor)){
                count++;
            }else {
                discsMatch = false;
            }
            i--;
        }
        return count;
    }

    private boolean checkDiagonal(int columnNum){
        int countDiagonalLTR = 1 +
                checkDiagonalLTR_up(columnNum-1, lastFilledGrid+1) +
                checkDiagonalLTR_down(columnNum+1, lastFilledGrid-1);

        int countDiagonalRTL = 1 +
                checkDiagonalRTL_up(columnNum+1, lastFilledGrid+1) +
                checkDiagonalRTL_down(columnNum-1, lastFilledGrid-1);

        return (isLineFormed(countDiagonalLTR) || isLineFormed(countDiagonalRTL));
    }

    private int checkDiagonalLTR_up(int columnNum, int gridPosition){
        if(invalidColumnNum(columnNum) || invalidGridPosition(gridPosition)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(gridPosition)){
            return 0;
        }
        if(column.getGrid(gridPosition).getDisc().equals(lastColor)){
            return 1 + checkDiagonalLTR_up(columnNum-1, gridPosition+1);
        }
        return 0;
    }

    private int checkDiagonalLTR_down(int columnNum, int gridPosition){
        if(invalidColumnNum(columnNum) || invalidGridPosition(gridPosition)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(gridPosition)){
            return 0;
        }
        if(column.getGrid(gridPosition).getDisc().equals(lastColor)){
            return 1 + checkDiagonalLTR_down(columnNum+1, gridPosition-1);
        }
        return 0;
    }

    private int checkDiagonalRTL_up(int columnNum, int gridPosition){
        if(invalidColumnNum(columnNum) || invalidGridPosition(gridPosition)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(gridPosition)){
            return 0;
        }
        if(column.getGrid(gridPosition).getDisc().equals(lastColor)){
            return 1 + checkDiagonalRTL_up(columnNum+1, gridPosition+1);
        }
        return 0;
    }

    private int checkDiagonalRTL_down(int columnNum, int gridPosition){
        if(invalidColumnNum(columnNum) || invalidGridPosition(gridPosition)){
            return 0;
        }
        Column column = columns.get(columnNum-1);
        if(!column.isGridFilled(gridPosition)){
            return 0;
        }
        if(column.getGrid(gridPosition).getDisc().equals(lastColor)){
            return 1 + checkDiagonalRTL_down(columnNum-1, gridPosition-1);
        }
        return 0;
    }

    abstract void displayBoard();

    abstract boolean dropDisc(Disc disc, int column);

    abstract boolean dropDisc(Disc disc, int column, int row);

    abstract boolean invalidColumnNum(int colNum);

    abstract boolean invalidGridPosition(int gridPosition);

    abstract boolean isLineFormed(int discsNum);
}
