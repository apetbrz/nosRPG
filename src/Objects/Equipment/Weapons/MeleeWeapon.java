package Objects.Equipment.Weapons;

import Mechanics.WeaponAction;
import Mechanics.d;

public class MeleeWeapon extends Weapon{
    private static final WeaponAction DEFAULT_ACTION = new WeaponAction("Hit", new d[]{new d(4)});
    public MeleeWeapon(){
        super();
        _type = WeaponClass.MELEE;
    }
    public MeleeWeapon(String name, String desc, float weight){
        super(name, desc, weight, WeaponClass.MELEE);
        _weaponActions = new WeaponAction[]{DEFAULT_ACTION};
    }
    public MeleeWeapon(String name, String desc, float weight, WeaponAction[] weaponActions){
        super(name, desc, weight, WeaponClass.MELEE);
        _weaponActions = weaponActions;
    }
}
