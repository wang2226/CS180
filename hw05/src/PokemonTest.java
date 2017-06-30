import org.junit.Test;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PokemonTest {

    // isNumeric: 15%

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericTrueInteger() {
        String message = "MyUtils::isNumeric check that it returns " +
                "true when a String repr of an int is passed";
        assertTrue(message, MyUtils.isNumeric("12"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericTrueFloat() {
        String message = "MyUtils::isNumeric check that it returns " +
                "true when a String repr of a float is passed";
        assertTrue(message, MyUtils.isNumeric("12.5"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericFalseAlpha() {
        String message = "MyUtils::isNumeric check that it returns " +
                "false when a String repr of alpha characters is passed";
        assertFalse(message, MyUtils.isNumeric("alpha"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericFalseAlphaNum() {
        String message = "MyUtils::isNumeric check that it returns " +
                "false when a String repr of alpha and numeric characters is passed";
        assertFalse(message, MyUtils.isNumeric("alpha56"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericFalseExtraDot() {
        String message = "MyUtils::isNumeric check that it returns " +
                "false when a numeric String has an extra dot";
        assertFalse(message, MyUtils.isNumeric("12..5"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testIsNumericFalseEmptyStr() {
        String message = "MyUtils::isNumeric check that it returns " +
                "false when a String is empty";
        assertFalse(message, MyUtils.isNumeric(""));
    }

    // formatStr: 5%

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testFormatStrLower() {
        String message = "MyUtils::formatStr check that a full " +
                "lower case string is correctly formatted";
        assertEquals(message, "Lowercase", MyUtils.formatStr("lowercase"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testFormatStrUpper() {
        String message = "MyUtils::formatStr check that a full " +
                "upper case string is correctly formatted";
        assertEquals(message, "Uppercase", MyUtils.formatStr("UPPERCASE"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testFormatStrMixed() {
        String message = "MyUtils::formatStr check that a mixed " +
                "upper case / lower case string is correctly formatted";
        assertEquals(message, "Mixed", MyUtils.formatStr("MiXeD"));

    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testFormatStrMixed2() {
        String message = "MyUtils::formatStr check that a mixed " +
                "upper case / lower case string is correctly formatted";
        assertEquals(message, "Mixed", MyUtils.formatStr("mIxEd"));
    }

    // Constructor: 10%
    // Accessors: 10%

    // Also tests accessor 2.5
    // Constructor 1
    @Test(timeout = 100)
    //@ScoringWeight(3.5)
    public void testConstructorIDFirst() throws Exception {
        String message = "Pokemon::Pokemon/getID check that constructor " +
                "sets correct ID and accessor retrieves it";
        // code to reset static variables needed for testing
        Field privateIntField =
                Pokemon.class.getDeclaredField("NUM_POKEMONS");
        privateIntField.setAccessible(true);
        privateIntField.set(null, 0);

        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, 0, p.getId());
    }

    @Test(timeout = 100)
    //@ScoringWeight(1)
    public void testConstructorIDSecond() throws Exception {
        String message = "Pokemon::Pokemon/getID check that constructor " +
                "sets correct ID for a consecutive Pokemon";
        // code to reset static variables needed for testing
        Field privateIntField =
                Pokemon.class.getDeclaredField("NUM_POKEMONS");
        privateIntField.setAccessible(true);
        privateIntField.set(null, 0);

        Pokemon p1 = new Pokemon("Pikachu", "Electric", 0, 1);
        Pokemon p2 = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, 1, p2.getId());
    }

    // Also tests accessor 2.5
    // Constructor 1
    @Test(timeout = 100)
    //@ScoringWeight(3.5)
    public void testConstructorName() {
        String message = "Pokemon::Pokemon/getName check that constructor " +
                "sets correct name and accessor retrieves it";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, "Pikachu", p.getName());
    }

    // Also tests accessor 2.5
    // Constructor 1
    @Test(timeout = 100)
    //@ScoringWeight(3.5)
    public void testConstructorType() {
        String message = "Pokemon::Pokemon/getName check that constructor " +
                "sets correct type and accessor retrieves it";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, "Electric", p.getType());
    }

    @Test(timeout = 100)
    //@ScoringWeight(1)
    public void testConstructorTypeSetDefault() {
        String message = "Pokemon::Pokemon check that constructor " +
                "sets default type when an unsupported type is given";
        Pokemon p = new Pokemon("Pikachu", "Mountain", 0, 1);
        assertEquals(message, "Fire", p.getType());
    }

    @Test(timeout = 100)
    //@ScoringWeight(1)
    public void testConstructorTypeFormatting() {
        String message = "Pokemon::Pokemon check that constructor " +
                "sets type in the correct format";
        Pokemon p = new Pokemon("Pikachu", "ElEcTrIc", 0, 1);
        assertEquals(message, "Electric", p.getType());
    }

    // Also tests accessor 2.5
    // Constructor 1
    @Test(timeout = 100)
    //@ScoringWeight(3.5)
    public void testConstructorHealthPower() {
        String message = "Pokemon::Pokemon/getHealthPower check that constructor " +
                "sets correct health power and accessor retrieves it";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, 0, p.getHealthPower());
    }

    @Test(timeout = 100)
    //@ScoringWeight(1)
    public void testConstructorHealthPowerDefault() {
        String message = "Pokemon::Pokemon check that constructor " +
                "sets default health power when an unsupported number is given";
        Pokemon p = new Pokemon("Pikachu", "Electric", -5, 1);
        assertEquals(message, 0, p.getHealthPower());
    }

    // Also tests accessor 2.5
    // Constructor 1
    @Test(timeout = 100)
    //@ScoringWeight(3.5)
    public void testConstructorBaseAttack() {
        String message = "Pokemon::Pokemon/getHealthPower check that constructor " +
                "sets correct base attack power and accessor retrieves it";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        assertEquals(message, 1, p.getBaseAttackPower(), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1)
    public void testConstructorBaseAttackDefault() {
        String message = "Pokemon::Pokemon check that constructor " +
                "sets default base attack power when an unsupported number is given";
        Pokemon p = new Pokemon("Pikachu", "Electric", 1, -5);
        assertEquals(message, 1, p.getBaseAttackPower(), 0.001);
    }


    // Mutators: 10%

    @Test(timeout = 100)
    //@ScoringWeight(1.66)
    public void testSetTypeCorrect() {
        String message = "Pokemon::setType check that type is set when " +
                "input is correct and returns true";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setType("Fire");
        assertEquals(message, "Fire", p.getType());
        assertTrue(res);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.66)
    public void testSetTypeIncorrect() {
        String message = "Pokemon::setType check that type is not set when " +
                "input is incorrect and returns false";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setType("Mountain");
        assertEquals(message, "Electric", p.getType());
        assertFalse(res);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.66)
    public void testSetHealthCorrect() {
        String message = "Pokemon::setHealthPower check that health is set when " +
                "input is correct and returns true";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setHealthPower(10);
        assertEquals(message, 10, p.getHealthPower());
        assertTrue(res);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.66)
    public void testSetHealthIncorrect() {
        String message = "Pokemon::setHealthPower check that health is not set when " +
                "input is incorrect and returns false";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setHealthPower(-10);
        assertEquals(message, 0, p.getHealthPower());
        assertFalse(res);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.66)
    public void testSetAttackCorrect() {
        String message = "Pokemon::setBaseAttackPower check that attack is set when " +
                "input is correct and returns true";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setBaseAttackPower(10);
        assertEquals(message, 10, p.getBaseAttackPower(), 0.001);
        assertTrue(res);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.7)
    public void testSetAttackIncorrect() {
        String message = "Pokemon::setBaseAttackPower check that attack is not set when " +
                "input is incorrect and returns false";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 1);
        boolean res = p.setBaseAttackPower(-10);
        assertEquals(message, 1, p.getBaseAttackPower(), 0.001);
        assertFalse(res);
    }

    // Object methods: 25%
    // toString 6.25
    // isDead 6.25
    // boostHealthPower 6.25
    // reduceHealthPower 6.25

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testToStringName() {
        String message = "Pokemon::toString check that string " +
                "representation contains the name";
        Pokemon p = new Pokemon("Pikachu", "Electric", 2, 3);
        String rep = p.toString();
        String[] info = rep.split("\\s+");
        assertTrue(message, Arrays.asList(info).contains("Pikachu"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testToStringID() throws Exception {
        String message = "Pokemon::toString check that string " +
                "representation contains the ID";
        // code to reset static variables needed for testing
        Field privateIntField =
                Pokemon.class.getDeclaredField("NUM_POKEMONS");
        privateIntField.setAccessible(true);
        privateIntField.set(null, 0);

        Pokemon p = new Pokemon("Pikachu", "Electric", 2, 3);
        String rep = p.toString();
        String[] info = rep.split("\\s+");
        assertTrue(message, Arrays.asList(info).contains("0"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testToStringType() {
        String message = "Pokemon::toString check that string " +
                "representation contains the type";
        Pokemon p = new Pokemon("Pikachu", "Electric", 2, 3);
        String rep = p.toString();
        String[] info = rep.split("\\s+");
        assertTrue(message, Arrays.asList(info).contains("Electric"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testToStringHealth() {
        String message = "Pokemon::toString check that string " +
                "representation contains the health";
        Pokemon p = new Pokemon("Pikachu", "Electric", 2, 3);
        String rep = p.toString();
        String[] info = rep.split("\\s+");
        assertTrue(message, Arrays.asList(info).contains("2"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.25)
    public void testToStringAttack() {
        String message = "Pokemon::toString check that string " +
                "representation contains the attack";
        Pokemon p = new Pokemon("Pikachu", "Electric", 2, 3);
        String rep = p.toString();
        String[] info = rep.split("\\s+");
        assertTrue(message, Arrays.asList(info).contains("3.0"));
    }

    @Test(timeout = 100)
    //@ScoringWeight(3.125)
    public void testIsDeadTrue() {
        String message = "Pokemon::isDead check that " +
                "returns true when health is 0";
        Pokemon p = new Pokemon("Pikachu", "Electric", 0, 3);
        assertTrue(message, p.isDead());
    }

    @Test(timeout = 100)
    //@ScoringWeight(3.125)
    public void testIsDeadFalse() {
        String message = "Pokemon::isDead check that " +
                "returns false when health is > 0";
        Pokemon p = new Pokemon("Pikachu", "Electric", 1, 3);
        assertFalse(message, p.isDead());
    }

    @Test(timeout = 100)
    //@ScoringWeight(6.25)
    public void testBoostHealthPower() {
        String message = "Pokemon::boostHealthPower check that " +
                "value is increased";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        p.boostHealthPower(3);
        assertEquals(message, 8, p.getHealthPower());

    }

    @Test(timeout = 100)
    //@ScoringWeight(3.125)
    public void testReduceHealthPower() {
        String message = "Pokemon::reduceHealthPower check that " +
                "value is reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        p.reduceHealthPower(3);
        assertEquals(message, 2, p.getHealthPower());

    }

    @Test(timeout = 100)
    //@ScoringWeight(3.125)
    public void testReduceHealthPowerLessThanZero() {
        String message = "Pokemon::reduceHealthPower check that " +
                "value is reduced to minimum 0";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        p.reduceHealthPower(6);
        assertEquals(message, 0, p.getHealthPower());
    }


    // Class methods 25%
    // battle 9
    // getAttackMultiplier 7
    // battleOracle 9

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon1OneIteration() {
        String message = "Pokemon::battle check when p1 wins in one iteration " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 5, 6);
        int res = Pokemon.battle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 2);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon2OneIteration() {
        String message = "Pokemon::battle check when p2 wins in one iteration " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 5, 6);
        int res = Pokemon.battle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 2);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon1TwoIterations() {
        String message = "Pokemon::battle check when p1 wins in two iterations " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 10, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 6);
        int res = Pokemon.battle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 4);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon2TwoIterations() {
        String message = "Pokemon::battle check when p2 wins in two iterations " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 10, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 6);
        int res = Pokemon.battle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 4);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon1ThreeIterations() {
        String message = "Pokemon::battle check when p1 wins in three iterations " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 15, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 15, 6);
        int res = Pokemon.battle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 6);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleWinPokemon2ThreeIterations() {
        String message = "Pokemon::battle check when p2 wins in three iterations " +
                "and make sure health is being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 15, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 15, 6);
        int res = Pokemon.battle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 0);
        assertEquals(p2.getHealthPower(), 6);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon1OneIteration() {
        String message = "Pokemon::battleOracle check when p1 wins in one iteration " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 5, 6);
        int res = Pokemon.battleOracle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 5);
        assertEquals(p2.getHealthPower(), 5);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon2OneIteration() {
        String message = "Pokemon::battleOracle check when p2 wins in one iteration " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 5, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 5, 6);
        int res = Pokemon.battleOracle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 5);
        assertEquals(p2.getHealthPower(), 5);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon1TwoIterations() {
        String message = "Pokemon::battleOracle check when p1 wins in two iterations " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 10, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 6);
        int res = Pokemon.battleOracle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 10);
        assertEquals(p2.getHealthPower(), 10);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon2TwoIterations() {
        String message = "Pokemon::battleOracle check when p2 wins in two iterations " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 10, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 6);
        int res = Pokemon.battleOracle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 10);
        assertEquals(p2.getHealthPower(), 10);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon1ThreeIterations() {
        String message = "Pokemon::battleOracle check when p1 wins in three iterations " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 15, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 15, 6);
        int res = Pokemon.battleOracle(p, p2);
        assertEquals(message, 2, res);
        assertEquals(p.getHealthPower(), 15);
        assertEquals(p2.getHealthPower(), 15);
    }

    @Test(timeout = 100)
    //@ScoringWeight(1.5)
    public void testBattleOracleWinPokemon2ThreeIterations() {
        String message = "Pokemon::battleOracle check when p2 wins in three iterations " +
                "and make sure health is NOT being reduced";
        Pokemon p = new Pokemon("Pikachu", "Electric", 15, 3);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 15, 6);
        int res = Pokemon.battleOracle(p2, p);
        assertEquals(message, 1, res);
        assertEquals(p.getHealthPower(), 15);
        assertEquals(p2.getHealthPower(), 15);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierFireAttacksFire() {
        String message = "Pokemon::getAttackMultiplier check when fire attacks fire";
        Pokemon p1 = new Pokemon("Charmander", "Fire", 10, 5);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierFireAttacksWater() {
        String message = "Pokemon::getAttackMultiplier check when fire attacks water";
        Pokemon p1 = new Pokemon("Charmander", "Fire", 10, 5);
        Pokemon p2 = new Pokemon("Squirtle", "Water", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierFireAttacksGrass() {
        String message = "Pokemon::getAttackMultiplier check when fire attacks grass";
        Pokemon p1 = new Pokemon("Charmander", "Fire", 10, 5);
        Pokemon p2 = new Pokemon("Metapod", "Grass", 10, 5);
        assertEquals(message, 2, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierFireAttacksElectric() {
        String message = "Pokemon::getAttackMultiplier check when fire attacks electric";
        Pokemon p1 = new Pokemon("Charmander", "Fire", 10, 5);
        Pokemon p2 = new Pokemon("Pikachu", "Electric", 10, 5);
        assertEquals(message, 1, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierWaterAttacksFire() {
        String message = "Pokemon::getAttackMultiplier check when water attacks fire";
        Pokemon p1 = new Pokemon("Squirtle", "Water", 10, 5);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 5);
        assertEquals(message, 2, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierWaterAttacksWater() {
        String message = "Pokemon::getAttackMultiplier check when water attacks water";
        Pokemon p1 = new Pokemon("Squirtle", "Water", 10, 5);
        Pokemon p2 = new Pokemon("Squirtle", "Water", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierWaterAttacksGrass() {
        String message = "Pokemon::getAttackMultiplier check when water attacks grass";
        Pokemon p1 = new Pokemon("Squirtle", "Water", 10, 5);
        Pokemon p2 = new Pokemon("Metapod", "Grass", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierWaterAttacksElectric() {
        String message = "Pokemon::getAttackMultiplier check when water attacks electric";
        Pokemon p1 = new Pokemon("Squirtle", "Water", 10, 5);
        Pokemon p2 = new Pokemon("Pikachu", "Electric", 10, 5);
        assertEquals(message, 1, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierElectricAttacksFire() {
        String message = "Pokemon::getAttackMultiplier check when electric attacks fire";
        Pokemon p1 = new Pokemon("Pikachu", "Electric", 10, 5);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 5);
        assertEquals(message, 1, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierElectricAttacksWater() {
        String message = "Pokemon::getAttackMultiplier check when electric attacks water";
        Pokemon p1 = new Pokemon("Pikachu", "Electric", 10, 5);
        Pokemon p2 = new Pokemon("Squirtle", "Water", 10, 5);
        assertEquals(message, 2, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierElectricAttacksGrass() {
        String message = "Pokemon::getAttackMultiplier check when electric attacks grass";
        Pokemon p1 = new Pokemon("Pikachu", "Electric", 10, 5);
        Pokemon p2 = new Pokemon("Metapod", "Grass", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierElecticAttacksElectric() {
        String message = "Pokemon::getAttackMultiplier check when electric attacks electric";
        Pokemon p1 = new Pokemon("Pikachu", "Electric", 10, 5);
        Pokemon p2 = new Pokemon("Pikachu", "Electric", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierGrassAttacksFire() {
        String message = "Pokemon::getAttackMultiplier check when grass attacks fire";
        Pokemon p1 = new Pokemon("Metapod", "Grass", 10, 5);
        Pokemon p2 = new Pokemon("Charmander", "Fire", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierGrassAttacksWater() {
        String message = "Pokemon::getAttackMultiplier check when grass attacks water";
        Pokemon p1 = new Pokemon("Metapod", "Grass", 10, 5);
        Pokemon p2 = new Pokemon("Squirtle", "Water", 10, 5);
        assertEquals(message, 2, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierGrassAttacksGrass() {
        String message = "Pokemon::getAttackMultiplier check when grass attacks grass";
        Pokemon p1 = new Pokemon("Metapod", "Grass", 10, 5);
        Pokemon p2 = new Pokemon("Metapod", "Grass", 10, 5);
        assertEquals(message, 0.5, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }

    @Test(timeout = 100)
    //@ScoringWeight(0.4375)
    public void testAttackMultiplierGrassAttacksElectric() {
        String message = "Pokemon::getAttackMultiplier check when grass attacks electric";
        Pokemon p1 = new Pokemon("Metapod", "Grass", 10, 5);
        Pokemon p2 = new Pokemon("Pikachu", "Electric", 10, 5);
        assertEquals(message, 1, Pokemon.getAttackMultiplier(p1, p2), 0.001);
    }


}