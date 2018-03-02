import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Term {
        int coef = 1;
        ArrayList<Character> vars = new ArrayList<>();
        ArrayList<Integer> exps = new ArrayList<>();

        public Term(Term another){//Danger!!!!
            another = this;
        }//not used

        public Term() {

        }

        public Term nextTermFrom(PushbackInputStream s) {
            int sign = 1;


            try {
                if (s.available() == 0) //when s is all parsed
                    return null;

                char ch = (char) s.read();//get the next char

                if (ch == '-') {
                    sign = -1;
                    ch = (char) s.read();
                } else if (ch == '+') {
                    ch = (char) s.read();
                }

                if (!Character.isDigit(ch)) {
                    coef = 1;
                } else {
                    coef = 0;
                    while (Character.isDigit(ch)) {
                        coef = coef * 10 + ch - '0';
                        ch = (char) s.read();
                    }
                }

                coef *= sign;

                while (Character.isLowerCase(ch)) {
                    vars.add(ch);
                    ch = (char) s.read();
                    int exp;

                    if (ch == '^') {
                        exp = 0;
                        ch = (char) s.read();
                        while (Character.isDigit(ch)) {
                            exp = exp * 10 + ch - '0';
                            ch = (char) s.read();
                        }
                    } else {
                        exp = 1;
                    }

                    exps.add(exp);
                }
                s.unread(ch);
                // At this point the variables hold the following information about the term:
                // coef - The coefficient of the term
                // vars - The variables in the term
                // exps - The exponent of the corresponding variable from vars
            } catch (IOException e) {
                return null;
            }
            return  null;
        }

        public String to_String() {
            String term = ""+ coef;
            for (int i=0; i < vars.size(); i++) {//print the vars and exponents
                if(exps.get(i) == 1)
                    term += vars.get(i); //should use StringBuilder
                else
                    term += vars.get(i)+"^"+exps.get(i);
            }
            return term;
        }

        public boolean equalTerm(Term term2){
            //fist call equalLists to check weather the two list have the same
            //variables or NOT
            //then check if the corresponding elements have the same exponent
            //
            //use vars.lastIndexOf() to find the position of the element...
            //if everything went well..return true. Otherwise return false

            if(!equalLists(this.vars, term2.vars))
                return false;

            for (int i=0; i < vars.size(); i++) {
                int index = term2.vars.lastIndexOf(vars.get(i));
                if (! (exps.get(i).intValue() == term2.exps.get(index).intValue()) )
                    return false;
            }

            return true;
        }

        private boolean equalLists(ArrayList<Character> one, ArrayList<Character> two){
            if (one == null && two == null){
                return true;
            }

            if((one == null && two != null)
                    || (one != null && two == null)
                    || (this.vars.size() != two.size())){
                return false;
            }

            //to avoid messing the order of the lists we will use a copy
            //as noted in comments by A. R. S.
            one = new ArrayList<Character>(one);
            two = new ArrayList<Character>(two);

            Collections.sort(one);
            Collections.sort(two);
            return one.equals(two);
        }

        public Term multiply(Term term2){//takes two terms and return the product term
            Term productTerm = new Term();
            productTerm.coef = coef*term2.coef;
            for (int i=0; i<vars.size(); i++) {
                if(term2.vars.contains(vars.get(i))){
                    int position = term2.vars.lastIndexOf(vars.get(i));
                    productTerm.vars.add(vars.get(i));
                    productTerm.exps.add(exps.get(i)+term2.exps.get(position));
                } else{
                    productTerm.vars.add(vars.get(i));
                    productTerm.exps.add(exps.get(i));
                }
            }

            for (int i=0; i<term2.vars.size(); i++) {
                if(!vars.contains(term2.vars.get(i))){
                    productTerm.vars.add(term2.vars.get(i));
                    productTerm.exps.add(term2.exps.get(i));
                }
            }
            return productTerm;
        }

        public double evaluate(Character []variables, double []varValues){
            double TermValue=1;
            for (int i=0; i<vars.size(); i++){
                //get the index of where the variable is in 'variables'
                int index = Arrays.asList(variables).indexOf(vars.get(i));;
                if(index < 0)
                    throw new ArithmeticException("There is a variable in the polynomial " +
                            "has no value");
                //get the value of the exponent
                double value = varValues[index];
                TermValue *= Math.pow(value, exps.get(i));
            }
            TermValue *= coef;
            return TermValue;
        }

    }
