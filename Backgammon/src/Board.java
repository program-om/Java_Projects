import java.util.ArrayList;
import java.util.Scanner;

class Board {

    private Point[] points = new Point[24];
    private Player player1, player2;
    private Dice dice;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    Board() {
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

        player1 = new Player("white");
        player2 = new Player("red");
        dice = new Dice();

        setUp();

        boolean done = false;
        int turn = 0, i = 0;
        int moves = 0;
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> movesList = new ArrayList<>();

        System.out.println(ANSI_RED + "Red checkers move clockwise" + ANSI_RESET);
        System.out.println("White checkers move counter-clockwise");
        System.out.println();

        displayBoard();
        Player currentPlayer = player1;

        while(!done){

            if (moves == 0){
                dice.throwDice();
                int dice1 = dice.dice1;
                int dice2 = dice.dice2;
                System.out.println(ANSI_PURPLE + "Dice thrown: dice1 = " + dice1 + "  and dice2 = " + dice2 + ANSI_RESET);

                if (dice.doubleDice()){
                    moves = 4;
                    for (int j = 0; j < 4; j++) {
                        movesList.add(dice1);
                    }
                } else{
                    moves = 2;
                    movesList.add(dice1);
                    movesList.add(dice2);
                }

                //determine which player is turn now!!
                if ((turn % 2) == 0){//palyer1 turn
                    currentPlayer = player1;
                    System.out.println(ANSI_PURPLE + "Player 1 turn -> " + currentPlayer.getColor() + ANSI_RESET);
                }else{ //player 2 turn
                    currentPlayer = player2;
                    System.out.println(ANSI_PURPLE + "Player 2 turn -> " + currentPlayer.getColor() + ANSI_RESET);
                }
                turn++;
            }

            System.out.print(ANSI_PURPLE + "Moves left = " + moves);
            System.out.println(" : Dice value = " + movesList.get(0));
            System.out.print("Choose a Point to move a checker from(1 to 24): " + ANSI_RESET);
            int chosenPoint = input.nextInt();
            input.nextLine();
            System.out.println();

            if (chosenPoint < 1 || chosenPoint > 24){
                System.out.println(ANSI_YELLOW + "ERROR: point position is not valid!" + ANSI_RESET);
                continue;
            }


            if (!currentPlayer.hasBar()){
                if (currentPlayer.getColor().equals(player1.getColor())){
                    play(chosenPoint-1, (chosenPoint + movesList.get(0))-1, currentPlayer);

                } else{
                    play(chosenPoint-1, (chosenPoint - movesList.get(0))-1, currentPlayer);
                }
            }else{ //if the current player has checkers in the Bar
                if (dice.doubleDice()){

                }else{//two different dices
                    if (currentPlayer == player1){//white checkers
                        placeable(24-movesList.get(0), currentPlayer);
                        placeable(24-movesList.get(1), currentPlayer);
                    }else{//red checkers

                    }
                }

                System.out.print("");
                int chosenDice = input.nextInt();
                input.nextLine();

                if (chosenDice != movesList.get(0) || chosenDice != movesList.get(1)){
                    System.out.println(ANSI_YELLOW + "ERROR: choose one of the dice values please!" + ANSI_RESET);
                    continue;
                }

                //choose a dice number to use
                //move the top of the bar to that point

            }

            movesList.remove(0);

            displayBoard();
            moves--;
        }

    }

    String changeColor(String color){
        if (color.equals("red")){
            return ANSI_RED + color + ANSI_RESET;
        }else{
            return ANSI_WHITE + color + ANSI_RESET;
        }
    }

    private int pointIndex(int i, Player player){//return the index of the point corresponding to the player
        if (player == player1){//white
            return (23-i);
        }else{//red
            return i;
        }
    }

    private void addChecker(int point, Checker ch) {
        points[point - 1].addChecker(ch);
    }

    private void setUp() {

        addCheckerToPoint(2, 1, player1.color);
        addCheckerToPoint(5, 12, player1.color);
        addCheckerToPoint(3, 17, player1.color);
        addCheckerToPoint(5, 19, player1.color);
        //---
        addCheckerToPoint(2, 24, player2.color);
        addCheckerToPoint(5, 13, player2.color);
        addCheckerToPoint(3, 8, player2.color);
        addCheckerToPoint(5, 6, player2.color);
    }

    private void addCheckerToPoint(int amount, int pointNum, String color){
        for (int i = 0; i < amount; i++) {
            addChecker(pointNum, new Checker(color));
        }
    }

    private void displayBoard() {
        String color, color2;
        System.out.println("Red's Home \t\t\t\t\t\tWhite's Home");
        System.out.println("============================================");
        for (int i = 0; i < 12; i++) {

            if (!points[i].checkers.empty()){
                color = (points[i].checkers.get(0)).getColor();
                color = changeColor(color);
            } else{
                color = "None";
            }

            if (i < 9)
                System.out.print(i+1 + " | " + points[i].checkers.size() + " " + color);
            else
                System.out.print(i+1 + "| " + points[i].checkers.size() + " " + color);
            System.out.print("\t\t\t\t\t\t");
            //System.out.println(i);
            int secondPoint = 23-i;

            if (i <6) {
                if (!points[secondPoint].checkers.empty()) {
                    color2 = (points[secondPoint].checkers.get(0)).getColor();
                    color2 = changeColor(color2);
                } else {
                    color2 = "None";
                }
                System.out.print(points[secondPoint].checkers.size() + " " + color2 + "\t|"+ (24-i));
            } else {
                if (!points[secondPoint].checkers.empty()) {
                    color2 = (points[secondPoint].checkers.get(0)).getColor();
                    color2 = changeColor(color2);
                } else {
                    color2 = "None";
                }
                System.out.print(points[secondPoint].checkers.size() + " " + color2 + "\t|"+ (24-i));
            }

            System.out.println();
            if (i == 5) {
                System.out.println("============================================");
                if (!player1.Bar.empty()) {
                    System.out.println(player1.Bar.size() + " " + changeColor(player1.Bar.peek().getColor()));
                }
                if (!player2.Bar.empty()){
                    System.out.println(player2.Bar.size() + " " + changeColor(player2.Bar.peek().getColor()));
                }
                System.out.println("============================================");

            }
        }
        System.out.println("============================================");
        System.out.println();

    }

    private void play(int from, int to, Player player) {
        //check the condition of the point that we will move to

        boolean sameColor = false;
        if (!points[to].checkers.empty()){
            String color = (points[to].checkers.peek()).getColor();
            sameColor = (color.equals(player.getColor()));
        }

        //is it empty? //has same checkers?
        if (points[to].emptyPoint() || sameColor){
            moveChecker(from, to);

        }else if(points[to].checkers.size() == 1){ //has one different checker?
            //move the checker to the Bar
            if (player == player1){
                player2.Bar.push(points[to].checkers.pop());
            }else {
                player1.Bar.push(points[to].checkers.pop());
            }
            moveChecker(from, to);
            //add a checker to
        }else{ //has more than one different checker?
            System.out.println(ANSI_YELLOW + "ERROR: This point is occupied by another player!" + ANSI_RESET);

        }
    }

    private void moveFromBar(int to, Player player){

        boolean sameColor = false;
        if (!points[to].checkers.empty()){
            String color = (points[to].checkers.peek()).getColor();
            sameColor = (color.equals(player.getColor()));
        }

        //is it empty? //has same checkers?
        if (points[to].emptyPoint() || sameColor){
            points[to].checkers.push(player.Bar.pop());

        }else if(points[to].onlyOneChecker()){ //has one different checker?
            //move the checker to the Bar
            if (player == player1){
                player2.Bar.push(points[to].checkers.pop());
            }else {
                player1.Bar.push(points[to].checkers.pop());
            }
            points[to].checkers.push(player.Bar.pop());
            //add a checker to
        }else{ //has more than one different checker?
            System.out.println("");
        }

    }

    private boolean placeable(int to, Player player){

        boolean sameColor = false;
        if (!points[to].checkers.empty()){
            String color = (points[to].checkers.peek()).getColor();
            sameColor = (color.equals(player.getColor()));
        }

        //is it empty? //has same checkers?
        return  (points[to].emptyPoint() || points[to].checkers.size() == 1 || sameColor);

    }

    private void moveChecker(int from, int to){
        points[to].checkers.push(points[from].checkers.pop());
    }

}
