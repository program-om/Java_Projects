package BoardGames;


public class Disc {
    protected Color color;
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_RESET = "\u001B[0m";

    public Disc(){ }

    public Disc(Color color){
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean equals(Disc disc) {
        return disc.getColor() == this.color;
    }

    public boolean equals(Color color) {
        return color == this.color;
    }

    public String toString(){
        if(this.color == Color.RED){
            return ANSI_RED + "R" + ANSI_RESET;
        }else if(this.color == Color.YELLOW){
            return ANSI_YELLOW + "Y" + ANSI_RESET;
        }else{
            return "·";
        }
    }

}
