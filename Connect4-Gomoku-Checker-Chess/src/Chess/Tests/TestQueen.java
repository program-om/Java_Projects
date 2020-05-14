package Chess.Tests;

import BoardGames.Position;
import Chess.Queen;
import Chess.ChessBoard;
import org.junit.Assert;
import org.junit.Test;

public class TestQueen {

    @Test
    public void testQueen(){
        ChessBoard board  = new ChessBoard();
        Queen queen = (Queen) board.getColumns().get(3).getGrid(1).getDisc();
        Assert.assertFalse(queen.isMovable(new Position(1, 3)));
        Assert.assertFalse(queen.isMovable(new Position(2, 3)));
        Assert.assertFalse(queen.isMovable(new Position(2, 5)));
        Assert.assertFalse(queen.isMovable(new Position(3, 2)));
        Assert.assertFalse(queen.isMovable(new Position(3, 6)));
    }
}
