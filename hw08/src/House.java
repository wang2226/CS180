/**
 * Created by Bruce on 10/30/16.
 */
public class House extends Residence {
    private int numFloors;
    private boolean hasGarage;

    public House(String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int numFloors, boolean hasGarage) {
        super(address,numBedrooms,numBathrooms,squareFootage,monthlyRent);
        this.numFloors = numFloors;
        this.hasGarage = hasGarage;
    }

    public int getNumFloors(){
        return numFloors;
    }

    public boolean hasGarage() {
        return hasGarage;
    }

    public String toString() {
        return "Address: " + getAddress() +
                "\nNumber of Bedrooms: " + getNumBedrooms() +
                "\nNumber of Bathrooms: " + getNumBathrooms() +
                "\nSquare footage " + getSquareFootage() +
                "\nMonthly Rent: " + getMonthlyRent() +
                "\nNumber of floors: " + getNumFloors() +
                "\nHas Garage?" + hasGarage();
    }
}
