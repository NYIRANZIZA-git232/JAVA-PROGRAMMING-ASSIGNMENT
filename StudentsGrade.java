package Grading_system;
import java.util.Scanner;
public class StudentsGrade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

        int totalStudents = 0;
        int passCount = 0;
        int failCount = 0;

        while (true) {
            // Step 1: Ask for marks
            System.out.print("Enter student marks (0–100) or -1 to stop: ");
            int marks = input.nextInt();

            // Step 2: Check for value
            if (marks == -1) {
                break; // exit loop
            }

            // Validate marks range
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter between 0–100.");
                continue; 
            }

            totalStudents++; 

            // Step 3: Assign grade
            if (marks >= 80) {
                System.out.println("Grade: A");
            } else if (marks >= 70) {
                System.out.println("Grade: B");
            } else if (marks >= 60) {
                System.out.println("Grade: C");
            } else if (marks >= 50) {
                System.out.println("Grade: D");
            } else {
                System.out.println("Grade: F");
            }

            // Step 4: Count pass/fail
            if (marks >= 50) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // Step 5: Print class report report
        System.out.println("\n=========== CLASS REPORT ===========");
        System.out.println("Total students: " + totalStudents);
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);

        if (totalStudents > 0) {
            double passRate = (passCount * 100) / totalStudents;
            System.out.println("Class Pass Rate: student\n");
        } else {
            System.out.println("No student data entered.");
        }
        System.out.println("=====================================");

        input.close();
	}
}