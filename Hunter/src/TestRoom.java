import org.junit.Assert;
import org.junit.Test;

public class TestRoom {

    @Test
    public void testRoom(){
        GameMap gameMap = new GameMap();
        Room room = gameMap.getRooms().get(0);
        room = room.getNeighbor("n");
        room = room.getNeighbor("e");
        Assert.assertNull(room);
    }
}
