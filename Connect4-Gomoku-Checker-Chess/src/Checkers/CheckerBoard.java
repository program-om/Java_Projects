package Checkers;

import BoardGames.*;

public class CheckerBoard extends Board {
    CheckerPlayer[] players = new CheckerPlayer[2];
    CheckerPlayer currentPlayer;

    public CheckerBoard(){
        for (int i = 0; i < 8; i++) {
            columns.add(new CheckerColumn());
        }
        players[0] = new CheckerPlayer(1, Color.RED, this);
        players[1] = new CheckerPlayer(2, Color.YELLOW, this);
        currentPlayer = players[0];
    }

    protected int checkUp(Column col){
        return 0;
    }

    public void displayBoard(){
        System.out.println("=====================================");
        for (int i = 0; i < 8; i++) {
            System.out.print((8-i) + "\t|\t");
            for (int j = 0; j < 8; j++) {
                System.out.print(columns.get(j).getGrids().get(7-i).toString() + "\t");
            }
            System.out.println();
        }
        System.out.println("=====================================");
        System.out.println("\t\tA\tB\tC\tD\tE\tF\tG\tH");
        System.out.println("=====================================");
    }

    public void setCurrentPlayer(int playerTurn) {
        this.currentPlayer = players[playerTurn-1];
    }

    public boolean dropDisc(Disc disc, int column){ return false; }

    public boolean dropDisc(Disc disc, int row, int column){
        CheckerColumn targetColumn = (CheckerColumn) columns.get(column-1);
        targetColumn.occupyGrid(disc, row);
        ((CheckerDisc)disc).setPosition(new Position(row, column));
        return true;
    }

    public Disc takeDisc(Position from){
        return columns.get(from.getColumn()-1).getGrid(from.getRow()).pop();
    }

    public boolean isValidMove(Position from, Position to, int playerTurn, Color color){
        return (isValidFrom(from, color) && isValidTo(from, to, color));
//        if (!movable(from, to, color)) return false;
//        if (playerTurn == 1){
//            Player1Disc disc = (Player1Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
//            return disc.isMovable(to);
//        }else{
//            Player2Disc disc = (Player2Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
//            return disc.isMovable(to);
//        }
    }

    public boolean isValidTo(Position from, Position to, Color color){
        Grid toGrid = columns.get(to.getColumn()-1).getGrid(to.getRow());
        if (color == Color.RED){
            Player1Disc disc = (Player1Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
            return (!toGrid.isOccupied() && disc.isMovable(to));
        }else{
            Player2Disc disc = (Player2Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
            return (!toGrid.isOccupied() && disc.isMovable(to));
        }
    }

    public boolean isValidFrom(Position from, Color color){
        Grid fromGrid = columns.get(from.getColumn()-1).getGrid(from.getRow());
        //Grid toGrid = columns.get(to.getColumn()-1).getGrid(to.getRow());
        return (fromGrid.isOccupied() &&
                fromGrid.getDisc().equals(color) /*&&
                !toGrid.isOccupied()*/);
    }

    public boolean isValidCapture(Position from, Position to, int playerTurn){
        if (!isValidFrom(from, currentPlayer.getColor()) ||
                columns.get(to.getColumn()-1).getGrid(to.getRow()).isOccupied()) return false;
        boolean capturable;
        if (playerTurn == 1){
            Player1Disc disc = (Player1Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
            capturable = disc.isCaptureable(to);
        }else{
            Player2Disc disc = (Player2Disc) columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
            capturable = disc.isCaptureable(to);
        }

        if (!capturable) return false;

        //does the grid between from and to has the opponent disc?
        return victimGrid(from, to).getDisc().equals(players[playerTurn % 2].getColor());
    }

    public int makePlay(int iteration, int playerTurn, Position from, Position to){
        setCurrentPlayer(playerTurn);
        if (canPlayerCapture(playerTurn)){
            if (isValidCapture(from, to, playerTurn)) {
                capture(from, to, playerTurn);
                if (canPlayerCapture(playerTurn)){//can capture again?
                    displayBoard();
                    return iteration;
                }
                return iteration + 1;
            }else{
                System.out.println("You have to capture!");
            }
        } else if (isValidMove(from, to, playerTurn, currentPlayer.getColor())){
            dropDisc(takeDisc(from), to.getRow(), to.getColumn());
            return iteration+1;
        } else {
            System.out.println("You can not make that move!");
        }
        return iteration;
    }

    public void capture(Position from, Position to, int playerTurn){
        dropDisc(takeDisc(from), to.getRow(), to.getColumn());
        deleteDisc(((CheckerDisc)victimGrid(from, to).pop()), (playerTurn%2)+1 );
    }

    private Grid victimGrid(Position from, Position to){
        int rowDiff, colDiff;
        if (from.getRow() > to.getRow()) {
            rowDiff = -1;
        } else {
            rowDiff = 1;
        }
        if (from.getColumn() > to.getColumn()){
            colDiff = -1;
        } else {
            colDiff = 1;
        }
        return columns.get((from.getColumn() + colDiff - 1))
                .getGrid(from.getRow() + rowDiff);
    }

    public boolean canPlayerCapture(int playerTurn){
        return players[playerTurn-1].canCapture(playerTurn);
    }

    public boolean deleteDisc(CheckerDisc disc, int playerTurn){
        return players[playerTurn-1].removeDisc(disc);
    }

    protected boolean invalidColumnNum(int colNum){
        return (colNum < 1 || colNum > 8);
    }

    protected boolean invalidGridPosition(int gridPosition){
        return (gridPosition < 1 || gridPosition > 8);
    }

    public boolean isLineFormed(int discsNum){
        return false;
    }

    @Override
    public boolean isWon(int chosenColumn) {
        //is a player run out of discs or can't move any disc
        CheckerPlayer nextPlayer = players[ (currentPlayer.getNumber() % 2) ];
        return (nextPlayer.isOutOfDiscs() || !nextPlayer.canMoveAnyDisc() );
    }

    int getBoardHeight(){
        return 8;
    }

    int getBoardWidth(){
        return 8;
    }

    public CheckerPlayer getPlayer(int player) {
        return players[player-1];
    }
}
