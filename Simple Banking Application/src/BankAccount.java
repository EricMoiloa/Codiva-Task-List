import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;
    private static final String LOG_FILE = "bank_history.txt";

    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        String entry = "Account created with initial deposit: M" + initialDeposit;
        transactionHistory.add(entry);
        logToFile(entry);
    }

    /** Deposit money into the account */
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        String entry = "Deposited: M" + amount;
        transactionHistory.add(entry);
        logToFile(entry);
        System.out.println("Deposited M" + amount + " successfully.");
    }

    /** Withdraw money from the account */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (amount > balance) {
            String failEntry = "Failed withdrawal attempt: M" + amount + " (Insufficient funds)";
            transactionHistory.add(failEntry);
            logToFile(failEntry);
            System.out.println("Insufficient funds! Current balance: M" + balance);
            return;
        }

        balance -= amount;
        String entry = "Withdrawn: M" + amount;
        transactionHistory.add(entry);
        logToFile(entry);
        System.out.println("Withdrawn M" + amount + " successfully.");
    }

    /** Check current balance */
    public void checkBalance() {
        System.out.println("Current Balance: M" + balance);
    }

    /** Print all transaction history */
    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountHolder + ":");
        for (String entry : transactionHistory) {
            System.out.println(" - " + entry);
        }
    }

    /** Write each transaction to a text file with timestamp */
    private void logToFile(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);

            pw.println("[" + timestamp + "] " + accountHolder + ": " + message);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }
}
