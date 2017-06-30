/**
 * Created by Bruce on 9/17/16.
 */
import java.util.Scanner;
public class Movie {
    private String name;
    private double criticRating;
    private double usersRating;
    private int numUsersRatings;

    public Movie(String name, double criticRating, double usersRating, int numUsersRatings) {
        this.name = name;

        if (criticRating >=1 && criticRating <=5) {
            this.criticRating = criticRating;
        }
        else this.criticRating = 5;

        if (usersRating >=1 && usersRating <=5) {
            this.usersRating = usersRating;
        }
        else this.usersRating = 5;

        this.numUsersRatings = numUsersRatings;
    }

    public String getName() {
        return name;
    }

    public double getCriticRating() {
        return criticRating;
    }

    public double getUsersRating() {
        return usersRating;
    }

    public int getNumUsersRatings() {
        return numUsersRatings;
    }

    public boolean addUserRating(int newRating) {
        if (newRating >= 1 && newRating <=5) {
            usersRating = (usersRating*numUsersRatings + newRating)/(numUsersRatings + 1);
            numUsersRatings = numUsersRatings + 1;
            return true;
        }
        return false;
    }

    private int getReviewRange(double numUsersRaging) {
        if (numUsersRaging >= 0 && numUsersRaging <=1000)
            return 1;
        else if (numUsersRaging >= 1001 && numUsersRaging <=5000)
            return 2;
        else if (numUsersRaging >= 5001 && numUsersRaging <= 10000)
            return 3;
        else if (numUsersRaging >= 10001 && numUsersRaging <= 15000)
            return 4;
        else if (numUsersRaging >= 15001 && numUsersRaging <= 20000)
            return 5;
        else if (numUsersRaging >= 20001 && numUsersRaging <= 25000)
            return 6;
        else if (numUsersRaging >= 25001 && numUsersRaging <= 30000)
            return 7;
        else if (numUsersRaging >= 30001 && numUsersRaging <= 50000)
            return 8;
        else if (numUsersRaging >= 50001 && numUsersRaging <= 100000)
            return 9;
        else if (numUsersRaging > 100000)
            return 10;
        return -1;
    }

    private static double getSmartScore(Movie movie) {
        int reviewRange = movie.getReviewRange(movie.numUsersRatings);
        return 0.5 * movie.criticRating + 0.3 * movie.usersRating + 0.1 * reviewRange;
    }

    public static int compareMovies(Movie movie1, Movie movie2) {
        if (movie1.criticRating > movie2.criticRating && movie1.usersRating >= movie2.usersRating) {
            return 1;
        } else if (movie1.criticRating > movie2.criticRating && movie1.usersRating < movie2.usersRating) {
            if (getSmartScore(movie1) > getSmartScore(movie2)) {
                return 1;
            }
            else if (getSmartScore(movie1) == getSmartScore(movie2)) {
                return 0;
            } else return 2;
        } else if (movie1.criticRating == movie2.criticRating && movie1.usersRating > movie2.usersRating) {
            return 1;
        } else if (movie1.criticRating == movie2.criticRating && movie1.usersRating < movie2.usersRating) {
            return 2;
        }
        else if (movie1.criticRating == movie2.criticRating && movie1.usersRating == movie2.usersRating) {
            return 0;
        } else if (movie1.criticRating < movie2.criticRating && movie1.usersRating <= movie2.usersRating) {
            return 2;
        } else if (movie1.criticRating < movie2.criticRating && movie1.usersRating > movie2.usersRating) {
            if (getSmartScore(movie1) < getSmartScore(movie2)) {
                return 2;
            }
            else if (getSmartScore(movie1) == getSmartScore(movie2)) {
                return 0;
            } else return 1;
        }
        return -1;
    }

    public static void main(String[]  args) {
        Scanner s = new Scanner(System.in);
        String name = "";
        double criticRating = 0.0;
        double usersRating = 0.0;
        int numUsersRating = 0;
        int adduserRate = 0;

        System.out.println("Enter the name of the movie1");
        name = s.next();
        System.out.println("Enter the critic rating of " + name);
        criticRating = s.nextDouble();
        System.out.println("Enter the user's rating of " + name);
        usersRating = s.nextDouble();
        System.out.println("Enter the number of ratings given by users of " + name);
        numUsersRating = s.nextInt();

        Movie movie1 = new Movie(name,criticRating,usersRating,numUsersRating);

        System.out.println("Enter the name of the movie2");
        name = s.next();
        System.out.println("Enter the critic rating of " + name);
        criticRating = s.nextDouble();
        System.out.println("Enter the user's rating of " + name);
        usersRating = s.nextDouble();
        System.out.println("Enter the number of ratings given by users of " + name);
        numUsersRating = s.nextInt();

        Movie movie2 = new Movie(name,criticRating,usersRating,numUsersRating);

        System.out.println("Add the user's rating for " + movie1.getName());
        adduserRate = s.nextInt();
        if(movie1.addUserRating(adduserRate))
            System.out.println("Add User Rating for " +  movie1.getName() + " Success!");

        System.out.println("Add the user's rating for " + movie2.getName());
        adduserRate = s.nextInt();
        if(movie2.addUserRating(adduserRate))
            System.out.println("Add User Rating for " +  movie2.getName() + " Success!");

        System.out.println(movie1.getName() + "'s critic rating: " +  String.format("%.2f",movie1.getCriticRating()));
        System.out.println(movie1.getName() + "'s user's rating: " +  String.format("%.2f",movie1.getUsersRating()));
        System.out.println(movie1.getName() + "'s number of ratings: " + movie1.getNumUsersRatings());
        System.out.println(movie2.getName() + "'s critic rating: " +  String.format("%.2f",movie2.getCriticRating()));
        System.out.println(movie2.getName() + "'s user's rating: " +  String.format("%.2f",movie2.getUsersRating()));
        System.out.println(movie2.getName() + "'s number of ratings: " + movie2.getNumUsersRatings());

        switch (compareMovies(movie1,movie2)){
            case 1:
                System.out.println(movie1.getName() + " is better");
                break;
            case 2:
                System.out.println(movie2.getName() + " is better");
                break;
            case 0:
                System.out.println("two movies are considered equally good");
                break;
            default:
                System.out.println("There is bug in the compareMovies method!!!");
        }

    }
}