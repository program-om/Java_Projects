import java.util.Scanner;

public class Game {
    Game(){
        GameMap gameMap = new GameMap();
        Player player = new Player(gameMap.getRooms().get(0));
        Command command = new Command(player);
        String commandInput = "";
        Scanner in = new Scanner(System.in);
        while(!command.equals("exit")){
            System.out.print("> ");
            commandInput = in.nextLine();
            command.execute(commandInput);
            if (player.getEnergy() > 100 || player.getEnergy() <= 0) break;
        }

        if (player.getEnergy() > 100){
            System.out.println("you have eaten well. Now, you can continue your trip");
        }else{
            System.out.println("you died! RIP");
        }
    }
}
