import com.bth.Game.Character.Creature;
import com.bth.Game.Character.Wizard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//@RunWith(HierarchicalContextRunner.class)
public class CreatureSpec {
    private Creature creature;

    @Before
    public void setUp() {
        this.creature = new Wizard();
    }

    /* isAlive() */
    @Test
    public void shouldReturnTrueIfHasHealth() {
        creature.setHealth(100);

        assertTrue(creature.isAlive());
    }
    @Test
    public void shouldReturnFalseIfHasNoHealth() {
        creature.setHealth(0);
        assertFalse(creature.isAlive());
    }
    /* /isAlive() */


    /* getDamageText() */
    @Test
    public void shouldReturnEmptyStringIfNoDamageText() {
        List<String> emptyList = new ArrayList<>();
        creature.setDamageText(emptyList);

        assertEquals(creature.getDamageText(), "");
    }

    @Test
    public void shouldReturnOneOfTheDamageTexts() {
        List<String> populatedList = new ArrayList<>();
        populatedList.add("Damage text 1");
        populatedList.add("Damage text 2");

        creature.setDamageText(populatedList);

        String damageText = creature.getDamageText();

        assertTrue(damageText.equals("Damage text 1") || damageText.equals("Damage text 2"));
    }
    /* /getDamageText() */

    /* dealDamage() */
    @Test
    public void shouldReturnIntBetweenMinAndMax() {
        int damage = creature.dealDamage();

        assertTrue(damage > creature.getDamageMin() && damage < creature.getDamageMax());
    }
    /* /dealDamage() */
}
