import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class polynomialTest {

    @Test
    public void testConstructor(){
        polynomial p1 = new polynomial("2x+2");
        assertEquals(2, p1.terms.size());

        polynomial p2 = new polynomial("2x+2+4+5");
        assertEquals(4, p2.terms.size());

        polynomial p3 = p1.add(p2);
        polynomial p4 = p1.sub(p2);
        polynomial p5 = p1.mult(p2);

    }

    @Test
    public void addTerms(){
        polynomial p = new polynomial("2x^2-4w");
        assertEquals("2x^2", p.terms.get(0).to_String());
        assertEquals("-4w", p.terms.get(1).to_String());

        polynomial p2 = new polynomial("4+4w+3x^2");

        polynomial p3 = p.add(p2);
        assertEquals(2, p3.terms.size());
        assertEquals("5x^2", p3.terms.get(0).to_String());
        assertEquals("4", p3.terms.get(1).to_String());

    }

    @Test
    public void subTerms(){
        polynomial p = new polynomial("2x^2-4w");
        assertEquals("2x^2", p.terms.get(0).to_String());
        assertEquals("-4w", p.terms.get(1).to_String());

        polynomial p2 = new polynomial("4+4w+3x^2");

        polynomial p3 = p.sub(p2);
        assertEquals(3, p3.terms.size());
        assertEquals("-1x^2", p3.terms.get(0).to_String());
        assertEquals("-8w", p3.terms.get(1).to_String());
        assertEquals("-4", p3.terms.get(2).to_String());

    }

    @Test
    void multiplyPolynomials(){
        polynomial p = new polynomial("2x^2-4w");
        assertEquals("2x^2", p.terms.get(0).to_String());
        assertEquals("-4w", p.terms.get(1).to_String());

        polynomial p2 = new polynomial("4+4w+3x^2");

        polynomial p3 = p.mult(p2);
        assertEquals(6, p3.terms.size());
        assertEquals("8x^2", p3.terms.get(0).to_String());
        assertEquals("8x^2w", p3.terms.get(1).to_String());
        assertEquals("6x^4", p3.terms.get(2).to_String());
        assertEquals("-16w", p3.terms.get(3).to_String());
        assertEquals("-16w^2", p3.terms.get(4).to_String());
        assertEquals("-12wx^2", p3.terms.get(5).to_String());
    }


    @Test
    public void testBadEvaluate(){
        assertThrows(ArithmeticException.class, () -> {
            polynomial p1 = new polynomial("2x+2xy^2-3z");
            Character []variables = {1, 4};
            double []varValues = {'x', 'y'};
            p1.Evaluate(variables, varValues);
        });
    }

    @Test
    public void testEvaluate(){
        polynomial p1 = new polynomial("2x+2xy^2-3z");
        Character []variables = {'x', 'y', 'z'};
        double []varValues = {4, 2, 3};
        double result = p1.Evaluate(variables, varValues);
        assertEquals(31 , result);
    }

    public void testParsing(){
        polynomial p1 = new polynomial("2x+2");
        assertEquals(2, p1.terms.size());
    }

}
