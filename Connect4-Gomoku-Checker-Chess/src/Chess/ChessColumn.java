package Chess;

import BoardGames.Column;
import BoardGames.Disc;
import BoardGames.Grid;

public class ChessColumn extends Column {

    ChessColumn(){
        for (int i = 0; i < 8; i++) {
            grids.add(new Grid());
        }
    }

    public int dropDisc(Disc disc){
        return 0;
    }
}
