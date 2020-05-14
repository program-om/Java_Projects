package Checkers;

import BoardGames.Color;
import BoardGames.Position;

public class Player1Disc extends CheckerDisc {

    public Player1Disc(int row, int column, Color color){
        super(row, column, color);
    }

    public boolean isForwardDiagonal(Position position){
        return  (this.position.getColumn() - position.getColumn() == -1 && //forward right
                this.position.getRow() - position.getRow() == -1) ||
                (this.position.getColumn() - position.getColumn() == 1 && //forward left
                this.position.getRow() - position.getRow() == -1);
    }

    public boolean isBackwardDiagonal(Position position){
        return  (this.position.getColumn() - position.getColumn() == -1 && //backward right
                this.position.getRow() - position.getRow() == 1) ||
                (this.position.getColumn() - position.getColumn() == 1 && //backward left
                this.position.getRow() - position.getRow() == 1);
    }

    public boolean isForwardCapturable(Position position){
        return  (this.position.getColumn() - position.getColumn() == -2 && //forward right
                this.position.getRow() - position.getRow() == -2) ||
                (this.position.getColumn() - position.getColumn() == 2 && //forward left
                this.position.getRow() - position.getRow() == -2);
    }

    public boolean isBackwardCapturable(Position position){
        return  (this.position.getColumn() - position.getColumn() == -2 && //backward right
                this.position.getRow() - position.getRow() == 2) ||
                (this.position.getColumn() - position.getColumn() == 2 && //backward left
                this.position.getRow() - position.getRow() == 2);
    }

    public int kingRow(){
        return 8;
    }
}
