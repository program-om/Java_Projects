import java.util.Stack;

class Point {
    Stack<Checker> checkers = new Stack<>();

    public Point(){

    }

    void addChecker(Checker ch){
        checkers.add(ch);
    }

    boolean onlyOneChecker(){
        return checkers.size() == 1;
    }

    boolean emptyPoint(){
        return checkers.empty();
    }
}
