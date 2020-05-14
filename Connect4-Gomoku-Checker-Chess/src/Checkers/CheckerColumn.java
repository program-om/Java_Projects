package Checkers;

import BoardGames.*;

public class CheckerColumn extends Column {

    CheckerColumn(){
        for (int i = 0; i < 8; i++) {
            grids.add(new Grid());
        }
    }

    public int dropDisc(Disc disc){
        return 0;
    }

    public boolean isGridOccupied(int row){
        return grids.get(row-1).isOccupied();
    }

    public Disc pop(int row){
        return grids.get(row-1).pop();
    }

}
