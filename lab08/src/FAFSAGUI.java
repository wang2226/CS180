import javax.swing.*;

/**
 * Created by Bruce on 17/10/2016.
 */

public class FAFSAGUI {

    public static boolean intToBoolean(int number) {
        if (number == JOptionPane.YES_OPTION)
            return true;
        return false;
    }

    public static void main(String[] args) {
        boolean keepGoing = true;

        while (keepGoing) {
            JOptionPane.showMessageDialog(null, "Welcome to the FAFSA!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

            int response = JOptionPane.showOptionDialog(null, "Have you been accepted into a degree or certificate program?", "Program Acceptance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            boolean isAcceptedStudent = intToBoolean(response);

            response = JOptionPane.showOptionDialog(null, "Are you registered for the selective service?", "Selective Service", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            boolean isSSregistered = intToBoolean(response);

            response = JOptionPane.showOptionDialog(null, "Do you have a social security number?", "Social Security Number", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            boolean hasSSN = intToBoolean(response);

            response = JOptionPane.showOptionDialog(null, "Do you have valid residency status?", "Residence Status", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            boolean hasValidResidency = intToBoolean(response);

            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "How old are you?", "Age",JOptionPane.QUESTION_MESSAGE));
            while (age < 0) {
                JOptionPane.showMessageDialog(null, "Age cannot be a negative number.", "Error: Age", JOptionPane.ERROR_MESSAGE);
                age = Integer.parseInt(JOptionPane.showInputDialog(null, "How old are you?", "Age", JOptionPane.QUESTION_MESSAGE));
            }

            int creditHours = Integer.parseInt(JOptionPane.showInputDialog(null, "How many credit hours do you plan on taking?", "Credit Hours", JOptionPane.QUESTION_MESSAGE));
            while (creditHours < 1 || creditHours > 24) {
                JOptionPane.showMessageDialog(null, "Credit hours must be between 1 and 24, inclusive.", "Error: Credit Hours", JOptionPane.ERROR_MESSAGE);
                creditHours = Integer.parseInt(JOptionPane.showInputDialog(null, "How many credit hours do you plan on taking?", "Credit Hours", JOptionPane.QUESTION_MESSAGE));
            }

            double studentIncome = Double.parseDouble(JOptionPane.showInputDialog(null, "What is your total yearly income?", "Student Income", JOptionPane.QUESTION_MESSAGE));
            while (studentIncome < 0) {
                JOptionPane.showMessageDialog(null, "Income cannot be a negative number", "Error: Student Income", JOptionPane.ERROR_MESSAGE);
                studentIncome = Double.parseDouble(JOptionPane.showInputDialog(null, "What is your total yearly income?", "Student Income", JOptionPane.QUESTION_MESSAGE));
            }

            double parentIncome = Double.parseDouble(JOptionPane.showInputDialog(null, "What is your parent's total yearly income?", "Parent Income", JOptionPane.QUESTION_MESSAGE));
            while (parentIncome < 0) {
                JOptionPane.showMessageDialog(null, "Income cannot be a negative number", "Error: Parent Income", JOptionPane.ERROR_MESSAGE);
                parentIncome = Double.parseDouble(JOptionPane.showInputDialog(null, "What is your parent's total yearly income?", "Parent Income", JOptionPane.QUESTION_MESSAGE));
            }

            response = JOptionPane.showOptionDialog(null, "Are you a dependent?", "Dependency", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            boolean isDependent = intToBoolean(response);

            String[] classStandingSelection = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
            String selection = (String)JOptionPane.showInputDialog(null, "What is your current class standing?", "Class Standing", JOptionPane.PLAIN_MESSAGE, null, classStandingSelection, classStandingSelection[0]);
            if(!selection.equals("Graduate")) {
                selection = "Undergraduate";
            }
            String classStanding = selection;

            FAFSA fafsa = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN, hasValidResidency, isDependent, age, creditHours, studentIncome, parentIncome, classStanding);

            double loan = fafsa.calcStaffordLoan();
            double grant = fafsa.calcFederalGrant();
            double workStudy = fafsa.calcWorkStudy();
            double totalAwards = fafsa.calcFederalAidAmount();

            JOptionPane.showMessageDialog(null, "Loans: " + "$" + loan
                    + "\nGrants: " + "$" + grant
                    + "\nWorkStudy: " + "$" + workStudy
                    + "\n----------------"
                    + "\nTotal: " + "$" + totalAwards,  "FAFSA Results", JOptionPane.INFORMATION_MESSAGE);

            int input = JOptionPane.showOptionDialog(null, "Would you like to complete another Application?", "Continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (input == JOptionPane.NO_OPTION)
                keepGoing = false;
        }

    }
}
