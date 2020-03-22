public class Connect4Column extends Column {

    private int numFilledGrids;

    Connect4Column(){
        for (int i = 0; i < 6; i++) {
            grids.add(new Grid());
        }
        numFilledGrids = 0;
    }



    public int getNumFilledGrids() {
        return numFilledGrids;
    }

    public int dropDisc(Disc disc){
        if (isFull()) return -1;
        this.grids.get(this.numFilledGrids).occupy(disc);
        this.numFilledGrids++;
        return numFilledGrids;
    }

    public boolean isFull() {
        return this.numFilledGrids == 6;
    }

    public Grid top(){
        return this.grids.get(this.numFilledGrids);
    }
}
