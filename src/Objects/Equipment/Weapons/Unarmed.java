package Objects.Equipment.Weapons;

import Mechanics.WeaponAction;
import Mechanics.d;

public class Unarmed extends MeleeWeapon{
    public Unarmed(){
        super("Unarmed","Your fists.",0);

        _weaponActions = new WeaponAction[]{new WeaponAction("Punch", new d[]{new d(4)})};
    }
}
