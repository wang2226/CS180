/**
 * Created by Bruce on 10/30/16.
 */
public class Residence {
    private String address;
    private int numBedrooms;
    private int numBathrooms;
    private int squareFootage;
    private double monthlyRent;

    public Residence(String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent) {
        this.address = address;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.squareFootage = squareFootage;
        this.monthlyRent = monthlyRent;
    }

    public String getAddress() {
        return address;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

}
