package Chess.Tests;

import BoardGames.Position;
import Chess.Bishop;
import Chess.ChessBoard;
import org.junit.Assert;
import org.junit.Test;

public class TestBishop {

    @Test
    public void testIsMovable(){
        ChessBoard board  = new ChessBoard();
        Bishop bishop = (Bishop) board.getColumns().get(2).getGrid(1).getDisc();
        Assert.assertFalse(bishop.isMovable(new Position(1, 3)));
        Assert.assertFalse(bishop.isMovable(new Position(2, 2)));
        Assert.assertFalse(bishop.isMovable(new Position(2, 4)));
        Assert.assertFalse(bishop.isMovable(new Position(3, 1)));
        Assert.assertFalse(bishop.isMovable(new Position(3, 5)));
    }
}
