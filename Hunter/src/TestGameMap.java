import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGameMap {

    @Test
    public void testReading(){
        GameMap gameMap = new GameMap();

    }

    @Test
    public void testReadDescription(){
        GameMap gameMap = new GameMap();
        ArrayList<Room> rooms = gameMap.getRooms();
        Assert.assertTrue(rooms.get(0).equals(1));
        Assert.assertEquals(rooms.get(0).getShortDescription(), "Rocky Valley");
        Assert.assertEquals(rooms.get(0).getLongDescription(), "You are striving to find something to eat in order to complete" +
                "your trip before you die of hunger. There are few animals" +
                "hanging around in this area.");

        Assert.assertTrue(rooms.get(1).equals(2));
        Assert.assertEquals(rooms.get(1).getShortDescription(), "Dry River");
        Assert.assertEquals(rooms.get(1).getLongDescription(), "You are standing in front of a dry river that has not water.");
    }

    @Test
    public void testAddingObjects(){
        GameMap gameMap = new GameMap();
        ArrayList<Room> rooms = gameMap.getRooms();

        Assert.assertEquals(1, rooms.get(3).getObjects().size());
        Assert.assertEquals("Riffle", rooms.get(3).getObjects().get(0).getName());

        Assert.assertEquals(2, rooms.get(4).getObjects().size());
        Assert.assertEquals("Arrow", rooms.get(4).getObjects().get(0).getName());
        Assert.assertEquals("Bow", rooms.get(4).getObjects().get(1).getName());

        Assert.assertEquals(1, rooms.get(6).getObjects().size());
        Assert.assertEquals("Bird", rooms.get(6).getObjects().get(0).getName());

        Assert.assertEquals(1, rooms.get(12).getObjects().size());
        Assert.assertEquals("Fire", rooms.get(12).getObjects().get(0).getName());

        Assert.assertEquals(1, rooms.get(14).getObjects().size());
        Assert.assertEquals("Deer", rooms.get(14).getObjects().get(0).getName());

        Assert.assertEquals(1, rooms.get(16).getObjects().size());
        Assert.assertEquals("Deer", rooms.get(16).getObjects().get(0).getName());

    }
}
