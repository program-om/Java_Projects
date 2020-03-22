import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<GameObject> inventory = new ArrayList<GameObject>();
    private int energy = 32;
    private boolean alive = true;

    Player(Room room){
        this.currentRoom = room; //set it to the start room
        this.currentRoom.visitMe();
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void displayInventory(){
        System.out.println("You currently have: ");
        for (GameObject obj: this.inventory) {
            System.out.println("A " + obj.getName());
        }
    }

    public void move(String direction){
        Room room = this.currentRoom.getNeighbor(direction);
        if(room == null){
            System.out.println("you can't go that way");
        }else{
            this.currentRoom = room;
            this.currentRoom.visitMe();
            this.energy--;
            if (energy == 0){
                this.alive = false;
            }
        }
    }

    public ArrayList<GameObject> getObjects() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addObject(GameObject obj){
        this.inventory.add(obj);
    }

    public void removeObject(GameObject obj){
        this.inventory.remove(obj);
    }

    public GameObject findObject(String objectName){
        for (GameObject obj: this.inventory) {
            if(obj.equals(objectName)){
                return obj;
            }
        }
        return new GameObject("");
    }

    public void incrementEnergy(int value){
        this.energy += value;
    }

    public void examine(){
        if (this.energy >= 28){
            System.out.println("You are in a great condition");
        } else if(this.energy >= 20){
            System.out.println("You are in a good condition");
        } else if(this.energy >= 10){
            System.out.println("Your condition is getting worse");
        } else {
            System.out.println("You are about to die of hunger");
        }
    }
}
