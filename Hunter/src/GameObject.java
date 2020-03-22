public class GameObject {
    private String name;
    private String description;
    private boolean cooked = false;
    private boolean takeable = true;

    GameObject(String name){
        this.name = name;
        if (name.equals("fire") || name.equals("bird") || name.equals("deer")){
            this.takeable = false;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTakeable(){
        return this.takeable;
    }

    public boolean isCooked(){
        return this.cooked;
    }

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }

    public void setCooked(boolean cooked) {
        this.cooked = cooked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void examine(){
        System.out.println(this.description);
    }

    public boolean equals(String name){
        return this.name.equals(name);
    }

    public void move(Room source, Player destination){
        source.removeObject(this);
        destination.addObject(this);
    }

    public void move(Player source, Room destination){
        source.removeObject(this);
        destination.addObject(this);
    }
}
