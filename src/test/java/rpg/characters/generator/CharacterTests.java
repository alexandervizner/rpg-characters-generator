package rpg.characters.generator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CharacterTests {

    @Test public void characterNameShouldMatchExpected() {
        String expected = "Test";
        Character test = new Character(expected);

        assertEquals(expected, test.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void unexpectedNumberOfAttributesShouldRiseException() {
        List<Integer> attrs = new ArrayList<>();
        attrs.add(1);

        new Character("Test", attrs);
    }

    @Test public void charactersAttributesShouldBeSeededInExpectedOrder() {
        List<Integer> attrs = new ArrayList<>();
        attrs.add(1);
        attrs.add(2);
        attrs.add(3);
        attrs.add(4);
        attrs.add(5);
        attrs.add(6);

        Character test = new Character("Test", attrs);

        assertEquals(1, test.getWisdom());
        assertEquals(2, test.getStrength());
        assertEquals(3, test.getCharisma());
        assertEquals(4, test.getDexterity());
        assertEquals(5, test.getConstitution());
        assertEquals(6, test.getIntelligence());
    }

    @Test public void attributesTotalShouldBeCorrect() {

        int seedAttrValue = 5;
        int expected = seedAttrValue * Character.ATTRS_NUMBER;
        List<Integer> attrs = new ArrayList<>();
        attrs.add(seedAttrValue);
        attrs.add(seedAttrValue);
        attrs.add(seedAttrValue);
        attrs.add(seedAttrValue);
        attrs.add(seedAttrValue);
        attrs.add(seedAttrValue);

        Character test = new Character("test", attrs);

        assertEquals(expected, test.getTotals());
    }

    @Test public void verifyCharacterStringRepresentation() {
        List<Integer> attrs = new ArrayList<>();
        attrs.add(1);
        attrs.add(2);
        attrs.add(3);
        attrs.add(4);
        attrs.add(5);
        attrs.add(6);

        String expected = "Character: Test" +
                "\n  Attributes: " +
                "\n      wisdom: " + attrs.get(0) +
                "\n    strength: " + attrs.get(1) +
                "\n    charisma: " + attrs.get(2) +
                "\n   dexterity: " + attrs.get(3) +
                "\nconstitution: " + attrs.get(4) +
                "\nintelligence: " + attrs.get(5) +
                "\n\nTotal scores: " + attrs.stream().mapToInt(i -> i).sum();

        Character test = new Character("Test", attrs);

        assertEquals(expected, test.toString());
    }
}
