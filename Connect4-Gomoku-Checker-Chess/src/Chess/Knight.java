package Chess;

import BoardGames.Color;
import BoardGames.Grid;
import BoardGames.Position;

public class Knight extends ChessDisc {
    boolean firstMove = true;

    Knight(ChessBoard board, int playerNumber, Position position){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        name = "knight";
    }

    public boolean isMovable(Position to){

        return ( (firstMove && to.getRow() == position.getRow() &&
                  Math.abs(to.getColumn() - position.getColumn()) == 2) ||
                 (Math.abs(position.getRow() - to.getRow()) == 1 &&
                  Math.abs(position.getColumn() - to.getColumn()) == 2) ||
                 (Math.abs(position.getRow() - to.getRow()) == 2 &&
                  Math.abs(position.getColumn() - to.getColumn()) == 1) ) ;
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2658";
        }
        return "\u265E";
    }
}
