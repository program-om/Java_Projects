import org.junit.Assert;
import org.junit.Test;

public class TestMeasurement {

    @Test
    public void testMeasurement(){
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.mult(m, new Measurement(1.0, "m"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m", m.toString());

        m = m.div(m, new Measurement(1.0, "s"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m / s", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m / s", m.toString());

        m = m.div(m, new Measurement(1.0, "s"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m / s^2", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m / s^2", m.toString());

        m = m.mult(m, new Measurement(1.0, "m"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m^2 / s^2", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m^2 / s^2", m.toString());

        m = m.div(m, new Measurement(1.0, "m"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m / s^2", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m / s^2", m.toString());

        //-- m2
        Measurement m2 = new Measurement(3.0, "s");
        Assert.assertEquals(3.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("s", m2.getUnit().toString());
        Assert.assertEquals("3.0 s", m2.toString());
        m2 = m2.mult(m2, new Measurement(1.0, "s"));
        Assert.assertEquals(3.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("s^2", m2.getUnit().toString());
        Assert.assertEquals("3.0 s^2", m2.toString());
        m2 = m2.mult(m2, new Measurement(1.0, "s"));
        Assert.assertEquals(3.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("s^3", m2.getUnit().toString());
        Assert.assertEquals("3.0 s^3", m2.toString());
        m2 = m2.div(m2, new Measurement(1.0, "v"));
        Assert.assertEquals(3.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("s^3 / v", m2.getUnit().toString());
        Assert.assertEquals("3.0 s^3 / v", m2.toString());

//        Measurement result = m.mult(m, m2);
//        Assert.assertEquals(36.0, result.getQuantity(), 1e-5);
//        Assert.assertEquals("kg m s / v", result.getUnit().toString());
//        Assert.assertEquals(result.toString(), "36.0 kg m s / v");

        Measurement result2 = m.div(m, m2);
        Assert.assertEquals(4.0, result2.getQuantity(), 1e-5);
        Assert.assertEquals("kg m v / s^5", result2.getUnit().toString());
        Assert.assertEquals("4.0 kg m v / s^5", result2.toString());
    }

    @Test
    public void testAdd() {
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.add(m, new Measurement(3.0, "kg"));
        Assert.assertEquals(15.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals("15.0 kg", m.toString());
    }

    @Test
    public void testAddDifferentOrder() {
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.mult(m, new Measurement(1.0, "m"));
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m", m.getUnit().toString());
        Assert.assertEquals("12.0 kg m", m.toString());

        Measurement m2 = new Measurement(8.0, "m");
        Assert.assertEquals(8.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("m", m2.getUnit().toString());
        Assert.assertEquals(m2.toString(), "8.0 m");

        m2 = m2.mult(m2, new Measurement(1.0, "kg"));
        Assert.assertEquals(8.0, m2.getQuantity(), 1e-5);
        Assert.assertEquals("m kg", m2.getUnit().toString());
        Assert.assertEquals("8.0 m kg", m2.toString());

        m = m.add(m, m2);
        Assert.assertEquals(20.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg m", m.getUnit().toString());
        Assert.assertEquals("20.0 kg m", m.toString());
    }

    @Test(expected = ArithmeticException.class)
    public void testIncompatibleAdd() {
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.add(m, new Measurement(3.0, "m"));
    }

    @Test
    public void testSubtract() {
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.sub(m, new Measurement(3.0, "kg"));
        Assert.assertEquals(9.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals("9.0 kg", m.toString());
    }

    @Test(expected = ArithmeticException.class)
    public void testIncompatibleSubtract() {
        Measurement m = new Measurement(12.0, "kg");
        Assert.assertEquals(12.0, m.getQuantity(), 1e-5);
        Assert.assertEquals("kg", m.getUnit().toString());
        Assert.assertEquals(m.toString(), "12.0 kg");

        m = m.sub(m, new Measurement(3.0, "m"));
    }
}
