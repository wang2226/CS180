/**
 * Created by Bruce on 9/25/16.
 */
import javax.swing.*;
import java.lang.Math;

public class Team {
    private String name;
    private String location;
    private int nWins;
    private int nLosses;
    private double offense;
    private double defense;

    public String getName(){
        return name;
    }

    public String getLocaiton() {
        return location;
    }

    public int getN_Wins() {
        return nWins;
    }

    public int getN_Losses() {
        return nLosses;
    }

    public double getOffense() {
        return offense;
    }

    public double getDefense() {
        return defense;
    }

    public static double luck(){
        return Math.random();
    }
    public Team(String name, String location) {
        this.name = name;
        this.location = location;
        this.nWins = 0;
        this.nLosses = 0;
        this.offense = luck();
        this.defense = luck();
    }
    public static Team play(Team team1, boolean isHome, Team team2) {
        Team homeTeam, awayTeam;
        double homeScore, awayScore;
        if (isHome) {
            homeTeam = team1;
            awayTeam = team2;
        } else {
            homeTeam = team2;
            awayTeam = team1;
        }
        homeScore = (homeTeam.getOffense() + homeTeam.getDefense() + 0.2) * luck();
        awayScore = (awayTeam.getOffense() + awayTeam.getDefense()) * luck();
        if (homeScore > awayScore) {
            homeTeam.nWins = homeTeam.nWins + 1;
            awayTeam.nLosses = awayTeam.nLosses + 1;
            return homeTeam;
        } else if (homeScore < awayScore) {
            homeTeam.nLosses = homeTeam.nLosses + 1;
            awayTeam.nWins = awayTeam.nWins + 1;
            return awayTeam;
        }
        return null;
    }
    public int calcTotalGames() {
        return nWins + nLosses;
    }
    public double calcWinRate() {
        return (double)nWins / calcTotalGames();
    }
    public double calcLossRate() {
        return (double) nLosses / calcTotalGames();
    }
    public int calcDifference() {
        return Math.abs(nWins - nLosses);
    }
    public String generateStats() {
        String stats = getName() + "(" + getLocaiton() + ")"
                + "\nTotal games: " + calcTotalGames()
                + "\nNo. wins: " + getN_Wins() + " (" + String.format("%.2f",100 * calcWinRate()) + "%)"
                + "\nNo. losses: " + getN_Losses() + "(" + String.format("%.2f", 100 * calcLossRate()) + "%)"
                + "\nDifference: " + calcDifference();
        return stats;
    }
    public static void main(String[] args) {
        String name, location, information;

        String title="Input";
        String message="Enter the name and location for home team separated by a comma (,): ";

        information = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
        name = information.substring(0,information.indexOf(","));
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        location = information.substring(information.indexOf(",")+1,information.length());
        location = location.substring(0, 1).toUpperCase() + location.substring(1).toLowerCase();
        Team team1 = new Team(name, location);

        message="Enter the name and location for away team separated by a comma (,): ";

        information = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
        name = information.substring(0,information.indexOf(","));
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        location = information.substring(information.indexOf(",")+1,information.length());
        location = location.substring(0, 1).toUpperCase() + location.substring(1).toLowerCase();
        Team team2 = new Team(name,location);

        title="Message";
        message="Home team is " + team1.getName() + " from " + team1.getLocaiton() + " rated "
                + String.format("%.2f", team1.getOffense()) + "(offense) " + String.format("%.2f", team1.getDefense()) + "(defense)";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
        message="Away team is " + team2.getName() + " from " + team2.location + " rated "
                + String.format("%.2f", team2.getOffense()) + "(offense) " + String.format("%.2f", team2.getDefense()) + "(defense)";
        JOptionPane.showMessageDialog(null, message,title,JOptionPane.INFORMATION_MESSAGE);

        Team winTeam=play(team1,true, team2);
        message="Round1" +
                "\nWinner is: " + winTeam.getName() + " from " + winTeam.getLocaiton()
                + " rated " + String.format("%.2f", winTeam.getOffense()) + "(offense) "
                + String.format("%.2f", winTeam.getDefense()) + "(defense)";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

        winTeam=play(team1,true, team2);
        message="Round2" +
                "\nWinner is: " + winTeam.getName() + " from " + winTeam.getLocaiton()
                + " rated " + String.format("%.2f", winTeam.getOffense()) + "(offense) "
                + String.format("%.2f", winTeam.getDefense()) + "(defense)";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

        winTeam=play(team1,true, team2);
        message="Round3" +
                "\nWinner is: " + winTeam.getName() + " from " + winTeam.getLocaiton()
                + " rated " + String.format("%.2f", winTeam.getOffense()) + "(offense) "
                + String.format("%.2f", winTeam.getDefense()) + "(defense)";
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null,team1.generateStats(),title,JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,team2.generateStats(),title,JOptionPane.INFORMATION_MESSAGE);

    }

}
