public class Grid {
    private boolean occupied;
    private Disc disc = new Disc(Color.NoColor);

    Grid(){
        this.occupied = false;
    }

    public Disc getDisc() {
        return this.disc;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void occupy(Disc disc){
        this.occupied = true;
        this.disc = disc;
    }

    public String toString(){
        return this.disc.toString();
    }
}
