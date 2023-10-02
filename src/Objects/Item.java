package Objects;

import Creatures.Unit;
import Mechanics.StatusEffect;

public class Item {
    String name;
    String description;
    float weight;
    Unit holder;
    public Item(){
        name = "DEFAULT_NAME";
        description = "DEFAULT_DESCRIPTION";
        weight = 0.0f;
    }
    public Item(String n, String d, float w){
        name = n;
        description = d;
        weight = w;
    }
    public void equip(Unit u){
        holder = u;
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
        return name;
    }

    public String getDescription(){
        return description;
    }
}