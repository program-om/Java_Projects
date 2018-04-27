import java.util.Random;

class Dice {
    int dice1, dice2;

    private int getRandom(int max){
        Random r = new Random();
        return r.nextInt(max)+1;
    }

    void throwDice(){
        dice1 = getRandom(6);
        dice2 = getRandom(6);
    }

    boolean doubleDice(){
        return dice1 == dice2;
    }
}
