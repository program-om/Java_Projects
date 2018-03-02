import java.util.Scanner;

public class Main {
    private static Player[] players = new Player[2];
    static boolean playAgain;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        int i = 0;

        while (!players[0].EmptyCups() || !players[1].EmptyCups()){

            int turn = (i%2)+1;
            playAgain = false;

            players[0].displayCups();
            System.out.println(players[1].getMancala().getStones() +
                    "\t\t\t\t\t\t\t" + players[0].getMancala().getStones());
            players[1].reverseDisplayReverseCups();
            System.out.println();
            System.out.println("Player " + turn + " :");
            System.out.print("Choose one of the cups from 1 to 6 : ");
            int cupChosen = scanner.nextInt();
            scanner.nextLine();//this will get rid of the \n

            //do the work
            int stonesLeft;
            if(turn == 1) {
                stonesLeft = players[turn-1].emptyCup(cupChosen - 1);
                while (stonesLeft > 0) {

                    stonesLeft = h(cupChosen, turn-1, stonesLeft);
                }
            } else{
                    stonesLeft = players[turn-1].emptyCup(cupChosen - 1);
                    while (stonesLeft > 0) {

                        stonesLeft = h(cupChosen, turn-1, stonesLeft);
                    }
                }
            System.out.println(playAgain);
                if (!playAgain)
                    i++;
        }
    }

    static int  h(int cupChosen, int turn, int stonesLeft) {

        //int stonesLeft;
        stonesLeft = players[turn].distributeStones(stonesLeft, cupChosen, true);
        if (players[turn].lastStoneIndex != -1){
            stonesToMancala(turn);
        }
        //add one to mancala 1
        if (stonesLeft > 0){
            players[turn].addStoneToMancala();
            stonesLeft--;

            if (stonesLeft == 0)
                playAgain = true;
        }
        stonesLeft = players[(turn+1)%2].distributeStones(stonesLeft, 0, false);
        //add one to mancal 2
        if (stonesLeft > 0) {
            players[(turn + 1) % 2].addStoneToMancala();
            stonesLeft--;
        }

        return stonesLeft;
    }

    private static void stonesToMancala(int turn) {
        int stonesMove = players[(turn+1)%2].getCup(5-(players[turn].lastStoneIndex)).numberOfStones();
        System.out.println(stonesMove);
        if (turn == 1)
            players[(turn+1)%2].emptyCup(players[turn].lastStoneIndex-1);
        else
            players[(turn+1)%2].emptyCup(players[turn].lastStoneIndex+1);
        players[turn].mancala.addStones(stonesMove);
    }
}
