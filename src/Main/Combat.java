package Main;

import World.Creatures.Unit;

import static Enums.StatType.*;
import Mechanics.d;

import java.util.ArrayList;

public class Combat {
    ArrayList<Unit> units;
    d initiative = new d(20);
    public Combat(Unit[] unitList){
        units = generateInitiativeOrder(unitList);
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

    public String toString(){
        return units.toString();
    }
}
