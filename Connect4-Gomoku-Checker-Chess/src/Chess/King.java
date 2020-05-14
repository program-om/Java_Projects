package Chess;

import BoardGames.Color;
import BoardGames.Position;

public class King extends ChessDisc {
    private int playerNumber;
    private boolean firstMove = true;

    King(ChessBoard board, int playerNumber, Position position){
        super(board, playerNumber);
        this.playerNumber = playerNumber;
        this.position = position;
        this.isKing = true;
        name = "king";
    }

    @Override
    public void setPosition(Position position) {
        if (firstMove) firstMove = false;
        super.setPosition(position);
    }

    public boolean isMovable(Position to){
        return  ( isOneGridAtMost(position.getRow(), to.getRow()) &&
                isOneGridAtMost(position.getColumn(), to.getColumn()));
    }

//    public boolean isStaleMate(Position to){
//        if (    firstMove && to.getRow() == position.getRow() &&
//                Math.abs(to.getColumn() - position.getColumn()) == 2 &&
//                ((  columns.get(0).getGrid(to.getRow()).isOccupied() &&
//                    ((ChessDisc)columns.get(0).getGrid(to.getRow()).getDisc()).getName().equals("rook") &&
//                    ((Rook)columns.get(0).getGrid(to.getRow()).getDisc()).isFirstMove() ) ||
//                (   columns.get(7).getGrid(to.getRow()).isOccupied() &&
//                    ((ChessDisc)columns.get(7).getGrid(to.getRow()).getDisc()).getName().equals("rook") &&
//                    ((Rook)columns.get(7).getGrid(to.getRow()).getDisc()).isFirstMove()) ) &&
//                ){
//        }
//        return false;
//    }
//
//    public boolean isValidStaleMate(Position to){
//        position.getRelativePosition(to.getRow() - position.getRow(), to.getColumn() - position.getColumn();
//        //make sure that the two grids are empty
//        //make sure that the three grids are uncapturable by opponent
//        if (to.getColumn() < position.getColumn()){
//            columns.get(to.getColumn()-1).getGrid(to.getRow()).isOccupied();
//            columns.get(to.getColumn()).getGrid(to.getRow()+1).isOccupied();
//        } else {
//
//        }
//    }

    public boolean isOneGridAtMost(int a, int b){
        return ( (Math.abs(a - b) == 0 ||
                Math.abs(a - b) == 1) );
    }

    @Override
    public String toString() {
        if (playerNumber == 1){
            return "\u2654";
        }
        return "\u265A";
    }
}
