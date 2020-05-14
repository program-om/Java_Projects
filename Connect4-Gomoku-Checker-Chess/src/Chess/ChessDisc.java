package Chess;

import BoardGames.Color;
import BoardGames.Disc;
import BoardGames.Grid;
import BoardGames.Position;
import BoardGames.Column;

import java.util.ArrayList;

public abstract class ChessDisc extends Disc {
    ChessBoard board;
    ArrayList<Column> columns;
    Position position;
    protected int playerNumber;
    protected boolean isKing = false;
    protected String name;

    public ChessDisc(ChessBoard board, int playerNumber){
        this.board = board;
        columns = board.getColumns();
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isKing(){
        return isKing;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    @Override
    public String toString() {
        return "·";
    }

    public boolean canCapture(Position to){
        return isMovable(to);
    }

    abstract boolean isMovable(Position to);
}
