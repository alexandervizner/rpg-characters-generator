package rpg.characters.generator;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Die> diceSet = Arrays.asList(new Die(), new Die(), new Die(), new Die());
        DiceRoller roller = new DiceRoller(diceSet);
        List<Integer> attrs = roller.generate();
        Character elf = new Character("Elf", attrs);

        System.out.println(elf);
    }
}
