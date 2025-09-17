package Attendancetracker;
import java.util.Scanner;

public class Attendance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Ask for total class size
        System.out.print("Enter total number of students in the class: ");
        int totalStudents = sc.nextInt();

        int[] attendance = new int[30];  // store max 30 days
        int days = 0;
        char choice;

        // 2. Record attendance using do-while
        do {
            System.out.print("Enter number of students present for Day " + (days + 1) + ": ");
            attendance[days] = sc.nextInt();
            days++;

            System.out.print("Do you want to enter attendance for another day? (Y/N): ");
            choice = sc.next().charAt(0);

        } while ((choice == 'Y' || choice == 'y') && days < 30);

        // 3. Process data
        int totalAttendance = 0;
        int lowAttendanceDays = 0;

        System.out.println("\n--- Attendance Report ---");
        System.out.println("Day\tStudents Present");
        for (int i = 0; i < days; i++) {
            System.out.println((i + 1) + "\t" + attendance[i]);
            totalAttendance += attendance[i];

            if (attendance[i] < (totalStudents / 2)) {
                lowAttendanceDays++;
            }
        }

        // Calculate average
        double average = (double) totalAttendance / days;
        double lowPercentage = ((double) lowAttendanceDays / days) * 100;

        System.out.println("\nAverage Attendance: " + average);
        System.out.println("Low Attendance Days (<50%): " + lowAttendanceDays);
        System.out.println("Percentage of Low Attendance Days: " + lowPercentage + "%");

        sc.close();
    }
}