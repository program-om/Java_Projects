package BoardGames;

import java.util.Scanner;

import Checkers.*;
import Connect4.*;
import Gomoku.*;
import Chess.Chess;

public class Controller {
    Controller(){
        pickAGame();
    }

    private void pickAGame(){
        Scanner input = new Scanner(System.in);
        System.out.print("Which game would like to play (Gomoku, Connect4.Connect4, Checkers, or Chess): ");
        String line = input.nextLine();
        if (line.equals("Gomoku") || line.equals("gomoku")){
            new Gomoku();
        } else if (line.equals("Connect4") || line.equals("connect4")){
            new Connect4();
        } else if (line.equals("Checkers") || line.equals("checkers")){
            new Checker();
        } else if (line.equals("Chess") || line.equals("chess")) {
            new Chess();
        } else{
            System.out.println("Invalid input!");
            pickAGame();
        }
    }
}
