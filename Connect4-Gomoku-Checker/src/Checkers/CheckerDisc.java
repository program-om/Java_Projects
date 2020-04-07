package Checkers;

import BoardGames.Disc;
import BoardGames.Position;
import BoardGames.Color;

public abstract class CheckerDisc extends Disc {

    private boolean king = false;
    protected Position position;


    public CheckerDisc(int row, int column, Color color) {
        super(color);
        this.position = new Position(row, column);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        if (!isKing() && this.position.getRow() == kingRow()){
            switchToKing();
        }
    }

    private void switchToKing(){
        king = true;
    }

    protected boolean isKing() {
        return king;
    }

    public boolean isMovable(Position position){
        if (isKing()){
            return (isForwardDiagonal(position) || isBackwardDiagonal(position));
        }else{
            return isForwardDiagonal(position);
        }
    }

    public boolean isCaptureable(Position position){
        if (isKing()){
            return (isForwardCapturable(position) || isBackwardCapturable(position));
        }else{
            return isForwardCapturable(position);
        }
    }

    @Override
    public String toString(){
        if(this.color == Color.RED){
            if (isKing()) return ANSI_RED + "R˚" + ANSI_RESET;
            return ANSI_RED + "R" + ANSI_RESET;
        }else if(this.color == Color.YELLOW){
            if (isKing()) return ANSI_YELLOW + "Y˚" + ANSI_RESET;
            return ANSI_YELLOW + "Y" + ANSI_RESET;
        }else{
            return "O";
        }
    }

    public boolean equals(Position position) {
        return (this.position.getRow() == position.getRow() && this.position.getColumn() == position.getColumn());
    }

    abstract boolean isForwardDiagonal(Position position);

    abstract boolean isBackwardDiagonal(Position position);

    abstract boolean isForwardCapturable(Position position);

    abstract boolean isBackwardCapturable(Position position);

    abstract int kingRow();
}
