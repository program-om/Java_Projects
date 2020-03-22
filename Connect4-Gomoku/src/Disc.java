enum Color { RED, YELLOW, NoColor }

public class Disc {
    private Color color;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    Disc(Color color){
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
            return "O";
        }
    }
}
