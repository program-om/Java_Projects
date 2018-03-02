import java.util.ArrayList;

public class Player {
    public Mancala getMancala() {
        return mancala;
    }

    void addStoneToMancala(){
        mancala.addStone();
    }

     Mancala mancala;

    public Cup getCup(int i) {
        return cups[i];
    }

    private Cup[] cups;
    public int lastStoneIndex = -1;

    Player(){
        //cups = new Cup[6];
        mancala = new Mancala();
        cups = new Cup[6];
        for (int i=0; i < cups.length; i++){
            cups[i] = new Cup();
        }
    }

    boolean EmptyCups(){
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

    void addStoneToCup(int stoneNum){
        cups[stoneNum].addStone();
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

    void displayMancala(){
        System.out.print(mancala.getStones());
    }

    int distributeStones(int n, int index, boolean check){
        int i;
        for(i=index; i < cups.length; i++){
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
}
