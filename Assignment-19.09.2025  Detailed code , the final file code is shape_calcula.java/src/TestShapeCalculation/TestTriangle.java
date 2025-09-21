package TestShapeCalculation;
import ShapeCalculation.Triangle;
import java.util.Scanner;

public class TestTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== TRIANGLE CALCULATION ===");
        
        // Get dimensions
        System.out.print("Enter the base of the triangle: ");
        double base = scanner.nextDouble();
        System.out.print("Enter the height of the triangle: ");
        double height = scanner.nextDouble();
        System.out.print("Enter side 1 of the triangle: ");
        double side1 = scanner.nextDouble();
        System.out.print("Enter side 2 of the triangle: ");
        double side2 = scanner.nextDouble();
        System.out.print("Enter side 3 of the triangle: ");
        double side3 = scanner.nextDouble();
        
        // Create triangle object
        Triangle triangle = new Triangle(base, height, side1, side2, side3);
        
        // Ask what to calculate
        System.out.println("What would you like to calculate?");
        System.out.println("A. Area");
        System.out.println("B. Perimeter");
        System.out.print("Enter your choice (A/B): ");
        char calcChoice = scanner.next().toUpperCase().charAt(0);
        
        // Perform calculation
        if (calcChoice == 'A') {
            double area = triangle.calculateArea();
            System.out.println("The area of the triangle is: " + area);
        } else if (calcChoice == 'B') {
            double perimeter = triangle.calculatePerimeter();
            System.out.println("The perimeter of the triangle is: " + perimeter);
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