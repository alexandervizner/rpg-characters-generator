package rpg.characters.generator;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class DiceRollerTests {

    private List<Die> diceSet = Arrays.asList(new Die(), new Die(), new Die(), new Die());
    private DiceRoller roller = new DiceRoller(diceSet);

    @Test public void rollShouldPopulateDicePool() {
        roller.rollDice();

        assertEquals(roller.getDice()
                .stream()
                .filter(d -> d.getValue() > 0 && d.getValue() <= 6)
                .count(), roller.getDice().size());
    }

    @Test public void discardLowestAndSumShouldReturnSumOfBiggestThree() {
        List<Die> diceSet = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Die die = new Die();
            die.setValue(i + 2);
            diceSet.add(die);
        }
        int expected = diceSet.get(1).getValue() + diceSet.get(2).getValue() + diceSet.get(3).getValue();
        int result = roller.getSumDiscardingLowest(diceSet);

        assertEquals(expected, result);
    }

    @Test public void rollMultipleShouldReturnCorrectAmountOfAttributes() {
        List<Integer> attrs = roller.getAttributes();

        assertEquals(attrs.size(), Character.ATTRS_NUMBER);
    }

    @Test public void dieValueShouldNotExceedMax() {
        roller.rollDice();
        List<Die> seededDice = roller.getDice();

        long result = seededDice.stream().filter(d -> d.getValue() > 6).count();

        assertEquals(result, 0);
    }

    @Test public void attributesValuesShouldMatchRequirements() {
        int count = 1000;
        while (count >= 0) {
            count--;

            List<Integer> attributes = roller.generate();

            assertEquals(attributes.size(), Character.ATTRS_NUMBER);
            assertTrue(attributes.stream().mapToInt(i -> i).sum() >= Constants.MIN_REQUIRED_CHARACTER_ATTRS_SUM);
            assertTrue(attributes.stream().filter(i -> i >= Constants.MIN_REQUIRED_VALUE).count() >= Constants.MIN_REQUIRED_VALUES_COUNT);
        }
    }

    @Test public void isMeetRequirementsShouldReturnFalseIfSumLessThan75AndNo15x2() {
        List<Integer> attributes = new ArrayList<>();
        attributes.add(12);
        attributes.add(12);
        attributes.add(12);
        attributes.add(12);
        attributes.add(12);
        attributes.add(12);
        // Sum: 72

        assertFalse(roller.isMeetRequirements(attributes));
    }

    @Test public void isMeetRequirementsShouldReturnFalseIfNo15x2() {
        List<Integer> attributes = new ArrayList<>();
        attributes.add(13);
        attributes.add(13);
        attributes.add(13);
        attributes.add(13);
        attributes.add(13);
        attributes.add(13);
        // Sum: 78

        assertFalse(roller.isMeetRequirements(attributes));
    }

    @Test public void isMeetRequirementsShouldReturnFalseIfSumLess75() {
        List<Integer> attributes = new ArrayList<>();
        attributes.add(15);
        attributes.add(15);
        attributes.add(11);
        attributes.add(11);
        attributes.add(11);
        attributes.add(11);
        // Sum: 74

        assertFalse(roller.isMeetRequirements(attributes));
    }

    @Test public void isMeetRequirementsShouldReturnTrue() {
        List<Integer> attributes = new ArrayList<>();
        attributes.add(15);
        attributes.add(15);
        attributes.add(12);
        attributes.add(11);
        attributes.add(11);
        attributes.add(11);
        // Sum: 75

        assertTrue(roller.isMeetRequirements(attributes));
    }
}

