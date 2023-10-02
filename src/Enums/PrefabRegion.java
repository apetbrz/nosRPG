package Enums;

import World.Region;
import World.Room;

public enum PrefabRegion {
    ENTRANCE,
    DUNGEON_1,
    DUNGEON_2,
    DUNGEON_3,
    TEST_AREA;

    public Region reg;

    static{
        //ENTRANCE.reg = new Region(1);
        //ENTRANCE.reg.setRoom(0,0,new Room("Dungeon Entrance"));

        TEST_AREA.reg = new Region(1);
        Room test = new Room("TESTING AREA");
        TEST_AREA.reg.setRooms(0,0,new Room("TESTING AREA"));
    }

}
