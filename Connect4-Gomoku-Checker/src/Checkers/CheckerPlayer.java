package Checkers;
import BoardGames.*;

import java.util.ArrayList;

public class CheckerPlayer {
    private ArrayList<CheckerDisc> discs = new ArrayList<CheckerDisc>();
    private ArrayList<Column> columns;
    private Color color;
    private CheckerBoard checkerBoard;
    private int number;

    public CheckerPlayer(int number, Color color, CheckerBoard checkerBoard){
        this.number = number;
        this.color = color;
        int col = 1;
        this.columns = checkerBoard.getColumns();
        this.checkerBoard = checkerBoard;
        while (col <= 8) {
            if (number == 1) {
                if (col % 2 == 1) {
                    createPlayer1Disc(1, col, color);
                    createPlayer1Disc(3, col, color);
                    //put disc at row 1 and 3
                } else {
                    //put disc at row 2
                    createPlayer1Disc(2, col, color);
                }
            } else {
                if (col % 2 == 1) {
                    //put disc at row 7
                    createPlayer2Disc(7, col, color);
                } else {
                    //put disc at row 6 and 8
                    createPlayer2Disc(6, col, color);
                    createPlayer2Disc(8, col, color);
                }
            }
            col++;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public ArrayList<CheckerDisc> getDiscs() {
        return discs;
    }

    public int getNumber() {
        return number;
    }

    private void createPlayer1Disc(int row, int col, Color color){
        Player1Disc disc = new Player1Disc(row, col, color);
        discs.add(disc);
        CheckerColumn column = (CheckerColumn)columns.get(col-1);//.dropDisc(row-1, disc);
        column.occupyGrid(disc, row);
    }

    private void createPlayer2Disc(int row, int col, Color color){
        Player2Disc disc = new Player2Disc(row, col, color);
        discs.add(disc);
        CheckerColumn column = (CheckerColumn)columns.get(col-1);//.dropDisc(row-1, disc);
        column.occupyGrid(disc, row);
    }

    public boolean isOutOfDiscs(){
        return (discs.size() == 0);
    }

    public boolean removeDisc(CheckerDisc disc){
        return discs.remove(disc);
    }

    public boolean canMoveAnyDisc(){
        for (CheckerDisc disc: discs) {
            Position position = disc.getPosition();
            if (disc.isKing()){
                if (canMoveForward(position) || canMoveBackward(position)){
                    return true;
                }
            } else {
                if (number == 1){
                    if (canMoveForward(position)){
                        return true;
                    }
                } else {
                    if (canMoveBackward(position)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canMoveForward(Position position){
        boolean moveRight = false;
        boolean moveLeft = false;
        if (position.getRow()+1 <= 8 && position.getColumn()-1 > 0){
            moveRight = checkerBoard.isValidMove(position, new Position(
                    position.getRow()+1, position.getColumn()-1), number, color);
        }
        if (position.getRow()+1 <= 8 && position.getColumn()+1 <= 8){
            moveLeft = checkerBoard.isValidMove(position, new Position(
                    position.getRow()+1, position.getColumn()+1), number, color);
        }
        return ( moveRight|| moveLeft);
    }

    private boolean canMoveBackward(Position position){
        boolean moveRight = false;
        boolean moveLeft = false;
        if (position.getRow()-1 > 0 && position.getColumn()-1 > 0){
            moveRight = checkerBoard.isValidMove(position, new Position(
                    position.getRow()-1, position.getColumn()-1), number, color);
        }
        if (position.getRow()-1 > 0 && position.getColumn()+1 <= 8){
            moveLeft = checkerBoard.isValidMove(position, new Position(
                    position.getRow()-1, position.getColumn()+1), number, color);
        }

        return (moveRight || moveLeft);
    }

    public boolean canCapture(int playerTurn){
        for(CheckerDisc disc: discs){
            Position position = disc.getPosition();
            if (disc.isKing()){
                if (canCaptureForward(position, playerTurn) || canCaptureBackward(position, playerTurn)){
                    return true;
                }
            }else {
                if (playerTurn == 1){
                    if (canCaptureForward(position, playerTurn)){
                        return true;
                    }
                }else{
                    if (canCaptureBackward(position, playerTurn)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canCaptureForward(Position position, int playerTurn){
        boolean captureRight = false;
        boolean captureLeft = false;
        if (position.getRow()+2 <= 8 && position.getColumn()-2 > 0){
            captureRight = checkerBoard.isValidCapture(position, new Position(
                    position.getRow()+2, position.getColumn()-2), playerTurn);
        }
        if (position.getRow()+2 <= 8 && position.getColumn()+2 <= 8){
            captureLeft = checkerBoard.isValidCapture(position, new Position(
                    position.getRow()+2, position.getColumn()+2), playerTurn);
        }
        return ( captureRight|| captureLeft);
    }

    private boolean canCaptureBackward(Position position, int playerTurn){
        boolean captureRight = false;
        boolean captureLeft = false;
        if (position.getRow()-2 > 0 && position.getColumn()-2 > 0){
            captureRight = checkerBoard.isValidCapture(position, new Position(
                    position.getRow()-2, position.getColumn()-2), playerTurn);
        }
        if (position.getRow()-2 > 0 && position.getColumn()+2 <= 8){
            captureLeft = checkerBoard.isValidCapture(position, new Position(
                    position.getRow()-2, position.getColumn()+2), playerTurn);
        }
        return ( captureRight || captureLeft);
    }
}
