import org.junit.Assert;
import org.junit.Test;

public class TestPlayer {

    @Test
    public void testPlayer(){
        GameMap gameMap = new GameMap();
        Player player = new Player(gameMap.getRooms().get(0));
        Assert.assertEquals("Rocky Valley", player.getCurrentRoom().getShortDescription());
        player.move("n");
        Assert.assertEquals("3rd room", player.getCurrentRoom().getShortDescription());
        player.move("w");
        Assert.assertEquals("3rd room", player.getCurrentRoom().getShortDescription());
        player.move("s");
        Assert.assertEquals("Rocky Valley", player.getCurrentRoom().getShortDescription());
    }
}
