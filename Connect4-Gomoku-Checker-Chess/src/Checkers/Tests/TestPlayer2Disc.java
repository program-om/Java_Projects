package Checkers.Tests;

import BoardGames.Color;
import BoardGames.Position;
import Checkers.Player1Disc;
import Checkers.Player2Disc;
import org.junit.Assert;
import org.junit.Test;

public class TestPlayer2Disc {

    @Test
    public void testIsForwardDiagonal(){
        Player2Disc player2Disc = new Player2Disc(3, 3, Color.YELLOW);
        Assert.assertTrue(player2Disc.isForwardDiagonal(new Position(2,4)));
        Assert.assertTrue(player2Disc.isForwardDiagonal(new Position(2,2)));
        Assert.assertFalse(player2Disc.isForwardDiagonal(new Position(4,4)));
        Assert.assertFalse(player2Disc.isForwardDiagonal(new Position(4,2)));
        Assert.assertFalse(player2Disc.isForwardDiagonal(new Position(4,3)));
        Assert.assertFalse(player2Disc.isForwardDiagonal(new Position(2,3)));
    }

    @Test
    public void testIsBackwardDiagonal(){
        Player2Disc player2Disc = new Player2Disc(3, 3, Color.YELLOW);
        Assert.assertTrue(player2Disc.isBackwardDiagonal(new Position(4,4)));
        Assert.assertTrue(player2Disc.isBackwardDiagonal(new Position(4,2)));
        Assert.assertFalse(player2Disc.isBackwardDiagonal(new Position(4,3)));
        Assert.assertFalse(player2Disc.isBackwardDiagonal(new Position(2,3)));
        Assert.assertFalse(player2Disc.isBackwardDiagonal(new Position(2,4)));
        Assert.assertFalse(player2Disc.isBackwardDiagonal(new Position(2,2)));
    }


}
