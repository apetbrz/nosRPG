package World.Prefabs;

import Enums.Alliance;
import World.Creatures.Unit;

public class PrefabUnits {
    public static Unit generate(String name){
        switch(name.toLowerCase()){
            case "goblin":
                return generateGoblin();
            default:
                return null;
        }
    }

    private static Unit generateGoblin(){
        Unit goblin = new Unit("Goblin","Goblin",
                "A little goblin, so little, it doesn't even do anything!", Alliance.MONSTER, 10);
        return goblin;
    }
}
