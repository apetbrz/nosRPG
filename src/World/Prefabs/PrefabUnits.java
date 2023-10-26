package World.Prefabs;

import Enums.Alliance;
import World.Creatures.Unit;

public class PrefabUnits {
    public static Unit generate(String name){
        switch(name.toLowerCase()){
            case "goblin":
                return generateGoblin();
            case "merchant":
                return generateMerchant();
            case "dummy":
                return generateDummy();
            default:
                return null;
        }
    }

    private static Unit generateGoblin(){
        Unit goblin = new Unit("Goblin","Goblin",
                "A little goblin, so little, it doesn't even do anything!", Alliance.MONSTER, 10);
        return goblin;
    }
    private static Unit generateMerchant(){
        Unit merchant = new Unit("Merchant","Human",
                "Lamp oil, rope, bombs – you want it? It's yours, my friend, as long as you have enough rupees.",
                Alliance.FRIENDLY, 50);
        return merchant;
    }
    private static Unit generateDummy(){
        Unit dummy = new Unit("Practice Dummy","Inanimate",
                "It just...sits there...", Alliance.NEUTRAL, 100);
        return dummy;
    }
}
