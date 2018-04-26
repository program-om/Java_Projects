class Mancala {
    private int Stones;

    Mancala(){
        Stones = 0;
    }

    void addStones(int stones) {
        Stones += stones;
    }

    int getStones(){
        return Stones;
    }

    void addStone(){
        Stones++;
    }
}
