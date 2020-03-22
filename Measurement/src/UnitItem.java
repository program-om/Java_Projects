public class UnitItem {
    int quantity;
    String unit;

    UnitItem(int q, String u){
        this.quantity = q;
        this.unit = u;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getUnit(){
        return this.unit;
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public void incrementQuantityBy(int incrVal){
        this.quantity = this.quantity + incrVal;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }

    public String toString(){
        if(this.quantity == 1){
            return this.unit + "";
        }else if(this.quantity > 1){
            return this.unit + "^" + this.quantity;
        }
        return "";
    }

    public boolean equals(UnitItem ui) {
        return this.unit.equals(ui.getUnit());
    }
}
