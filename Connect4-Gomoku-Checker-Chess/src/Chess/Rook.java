package Chess;

import BoardGames.Color;
import BoardGames.Position;

public class Rook extends ChessDisc {
    private int playerNumber;
    private MoveValidator moveValidator;
    private boolean firstMove = true;

    Rook(ChessBoard board, int playerNumber, Position position, MoveValidator mv){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        moveValidator = mv;
        name = "rook";
    }

    @Override
    public void setPosition(Position position) {
        if (firstMove) firstMove = false;
        super.setPosition(position);
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public boolean isMovable(Position to){
        return moveValidator.rookMoveValidator(position, to);
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2656";
        }
        return "\u265C";
    }
}
