package Chess;

import BoardGames.Color;
import BoardGames.Column;
import BoardGames.Disc;
import BoardGames.Position;

import java.util.ArrayList;

public class ChessPlayer {
    private int number;
    private Color color;
    private ChessBoard board;
    private ArrayList<ChessDisc> discs = new ArrayList<ChessDisc>();
    private ArrayList<ChessDisc> deadDiscs = new ArrayList<ChessDisc>();
    private ArrayList<Column> columns;

    public ChessPlayer(int playerNumber, Color color, ChessBoard board){
        this.number = playerNumber;
        this.color = color;
        this.board = board;
        this.columns = board.getColumns();

        placeDiscs(number);
    }

    public Color getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    private void placeDiscs(int player){
        MoveValidator moveValidator = new MoveValidator(columns);
        for (int i = 0; i < 8; i++){
            Pawn pawn;
            if (player == 1) {
                pawn = new Pawn(board, player, new Position(2, i+1));
                columns.get(i).occupyGrid(pawn, 2);
            } else {
                pawn = new Pawn(board, player, new Position(7, i+1));
                columns.get(i).occupyGrid(pawn, 7);
            }
            discs.add(pawn);
        }
        int row;
        if (player == 1) row = 1;
        else row = 8;
        Rook rook1 = new Rook(board, player, new Position(row, 1), moveValidator);
        Rook rook2 = new Rook(board, player, new Position(row, 8), moveValidator);
        Knight knight1 = new Knight(board, player, new Position(row, 2));
        Knight knight2 = new Knight(board, player, new Position(row, 7));
        Bishop bishop1 = new Bishop(board, player, new Position(row, 3), moveValidator);
        Bishop bishop2 = new Bishop(board, player, new Position(row, 6), moveValidator);
        Queen queen = new Queen(board, player, new Position(row, 4), moveValidator);
        King king = new King(board, player, new Position(row, 5));
        discs.add(rook1);
        discs.add(rook2);
        discs.add(knight1);
        discs.add(knight2);
        discs.add(bishop1);
        discs.add(bishop2);
        discs.add(queen);
        discs.add(king);
        columns.get(0).occupyGrid(rook1, row);
        columns.get(1).occupyGrid(knight1, row);
        columns.get(2).occupyGrid(bishop1, row);
        columns.get(3).occupyGrid(queen, row);
        columns.get(4).occupyGrid(king, row);
        columns.get(5).occupyGrid(bishop2, row);
        columns.get(6).occupyGrid(knight2, row);
        columns.get(7).occupyGrid(rook2, row);
    }

    //check if the player is cable of making at least one valid move
//    public boolean canMove(){
//
//    }

    public void addDeadDisc(ChessDisc disc){
        deadDiscs.add(disc);
    }

    public void promotePawn(String name, ChessDisc pawn){
        int targetIndex = 0;
        for (int i = 0; i < deadDiscs.size(); i++){
            if (deadDiscs.get(i).getName().equals(name)){
                deadDiscs.get(i).setPosition(pawn.getPosition());
                targetIndex = i;
            }
        }
        ChessDisc savedDisc = deadDiscs.get(targetIndex);
        columns.get(savedDisc.getPosition().getColumn()-1).getGrid(savedDisc.getPosition().getRow()).occupy(savedDisc);
        discs.add(savedDisc);
        discs.remove(pawn);
        deadDiscs.remove(savedDisc);
        deadDiscs.add(pawn);

    }

    public boolean isThereDead(String pieceName){
        for (ChessDisc disc : deadDiscs){
            if (disc.getName().equals(pieceName)){
                return true;
            }
        }
        return false;
    }

    public boolean canCapture(Position to){
        for (ChessDisc disc : discs){
            if (disc.canCapture(to)){
                return true;
            }
        }
        return false;
    }
}
