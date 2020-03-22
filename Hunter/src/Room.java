

import java.io.FileReader;
import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Room extends Object {
    private int roomId;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private Boolean visited = false;
    private Map<String, Room> neighbors = new HashMap<String, Room>();
    private String shortDescription;
    private String longDescription;


    Room(int roomId){
        this.roomId = roomId;
    }

    public Map<String, Room> getNeighbors() {
        return neighbors;
    }

    public boolean equals(int roomId){
        return this.roomId == roomId;
    }

    public void displayShortDescription(){
        System.out.println(this.shortDescription);
    }

    public void displayLongDescription(){
        System.out.println(this.longDescription);
        for (GameObject object: this.objects) {
            String str = "There is a " + object.getName() + " here";
            System.out.println(str);
        }
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription){
        this.longDescription = longDescription;
    }

    public void visitMe(){
        if(!this.visited){
            this.displayShortDescription();
            this.displayLongDescription();
            this.visited = true;
        } else{
            this.displayShortDescription();
        }
    }

    public void addNeighbor(String dir, Room room){
        this.neighbors.put(dir, room);
    }

    public Room getNeighbor(String dir){
        return this.neighbors.get(dir);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject obj){
        this.objects.add(obj);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }


    public GameObject findObject(String objectName){
        for (GameObject obj: this.objects) {
            if(obj.equals(objectName)){
                return obj;
            }
        }
        return new GameObject("");
    }
}
