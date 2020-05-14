package Chess;

import BoardGames.Column;
import BoardGames.Position;

import java.util.ArrayList;

public class MoveValidator {
    ArrayList<Column> columns;

    MoveValidator(ArrayList<Column> columns){
        this.columns = columns;
    }

    public boolean rookMoveValidator(Position from, Position to){
        if (from.getRow() == to.getRow() && from.getColumn() != to.getColumn()){//horizontal
            if (from.getColumn() > to.getColumn())
                return isClearPathLeft(from.getRelativePosition(0, -1),
                        to.getRelativePosition(0,1));
            return isClearPathRight(from.getRelativePosition(0, 1),
                    to.getRelativePosition(0,-1));
        } else if (from.getColumn() == to.getColumn() && from.getRow() != to.getRow()){//vertical
            if (from.getRow() > to.getRow())
                return isClearPathDown(from.getRelativePosition(-1, 0),
                        to.getRelativePosition(1,0));
            return isClearPathUp(from.getRelativePosition(1, 0),
                    to.getRelativePosition(-1,0));
        } else return false;
    }

    public boolean bishopMoveValidator(Position from, Position to){
        return (Math.abs(from.getRow() - to.getRow()) ==
                Math.abs(from.getColumn() - to.getColumn()) &&
                isClearPath(from, to));
    }

    private boolean isClearPath(Position from, Position to){
        if (from.getRow() > to.getRow()){//go down
            if (from.getColumn() > to.getColumn()){//go left
                return isClearPathDownLeft(from.getRelativePosition(-1, -1),
                        to.getRelativePosition(1,1));
            }//go right
            return isClearPathDownRight(from.getRelativePosition(-1, 1),
                    to.getRelativePosition(1,-1));
        } else {
            if (from.getColumn() > to.getColumn()){//go left
                return isClearPathUpLeft(from.getRelativePosition(1, -1),
                        to.getRelativePosition(-1,1));
            }//go right
            return isClearPathUpRight(from.getRelativePosition(1, 1),
                    to.getRelativePosition(-1,-1));
        }
    }

    private boolean isClearPathUp(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() > to.getRow())
            return true;
        else if (!isOccupied)
            return isClearPathUp(from.getRelativePosition(1,0), to);
        return false;
    }

    private boolean isClearPathDown(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() < to.getRow())
            return true;
        else if (!isOccupied)
            return isClearPathDown(from.getRelativePosition(-1,0), to);
        return false;
    }

    private boolean isClearPathLeft(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getColumn() < to.getColumn())
            return true;
        else if (!isOccupied)
            return isClearPathLeft(from.getRelativePosition(0,-1), to);
        return false;
    }

    private boolean isClearPathRight(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;


        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getColumn() > to.getColumn())
            return true;
        else if (!isOccupied)
            return isClearPathRight(from.getRelativePosition(0,1), to);
        return false;
    }

    private boolean isClearPathUpLeft(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() > to.getRow())//we assume it is occupied by opponent
            return true;
        else if (!isOccupied)
            return isClearPathUpLeft(from.getRelativePosition(1,-1), to);
        return false;
    }

    private boolean isInvalidPosition(Position position){
        return (position.getRow() > 8 || position.getRow() < 1 ||
                position.getColumn() > 8 || position.getColumn() < 1);
    }

    private boolean isClearPathUpRight(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() > to.getRow())
            return true;
        else if (!isOccupied)
            return isClearPathUpRight(from.getRelativePosition(1,1), to);
        return false;
    }

    private boolean isClearPathDownLeft(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        //boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() < to.getRow())
            return true;
        else if (!columns.get(to.getColumn()-1).getGrid(to.getRow()).isOccupied())
            return isClearPathDownLeft(from.getRelativePosition(-1,-1), to);
        return false;
    }

    private boolean isClearPathDownRight(Position from, Position to){
        if (isInvalidPosition(from) || isInvalidPosition(to)) return true;

        //boolean isOccupied = columns.get(from.getColumn()-1).getGrid(from.getRow()).isOccupied();
        if (from.getRow() < to.getRow())
            return true;
        else if (!columns.get(to.getColumn()-1).getGrid(to.getRow()).isOccupied())
            return isClearPathDownRight(from.getRelativePosition(-1,1), to);
        return false;
    }
}
