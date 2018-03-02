/*  Name: Abdulaziz Alshabibi
    Class: CS290
* */
import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;

class polynomial {
    //create a list of terms
    ArrayList<Term> terms = new ArrayList<Term>();

    polynomial(String s){

        ByteArrayInputStream poly = new ByteArrayInputStream(s.getBytes());
        PushbackInputStream p = new PushbackInputStream(poly);
        try {
            while (p.available() != 1) {
                //create a term
                Term trm = new Term();
                trm.nextTermFrom(p);
                //add the term to the list
                terms.add(trm);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void to_string(){
            System.out.print(terms.get(0).to_String());
            for (int k=1; k<terms.size(); k++) {

                if(terms.get(k).coef > 0)
                    System.out.print("+");
                else if (terms.get(k).coef < 0)
                    System.out.print("");
                else
                    break;
                System.out.print(terms.get(k).to_String());
            }
            System.out.println();
        }

    public polynomial add(polynomial p2){
            polynomial p3 = new polynomial(" ");
            //array to check which of the element
            boolean []EleUsed = new boolean[p2.terms.size()];
            int P2index;
            for (boolean each: EleUsed) {
                each = false;
            }
            //in the second list not used!
            for (Term termPoly1: terms) {

                for (Term termPoly2: p2.terms) {

                    //if these two terms match..Create term that is the sum
                    if(termPoly1.equalTerm(termPoly2)){
                        //create a term
                        int sum = termPoly1.coef+termPoly2.coef;
                        if (sum == 0){
                            P2index = p2.terms.indexOf(termPoly2);
                            EleUsed[P2index] = true;
                            break;
                        }
                         Term term = new Term();
                         term.exps = termPoly1.exps;
                         term.vars = termPoly1.vars;
                         term.coef = sum;

                         p3.terms.add(term);
                         P2index = p2.terms.indexOf(termPoly2);
                         EleUsed[P2index] = true;
                         break;
                    }
                    //if this is the last element in poly2..it will reach here if none of the terms
                    //match with this term.
                    if (p2.terms.indexOf(termPoly2) == p2.terms.size()-1){
                        Term term = new Term();
                        term.exps = termPoly1.exps;
                        term.vars = termPoly1.vars;
                        term.coef = termPoly1.coef;
                        p3.terms.add(term);
                    }
                }
            }
            for (int i=0; i < EleUsed.length; i++) {
                if(!EleUsed[i]){
                    //Term term = new Term(p2.terms.get(i));
                    Term term = new Term();
                    term.exps = p2.terms.get(i).exps;
                    term.vars = p2.terms.get(i).vars;
                    term.coef = p2.terms.get(i).coef;
                    p3.terms.add(term);
                }
            }
            return p3;
        }

    public polynomial sub(polynomial p2){
        polynomial p3 = new polynomial(" ");
        //array to check which of the element
        boolean []EleUsed = new boolean[p2.terms.size()];
        int P2index;
        for (boolean each: EleUsed) {
            each = false;
        }

        //in the second list not used!
        for (Term termPoly1: terms) {

            for (Term termPoly2: p2.terms) {
                //if these two terms match..Create term that is the sum
                if(termPoly1.equalTerm(termPoly2)){
                    int sum = termPoly1.coef-termPoly2.coef;
                    if (sum == 0){
                        P2index = p2.terms.indexOf(termPoly2);
                        EleUsed[P2index] = true;
                        break;
                    }
                    //create a term
                    Term term = new Term();
                    term.exps = termPoly1.exps;
                    term.vars = termPoly1.vars;
                    term.coef = sum;
                    p3.terms.add(term);
                    P2index = p2.terms.indexOf(termPoly2);
                    EleUsed[P2index] = true;
                    break;
                }
                //if this is the last element in poly2
                if (p2.terms.indexOf(termPoly2) == p2.terms.size()-1){
                    Term term = new Term();
                    term.exps = termPoly1.exps;
                    term.vars = termPoly1.vars;
                    term.coef = termPoly1.coef;
                    p3.terms.add(term);
                }
            }
        }
        for (int i=0; i < EleUsed.length; i++) {
            if(!EleUsed[i]){
                Term term = new Term();
                term.exps = p2.terms.get(i).exps;
                term.vars = p2.terms.get(i).vars;
                term.coef = -1 * p2.terms.get(i).coef;
                p3.terms.add(term);
            }
        }
        return p3;
    }

    public polynomial mult(polynomial p2){
        polynomial productPoly = new polynomial(" ");
        for (Term term1: terms) {
            for (Term term2: p2.terms) {
                Term newTerm = term1.multiply(term2); //Term class has multiply method for
                                                      //multiplying two terms.
                productPoly.terms.add(newTerm);
            }
        }
        return productPoly;
    }

    public double Evaluate(Character []variables, double []varValues){
        double value=0;
            for (Term term: terms) {
                    value += term.evaluate(variables, varValues);
            }
        return value;
    }
}
