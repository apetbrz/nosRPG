package Main;

import java.util.ArrayList;

import Creatures.*;
import Enums.PrefabRegion;
import World.*;

public class Gamemaster {
    //TODO: VARS
    ArrayList<Region> map;
    Player player;
    public Gamemaster(){
        //TODO: INIT VARS
        map = new ArrayList<Region>();
        map.add(PrefabRegion.TEST_AREA.reg);
        play();
    }
    public void play(){
        renderRegion(0);
    }
    public void renderRegion(int index){
        Room[][] r = map.get(index).getRooms();
        for(int i = 0; i < r.length; i++){
            for(int j = 0; j < r[i].length; j++){
                System.out.print("ROOM " + i + j + ": " + r[i][j]);
            }
            System.out.println();
        }
    }
    public void enterRoom(Room r){

    }

}