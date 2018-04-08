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

        while (!players[0].emptyCups() || !players[1].emptyCups()){

            int turn = (i%2)+1;
            playAgain = false;

            System.out.println("Mancala Game");
            System.out.println();
            System.out.println("\t\t  Player 1");
            for (int j=1; j < 7; j++){
                System.out.print("\t"+ j);
            }
            System.out.println();
            System.out.println("\t---------------------");

            players[0].displayCups();
            System.out.println(players[1].getMancala().getStones() +
                    "\t\t\t\t\t\t\t" + players[0].getMancala().getStones());
            players[1].reverseDisplayReverseCups();
            System.out.println("\t---------------------");
            System.out.println("\t\t  Player 2");
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

                    stonesLeft = moveStones(cupChosen, turn-1, stonesLeft);
                }
            } else{
                    stonesLeft = players[turn-1].emptyCup(cupChosen - 1);
                    while (stonesLeft > 0) {

                        stonesLeft = moveStones(cupChosen, turn-1, stonesLeft);
                    }
                }
            System.out.println(playAgain);
                if (!playAgain)
                    i++;
        }
    }

    static int moveStones(int cupChosen, int turn, int stonesLeft) {

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
        //add one to mancala 2
        if (stonesLeft > 0) {
            players[(turn + 1) % 2].addStoneToMancala();
            stonesLeft--;
        }

        return stonesLeft;
    }

    private static void stonesToMancala(int turn) {
        int stonesMove = players[(turn+1)%2].getCup(5-(players[turn].lastStoneIndex)).numberOfStones();
        System.out.println(turn);
        if (turn == 0)
            players[(turn+1)%2].emptyCup(players[turn].lastStoneIndex-1);
        else
            players[(turn+1)%2].emptyCup(players[turn].lastStoneIndex+1);
        players[turn].mancala.addStones(stonesMove);
    }
}
