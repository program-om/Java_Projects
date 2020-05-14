package Gomoku;

import BoardGames.Column;
import BoardGames.Disc;
import BoardGames.Grid;

public class GomokuColumn extends Column {

    GomokuColumn(){
        for (int i = 0; i < 19; i++) {
            grids.add(new Grid());
        }
    }

    public int dropDisc(Disc disc){
        return 0;
    }

    public boolean isGridOccupied(int row){
        return grids.get(row-1).isOccupied();
    }

    public void occupyGrid(Disc disc, int row){
        grids.get(row-1).occupy(disc);
    }
}
