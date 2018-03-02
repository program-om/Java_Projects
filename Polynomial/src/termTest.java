import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class termTest {

    @Test
    public void termConstructor(){
        Term term = new Term();
        assertEquals(1, term.coef);
    }

    @Test
    void multiplyTerms(){
        polynomial p = new polynomial("2x^2zy^3-4y^3x^2z");
        Term t1 = p.terms.get(0);
        Term t2 = p.terms.get(1);

        Term t3 = t1.multiply(t2);
        assertEquals("-8x^4z^2y^6", t3.to_String());
    }

    @Test
    void EqualTerms(){
        polynomial p = new polynomial("2x^2zy^3-4y^3x^2z");
        Term t1 = p.terms.get(0);
        Term t2 = p.terms.get(1);

        assertTrue(t1.equalTerm(t2));
    }
}
