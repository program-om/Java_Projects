class Cup {
    private int StonesNum;

    Cup(){
        StonesNum = 4;
    }

    void addStone(){
        StonesNum++;
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
