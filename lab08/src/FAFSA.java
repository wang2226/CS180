import javax.swing.*;

/**
 * The FAFSA class keeps track of information about a student and uses it to 
 * determine the student's eligibility for federal financial aid and the total
 * estimated amount of aid awarded to those who are eligible.
 * 
 * @author Neil Allison, nalliso@purdue.edu
 *
 */
public class FAFSA {
	// Three basic requirements
	boolean isAcceptedStudent;
	boolean isSSregistered;
	boolean hasSSN;
	
	// One of four required
	boolean hasValidResidency;
	
	// Student demographics
	boolean isDependent;
	int age;
	int creditHours;
	double studentIncome;
	double parentIncome;
	String classStanding;

	/**
	 * Constructor. Initializes the member variables for this student.
	 * 
	 * @param isAcceptedStudent The student is accepted into higher education
	 * @param isSSregistered The student is registered for selective service
	 * @param hasSSN The student has a social security number
	 * @param hasValidResidency The student has valid residency status
	 * @param age The student's age
	 * @param creditHours Number of credit hours the student is taking
	 * @param studentIncome The student's income
	 * @param parentIncome The student's parents' income
	 * @param classStanding The student's current class standing
	 */
	public FAFSA(boolean isAcceptedStudent, boolean isSSregistered, 
			boolean hasSSN, boolean hasValidResidency, boolean isDependent, 
			int age, int creditHours, double studentIncome, double parentIncome,
			String classStanding) {
		this.isAcceptedStudent = isAcceptedStudent;
		this.isSSregistered = isSSregistered;
		this.hasSSN = hasSSN;
		this.hasValidResidency = hasValidResidency;
		this.isDependent = isDependent;
		this.age = age;
		this.creditHours = creditHours;
		this.studentIncome = studentIncome;
		this.parentIncome = parentIncome;
		this.classStanding = classStanding;
	}
	
	/**
	 * Calculates the students expected family contribution. If the student is
	 * a dependent, then their EFC is calculated by the sum of the students 
	 * income and their parents income, else if it just the students income.
	 * 
	 * @return The students expected family contribution
	 */
	public double calcEFC() {
		return isDependent ? studentIncome + parentIncome : studentIncome;
	}
	
	/**
	 * Calculates the student's total Stafford loan award. The Stafford loan is 
	 * only available for students having 9 or more credit hours. The amount 
	 * of the award is calculated by the students dependency status and which
	 * year they are in school. For dependents, the awards are:
	 * Undergraduate: 5000
	 * Graduate: 10000
	 * 
	 * For independents:
	 * Undergraduate: 10000
	 * Graduate: 20000
	 * 
	 * @return The student's calculated Stafford loan award amount
	 */
	public double calcStaffordLoan() {
		if (classStanding == null) {
			return 0;
		}
		
		if (creditHours < 9) {
			return 0;
		}
		
		if (isDependent) {
			if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
				return 5000;
			} else if (classStanding.equalsIgnoreCase("GRADUATE")){
				return 10000;
			} else {
				return 0;
			}
		} else {
			if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
				return 10000;
			} else if (classStanding.equalsIgnoreCase("GRADUATE")){
				return 20000;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Calculates the student's federal grant award. The students EFC must be
	 * less than or equal to 50000 and they must be an undergraduate. If they
	 * have less than 9 credit hours, the award is 2500 and if they have 9 or
	 * more credit hours, the award is 5775;
	 * 
	 * @return The student's calculated federal grant award amount
	 */
	public double calcFederalGrant() {
		if (classStanding == null) {
			return 0;
		}
		
		if (calcEFC() > 50000) {
			return 0;
		}
		
		if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
			if (creditHours < 9) {
				return 2500;
			} else {
				return 5775;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * Calculates the student's work study award. The work study award is
	 * based on the student's EFC, broken down as follows:
	 * 0 - 30,000: 1500
	 * 30,000.01 - 40,000: 1000
	 * 40,000.01 - 50,000: 500
	 * > 50,000: 0
	 * 
	 * @return The student's calculated federal grant award amount
	 */
	public double calcWorkStudy() {
		if (calcEFC() > 50000) {
			return 0;
		} else if (calcEFC() > 40000) {
			return 500;
		} else if (calcEFC() > 30000) {
			return 1000;
		} else {
			return 1500;
		}
	}
	
	/**
	 * Calculates the student's total combined federal aid award. The total
	 * aid award is calculated as the sum of Stafford loan award, federal grant
	 * award, and work study award.
	 * 
	 * @return The student's total aid award amount
	 */
	public double calcFederalAidAmount() {
		if (!isFederalAidEligible()) {
			return 0;
		}
		
		double stafford = calcStaffordLoan();
		double grants = calcFederalGrant();
		double workStudy = calcWorkStudy();
		
		return stafford + grants + workStudy;
	}
	
	/**
	 * Determines if the student is eligible for federal financial aid. To be 
	 * eligible, the student must be an accepted student to a higher education
	 * program (isAcceptedStudent), must be registered with the selective service
	 * (isSSregistered) if they are between  the ages of 18-25 inclusive, must
	 * have a social security number (hasSSN), and must have valid residency 
	 * status (hasValidResidency).
	 * 
	 * @return True if the student is aid eligible and false otherwise
	 */
	public boolean isFederalAidEligible() {
		if ((age >= 18 && age <= 25) && !isSSregistered) {
			return false;
		}
		
		if (isAcceptedStudent && hasSSN) {
			if (hasValidResidency) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean intToBoolean(int number) {
		if (number == 1)
			return true;
		return false;
	}

	public static void main(String[] args) {
		boolean keepGoing = true;

		while (keepGoing) {
			JOptionPane.showMessageDialog(null, "Welcome to the FAFSA!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

			int a = JOptionPane.showOptionDialog(null, "Have you been accepted into a degree or certificate program?", "Program Acceptance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			boolean isAcceptedStudent = intToBoolean(a);

			int b = JOptionPane.showOptionDialog(null, "Are you registered for the selective service?", "Selective Service", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			boolean isSSregistered = intToBoolean(b);

			int c = JOptionPane.showOptionDialog(null, "Do you have a social security number?", "Social Security Number", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			boolean hasSSN = intToBoolean(c);

			int d = JOptionPane.showOptionDialog(null, "Do you have valid residency status?", "Residence Status", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			boolean hasValidResidency = intToBoolean(d);

			int age = Integer.parseInt(JOptionPane.showInputDialog(null, "How old are you?", JOptionPane.QUESTION_MESSAGE));
			while (age < 0) {
				JOptionPane.showMessageDialog(null, "Age cannot be a negative number.", "Error: Age", JOptionPane.ERROR_MESSAGE);
				age = Integer.parseInt(JOptionPane.showInputDialog(null, "How old are you?", JOptionPane.QUESTION_MESSAGE));
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
				JOptionPane.showMessageDialog(null, "Income cannot be a negative number", "Error: Student Income", JOptionPane.ERROR_MESSAGE);
				parentIncome = Double.parseDouble(JOptionPane.showInputDialog(null, "What is your parent's total yearly income?", "Parent Income", JOptionPane.QUESTION_MESSAGE));
			}

			int e = JOptionPane.showOptionDialog(null, "Are you a dependent?", "Dependency", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			boolean isDependent = intToBoolean(e);

			String[] classStandingSelection = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
			Object classStandingObject = JOptionPane.showInputDialog(null, "What is your current class standing?", "Class Standing", JOptionPane.PLAIN_MESSAGE, null, classStandingSelection, classStandingSelection[0]);
			String classStanding = classStandingObject.toString();

			FAFSA fafsa = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN, hasValidResidency, isDependent, age, creditHours, studentIncome, parentIncome, classStanding);

			double loan = fafsa.calcStaffordLoan();
			double grant = fafsa.calcFederalGrant();
			double workStudy = fafsa.calcWorkStudy();
			double totalAwards = fafsa.calcFederalAidAmount();

			JOptionPane.showMessageDialog(null, "Loans: " + "$" + loan
					+ "\nGrants: " + "$" + grant
					+ "\nWorkStudy: " + "$" + workStudy
					+ "\n----------------"
					+ "\nTotal: " + "$" + totalAwards);

			int input = JOptionPane.showOptionDialog(null, "Would you like to complete another Application?", "Continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (input == 2)
				keepGoing = false;
		}

	}
}
