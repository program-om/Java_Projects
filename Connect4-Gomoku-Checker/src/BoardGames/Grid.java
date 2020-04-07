package BoardGames;

public class Grid {
    private boolean occupied;
    private Disc disc = new Disc(Color.NoColor);

    public Grid(){
        occupied = false;
    }

    public Disc getDisc() {
        return disc;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy(Disc disc){
        occupied = true;
        this.disc = disc;
    }

    public String toString(){
        return disc.toString();
    }

    public Disc pop(){
        Disc d = disc;
        disc = new Disc(Color.NoColor);
        occupied = false;
        return d;
    }
}
