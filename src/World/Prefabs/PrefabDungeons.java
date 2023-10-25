package World.Prefabs;

import Enums.Direction;
import World.Creatures.Unit;
import World.Dungeon;
import World.Room;

public class PrefabDungeons {
    //i will use Prefab classes to generate preset dungeons/items
    private static final Direction[] NESW = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    private static final Direction[] _ESW = {Direction.EAST, Direction.SOUTH, Direction.WEST};
    private static final Direction[] N_SW = {Direction.NORTH, Direction.SOUTH, Direction.WEST};
    private static final Direction[] NE_W = {Direction.NORTH, Direction.EAST, Direction.WEST};
    private static final Direction[] NES_ = {Direction.NORTH, Direction.EAST, Direction.SOUTH};

    private static final Direction[] NE__ = {Direction.NORTH, Direction.EAST};
    private static final Direction[] N_S_ = {Direction.NORTH, Direction.SOUTH};
    private static final Direction[] N__W = {Direction.NORTH, Direction.WEST};
    private static final Direction[] _E_W = {Direction.EAST, Direction.WEST};
    private static final Direction[] __SW = {Direction.SOUTH, Direction.WEST};
    private static final Direction[] _ES_ = {Direction.EAST, Direction.SOUTH};

    private static final Direction[] N___ = {Direction.NORTH};
    private static final Direction[] _E__ = {Direction.EAST};
    private static final Direction[] __S_ = {Direction.SOUTH};
    private static final Direction[] ___W = {Direction.WEST};

    private static final Direction[] ____ = {};

    public static Dungeon tutorialDungeon(){
        Dungeon dungeon = new Dungeon(4,4);
        Direction[][] connections = new Direction[][]{
                _E__,_E_W,_E_W,__SW,
                _ES_,_E_W,__SW,N_S_,
                N_S_,_ES_,NESW,N__W,
                NE__,N__W,NE__,___W
        };


        int count = 0;
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                dungeon.addRoom(new Room("" + c + " " + r),c,r,connections[count++]);
            }
        }

        dungeon.setSpawn(0,0);

        dungeon.getRawMap()[0][0].addUnit(new Unit("Dummy"));

        return dungeon;
    }
}
