package Equipment.Weapons;

import Enums.WeaponProperties;
import Mechanics.StatusEffect;
import Mechanics.d;
import Objects.Item;

public class Weapon extends Item {
    d attack;
    WeaponProperties[] properties;
    StatusEffect[] coating;
    WeaponClass type;
    public Weapon(){
        super();
        type = WeaponClass.MELEE;
    }
    public Weapon(String n, String d, float w, WeaponClass t){
        super();
        type = t;
    }
    public Weapon(String n, String d, float w, WeaponClass t, StatusEffect[] c){
        super();
        type = t;
        coating = c;
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
}
enum WeaponClass{
    MELEE,
    RANGED
}