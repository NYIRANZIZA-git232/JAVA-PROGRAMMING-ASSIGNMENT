package com.Dayfour;
import java.util.Scanner;
public class Shape_calcula {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				String choice;
				do {
					
					System.out.println("Select a shape");
					System.out.println("1. Rectangle");
					System.out.println("2. Circle");
					System.out.println("3. Square");
					System.out.println("4. Triangle");
					System.out.print("Enter your choice (1-4): ");
					int shape = sc.nextInt();

				
					System.out.println("\nWhat would you like to calculate?");
					System.out.println("A. Area");
					System.out.println("B. Perimeter");
					System.out.print("Enter your choice (A/B): ");
					char calcChoice = sc.next().toUpperCase().charAt(0);

					double result = 0;

					switch (shape) {
					case 1: // Rectangle
						System.out.print("Enter length: ");
						double length = sc.nextDouble();
						System.out.print("Enter width: ");
						double width = sc.nextDouble();

						if (calcChoice == 'A') {
							result = length * width;
							System.out.println("Area of Rectangle = " + result);
						} else {
							result = 2 * (length + width);
							System.out.println("Perimeter of Rectangle = " + result);
						}
						break;

					case 2: // Circle
						System.out.print("Enter radius: ");
						double radius = sc.nextDouble();

						if (calcChoice == 'A') {
							result = Math.PI * radius * radius;
							System.out.println("Area of Circle = " + result);
						} else {
							result = 2 * Math.PI * radius;
							System.out.println("Perimeter (Circumference) of Circle = " + result);
						}
						break;

					case 3: // Square
						System.out.print("Enter side: ");
						double side = sc.nextDouble();

						if (calcChoice == 'A') {
							result = side * side;
							System.out.println("Area of Square = " + result);
						} else {
							result = 4 * side;
							System.out.println("Perimeter of Square = " + result);
						}
						break;

					case 4: // Triangle
						if (calcChoice == 'A') {
							System.out.print("Enter base: ");
							double base = sc.nextDouble();
							System.out.print("Enter height: ");
							double height = sc.nextDouble();
							result = ( base * height)/2;
							System.out.println("Area of Triangle = " + result);
						} else {
							System.out.print("Enter side 1: ");
							double s1 = sc.nextDouble();
							System.out.print("Enter side 2: ");
							double s2 = sc.nextDouble();
							System.out.print("Enter side 3: ");
							double s3 = sc.nextDouble();
							result = s1 + s2 + s3;
							System.out.println("Perimeter of Triangle = " + result);
						}
						break;

					default:
						System.out.println("Invalid shape choice!");
					}

					// Continue?
					System.out.print("\nWould you like to continue? (Yes/No): ");
					choice = sc.next();

				} while (choice.equalsIgnoreCase("Yes"));

				System.out.println("Thank you for using system!");
				sc.close();

			}
	}


