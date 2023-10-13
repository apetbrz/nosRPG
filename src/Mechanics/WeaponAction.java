package Mechanics;

public class WeaponAction {
    String _name;
    d[] _damageDice;
    int _flatDamage;
    public WeaponAction(){
        _name = "DEFAULT_ACTION";
        _damageDice = new d[]{};
        _flatDamage = 0;
    }
    public WeaponAction(String name){
        _name = name;
        _damageDice = new d[]{};
        _flatDamage = 0;
    }
    public WeaponAction(String name, d[] damageDice){
        _name = name;
        _damageDice = damageDice;
    }
    public WeaponAction(String name, d[] damageDice, int flatDamage){
        _name = name;
        _damageDice = damageDice;
        _flatDamage = flatDamage;
    }
    public int roll(){
        int sum = 0;
        for(d die : _damageDice){
            sum += die.roll();
        }
        sum += _flatDamage;
        return sum;
    }

    public void setDamageDice(d[] damageDice) {
        _damageDice = damageDice;
    }
    public String toString(){
        return _name + ": " + diceToString() + " damage";
    }

    public String diceToString(){
        if(_damageDice == null){
            return "ERROR: NULL DICE ARRAY";
        }
        else if(_damageDice.length == 0){
            return "ERROR: EMPTY DICE ARRAY";
        }
        String output = "";
        int count = 0;
        int dNumber = 0;
        for(d die : _damageDice){
            if(dNumber == 0){
                dNumber = die.num;
                count++;
            }
            else if(dNumber == die.num){
                count++;
            }
            else if(dNumber != die.num){
                output += count + "d" + dNumber + "+";
                dNumber = die.num;
                count = 1;
            }
        }
        output += count + "d" + dNumber;
        if(_flatDamage > 0){
            output += "+" + _flatDamage;
        }
        return output;
    }
}
