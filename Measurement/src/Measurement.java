

public class Measurement {
    private double quantity;
    private Unit unit;

    Measurement(){

    }

    Measurement(double q, String u) {
        this.quantity = q;
        this.unit = new Unit(u);
    }

    Measurement(double q, Unit u){
        this.quantity = q;
        this.unit = u;
    }

    public Unit getUnit(){
        return this.unit;
    }

    public double getQuantity(){
        return this.quantity;
    }

    public Measurement add(Measurement m1, Measurement m2){
        if (!m1.getUnit().equals(m2.getUnit())){
            throw new ArithmeticException("Incompatible measurements units");
        }
        double resultQuantity = m1.getQuantity() + m2.getQuantity();
        Measurement result = new Measurement(resultQuantity, m1.getUnit());
        return result;
    }

    public Measurement sub(Measurement m1, Measurement m2){
        if (!m1.getUnit().equals(m2.getUnit())){
            throw new ArithmeticException("Incompatible measurements units");
        }
        double resultQuantity = m1.getQuantity() - m2.getQuantity();
        Measurement result = new Measurement(resultQuantity, m1.getUnit());
        return result;
    }

    public Measurement mult(Measurement m1, Measurement m2){
        double resultQuantity = m1.getQuantity() * m2.getQuantity();
        Unit newUnit = new Unit();
        Unit resultUnit = newUnit.multiplyUnit(m1.getUnit(), m2.getUnit());
        resultUnit.cancelOut();
        Measurement result = new Measurement(resultQuantity, resultUnit);
        return result;
    }

    public Measurement div(Measurement m1, Measurement m2){
        double resultQuantity = m1.getQuantity() / m2.getQuantity();
        Unit newUnit = new Unit();
        Unit resultUnit = newUnit.divideUnit(m1.getUnit(), m2.getUnit());
        resultUnit.cancelOut();
        Measurement result = new Measurement(resultQuantity, resultUnit);
        return result;
    }

    public String toString(){
        return this.quantity + " " + this.unit.toString();
    }
}
