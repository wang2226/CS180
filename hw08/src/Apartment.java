/**
 * Created by Bruce on 10/30/16.
 */
public class Apartment extends Residence {
    private int floorNumber;

    public Apartment(String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int floorNumber) {
        super(address,numBedrooms,numBathrooms,squareFootage,monthlyRent);
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String toString() {
        return "Address: " + getAddress() +
                "\nNumber of Bedrooms: " + getNumBedrooms() +
                "\nNumber of Bathrooms: " + getNumBathrooms() +
                "\nSquare footage " + getSquareFootage() +
                "\nMonthly Rent: " + getMonthlyRent() +
                "\nFloor Number: " + getFloorNumber();
    }
}
