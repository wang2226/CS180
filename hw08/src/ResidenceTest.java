import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * JUnit tests for CS18000-F16 inheritance homework, Residence.
 *
 * @author Neil Allison
 */
public class ResidenceTest {
    public static final double DELTA = 1e-2;
    public static Random r;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUp() {
        r = new Random();
    }

    @Test(timeout = 100)
    public void testResidenceConstructor() {
        String message = "Residence constructor must assign its arguments to the corresponding instance variables";
        String expectedAddress = String.valueOf(r.nextDouble());
        int expectedNumBedrooms = r.nextInt();
        int expectedNumBathrooms = r.nextInt();
        int expectedSquareFootage = r.nextInt();
        double expectedMonthlyRent = r.nextDouble();
        Residence residence = new Residence(expectedAddress, expectedNumBedrooms, expectedNumBathrooms, expectedSquareFootage, expectedMonthlyRent);
        Class clazz = residence.getClass();

        try {
            Field actualAddressField = clazz.getDeclaredField("address");
            actualAddressField.setAccessible(true);
            assertEquals(message, expectedAddress, actualAddressField.get(residence));
            actualAddressField.setAccessible(false);

            Field actualNumBedroomsField = clazz.getDeclaredField("numBedrooms");
            actualNumBedroomsField.setAccessible(true);
            assertEquals(message, expectedNumBedrooms, actualNumBedroomsField.getInt(residence));
            actualNumBedroomsField.setAccessible(false);

            Field actualNumBathroomsField = clazz.getDeclaredField("numBathrooms");
            actualNumBathroomsField.setAccessible(true);
            assertEquals(message, expectedNumBathrooms, actualNumBathroomsField.getInt(residence));
            actualNumBathroomsField.setAccessible(false);

            Field actualSquareFootageField = clazz.getDeclaredField("squareFootage");
            actualSquareFootageField.setAccessible(true);
            assertEquals(message, expectedSquareFootage, actualSquareFootageField.getInt(residence));
            actualSquareFootageField.setAccessible(false);

            Field actualMonthlyRentField = clazz.getDeclaredField("monthlyRent");
            actualMonthlyRentField.setAccessible(true);
            assertEquals(message, expectedMonthlyRent, actualMonthlyRentField.getDouble(residence), DELTA);
            actualMonthlyRentField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. Make sure you are using the correct case and spelling.");
        }
        catch (IllegalAccessException e)
        {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    public void testResidenceGetters() {
        String message = "Getter methods should return their corresponding instance variable";
        String expectedAddress = String.valueOf(r.nextDouble());
        int expectedNumBedrooms = r.nextInt();
        int expectedNumBathrooms = r.nextInt();
        int expectedSquareFootage = r.nextInt();
        double expectedMonthlyRent = r.nextDouble();
        Residence residence = new Residence(expectedAddress, expectedNumBedrooms, expectedNumBathrooms, expectedSquareFootage, expectedMonthlyRent);

        assertEquals(message, expectedAddress, residence.getAddress());
        assertEquals(message, expectedNumBedrooms, residence.getNumBedrooms());
        assertEquals(message, expectedNumBathrooms, residence.getNumBathrooms());
        assertEquals(message, expectedSquareFootage, residence.getSquareFootage());
        assertEquals(message, expectedMonthlyRent, residence.getMonthlyRent(), DELTA);
    }

    @Test(timeout = 100)
    public void testApartmentExtendsResidence() {
        assertTrue("Apartment must be a subclass of Residence", Residence.class.isAssignableFrom(Apartment.class));
    }

    @Test(timeout = 100)
    public void testApartmentConstructor() {
        String message = "Apartment constructor must assign its arguments to the corresponding instance variables";
        int expectedFloorNumber = r.nextInt();
        Apartment apartment = new Apartment(String.valueOf(r.nextDouble()), r.nextInt(), r.nextInt(), r.nextInt(), r.nextDouble(), expectedFloorNumber);
        Class clazz = apartment.getClass();

        try {
            Field actualFloorNumberField = clazz.getDeclaredField("floorNumber");
            actualFloorNumberField.setAccessible(true);
            assertEquals(message, expectedFloorNumber, actualFloorNumberField.getInt(apartment));
            actualFloorNumberField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. Make sure you are using the correct case and spelling.");
        }
        catch (IllegalAccessException e)
        {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    public void testApartmentGetters() {
        String message = "Getter methods should return their corresponding instance variable";
        String expectedAddress = String.valueOf(r.nextDouble());
        int expectedNumBedrooms = r.nextInt();
        int expectedNumBathrooms = r.nextInt();
        int expectedSquareFootage = r.nextInt();
        double expectedMonthlyRent = r.nextDouble();
        int expectedFloorNumber = r.nextInt();
        Apartment residence = new Apartment(expectedAddress, expectedNumBedrooms, expectedNumBathrooms, expectedSquareFootage, expectedMonthlyRent, expectedFloorNumber);

        assertEquals(message, expectedAddress, residence.getAddress());
        assertEquals(message, expectedNumBedrooms, residence.getNumBedrooms());
        assertEquals(message, expectedNumBathrooms, residence.getNumBathrooms());
        assertEquals(message, expectedSquareFootage, residence.getSquareFootage());
        assertEquals(message, expectedMonthlyRent, residence.getMonthlyRent(), DELTA);
        assertEquals(message, expectedFloorNumber, residence.getFloorNumber());
    }

    @Test(timeout = 100)
    public void testApartmentOverridesToString() {
        try {
            assertEquals("Apartment must override the toString method", Apartment.class, Apartment.class.getMethod("toString").getDeclaringClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 100)
    public void testHouseExtendsResidence() {
        assertTrue("House must be a subclass of Residence", Residence.class.isAssignableFrom(House.class));
    }

    @Test(timeout = 100)
    public void testHouseConstructor() {
        String message = "House constructor must assign its arguments to the corresponding instance variables";
        int expectedNumFloors = r.nextInt();
        boolean expectedHasGarage = r.nextBoolean();
        House house = new House(String.valueOf(r.nextDouble()), r.nextInt(), r.nextInt(), r.nextInt(), r.nextDouble(), expectedNumFloors, expectedHasGarage);
        Class clazz = house.getClass();

        try {
            Field actualNumFloors = clazz.getDeclaredField("numFloors");
            actualNumFloors.setAccessible(true);
            assertEquals(message, expectedNumFloors, actualNumFloors.getInt(house));
            actualNumFloors.setAccessible(false);

            Field actualHasGarage = clazz.getDeclaredField("hasGarage");
            actualHasGarage.setAccessible(true);
            assertEquals(message, expectedHasGarage, actualHasGarage.getBoolean(house));
            actualHasGarage.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. Make sure you are using the correct case and spelling.");
        }
        catch (IllegalAccessException e)
        {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    public void testHouseGetters() {
        String message = "Getter methods should return their corresponding instance variable";
        String expectedAddress = String.valueOf(r.nextDouble());
        int expectedNumBedrooms = r.nextInt();
        int expectedNumBathrooms = r.nextInt();
        int expectedSquareFootage = r.nextInt();
        double expectedMonthlyRent = r.nextDouble();
        int expectedNumFloors = r.nextInt();
        boolean expectedHasGarage = r.nextBoolean();
        House residence = new House(expectedAddress, expectedNumBedrooms, expectedNumBathrooms, expectedSquareFootage, expectedMonthlyRent, expectedNumFloors, expectedHasGarage);

        assertEquals(message, expectedAddress, residence.getAddress());
        assertEquals(message, expectedNumBedrooms, residence.getNumBedrooms());
        assertEquals(message, expectedNumBathrooms, residence.getNumBathrooms());
        assertEquals(message, expectedSquareFootage, residence.getSquareFootage());
        assertEquals(message, expectedMonthlyRent, residence.getMonthlyRent(), DELTA);
        assertEquals(message, expectedNumFloors, residence.getNumFloors());
        assertEquals(message, expectedHasGarage, residence.hasGarage());
    }

    @Test(timeout = 100)
    public void testHouseOverridesToString() {
        try {
            assertEquals("House must override the toString method", House.class, House.class.getMethod("toString").getDeclaringClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 100)
    public void testCustomExceptionConstructor() {
        assertEquals("NoSuchResidenceException constructor should take a single String argument and pass it to the " +
                "superclass constructor", (new NoSuchResidenceException("Exception message")).getMessage(), "Exception message");
    }

    @Test(timeout = 100)
    public void testCustomExceptionExtendsException() {
        assertTrue("NoSuchResidenceException must be a subclass of Exception", Exception.class.isAssignableFrom(NoSuchResidenceException.class));
    }

    @Test(timeout = 100)
    public void testResidenceListingsConstructor() {
        String message = "ResidenceListings constructor must initialize the instance variables numResidence to 0, " +
                "maxResidences to 10, and residences to a new Residence array of size 10";
        ResidenceListings residenceListings = new ResidenceListings();
        Class clazz = residenceListings.getClass();

        try {
            Field numResidencesField = clazz.getDeclaredField("numResidences");
            numResidencesField.setAccessible(true);
            assertEquals(message, 0, numResidencesField.getInt(residenceListings));
            numResidencesField.setAccessible(false);

            Field maxResidencesField = clazz.getDeclaredField("maxResidences");
            maxResidencesField.setAccessible(true);
            assertEquals(message, 10, maxResidencesField.getInt(residenceListings));
            maxResidencesField.setAccessible(false);

            Field residencesField = clazz.getDeclaredField("residences");
            residencesField.setAccessible(true);
            assertNotNull(message, residencesField.get(residenceListings));
            assertEquals(message, 10, ((Residence[]) residencesField.get(residenceListings)).length);
            residencesField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. Make sure you are using the correct case and spelling.");
        }
        catch (IllegalAccessException e)
        {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    public void testResidenceListingsAddResidence() {
        ResidenceListings rl = new ResidenceListings();
        Residence residenceToAdd;
        for (int i = 0; i < 10; i++) {
            residenceToAdd = new Residence(String.valueOf(i), 0, 0, 0, 0);
            rl.addResidence(residenceToAdd);
            assertEquals("Each new Residence must be added at the first non-null position in the 'residences' array",
                    residenceToAdd, rl.getResidences()[i]);
        }
        residenceToAdd = new Residence("10", 0, 0, 0, 0);
        rl.addResidence(residenceToAdd);
        assertEquals("numResidences must be incremented by 1 each time a new Residence is added", 11, rl.getNumResidences());
        assertEquals("maxResidences must reflect the length of the array 'residences'", 20, rl.getMaxResidences());
        assertEquals("You must double the size of the array 'residences' if it is full and a new element is to be added",
                20, rl.getResidences().length);
        String message = "Make sure all the Residences remain in the same order in the array 'residences' when doubling its size";
        for (int i = 0; i < 10; i++) {
            assertNotNull(message, rl.getResidences()[i]);
            assertEquals(message, String.valueOf(i), rl.getResidences()[i].getAddress());
        }
        assertNull("All elements after the last added element in the array should be null", rl.getResidences()[11]);
    }

    @Test(timeout = 100)
    public void testResidenceListingsRemoveResidence() throws Exception {
        ResidenceListings rl = new ResidenceListings();
        Residence residenceToAdd;
        for (int i = 0; i < 5; i++) {
            residenceToAdd = new Residence(String.valueOf(i), 0, 0, 0, 0);
            rl.addResidence(residenceToAdd);
        }
        Residence residenceToRemove = new Residence("Remove Residence", 0, 0 ,0 ,0);
        rl.addResidence(residenceToRemove);
        for (int i = 6; i < 10; i++) {
            residenceToAdd = new Residence(String.valueOf(i), 0, 0, 0, 0);
            rl.addResidence(residenceToAdd);
            assertEquals("Each new Residence must be added at the first non-null position in the 'residences' array",
                    residenceToAdd, rl.getResidences()[i]);
        }
        rl.removeResidence(residenceToRemove);
        for (int i = 0; i < 5; i++) {
            assertEquals("Make sure all Residences remain in the same order in the array 'residences' when removing a Residence",
                    String.valueOf(i), rl.getResidences()[i].getAddress());
        }
        for (int i = 5; i < 9; i++) {
            assertEquals("The entries to the right of the removed Residence must be shifted to the left",
                    String.valueOf(i + 1), rl.getResidences()[i].getAddress());
        }
        assertNull("The last non-null entry must be nulled out after shifting entries left when removing a Residence",
                rl.getResidences()[9]);
    }

    @Test(timeout = 100)
    public void testResidenceListingsRemoveResidenceThrowsException() throws NoSuchResidenceException {
        ResidenceListings rl = new ResidenceListings();
        exception.expect(NoSuchResidenceException.class);
        rl.removeResidence(new Residence("", 0, 0 ,0, 0));
    }

    @Test(timeout = 100)
    public void testResidenceListingsFindResidenceByAddress() {
        ResidenceListings rl = new ResidenceListings();
        Residence residenceToAdd = null;
        for (int i = 0; i < 10; i++) {
            residenceToAdd = new Residence(String.valueOf(i), 0, 0, 0, 0);
            rl.addResidence(residenceToAdd);
        }
        assertEquals("You must return the Residence in the 'residences' array who's address is the same as the argument " +
                "address passed to the findResidenceByAddress method", residenceToAdd, rl.findResidenceByAddress("9"));
    }

    @Test(timeout = 100)
    public void testResidenceListingsFindResidenceByAddressNotFound() {
        ResidenceListings rl = new ResidenceListings();
        Residence residenceToAdd;
        for (int i = 0; i < 10; i++) {
            residenceToAdd = new Residence(String.valueOf(i), 0, 0, 0, 0);
            rl.addResidence(residenceToAdd);
        }
        assertNull("You must return null if there is no Residence in the 'residences' with the address specified as " +
                "the argument address passed to the findResidenceByAddress method", rl.findResidenceByAddress("NotFound"));
    }

    @Test(timeout = 100)
    public void testResidenceListingsGetters() {
        String message = "Getter methods should return their corresponding instance variable";
        ResidenceListings residenceListings = new ResidenceListings();
        assertEquals(message, 0, residenceListings.getNumResidences());
        assertEquals(message, 10, residenceListings.getMaxResidences());
        try {
            Field residencesField = residenceListings.getClass().getDeclaredField("residences");
            residencesField.setAccessible(true);
            assertEquals(message, residencesField.get(residenceListings), residenceListings.getResidences());
            residencesField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("Your instance variable for 'Residence[] residences' was spelled incorrectly or did not exist");
        } catch (IllegalAccessException e) {
            fail("Your instance variables must be declared as private");
        }
    }
}
