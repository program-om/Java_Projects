import java.util.Scanner;

class Board {
    private Player[] players = new Player[2];
    private boolean playAgain;

    Board(){

        Scanner scanner = new Scanner(System.in);

        players[0] = new Player();
        players[1] = new Player();
        int i = 0;

        while (!players[0].emptyCups() && !players[1].emptyCups()){

            int turn = (i%2)+1;

            displayBoard(turn);
            if (playAgain)
                System.out.println("Play Again!");
            System.out.print("Choose one of the cups from 1 to 6 : ");
            int cupChosen = scanner.nextInt();
            scanner.nextLine();//this will get rid of the \n
            if (cupChosen < 1 || cupChosen > 6){
                System.out.println("The chosen cup should be in the range 1 to 6!");
                continue;
            }
            playAgain = false;

            //do the work
            int stonesLeft;
            if(turn == 1) {
                stonesLeft = players[turn-1].emptyCup(cupChosen - 1);
                if (stonesLeft == 0){
                    System.out.println("This cup is empty!");
                    playAgain = true;
                }

                while (stonesLeft > 0) {

                    stonesLeft = moveStones(cupChosen, turn-1, stonesLeft);
                }
            } else{
                stonesLeft = players[turn-1].emptyCup(cupChosen - 1);
                if (stonesLeft == 0){
                    System.out.println("This cup is empty!");
                    playAgain = true;
                }

                while (stonesLeft > 0) {

                    stonesLeft = moveStones(cupChosen, turn-1, stonesLeft);
                }
            }
            //System.out.println(playAgain);
            if (!playAgain){
                i++;
            }

        }

        //capture all the stones
        players[0].freeCups();
        players[1].freeCups();
        //the winner
        System.out.println("player 1 stones = " + players[0].getMancalaStones());
        System.out.println("player 2 stones = " + players[1].getMancalaStones());

        if (players[0].getMancalaStones() > players[1].getMancalaStones()){
            System.out.println("Player 1 won!");
        } else if(players[0].getMancalaStones() < players[1].getMancalaStones()){
            System.out.println("Player 2 won!");
        } else{
            System.out.println("Players tie!");
        }
    }

    private void displayBoard(int turn) {
        System.out.println();
        System.out.println("\t\t  Player 1");
        for (int j=1; j < 7; j++){
            System.out.print("\t"+ j);
        }
        System.out.println();
        System.out.println("<------------<--------------<");
        players[0].displayCups();
        System.out.println(players[0].getMancala().getStones() +
                "\t\t\t\t\t\t\t" + players[1].getMancala().getStones());
        players[1].reverseDisplayReverseCups();
        System.out.println(">------------>-------------->");
        for (int j=6; j > 0; j--){
            System.out.print("\t"+ j);
        }
        System.out.println();
        System.out.println("\t\t  Player 2");
        System.out.println();
        System.out.println("Player " + turn + " :");
    }

    private int moveStones(int cupChosen, int turn, int stonesLeft) {

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
        stonesLeft = players[(turn+1)%2].distributeStones(stonesLeft, 7, false);
        //add one to mancala 2
        if (stonesLeft > 0) {
            players[(turn + 1) % 2].addStoneToMancala();
            stonesLeft--;
        }

        return stonesLeft;
    }

    private void stonesToMancala(int turn) {
        int stonesMove = players[(turn+1)%2].getCup(5-(players[turn].lastStoneIndex)).numberOfStones();

        if (turn == 0)
            players[(turn+1)%2].emptyCup(5 - players[turn].lastStoneIndex);
        else
            players[(turn+1)%2].emptyCup(5 - players[turn].lastStoneIndex);
        players[turn].mancala.addStones(stonesMove);
    }

}
