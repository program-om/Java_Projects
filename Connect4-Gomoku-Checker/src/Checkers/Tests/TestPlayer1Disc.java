package Checkers.Tests;

import BoardGames.Color;
import BoardGames.Position;
import Checkers.Player1Disc;
import org.junit.Assert;
import org.junit.Test;

public class TestPlayer1Disc {

    @Test
    public void testIsForwardDiagonal(){
        Player1Disc player1Disc = new Player1Disc(3, 3, Color.RED);
        Assert.assertTrue(player1Disc.isForwardDiagonal(new Position(4,4)));
        Assert.assertTrue(player1Disc.isForwardDiagonal(new Position(4,2)));
        Assert.assertFalse(player1Disc.isForwardDiagonal(new Position(4,3)));
        Assert.assertFalse(player1Disc.isForwardDiagonal(new Position(2,3)));
        Assert.assertFalse(player1Disc.isForwardDiagonal(new Position(2,4)));
        Assert.assertFalse(player1Disc.isForwardDiagonal(new Position(2,2)));
    }

    @Test
    public void testIsBackwardDiagonal(){
        Player1Disc player1Disc = new Player1Disc(3, 3, Color.RED);
        Assert.assertTrue(player1Disc.isBackwardDiagonal(new Position(2,4)));
        Assert.assertTrue(player1Disc.isBackwardDiagonal(new Position(2,2)));
        Assert.assertFalse(player1Disc.isBackwardDiagonal(new Position(4,4)));
        Assert.assertFalse(player1Disc.isBackwardDiagonal(new Position(4,2)));
        Assert.assertFalse(player1Disc.isBackwardDiagonal(new Position(4,3)));
        Assert.assertFalse(player1Disc.isBackwardDiagonal(new Position(2,3)));
    }
}
