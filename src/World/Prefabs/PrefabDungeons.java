package World.Prefabs;

import Enums.Direction;
import World.Creatures.Unit;
import World.Dungeon;
import World.Room;

public class PrefabDungeons{
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
        int tutorialDungeonSize = 5;
        Dungeon dungeon = new Dungeon(tutorialDungeonSize,tutorialDungeonSize);
        Direction[][] connections = new Direction[][]{
                _ES_,_E_W,_E_W,__SW,__S_,
                NE__,_E_W,__SW,NE__,N__W,
                _ES_,_ESW,NESW,_E_W,__SW,
                N_S_,N___,NE__,__SW,N_S_,
                NE__,_E_W,_E_W,NE_W,N__W
        };

        int count = 0;
        for(int r = 0; r < tutorialDungeonSize; r++) {
            for (int c = 0; c < tutorialDungeonSize; c++) {
                dungeon.addRoom(new Room("" + c + " " + r),c,r,connections[count++]);
            }
        }

        dungeon.setSpawn(4,0);

        dungeon.addUnit(PrefabUnits.generate("Dummy"),0,0);
        dungeon.addUnit(PrefabUnits.generate("Goblin"),3,3);
        dungeon.addUnit(PrefabUnits.generate("Merchant"),1,3);

        return dungeon;
    }
}
