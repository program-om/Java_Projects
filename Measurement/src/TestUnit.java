import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestUnit {

    @Test
    public void testUnit(){
        ArrayList<UnitItem> num = new ArrayList<UnitItem>();
        ArrayList<UnitItem> deno = new ArrayList<UnitItem>();
        num.add(new UnitItem(2, "N"));
        num.add(new UnitItem(1, "f"));
        num.add(new UnitItem(1, "cm"));
        deno.add(new UnitItem(2, "L"));
        deno.add(new UnitItem(1, "km"));
        Unit unit = new Unit(num, deno);
        Assert.assertEquals("N^2 f cm / L^2 km", unit.toString());
    }

    @Test
    public void testConcatenate(){
        ArrayList<UnitItem> num = new ArrayList<UnitItem>();
        ArrayList<UnitItem> deno = new ArrayList<UnitItem>();
        num.add(new UnitItem(2, "N"));
        num.add(new UnitItem(1, "f"));
        num.add(new UnitItem(1, "cm"));
        deno.add(new UnitItem(2, "L"));
        deno.add(new UnitItem(1, "km"));
        Unit unit = new Unit();
        ArrayList<UnitItem> newUnit = unit.concatenate(num, deno);
        StringBuilder concatenatedUnit = new StringBuilder();
        for (int i = 0; i < newUnit.size(); i++) {
            if(i != 0) concatenatedUnit.append(" ");
            concatenatedUnit.append(newUnit.get(i).toString());
        }
        Assert.assertEquals("N^2 f cm L^2 km", concatenatedUnit.toString());
    }

    @Test
    public void testUnitEquality(){
        Unit u = new Unit("m");
        u = u.multiplyUnit(u, new Unit("F"));
        Assert.assertEquals(u.toString(), "m F");
        Unit u2 = new Unit("F");
        u2 = u2.multiplyUnit(u2, new Unit("m"));
        Assert.assertTrue(u.equals(u2));

        Unit u3 = new Unit("m");
        u3 = u3.multiplyUnit(u3, new Unit("F"));
        Assert.assertEquals(u3.toString(), "m F");
        Unit u4 = new Unit("F");
        u4 = u4.multiplyUnit(u4, new Unit("ml"));
        Assert.assertFalse(u3.equals(u4));
    }
}
