package Chess.Tests;

import BoardGames.Position;
import Chess.Rook;
import Chess.ChessBoard;
import org.junit.Assert;
import org.junit.Test;

public class TestRook {

    @Test
    public void testIsMovable(){
        ChessBoard board  = new ChessBoard();
        Rook bishop = (Rook) board.getColumns().get(0).getGrid(1).getDisc();
        Assert.assertFalse(bishop.isMovable(new Position(1, 1)));
        Assert.assertFalse(bishop.isMovable(new Position(2, 1)));
        Assert.assertFalse(bishop.isMovable(new Position(1, 2)));
        Assert.assertFalse(bishop.isMovable(new Position(3, 1)));
        Assert.assertFalse(bishop.isMovable(new Position(1, 3)));

        Assert.assertFalse(bishop.isMovable(new Position(1, 3)));
        Assert.assertFalse(bishop.isMovable(new Position(2, 2)));
        Assert.assertFalse(bishop.isMovable(new Position(2, 4)));
        Assert.assertFalse(bishop.isMovable(new Position(3, 1)));
        Assert.assertFalse(bishop.isMovable(new Position(3, 5)));
    }
}
