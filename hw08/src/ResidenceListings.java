import java.util.NoSuchElementException;

/**
 * Created by Bruce on 10/30/16.
 */
public class ResidenceListings extends Throwable {
    private int numResidences;
    private int maxResidences;
    private Residence[] residences;

    public ResidenceListings() {
        this.numResidences = 0;
        this.maxResidences = 10;
        this.residences = new Residence[maxResidences];
    }

    public void addResidence(Residence residence) {
        //decide whether the array is full first
        for (int i = 0; i < maxResidences; i++) {
            if (residences[i] == null) {
                residences[i] = residence;
                numResidences = numResidences + 1;
                return;
            }
        }

        //means array is full: numResidences equal ro maxResidences
        maxResidences = 2 * maxResidences;
        Residence[] tmpArray = new Residence[maxResidences];

        //move the array first
        for (int i = 0; i < residences.length; i++) {
            tmpArray[i] = residences[i];
        }

        tmpArray[residences.length] = residence;
        numResidences = numResidences + 1;
        residences = tmpArray;
    }

    public void removeResidence(Residence residence) throws NoSuchResidenceException {
        boolean removeFlag = false;
        for (int i = 0; i < residences.length; i++) {
            if (residences[i] != null && residences[i].equals(residence)) {
                for (int j = i; j < (residences.length - 1); j++) {
                    residences[j] = residences[j + 1];
                    removeFlag = true;
                }
                numResidences = numResidences - 1;
            }
        }
        //The last non-null entry must be nulled out
        if (residences[residences.length - 1] != null) {
            residences[residences.length - 1] = null;
        }

        if (!removeFlag) {
            String errorString = "Residence with following properties not found \n" +
                    residence.toString();
            throw new NoSuchResidenceException(errorString);
        }

    }

    public Residence findResidenceByAddress(String address) {
        for (int i = 0; i < numResidences; i++) {
            if (residences[i].getAddress().equals(address)) {
                return residences[i];
            }
        }
        return null;
    }

    public int getNumResidences() {
        return numResidences;
    }

    public int getMaxResidences() {
        return maxResidences;
    }

    public Residence[] getResidences() {
        return residences;
    }
}
