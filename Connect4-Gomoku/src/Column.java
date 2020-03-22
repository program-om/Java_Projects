import java.util.ArrayList;

abstract public class Column {
    protected ArrayList<Grid> grids = new ArrayList<Grid>();

    Column() {

    }

    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public Grid getGrid(int number){
        return this.grids.get(number-1);
    }

    abstract int dropDisc(Disc disc);

    public boolean isGridFilled(int gridNum){
        return getGrid(gridNum).isOccupied();
    }

}
