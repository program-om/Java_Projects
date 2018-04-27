class Player {

    private Cup[] cups;
    public int lastStoneIndex = -1;
    Mancala mancala;

    Player(){
        mancala = new Mancala();
        cups = new Cup[6];

        for (int i=0; i < cups.length; i++){
            cups[i] = new Cup();
        }
    }

    public Mancala getMancala() {
        return mancala;
    }

    void addStoneToMancala(){
        mancala.addStone();
    }

    public Cup getCup(int i) {
        return cups[i];
    }

    boolean emptyCups(){
        //return true if all the cups are empty
        //       false if not
        for (Cup each:
             cups) {
            if (!each.IsEmpty()){ //if there is a cup that is not empty then return false.
                return false;
            }
        }
        return true;
    }

    int emptyCup(int stoneNum){ //make a cup empty and get all the stones from it.
        return cups[stoneNum].makeEmpty();
    }

    void displayCups(){
        System.out.print("\t");
        for (Cup each:
             cups) {
            System.out.print(each.numberOfStones() + "\t");
        }
        System.out.println();
    }

    void reverseDisplayReverseCups(){
        System.out.print("\t");
        for (int i=cups.length-1; i >= 0; i--) {
            System.out.print(cups[i].numberOfStones() + "\t");
        }
        System.out.println();
    }

    int distributeStones(int n, int index, boolean check){
        int i;

        for(i=index-2; i >= 0; i--){
            if (n > 0) {
                if (n == 1 && cups[i].numberOfStones() == 0 && check){
                    cups[i].addStone();
                    n--;
                    lastStoneIndex = i;
                } else {
                    cups[i].addStone();
                    n--;
                }
            } else{
                return 0;
            }
        }

        return n;
    }

    int getMancalaStones(){
        return mancala.getStones();
    }

    void freeCups(){//free all the cups and move stones to player's mancala
        for(int i=0; i < 6; i++){
            mancala.addStones(emptyCup(i));
        }
    }

}
