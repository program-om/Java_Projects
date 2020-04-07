package BoardGames;

public class Position {
    protected int row;
    protected int column;

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "( " + row + ", " + column + " )";
    }
}
