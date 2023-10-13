package Objects.Equipment.Weapons;

import Enums.WeaponProperties;
import Mechanics.StatusEffect;
import Mechanics.WeaponAction;
import Mechanics.d;
import Objects.Item;

public class Weapon extends Item {
    d attack;
    WeaponProperties[] properties;
    WeaponAction[] _weaponActions;
    private final WeaponAction DEFAULT_ACTION = new WeaponAction("Struggle", new d[]{new d(4)});
    //StatusEffect[] coating; //TODO: replace StatusEffect[] with Coating object
    WeaponClass _type;
    public Weapon(){
        super();
        _weaponActions = new WeaponAction[]{DEFAULT_ACTION};
    }
    public Weapon(String name, String desc, float weight, WeaponClass type){
        super();
        _type = type;
        _weaponActions = new WeaponAction[]{DEFAULT_ACTION};
    }

    public int damage(){
        return damage(0);
    }
    public int damage(int actionChoice){
        return _weaponActions[actionChoice].roll();
    }
    public String actionsToString(){
        String output = "";
        int count = 1;
        for(WeaponAction action : _weaponActions){
            output += "[" + count++ + ": ";
            output += action;
            output += "]\n";
        }
        return output;
    }
    public StatusEffect[] attackEffectSelf(){
        return new StatusEffect[]{};
    }
    public StatusEffect[] attackEffectTarget(){
        return new StatusEffect[]{};
    }
}
enum WeaponClass{
    MELEE,
    RANGED
}