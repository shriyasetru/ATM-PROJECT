import java.util.Scanner;

public class ATM {
    private int atmNumber;
    private int pin;
    private double balance;
    private String miniStatement;

    public ATM(int atmNumber, int pin, double balance) {
        this.atmNumber = atmNumber;
        this.pin = pin;
        this.balance = balance;
        this.miniStatement = "";
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getMiniStatement() {
        return miniStatement;
    }

    public void setMiniStatement(String miniStatement) {
        this.miniStatement = miniStatement;
    }

    public void checkBalance() {
        System.out.println("Your available balance is: $" + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            String transaction = "Withdrawn $" + amount;
            setMiniStatement(getMiniStatement() + transaction + "\n");
            System.out.println("You have withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        String transaction = "Deposited $" + amount;
        setMiniStatement(getMiniStatement() + transaction + "\n");
        System.out.println("You have deposited: $" + amount);
    }

    public void miniStatement() {
        System.out.println("Mini statement:");
        System.out.println(getMiniStatement());
    }
    
    public void changePin(int newPin) {
        pin = newPin;
        System.out.println("PIN changed successfully");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM[] accounts = {new ATM(12345, 6789, 500.0), new ATM(67890, 1234, 1000.0)};
        System.out.print("Enter ATM number: ");
        int atmNumber = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        boolean found = false;
        for (ATM account : accounts) {
            if (account.getAtmNumber() == atmNumber && account.getPin() == pin) {
                found = true;
                while (true) {
                    System.out.println("Choose an option:");
                    System.out.println("1. Validate user and change PIN");
                    System.out.println("2. Check available balance");
                    System.out.println("3. Withdraw amount");
                    System.out.println("4. Deposit amount");
                    System.out.println("5. View mini statement");
                    System.out.println("6. Exit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("Enter new PIN: ");
                            int newPin = scanner.nextInt();
                            account.changePin(newPin);
                            break;
                        case 2:
                            account.checkBalance();
                            break;
                        case 3:
                            System.out.print("Enter amount to withdraw: ");
                            double withdrawAmount = scanner.nextDouble();
                            account.withdraw(withdrawAmount);
                            break;
                        case 4:
                            System.out.print("Enter amount to deposit: ");
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 5:
                            account.miniStatement();
                            break;
                        case 6:
                            System.out.println("Thank you for using the ATM");
                            return;
                        default:
                            System.out.println("Invalid option");
                            break
                }
            }
        }
    }

    if (!found) {
        System.out.println("Invalid ATM number or PIN");
    }
}

}
