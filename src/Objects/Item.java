package Objects;

import World.Creatures.Unit;
import Mechanics.StatusEffect;

public class Item {
    String _name;
    String _description;
    float _weight;
    Unit _holder;
    public Item(){
        _name = "DEFAULT_NAME";
        _description = "DEFAULT_DESCRIPTION";
        _weight = 0.0f;
    }
    public Item(String name, String desc, float weight){
        _name = name;
        _description = desc;
        _weight = weight;
    }
    public void equip(Unit u){
        _holder = u;
    }
    public int damage(){
        return 0;
    }
    public StatusEffect[] attackEffectSelf(){
        return new StatusEffect[]{};
    }
    public StatusEffect[] attackEffectTarget(){
        return new StatusEffect[]{};
    }
    public String toString(){
        return _name;
    }

    public String getDescription(){
        return _description;
    }
}