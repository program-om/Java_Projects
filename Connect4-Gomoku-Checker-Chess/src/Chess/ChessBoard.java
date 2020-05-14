package Chess;

import BoardGames.*;
import Checkers.Player1Disc;
import Checkers.Player2Disc;

public class ChessBoard extends Board {
    ChessPlayer[] players = new ChessPlayer[2];
    ChessPlayer currentPlayer;
    boolean endGame;

    public ChessBoard(){
        for (int i = 0; i < 8; i++) {
            columns.add(new ChessColumn());
        }
        players[0] = new ChessPlayer(1, Color.RED, this);
        players[1] = new ChessPlayer(2, Color.YELLOW, this);
        currentPlayer = players[0];
        endGame = false;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setCurrentPlayer(int playerTurn) {
        this.currentPlayer = players[playerTurn-1];
    }

    public int checkUp(Column col){
        return 0;
    } //not needed

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

    public int makePlay(int iteration, int playerTurn, Position from, Position to){
        setCurrentPlayer(playerTurn);
        if (isValidMove(from, to, playerTurn, currentPlayer.getColor())){
            ((ChessDisc)columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc()).setPosition(to);
            dropDisc(takeDisc(from), to.getRow(), to.getColumn());
            return iteration+1;
        } else {
            System.out.println("You can not make that move!");
        }
        return iteration;
    }

    public boolean isValidMove(Position from, Position to, int playerTurn, Color color){
        return (isValidFrom(from, playerTurn) && isValidTo(from, to));
    }

    public boolean isValidFrom(Position from, int playerTurn){
        Grid fromGrid = columns.get(from.getColumn()-1).getGrid(from.getRow());
        Grid toGrid = columns.get(0).getGrid(7);
        return (fromGrid.isOccupied() &&
                ((ChessDisc)fromGrid.getDisc()).getPlayerNumber() == playerTurn);
    }

    public boolean isValidTo(Position from, Position to) {
        Grid toGrid = columns.get(to.getColumn() - 1).getGrid(to.getRow());
        ChessDisc disc = (ChessDisc)columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
        if (!toGrid.isOccupied()){
            return disc.isMovable(to);
        }//occupied
        return  (((ChessDisc)toGrid.getDisc()).getPlayerNumber() != currentPlayer.getNumber() &&
                disc.isMovable(to));


//        return (!toGrid.isOccupied() &&  ||
//                ((ChessDisc)toGrid.getDisc()).getPlayerNumber() != currentPlayer.getNumber() &&
//                  disc.isMovable(to));
    }

    public boolean dropDisc(Disc disc, int column){
        return false;
    }

    public boolean dropDisc(Disc disc, int row, int column){
        Grid toGrid = columns.get(column-1).getGrid(row);

        if (toGrid.isOccupied()){
            getTheOtherPlayer().addDeadDisc((ChessDisc) toGrid.getDisc());
            if (((ChessDisc) toGrid.getDisc()).getName().equals("king")){
                endGame = true;
            }
        }
        toGrid.occupy(disc);
        return true;
    }

    public Disc takeDisc(Position from){
        return columns.get(from.getColumn()-1).getGrid(from.getRow()).pop();
    }

    protected boolean invalidColumnNum(int colNum){
        return false;
    }

    protected boolean invalidGridPosition(int gridPosition){
        return false;
    }

    public boolean isLineFormed(int discsNum){
        return false;
    }

    protected ChessPlayer getTheOtherPlayer(){
        return players[currentPlayer.getNumber() % 2];
    }

    public int replacePawn(int iteration, int playerTurn, Position from, String discName){
        setCurrentPlayer(playerTurn);
        ChessDisc disc = (ChessDisc)columns.get(from.getColumn()-1).getGrid(from.getRow()).getDisc();
        if (isValidReplace(disc, discName)){
            currentPlayer.promotePawn(discName, disc);
            return iteration+1;
        }
        System.out.println("You can not make that move!");
        return iteration;
    }

    private boolean isValidReplace(ChessDisc disc, String pieceName){
        return ( disc.getName().equals("pawn") &&
                 ((Pawn)disc).isReplaceable() &&
                 currentPlayer.isThereDead(pieceName));
    }


}
