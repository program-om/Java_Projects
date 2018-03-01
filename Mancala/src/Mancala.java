public class Mancala {
    public void addStones(int stones) {
        Stones += stones;
    }

    private int Stones;

    int getStones(){
        return Stones;
    }

    Mancala(){
        Stones = 0;
    }

    void addStone(){
        Stones++;
    }
}
