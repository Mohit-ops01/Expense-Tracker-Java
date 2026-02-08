package ExpenseApp;

import java.io.*;
import java.util.ArrayList;

public class ExpenseService {

    private static final String FILE_NAME = "expenses.txt";

    public void addExpense(Expense expense) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(expense.toFileFormat() + "\n");
            System.out.println("Expense saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving expense.");
        }
    }

    public ArrayList<String> getAllExpenses() {
        ArrayList<String> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            // file may not exist initially
        }
        return records;
    }

    public void viewExpenses() {
        ArrayList<String> records = getAllExpenses();

        if (records.isEmpty()) {
            System.out.println("No expense records available.");
            return;
        }

        System.out.println("\n--- Expense Records ---");
        int index = 1;
        for (String record : records) {
            String[] data = record.split(",");
            System.out.println(
                    index + ". Date: " + data[0] +
                            ", Category: " + data[1] +
                            ", Amount: ₹" + data[2]
            );
            index++;
        }
    }

    public void deleteExpense(int lineNumber) {
        ArrayList<String> records = getAllExpenses();

        if (lineNumber < 1 || lineNumber > records.size()) {
            System.out.println("Invalid expense number.");
            return;
        }

        records.remove(lineNumber - 1);

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Expense deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error deleting expense.");
        }
    }

    public void showTotalExpense() {
        ArrayList<String> records = getAllExpenses();
        double total = 0;

        for (String record : records) {
            String[] data = record.split(",");
            total += Double.parseDouble(data[2]);
        }

        System.out.println("Total Expense: ₹" + total);
    }

    public void filterByCategory(String category) {
        ArrayList<String> records = getAllExpenses();
        boolean found = false;

        System.out.println("\n--- Expenses for Category: " + category + " ---");
        for (String record : records) {
            String[] data = record.split(",");
            if (data[1].equalsIgnoreCase(category)) {
                System.out.println(
                        "Date: " + data[0] +
                                ", Amount: ₹" + data[2]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found for this category.");
        }
    }
}
