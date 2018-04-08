public class Mancala {
    public Mancala(){
        Stones = 0;
    }

    public void addStones(int stones) {
        Stones += stones;
    }

    private int Stones;

    int getStones(){
        return Stones;
    }

    void addStone(){
        Stones++;
    }
}
