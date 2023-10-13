package Mechanics;

import Lang.CombatStrings;
import Lang.Toolbox;
import Objects.Equipment.Weapons.Weapon;
import World.Creatures.Unit;
import java.util.Scanner;

import static Enums.StatType.*;

import java.util.ArrayList;

public class Combat {
    ArrayList<Unit> unitsInCombat;
    d initiative = new d(20);
    private static final Scanner SCAN = new Scanner(System.in);
    public Combat(Unit[] unitList){
        unitsInCombat = generateInitiativeOrder(unitList);
    }

    public void fight(){
        System.out.println("fight() CALLED");
        boolean fightEnd = false;
        while(!fightEnd) {
            healthDisplay();
            for (Unit activeUnit : unitsInCombat) {
                if (activeUnit.isPlayer()) {
                    playerActions(activeUnit);
                } else {
                    botActions();
                }
                if(checkCombatEnd()){
                    fightEnd = true;
                    break;
                }
            }
            //FORCE END:
            //fightEnd = true;
        }
    }
    private void healthDisplay(){
        for(Unit unit : unitsInCombat){
            Toolbox.print(String.format(CombatStrings.COMBAT_HEALTH_FORMAT,unit,
                    (unit.getCurrentHealth() + "/" + unit.getMaxHealth())));
        }
    }

    private void playerActions(Unit player){
        Toolbox.print(CombatStrings.PLAYER_COMBAT_ACTIONS);
        int choice = SCAN.nextInt();
        switch(choice){
            case 1: //ACT
                Unit target = playerTargetPrompt();
                Toolbox.print("\n" + ((Weapon)player.getMainHand()).actionsToString());
                choice = SCAN.nextInt() - 1;
                int damage = ((Weapon)player.getMainHand()).damage(choice);
                Toolbox.print("\nYou attacked " + target + " for " + damage + " damage!");
                target.damage(damage);
                if(target.isDead() && !target.isPlayer()){
                    unitsInCombat.remove(target);
                }
                break;
            case 2: //USE ITEM

                break;
        }
    }

    private Unit playerTargetPrompt(){
        int count = 1;
        for(Unit unit : unitsInCombat){
            Toolbox.print("[" + count++ + ": " + unit + "]");
        }
        int choice = SCAN.nextInt() - 1;
        return unitsInCombat.get(choice);
    }
    private void botActions(){
        Toolbox.print("The dummy sits there.");
    }

    private ArrayList<Unit> generateInitiativeOrder(Unit[] unitList){
        ArrayList<Unit> output = new ArrayList<Unit>(1);
        ArrayList<Integer> rolls = new ArrayList<Integer>(1);
        int roll;
        int sortingIterator;

        for (Unit unit : unitList) {
            roll = initiative.roll() + unit.getModifier(DEXTERITY);
            System.out.println(unit + " + rolled a " + roll + " for initiative!");

            sortingIterator = 0;
            while (sortingIterator < output.size() && rolls.get(sortingIterator) > roll) {
                sortingIterator++;
            }
            if(rolls.size() < sortingIterator) {
                if (rolls.get(sortingIterator) == roll) {
                    if (initiative.roll() > 10) {
                        sortingIterator++;
                    }
                }
            }

            output.add(sortingIterator, unit);
            rolls.add(sortingIterator, roll);
        }
        return output;
    }
    private boolean checkCombatEnd(){
        boolean output = true;
        for(Unit unit : unitsInCombat){
            if(!unit.isPlayer()){
                output = false;
            }
            if(unit.isPlayer() && unit.isDead()){
                return true;
            }
        }
        return output;
    }

    public String toString(){
        return unitsInCombat.toString();
    }
}
