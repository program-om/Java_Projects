public class Cup {

    Cup(int n){
        StonesNum = n;
    }
    public void setStonesNum(int stonesNum) {
        StonesNum = stonesNum;
    }

    private int StonesNum;

    Cup(){
        StonesNum = 5;
    }

    void addStone(){
        StonesNum++;
    }

    void removeStone(){
        StonesNum--;
    }

    int numberOfStones(){
        return StonesNum;
    }

    int makeEmpty(){ //set number of stones to 0 and return the number of stones the cup had
        int temp = StonesNum;
        StonesNum = 0;
        return temp;
    }

    boolean IsEmpty(){
        if (StonesNum == 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
