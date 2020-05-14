package Chess;

import BoardGames.Position;

public class Bishop extends ChessDisc {
    private int playerNumber;
    private MoveValidator moveValidator;

    Bishop(ChessBoard board, int playerNumber, Position position, MoveValidator mv){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        moveValidator = mv;
        name = "bishop";
    }

    public boolean isMovable(Position to){
        return moveValidator.bishopMoveValidator(position, to);
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2657";
        }
        return "\u265D";
    }
}
