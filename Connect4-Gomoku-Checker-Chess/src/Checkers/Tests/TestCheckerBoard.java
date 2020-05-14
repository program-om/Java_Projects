package Checkers.Tests;

import BoardGames.Color;
import BoardGames.Column;
import BoardGames.Position;
import Checkers.CheckerBoard;
import Checkers.CheckerDisc;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestCheckerBoard {

    @Test
    public void testBoard(){
        CheckerBoard checkerBoard = new CheckerBoard();
        ArrayList<Column> columns =  checkerBoard.getColumns();
        Assert.assertEquals(8, columns.size());
        Assert.assertEquals(Color.RED, columns.get(0).getGrid(1).getDisc().getColor() );
        Assert.assertTrue(checkerBoard.isValidMove(new Position(3,1),
                new Position(4, 2), 1, Color.RED));
        Assert.assertFalse(checkerBoard.isValidMove(new Position(3,1),
                new Position(3, 2), 1, Color.RED));


        checkerBoard.dropDisc(checkerBoard.getColumns().get(0).getGrid(3).pop(), 4, 2);
        Assert.assertEquals(Color.RED, columns.get(1).getGrid(4).getDisc().getColor() );

        checkerBoard.dropDisc(checkerBoard.getColumns().get(3).getGrid(6).pop(), 5, 3);
        Assert.assertEquals(Color.YELLOW, columns.get(2).getGrid(5).getDisc().getColor() );

        Assert.assertTrue(checkerBoard.isValidCapture(new Position(4,2), new Position(6,4), 1));
    }

    @Test
    public void testCapture(){
        CheckerBoard checkerBoard = new CheckerBoard();
        ArrayList<Column> columns =  checkerBoard.getColumns();
        Assert.assertEquals(8, columns.size());
        Assert.assertEquals(Color.RED, columns.get(0).getGrid(1).getDisc().getColor() );
        Assert.assertTrue(checkerBoard.isValidMove(new Position(3,1),
                new Position(4, 2), 1, Color.RED));
        Assert.assertFalse(checkerBoard.isValidMove(new Position(3,1),
                new Position(3, 2), 1, Color.RED));


        checkerBoard.dropDisc(columns.get(0).getGrid(3).pop(), 4, 2);
        Assert.assertEquals(Color.RED, columns.get(1).getGrid(4).getDisc().getColor() );

        checkerBoard.dropDisc(columns.get(3).getGrid(6).pop(), 5, 3);
        Assert.assertTrue(columns.get(2).getGrid(5).isOccupied());
        Assert.assertEquals(Color.YELLOW, columns.get(2).getGrid(5).getDisc().getColor() );

        checkerBoard.capture(new Position(4, 2), new Position(6, 4), 1);
        Assert.assertEquals(11, checkerBoard.getPlayer(2).getDiscs().size());
        Assert.assertEquals(12, checkerBoard.getPlayer(1).getDiscs().size());
        Assert.assertEquals(Color.NoColor, columns.get(2).getGrid(5).getDisc().getColor());
        Assert.assertEquals(Color.RED, columns.get(3).getGrid(6).getDisc().getColor());
    }

    @Test
    public void testDeleteDisc(){
        CheckerBoard checkerBoard = new CheckerBoard();
        ArrayList<Column> columns =  checkerBoard.getColumns();
        Assert.assertEquals(8, columns.size());
        Assert.assertEquals(Color.RED, columns.get(0).getGrid(1).getDisc().getColor() );
        Assert.assertTrue(checkerBoard.isValidMove(new Position(3,1),
                new Position(4, 2), 1, Color.RED));
        Assert.assertFalse(checkerBoard.isValidMove(new Position(3,1),
                new Position(3, 2), 1, Color.RED));


        checkerBoard.dropDisc(columns.get(0).getGrid(3).pop(), 4, 2);
        Assert.assertEquals(Color.RED, columns.get(1).getGrid(4).getDisc().getColor() );

        checkerBoard.dropDisc(columns.get(3).getGrid(6).pop(), 5, 3);
        Assert.assertTrue(columns.get(2).getGrid(5).isOccupied());
        Assert.assertEquals(Color.YELLOW, columns.get(2).getGrid(5).getDisc().getColor() );

        Assert.assertTrue(checkerBoard.deleteDisc((CheckerDisc) columns.get(2).getGrid(5).getDisc(), 2));
        Assert.assertEquals(11, checkerBoard.getPlayer(2).getDiscs().size());
        //Assert.assertEquals(Color.NoColor, checkerBoard.getColumns().get(2).getGrid(5).getDisc().getColor());
    }

    @Test
    public void testMoveAnyDisc(){
        CheckerBoard checkerBoard = new CheckerBoard();
        ArrayList<Column> columns =  checkerBoard.getColumns();

        Assert.assertFalse(checkerBoard.isWon(0));
    }
}
