package BoardGames;

import java.util.ArrayList;

public abstract class Column {
    protected ArrayList<Grid> grids = new ArrayList<Grid>();

    protected Column() {

    }

    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public Grid getGrid(int number){
        return this.grids.get(number-1);
    }

    public abstract int dropDisc(Disc disc);

    public void dropDisc(int index, Disc disc){
        if (!grids.get(index).isOccupied()){
            grids.get(index).occupy(disc);
        }
    }

    public void occupyGrid(Disc disc, int row){
        grids.get(row-1).occupy(disc);
    }

    public boolean isGridFilled(int gridNum){
        return getGrid(gridNum).isOccupied();
    }

}
