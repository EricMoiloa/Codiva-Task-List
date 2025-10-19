import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String DATA_FILE = "bank_data.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<BankAccount> accounts = loadAccounts();  // Load saved accounts
        BankAccount currentAccount = null;
        boolean exit = false;

        System.out.println("Welcome to the Multi-Account Banking System (Maloti)");

        while (!exit) {
            System.out.println("\n====== MAIN MENU ======");
            System.out.println("1. Create new account");
            System.out.println("2. Switch account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check balance");
            System.out.println("6. Transaction history");
            System.out.println("7. List all accounts");
            System.out.println("0. Save & Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInt(sc);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter account holder name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter initial deposit amount: M");
                    double initial = getValidDouble(sc);

                    BankAccount newAccount = new BankAccount(name, initial);
                    accounts.add(newAccount);
                    currentAccount = newAccount;
                    System.out.println("Account created for " + name + ".");
                }

                case 2 -> {
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts available. Create one first!");
                        break;
                    }
                    System.out.println("Select account:");
                    for (int i = 0; i < accounts.size(); i++) {
                        System.out.println((i + 1) + ". " + accounts.get(i).getAccountHolder());
                    }
                    System.out.print("Enter choice: ");
                    int idx = getValidInt(sc) - 1;
                    if (idx >= 0 && idx < accounts.size()) {
                        currentAccount = accounts.get(idx);
                        System.out.println("Switched to " + currentAccount.getAccountHolder() + "'s account.");
                    } else {
                        System.out.println("Invalid account selection.");
                    }
                }

                case 3 -> {
                    if (checkCurrentAccount(currentAccount)) {
                        System.out.print("Enter amount to deposit: M");
                        double amt = getValidDouble(sc);
                        currentAccount.deposit(amt);
                    }
                }

                case 4 -> {
                    if (checkCurrentAccount(currentAccount)) {
                        System.out.print("Enter amount to withdraw: M");
                        double amt = getValidDouble(sc);
                        currentAccount.withdraw(amt);
                    }
                }

                case 5 -> {
                    if (checkCurrentAccount(currentAccount)) {
                        currentAccount.checkBalance();
                    }
                }

                case 6 -> {
                    if (checkCurrentAccount(currentAccount)) {
                        currentAccount.printTransactionHistory();
                    }
                }

                case 7 -> {
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts created yet.");
                    } else {
                        System.out.println("\nAll Accounts:");
                        for (BankAccount acc : accounts) {
                            System.out.println(" - " + acc.getAccountHolder() + " (Balance: M" + acc.getBalance() + ")");
                        }
                    }
                }

                case 0 -> {
                    saveAccounts(accounts);
                    System.out.println("Accounts saved successfully.");
                    System.out.println("Exiting system. Goodbye!");
                    exit = true;
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    private static boolean checkCurrentAccount(BankAccount acc) {
        if (acc == null) {
            System.out.println("Please select or create an account first!");
            return false;
        }
        return true;
    }

    private static int getValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double getValidDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a valid amount: M");
            sc.next();
        }
        return sc.nextDouble();
    }

    /** Save accounts to file */
    private static void saveAccounts(List<BankAccount> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    /** Load accounts from file */
    @SuppressWarnings("unchecked")
    private static List<BankAccount> loadAccounts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No existing data found — starting fresh.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<BankAccount> accounts = (List<BankAccount>) ois.readObject();
            System.out.println("Loaded " + accounts.size() + " account(s) from file.");
            return accounts;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load previous data. Starting new session.");
            return new ArrayList<>();
        }
    }
}
