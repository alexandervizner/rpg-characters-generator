package rpg.characters.generator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class DiceRoller {
    /*
        Main class which does all the magic of random attributes generation.
     */

    private List<Die> dicePool;
    private Random random;

    public DiceRoller(List<Die> dicePool) {
        this.dicePool = dicePool;
        this.random = new Random();
    }

    public List<Die> getDice() {
        return dicePool;
    }

    public void setDice(List<Die> diceCup) {
        this.dicePool = diceCup;
    }

    public void rollDice() {
        for (Die die : dicePool) {
            die.setValue(random.nextInt(die.getFaces()) + 1);
        }
    }

    public int rollDiscardingLowest() {
        rollDice();

        return getSumDiscardingLowest(dicePool);
    }

    public List<Integer> getAttributes() {
        List<Integer> attributes = new ArrayList<>();

        while (attributes.size() < Character.ATTRS_NUMBER) {
            attributes.add(rollDiscardingLowest());
        }

        return attributes;
    }

    public List<Integer> generate() {
        List<Integer> attributes = new ArrayList<>();

        while (!isMeetRequirements(attributes)) {
            attributes = getAttributes();
        }

        return attributes;
    }

    public int getSumDiscardingLowest(List<Die> dice) {
        dice.sort(Comparator.reverseOrder());

        return dice.subList(0, dice.size() - 1).stream().mapToInt(Die::getValue).sum();
    }

    public boolean isMeetRequirements(List<Integer> attributes) {
        int fifteenCount = 0;
        int total = 0;

        for (Integer attr : attributes) {
            if (attr >= Constants.MIN_REQUIRED_VALUE) {
                fifteenCount++;
            }

            total += attr;
        }

        return fifteenCount >= Constants.MIN_REQUIRED_VALUES_COUNT
                && total >= Constants.MIN_REQUIRED_CHARACTER_ATTRS_SUM;
    }
}
