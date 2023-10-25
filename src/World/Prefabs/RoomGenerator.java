package World.Prefabs;

import World.Room;

import java.util.Random;

public class RoomGenerator {
    private static Random rand;
    public RoomGenerator(long seed){
        rand = new Random(seed);
    }

    public Room generate(){
        return new Room("" + rand.nextInt(100));
    }
    public Room generate(String name){
        return new Room(name);
    }
    public Room[][] generate(int row, int col){
        Room[][] output = new Room[row][col];
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                output[r][c] = generate("" + r + "," + c);
            }
        }
        return output;
    }
}