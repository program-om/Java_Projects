package BoardGames;

public class Position {
    protected int row;
    protected int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public Position getRelativePosition(int rowDiff, int colDiff){
        return new Position(row + rowDiff, column + colDiff);
    }

    @Override
    public String toString() {
        return "( " + row + ", " + column + " )";
    }

    public boolean equals(Position position) {
        return (row == position.getRow() && column == position.getColumn());
    }
}
