

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    private ArrayList<Room> rooms = new ArrayList<Room>();

    GameMap(){
        try {
            this.readRoomsDescription();
            this.roomsConnections();
            this.addObjectsToRooms();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private void roomsConnections() throws Exception{
        Scanner in = new Scanner(new FileReader("files/room_connections.txt"));
        String line;
        while (in.hasNext()){
            line = in.nextLine();
            Room room = findRoom(Integer.parseInt(line));
            line = in.nextLine();
            while (!line.equals("---")){
                String[] lineParts = line.split(" ");
                Room neighbor = findRoom(Integer.parseInt(lineParts[1]));
                room.addNeighbor(lineParts[0], neighbor);
                line = in.nextLine();
            }
        }
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    private void readRoomsDescription(){
        try {
            Scanner in = new Scanner(new FileReader("files/rooms.txt"));
            String line;
            while (in.hasNext()){
                StringBuilder sb = new StringBuilder();
                line = in.nextLine(); //room id
                Room room = new Room(Integer.parseInt(line));
                line = in.nextLine(); //short description
                room.setShortDescription(line);
                while (in.hasNext()){
                    line = in.nextLine();
                    if(line.equals("---")) break;
                    sb.append(line);
                }
                room.setLongDescription(sb.toString());
                this.rooms.add(room);
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addObjectsToRooms(){
        try {
            Scanner in = new Scanner(new FileReader("files/objects.txt"));
            String line;
            int roomId;
            while (in.hasNext()){
                StringBuilder sb = new StringBuilder();
                roomId = Integer.parseInt(in.nextLine()); //room id
                line = in.nextLine();
                GameObject obj = new GameObject(line);
                while (in.hasNext()){
                    line = in.nextLine();
                    if(line.equals("---")) break;
                    sb.append(line);
                }
                obj.setDescription(sb.toString());
                this.findRoom(roomId).addObject(obj);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private Room findRoom(int roomId){
        for (Room room: rooms) {
            if(room.equals(roomId)) {
                return room;
            }
        }
        return new Room(0);
    }
}
