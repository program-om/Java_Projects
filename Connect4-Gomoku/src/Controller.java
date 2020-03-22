import java.util.Scanner;

public class Controller {
    Controller(){
        pickAGame();
    }

    private void pickAGame(){
        Scanner input = new Scanner(System.in);
        System.out.print("Which game would like to play (Gomoku or Connect4): ");
        String line = input.nextLine();
        if (line.equals("Gomoku") || line.equals("gomoku")){
            new Gomoku();
        } else if (line.equals("Connect4") || line.equals("connect4")){
            new Connect4();
        }else{
            System.out.println("Invalid input!");
            pickAGame();
        }
    }
}
