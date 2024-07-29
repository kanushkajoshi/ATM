import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATM(String initialPin) {
        this.balance = 0.0;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (this.pin.equals(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN successfully changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize ATM with default PIN
        ATM atm = new ATM("1234");

        System.out.println("Welcome to the ATM machine!");

        // PIN verification loop
        while (true) {
            System.out.print("Please enter your PIN: ");
            String inputPin = scanner.nextLine();
            if (atm.verifyPin(inputPin)) {
                break;
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }

        // ATM operations loop
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Show Transaction History");
            System.out.println("6. Exit");

            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    System.out.println("Your current balance is: $" + atm.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter your current PIN: ");
                    String oldPin = scanner.nextLine();
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.nextLine();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
