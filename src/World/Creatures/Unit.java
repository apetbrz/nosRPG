package World.Creatures;

import Enums.Alliance;
import Enums.StatType;
import Lang.Toolbox;
import Mechanics.*;
import Objects.*;
import Objects.Equipment.Weapons.Unarmed;

import java.util.ArrayList;

public class Unit {
    private String _name;
    private String _race;
    private String _description;
    private boolean _isPlayer = false;
    private boolean _isAlive = true;
    private Alliance _team;
    private int _maxHealth;
    private int _currentHealth;
    private StatSheet _stats;
    private ArrayList<StatusEffect> _statuses;
    private ArrayList<Modifier> _modifiers;
    private Inventory _inventory;
    private Item _mainHand;
    private Item _offHand;
    private d d20 = new d(20);

    public Unit(){
        _name = Toolbox.DEFAULT_TEXT+this.getClass().getName();
        _race = Toolbox.DEFAULT_TEXT+this.getClass().getName();
        _description = Toolbox.DEFAULT_TEXT+this.getClass().getName();
        _team = Alliance.NEUTRAL;
        _maxHealth = 10;
        _currentHealth = 10;
        _stats = new StatSheet();
        _statuses = new ArrayList<StatusEffect>();
        _modifiers = new ArrayList<Modifier>();
        _inventory = new Inventory();
        _mainHand = new Unarmed();
        _offHand = null;
    }
    public Unit(String name, String race, String desc, Alliance team, int maxHealth){
        _name = name;
        _race = race;
        _description = desc;
        _team = team;
        _maxHealth = maxHealth;
        _currentHealth = maxHealth;
        _stats = new StatSheet();
        _statuses = new ArrayList<StatusEffect>();
        _modifiers = new ArrayList<Modifier>();
        _inventory = new Inventory();
        _mainHand = new Unarmed();
        _offHand = null;
    }
    public Unit(String n){
        this();
        _name = n;
    }
    public Unit(String n, boolean isPlayer){
        this(n);
        if(isPlayer) assignPlayer();
    }

    public void initializeStats(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int armorClass){
        _stats.STR.set(strength);
        _stats.DEX.set(dexterity);
        _stats.CON.set(constitution);
        _stats.INT.set(intelligence);
        _stats.WIS.set(wisdom);
        _stats.CHA.set(charisma);
        _stats.AC.set(armorClass);
    }

    public void updateStats(){
        for(Modifier mod : _modifiers){
            _stats.getStat(mod.getStatToAffect()).modify(mod.getValue());
        }
    }
    public void equip(Item item){
        _mainHand = item;
    }
    public void attackTarget(Unit target){
        if(_mainHand == null){
            target.damage(1 + _stats.STR.getModifier());
        }
        else{
            target.damage(_mainHand.damage());
            this.applyEffect(_mainHand.attackEffectSelf());
            target.applyEffect(_mainHand.attackEffectTarget());
        }
    }
    public void damage(int value){
        _currentHealth -= value;
        if(_currentHealth <= 0){
            _currentHealth = 0;
            die();
        }
    }
    public void applyEffect(StatusEffect[] effectList) {
        for(StatusEffect effect : effectList){
            _statuses.add(effect);
            for(Modifier mod : effect.getModifiers()){
                _modifiers.add(mod);
            }
        }
        updateStats();
    }
    public void tickStatuses(){
        for(StatusEffect status : _statuses){
            status.tick();
            if(status.getDuration() < 1){
                _statuses.remove(status);
                for(Modifier mod : status.getModifiers()){
                    _modifiers.remove(mod);
                }
                updateStats();
            }
        }
    }
    private void die() {
        Toolbox.print(_name + " died!");
        _isAlive = false;
    }
    public boolean isAlive(){
        return _isAlive;
    }
    public boolean isDead() {
        return !_isAlive;
    }
    public boolean isPlayer(){
        return _isPlayer;
    }
    public void assignPlayer(){
        _isPlayer = true;
        _team = Alliance.PARTY;
    }
    public int getCurrentHealth(){
        return _currentHealth;
    }
    public int getMaxHealth() {
        return _maxHealth;
    }
    public Alliance getTeam() {
        return _team;
    }
    public Stat getStat(StatType statType){
        return _stats.getStat(statType);
    }
    public int getModifier(StatType statType){
        return _stats.getStatModifier(statType);
    }
    public Item getMainHand() {
        return _mainHand;
    }
    public Inventory getInventory() {
        return _inventory;
    }
    public String toString(){
        return _name;
    }
    public String toStringDetailed(){
        String output = _name + "\n" + _description + "\n" + _team +
                "\n" + _stats.toString();
        return output;
    }
    public boolean checkAggression(Unit u) {
        Alliance target = u.getTeam();
        for(Alliance a : this._team.getEnemies()){
            if(a.equals(target)){
                return true;
            }
        }
        return false;
    }
}
