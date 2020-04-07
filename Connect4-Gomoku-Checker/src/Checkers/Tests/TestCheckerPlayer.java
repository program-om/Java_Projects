package Checkers.Tests;

import BoardGames.Color;
import BoardGames.Column;
import Checkers.CheckerBoard;
import Checkers.CheckerPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestCheckerPlayer {

    @Test
    public void testCanMoveAnyDisc(){
        CheckerBoard checkerBoard = new CheckerBoard();
        ArrayList<Column> columns =  checkerBoard.getColumns();

        //Assert.assertFalse(checkerBoard.pla));

        CheckerPlayer player = new CheckerPlayer(1, Color.RED, checkerBoard);
        Assert.assertTrue(player.canMoveAnyDisc());
    }
}
