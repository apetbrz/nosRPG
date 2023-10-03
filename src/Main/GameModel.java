package Main;

import World.*;

import java.util.ArrayList;

public class GameModel {
    private ArrayList<Region> map;

    public GameModel(){
        map = new ArrayList<Region>();
    }

    public void addRegion(Region newRegion){
        map.add(newRegion);
    }
}
