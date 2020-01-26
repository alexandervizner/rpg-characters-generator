package rpg.characters.generator;

import java.util.List;

public class Character {
    /*
        This class describes desired character and
        intended for hold rundom values of its attributes.
     */

    final static int ATTRS_NUMBER = 6;

    private String name;
    private int wisdom;
    private int strength;
    private int charisma;
    private int dexterity;
    private int constitution;
    private int intelligence;


    public Character(String name) {
        this.name = name;
    }

    public Character(String name, List<Integer> attributesValues) {

        if (attributesValues.size() != ATTRS_NUMBER) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.wisdom = attributesValues.get(0);
        this.strength = attributesValues.get(1);
        this.charisma = attributesValues.get(2);
        this.dexterity = attributesValues.get(3);
        this.constitution = attributesValues.get(4);
        this.intelligence = attributesValues.get(5);
    }

    public String getName() {
        return name;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getTotals() {
        int result = this.getWisdom();
        result += this.getStrength();
        result += this.getCharisma();
        result += this.getDexterity();
        result += this.getConstitution();
        result += this.getIntelligence();

        return result;
    }

    public String toString() {
        return "Character: " + this.name +
                "\n  Attributes: " +
                "\n      wisdom: " + this.wisdom +
                "\n    strength: " + this.strength +
                "\n    charisma: " + this.charisma +
                "\n   dexterity: " + this.dexterity +
                "\nconstitution: " + this.constitution +
                "\nintelligence: " + this.intelligence +
                "\n\nTotal scores: " + this.getTotals();
    }
}

