import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        accountManager.createAccount("Haris", "HKD");
        accountManager.createAccount("Haris", "USD");
        accountManager.createAccount("Shoaib","HKD");
        accountManager.createAccount("Shoaib","SGD");
        accountManager.deposit(1000, 1000, "HKD");
        accountManager.deposit(1001, 700, "HKD");
        accountManager.deposit(1001, 300, "SGD");
        accountManager.deposit(1002, 300, "USD");
        accountManager.transfer(1000, 1002, 350,"HKD");
        accountManager.withdrawal(1002, 100, "USD");
        accountManager.displayStatement("Haris");
        accountManager.listBalance("Haris");
        accountManager.displayStatement("Shoaib");


    }
}
