package Checkers.Tests;

import Checkers.Checker;
import org.junit.Assert;
import org.junit.Test;

public class TestChecker {

    @Test
    public void testCharToInt(){
        Checker checker = new Checker("test");
        Assert.assertEquals(1, checker.charToInt('A'));
        Assert.assertEquals(2, checker.charToInt('B'));
        Assert.assertEquals(3, checker.charToInt('C'));
        Assert.assertEquals(4, checker.charToInt('D'));
        Assert.assertEquals(5, checker.charToInt('E'));
        Assert.assertEquals(6, checker.charToInt('F'));
        Assert.assertEquals(7, checker.charToInt('G'));
        Assert.assertEquals(8, checker.charToInt('H'));
        Assert.assertEquals(-1, checker.charToInt('I'));
    }

    @Test
    public void testGetInt(){
        Checker checker = new Checker("test");
        Assert.assertEquals(1, checker.getInt('1'));
        Assert.assertEquals(2, checker.getInt('2'));
        Assert.assertEquals(3, checker.getInt('3'));
        Assert.assertEquals(8, checker.getInt('8'));
        Assert.assertEquals(-1, checker.getInt('9'));
        Assert.assertEquals(-1, checker.getInt('0'));
    }
}
