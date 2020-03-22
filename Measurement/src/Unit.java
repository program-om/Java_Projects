import java.util.ArrayList;

public class Unit {
    private ArrayList<UnitItem> numerator = new ArrayList<UnitItem>();
    private ArrayList<UnitItem> denominator = new ArrayList<UnitItem>();

    public Unit(){

    }

    public Unit(String uItem){
        this.numerator.add(new UnitItem(1, uItem));
    }

    public Unit(ArrayList<UnitItem> nume, ArrayList<UnitItem> denom){
        this.numerator = nume;
        this.denominator = denom;
    }

    public ArrayList<UnitItem> getNumerator(){
        return this.numerator;
    }

    public ArrayList<UnitItem> getDenominator() {
        return denominator;
    }

    Unit multiplyUnit(Unit unit1, Unit unit2){
        ArrayList<UnitItem> resultNumerator = concatenate(unit1.getNumerator(), unit2.getNumerator());
        ArrayList<UnitItem> resultDenominator = concatenate(unit1.getDenominator(), unit2.getDenominator());
        Unit resultUnit = new Unit(resultNumerator, resultDenominator);
        return  resultUnit;
    }

    Unit divideUnit(Unit unit1, Unit unit2){
        ArrayList<UnitItem> resultNumerator = concatenate(unit1.getNumerator(), unit2.getDenominator());
        ArrayList<UnitItem> resultDenominator = concatenate(unit1.getDenominator(), unit2.getNumerator());
        Unit resultUnit = new Unit(resultNumerator, resultDenominator);
        return  resultUnit;
    }


    public boolean equals(Unit unit){
        //check if numerators are equal
        for (UnitItem ui: this.numerator) {
            boolean found = false;
            for (UnitItem ui2: unit.getNumerator()) {
                if(ui.equals(ui2)){
                    found = true;
                    break;
                }
            }
            if(!found) return false;
            found = false;
        }
        //check if denominators are equal
        for (UnitItem ui: this.denominator) {
            boolean found = false;
            for (UnitItem ui2: unit.getDenominator()) {
                if(ui.equals(ui2)){
                    found = true;
                    break;
                }
            }
            if(!found) return false;
            found = false;
        }
        return true;
    }

    ArrayList<UnitItem> concatenate(ArrayList<UnitItem> u1, ArrayList<UnitItem> u2){
        ArrayList<UnitItem> resultUnit = new ArrayList<UnitItem>();
        int i;
        //append the first Unit which has unique UnitItems
        for(i = 0; i < u1.size(); i++){
            resultUnit.add(u1.get(i));
        }
        for(i = 0; i < u2.size(); i++){
            int index = -1;
            for(int j=0; j < resultUnit.size(); j++){
                if (resultUnit.get(j).getUnit().equals(u2.get(i).getUnit())){
                    index = j;
                    break;
                }
            }
            if(index < 0){
                resultUnit.add(u2.get(i));
            }else {
                resultUnit.get(index).incrementQuantityBy(u2.get(i).getQuantity());
            }

        }
        return  resultUnit;
    }

    public void cancelOut(){
        int numLen = this.numerator.size();
        int denoLen = this.denominator.size();
        for (int i = 0; i < numLen; i++) {
            UnitItem numUnit = this.numerator.get(i);
            for (int j = 0; j < denoLen; j++) {
                UnitItem denoUnit = this.denominator.get(j);
                if(numUnit.getUnit().equals(denoUnit.getUnit())){
                    int diff = numUnit.getQuantity() - denoUnit.getQuantity();
                    if(diff == 0){
                        //eliminate both
                        numUnit.setQuantity(0);
                        denoUnit.setQuantity(0);
                    }else if(diff >= 1){
                        //eliminate denominator unit at j
                        denoUnit.setQuantity(0);
                        numUnit.setQuantity(diff);
                    }else{
                        //eliminate numerator unit at i
                        numUnit.setQuantity(0);
                        denoUnit.setQuantity(Math.abs(diff));
                    }
                    break;
                }
            }
        }
    }

    public String toString(){
        String resultString = "";
        int unitQuantity;
        for(int i=0; i < this.numerator.size(); i++){
            unitQuantity = this.numerator.get(i).getQuantity();
            if(i != 0 && unitQuantity >= 1){
                resultString += " ";
            }
            resultString += this.numerator.get(i).toString();
        }
        int denoLen = this.denominator.size();
        if(denoLen > 0){
            resultString += " / ";
        }
        for(int i=0; i < denoLen; i++){
            unitQuantity = this.denominator.get(i).getQuantity();
            if(i != 0 && unitQuantity >= 1){
                resultString += " ";
            }
            resultString += this.denominator.get(i).toString();
        }
        return resultString;
    }
}
