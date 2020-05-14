package Chess;

import BoardGames.Color;
import BoardGames.Grid;
import BoardGames.Position;

public class Pawn extends ChessDisc {
    private int playerNumber;
    boolean firstMove = true;

    Pawn(ChessBoard board, int playerNumber, Position position){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        name = "pawn";
    }

    @Override
    public void setPosition(Position position) {
        if (firstMove) firstMove = false;
        super.setPosition(position);
    }

    public boolean isMovable(Position to){
        if (canCapture(to))
            return true;
        if (playerNumber == 1){
            if (firstMove){
                return ((to.getRow() - position.getRow() == 1 ||
                        to.getRow() - position.getRow() == 2) && to.getColumn() == position.getColumn());
            }
            return (to.getRow() - position.getRow() == 1);
        } else {
            if (firstMove){
                return (to.getRow() - position.getRow() == -1 ||
                        to.getRow() - position.getRow() == -2);
            }
            return (to.getRow() - position.getRow() == -1);
        }
    }

    @Override
    public boolean canCapture(Position to){
        Grid toGrid = columns.get(to.getColumn()-1).getGrid(to.getRow());
        if (playerNumber == 1) {
            return (Math.abs(to.getColumn() - position.getColumn()) == 1 &&
                    to.getRow() - position.getRow() == 1 &&
                    toGrid.isOccupied());
        }
        return (Math.abs(to.getColumn() - position.getColumn()) == 1 &&
                to.getRow() - position.getRow() == -1 &&
                toGrid.isOccupied());
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2659";
        }
        return "\u265F";
    }

    public boolean isReplaceable(){
        if (playerNumber == 1) {
            return (position.getRow() == 8);
        }
        return (position.getRow() == 1);
    }
}

/*  Move is not the same as Capture for Pawn
* */