package TestShapeCalculation;
import ShapeCalculation.Circle;
import java.util.Scanner;

public class TestCircle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CIRCLE CALCULATION ===");
        
        // Get radius
        System.out.print("Enter the radius of the circle: ");
        double radius = scanner.nextDouble();
        
        // Create circle object
        Circle circle = new Circle(radius);
        
        // Ask what to calculate
        System.out.println("What would you like to calculate?");
        System.out.println("A. Area");
        System.out.println("B. Perimeter");
        System.out.print("Enter your choice (A/B): ");
        char calcChoice = scanner.next().toUpperCase().charAt(0);
        
        // Perform calculation
        if (calcChoice == 'A') {
            double area = circle.calculateArea();
            System.out.println("The area of the circle is: " + area);
        } else if (calcChoice == 'B') {
            double perimeter = circle.calculatePerimeter();
            System.out.println("The perimeter of the circle is: " + perimeter);
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