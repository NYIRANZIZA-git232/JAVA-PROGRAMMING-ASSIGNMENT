package TestShapeCalculation;
import ShapeCalculation.Rectangle;
import java.util.Scanner;

public class TestRectangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== RECTANGLE CALCULATION ===");
        
        // Get dimensions
        System.out.print("Enter the length of the rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter the width of the rectangle: ");
        double width = scanner.nextDouble();
        
        // Create rectangle object
        Rectangle rectangle = new Rectangle(length, width);
        
        // Ask what to calculate
        System.out.println("What would you like to calculate?");
        System.out.println("A. Area");
        System.out.println("B. Perimeter");
        System.out.print("Enter your choice (A/B): ");
        char calcChoice = scanner.next().toUpperCase().charAt(0);
        
        // Perform calculation
        if (calcChoice == 'A') {
            double area = rectangle.calculateArea();
            System.out.println("The area of the rectangle is: " + area);
        } else if (calcChoice == 'B') {
            double perimeter = rectangle.calculatePerimeter();
            System.out.println("The perimeter of the rectangle is: " + perimeter);
        } else {
            System.out.println("Invalid choice!");
        }
        
        // Ask if user wants to continue
        System.out.print("Would you like to continue? (Yes/No): ");
        String continueChoice = scanner.next().toLowerCase();
        if (!continueChoice.equals("yes")) {
            System.out.println("Thank you for using the system");
        }
        
        scanner.close();
    }
}