public class Command {
    Player player;

    Command(Player player){
        setPlayer(player);
    }

    public void execute(String command){
        String[] commandParts = command.split(" ");
        if(commandParts[0].equals("go")){
            if (validateCommand(2, commandParts)) go(commandParts[1]);
        } else if(commandParts[0].equals("take")){
            if (validateCommand(2, commandParts)) take(commandParts[1]);
        } else if(commandParts[0].equals("drop")){
            if (validateCommand(2, commandParts)) drop(commandParts[1]);
        } else if(commandParts[0].equals("examine")){
            if (commandParts.length == 1 || validateCommand(2, commandParts)){
                examine(commandParts);
            }
        } else if(commandParts[0].equals("hunt")){
            if (validateCommand(2, commandParts)) hunt(commandParts[1]);
        } else if(commandParts[0].equals("cook")){
            if (validateCommand(2, commandParts)) cook(commandParts[1]);
        } else if(commandParts[0].equals("eat")){
            if (validateCommand(2, commandParts)) eat(commandParts[1]);
        } else if(commandParts[0].equals("inventory")){
            if (validateCommand(1, commandParts)) inventory();
        } else {
            System.out.println("Sorry I don't know that");
        }
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private boolean validateCommand(int len, String[] commandParts){
        if (commandParts.length != len){
            System.out.println("I don't understand that");
            return false;
        }else return true;
    }

    private void cook(String objectName){
        GameObject obj = this.player.findObject(objectName);
        GameObject fire = this.player.getCurrentRoom().findObject("fire");
        if(!obj.getName().equals("")){
            if(objectName.equals("deer") || objectName.equals("bird")){
                if(!fire.getName().equals("")) {
                    obj.setCooked(true);
                    System.out.println(obj.getName() + " cooked.");
                }else {
                    System.out.println("There is no fire here");
                }
            }else{
                System.out.println("You can't cook that");
            }
        } else{
            System.out.println("You don't have that");
        }
        //check if player has an object to cook
        //check if there is a fire in the room
        //change the state of the object to eatable
    }

    private void drop(String object){
        Room playerRoom = this.player.getCurrentRoom();
        GameObject obj = this.player.findObject(object);
        if(!obj.getName().equals("")){
            obj.move(this.player, playerRoom);
            System.out.println("Done.");
        }else{
            System.out.println("I don't see that");
        }
        //check if player has that object
        //  true:
        //      get the object from the player
        //      give it to the room
        //  false
        //      display message of not having the object
    }

    private void eat(String objectName){
        GameObject obj = this.player.findObject(objectName);
        if(!obj.getName().equals("")){
            if(obj.isCooked()){
                this.player.removeObject(obj);
                if(obj.getName().equals("bird")){
                    this.player.incrementEnergy(11);
                    System.out.println("eating bird increase your energy level but you need to hunt something bigger to continue the trip");
                }else{
                    this.player.incrementEnergy(111);
                }
            }else{
                System.out.println("You can't eat that");
            }
        } else{
            System.out.println("You don't have that");
        }
    }

    private void examine(String[] commandParts){
        if(commandParts.length == 1){
            player.getCurrentRoom().displayShortDescription();
            player.getCurrentRoom().displayLongDescription();
        }else if (commandParts.length == 2){
            examine(commandParts[1]);
        }else{
            System.out.println("I don't understand that");
        }
    }

    private void examine(String objectName){
        if (objectName.equals("me")){
            this.player.examine();
            return;
        }
        GameObject obj = player.getCurrentRoom().findObject(objectName);
        if(!obj.getName().equals("")){
            obj.examine();
        } else{
            //check if the player possess the object
            obj = player.findObject(objectName);
            if(!obj.getName().equals("")){
                obj.examine();
            } else{
                System.out.println("I can't see that");
            }
        }
    }

    private void go(String direction){
        if(direction.equals("north") || direction.equals("n")){
            player.move("n");
        } else if(direction.equals("south") || direction.equals("s")){
            player.move("s");
        } else if(direction.equals("west") || direction.equals("w")){
            player.move("w");
        } else if(direction.equals("east") || direction.equals("e")){
            player.move("e");
        } else if(direction.equals("northwest") || direction.equals("nw")){
            player.move("nw");
        } else if(direction.equals("northeast") || direction.equals("ne")){
            player.move("ne");
        } else if(direction.equals("southwest") || direction.equals("sw")){
            player.move("sw");
        } else if(direction.equals("southeast") || direction.equals("se")){
            player.move("se");
        } else {
            System.out.println("Can't go that direction");
        }
    }

    private void hunt(String objectName){
        GameObject obj = this.player.getCurrentRoom().findObject(objectName);
        GameObject riffle = this.player.findObject("riffle");
        if(riffle.getName().equals("")){
            System.out.println("You do not have weapon to hunt");
            return;
        }
        if(!obj.getName().equals("")){
            if(objectName.equals("deer") || objectName.equals("bird")){
                int minHuntScore;
                if(objectName.equals("deer")){
                    minHuntScore = 35;
                }else{
                    minHuntScore = 80;
                }
                double n = (Math.random() * 100) + 30;
                double huntScore = n * (player.getEnergy()/32.0);
                if (huntScore > minHuntScore){
                    obj.setTakeable(true);
                    obj.setDescription("Killed "+objectName+", but is not cooked yet.");
                    System.out.println("you have hunted " + objectName + " successfully");
                }else{
                    //remove object from the room
                    player.getCurrentRoom().removeObject(obj);
                    System.out.println(objectName + " escaped!");
                }
            }else{
                System.out.println("You can't hunt that");
            }
        } else{
            System.out.println("I don't see that");
        }
    }

    private void inventory(){
        this.player.displayInventory();
    }

    private void take(String object){
        Room playerRoom = this.player.getCurrentRoom();
        GameObject obj = playerRoom.findObject(object);
        if(!obj.getName().equals("")){
            if(!obj.isTakeable()){
                System.out.println("you can't take that item");
                return;
            }
            obj.move(playerRoom, this.player);
            System.out.println("taken");
        }else{
            System.out.println("I don't see that");
        }
    }

}
