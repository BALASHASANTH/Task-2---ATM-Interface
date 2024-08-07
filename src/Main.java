import java.util.Scanner;

class SimpleATM {

    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.printf("Deposited $%.2f successfully.%n", amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.printf("Withdrew $%.2f successfully.%n", amount);
                return true;
            } else {
                System.out.println("Insufficient funds or invalid amount.");
                return false;
            }
        }
    }

    private static void handleATM(BankAccount account, Scanner scanner) {
        int option;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            option = getValidOption(scanner);

            switch (option) {
                case 1:
                    System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = getValidAmount(scanner);
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = getValidAmount(scanner);
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }

    private static int getValidOption(Scanner scanner) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine());
                if (option >= 1 && option <= 4) {
                    return option;
                } else {
                    System.out.println("Option out of range. Please choose between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getValidAmount(Scanner scanner) {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Amount must be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount myAccount = new BankAccount(1000.00); // Example initial balance

        handleATM(myAccount, scanner);

        scanner.close();
    }
}
