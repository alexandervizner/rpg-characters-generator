package rpg.characters.generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DieTests {

    @Test(expected = IllegalArgumentException.class)
    public void shouldRiseExeption() {
        new Die(0);
    }

    @Test public void shouldReturnValidValue() {
        int expected = 20;
        Die die = new Die(expected);

        assertEquals(expected, die.getFaces());
    }

    @Test public void defaultConstructorShouldInit6D() {
        Die die = new Die();

        assertEquals(6, die.getFaces());
    }

    @Test public void shouldBeBiggerThanOther() {
        Die die = new Die();
        die.setValue(20);

        Die other = new Die();
        other.setValue(10);

        assertEquals(1, die.compareTo(other));
    }

    @Test public void shouldBeSmallerThanOther() {
        Die die = new Die();
        die.setValue(10);

        Die other = new Die();
        other.setValue(20);

        assertEquals(-1, die.compareTo(other));
    }

    @Test public void shouldBeEquals() {
        Die die = new Die();
        die.setValue(10);

        Die other = new Die();
        other.setValue(10);

        assertEquals(0, die.compareTo(other));
    }
}

