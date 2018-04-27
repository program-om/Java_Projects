import java.util.Stack;

class Player {

    Stack<Checker> Bar = new Stack<>();

    public String getColor() {
        return color;
    }

    String color;


    Player(String color) {
        this.color = color;
    }

    boolean hasBar() {
        if (!Bar.empty()) {
            return Bar.peek().getColor().equals(color);
        } else {
            return false;
        }
    }

}
