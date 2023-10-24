package World.Prefabs;

import Mechanics.WeaponAction;
import Mechanics.d;
import Objects.Equipment.Weapons.MeleeWeapon;
import Objects.Equipment.Weapons.Weapon;

public class PrefabWeapons {
    public static Weapon generate(String name){
        switch(name.toLowerCase()){
            case "training sword":
                return trainingSword();
            default:
                return null;
        }
    }

    private static Weapon trainingSword(){
        return new MeleeWeapon("Training Sword",
                "A basic wooden sword, for training.",
                1,
                new WeaponAction[]{
                        new WeaponAction("Slash", new d[]{new d(6)}, 1),
                        new WeaponAction("Stab", new d[]{new d(8)}),
                        new WeaponAction("Hilt", new d[]{new d(4)}, 2)
                });
    }
}
