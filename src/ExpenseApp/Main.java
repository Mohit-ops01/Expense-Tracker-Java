package ExpenseApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseService service = new ExpenseService();

        while (true) {
            System.out.println("\n--- Personal Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Show Total Expense");
            System.out.println("5. Filter by Category");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Date (DD-MM-YYYY): ");
                    String date = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    service.addExpense(new Expense(date, category, amount));
                    break;

                case 2:
                    service.viewExpenses();
                    break;

                case 3:
                    service.viewExpenses();
                    System.out.print("Enter expense number to delete: ");
                    int lineNo = sc.nextInt();
                    service.deleteExpense(lineNo);
                    break;

                case 4:
                    service.showTotalExpense();
                    break;

                case 5:
                    System.out.print("Enter category name: ");
                    String filterCat = sc.nextLine();
                    service.filterByCategory(filterCat);
                    break;

                case 6:
                    System.out.println("Exiting Expense Tracker...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
