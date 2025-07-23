import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

// Account class
class Account {
    private String accountHolderName;
    private String email;
    private String phone;
    private String bankName;
    private String accountNumber;
    private String accountId;
    private double balance;

    // Constructor
    public Account(String accountHolderName, String email, String phone, String bankName) {
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.phone = phone;
        this.bankName = bankName;
        this.accountNumber = generateAccountNumber();
        this.accountId = UUID.randomUUID().toString(); // Unique ID
        this.balance = 0.0;
    }

    private String generateAccountNumber() {
        return "ACC" + (int)(Math.random() * 1000000);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void creditAmount(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " credited successfully.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void debitAmount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " debited successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void viewBalance() {
        System.out.println("Available Balance: ₹" + balance);
    }

    public void displayDetails() {
        System.out.println("---------- Account Details ----------");
        System.out.println("Account Holder Name : " + accountHolderName);
        System.out.println("Email               : " + email);
        System.out.println("Phone               : " + phone);
        System.out.println("Bank Name           : " + bankName);
        System.out.println("Account Number      : " + accountNumber);
        System.out.println("Account ID          : " + accountId);
        System.out.println("Balance             : ₹" + balance);
        System.out.println("-------------------------------------");
    }
}

// AccountManager class
class AccountManager {
    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Bank Name: ");
        String bank = scanner.nextLine();

        Account newAccount = new Account(name, email, phone, bank);
        accounts.add(newAccount);

        System.out.println("Account created successfully!");
        System.out.println("Your Account Number: " + newAccount.getAccountNumber());
    }

    public Account searchAccount(String accNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNumber)) {
                return acc;
            }
        }
        return null;
    }

    public void performOperations() {
        while (true) {
            System.out.println("\n--- Account Management System ---");
            System.out.println("1. Create New Account");
            System.out.println("2. Credit Amount");
            System.out.println("3. Debit Amount");
            System.out.println("4. Check Balance");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    Account acc1 = getAccountByNumber();
                    if (acc1 != null) {
                        System.out.print("Enter amount to credit: ₹");
                        double creditAmount = Double.parseDouble(scanner.nextLine());
                        acc1.creditAmount(creditAmount);
                    }
                    break;
                case 3:
                    Account acc2 = getAccountByNumber();
                    if (acc2 != null) {
                        System.out.print("Enter amount to debit: ₹");
                        double debitAmount = Double.parseDouble(scanner.nextLine());
                        acc2.debitAmount(debitAmount);
                    }
                    break;
                case 4:
                    Account acc3 = getAccountByNumber();
                    if (acc3 != null) {
                        acc3.viewBalance();
                    }
                    break;
                case 5:
                    Account acc4 = getAccountByNumber();
                    if (acc4 != null) {
                        acc4.displayDetails();
                    }
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using the system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1-6.");
            }
        }
    }

    private Account getAccountByNumber() {
        System.out.print("Enter your Account Number: ");
        String accNumber = scanner.nextLine();
        Account account = searchAccount(accNumber);

        if (account == null) {
            System.out.println("Account not found!");
        }
        return account;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        manager.performOperations();
    }
}
