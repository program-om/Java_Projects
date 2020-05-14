package Chess;

import BoardGames.Color;
import BoardGames.Position;

public class Queen extends ChessDisc {
    private int playerNumber;
    MoveValidator moveValidator;

    Queen(ChessBoard board, int playerNumber, Position position, MoveValidator mv){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        moveValidator = mv;
        name = "queen";
    }

    public boolean isMovable(Position to){
        if (position.getRow() == to.getRow() || position.getColumn() == to.getColumn()){ //Rook move
            return moveValidator.rookMoveValidator(position, to);
        }
        return (Math.abs(position.getRow() - to.getRow()) ==
                Math.abs(position.getColumn() - to.getColumn()) && //Bishop move
                moveValidator.bishopMoveValidator(position, to));
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2655";
        }
        return "\u265B";
    }
}
