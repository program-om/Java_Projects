import org.junit.Assert;
import org.junit.Test;

public class TestUnitItem {

    @Test
    public void testUnitItem(){
        UnitItem unitItem = new UnitItem(0, "t");
        Assert.assertEquals("", unitItem.toString());

        unitItem = new UnitItem(1, "t");
        Assert.assertEquals("t", unitItem.toString());

        unitItem = new UnitItem(2, "t");
        Assert.assertEquals("t^2", unitItem.toString());

        unitItem.incrementQuantityBy(4);
        Assert.assertEquals("t^6", unitItem.toString());

        unitItem.setQuantity(3);
        Assert.assertEquals("t^3", unitItem.toString());
    }
}
