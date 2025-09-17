package forloop_system;
import java.util.Scanner;
public class Suppermarketbilling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

        // Step 1: Ask for number of different items
        System.out.print("Enter the number of different items: ");
        int n = input.nextInt();

        // Arrays to store details
        String[] itemNames = new String[n];
        double[] prices = new double[n];
        int[] quantities = new int[n];
        double[] subtotals = new double[n];

        double totalBill = 0;  // grand total before discount

        // Step 2 & 3: Use for loop to input each item
        for (int i = 0; i < n; i++) {
            input.nextLine(); // clear buffer

            System.out.print("\nEnter name of item " + (i + 1) + ": ");
            itemNames[i] = input.nextLine();

            System.out.print("Enter price per unit: ");
            prices[i] = input.nextDouble();

            System.out.print("Enter quantity: ");
            quantities[i] = input.nextInt();

            // Step 4: Calculate subtotal
            subtotals[i] = prices[i] * quantities[i];
            totalBill += subtotals[i];
        }

        // Step 5: Apply discount if total > 50,000
        double discount = 0;
        if (totalBill > 50000) {
            discount = totalBill * 0.05;
        }
        double finalAmount = totalBill - discount;

        // Step 6: Print receipt
        System.out.println("\n=========== SUPERMARKET RECEIPT ===========");
        System.out.println("Item name\tqty\tprice\tsubtotal");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println(quantities[i]);
        }

        System.out.println("-------------------------------------------");
        System.out.println(totalBill);
        System.out.println(discount);
        System.out.println(finalAmount);
        System.out.println("===========================================");

        input.close();
    }

	}


