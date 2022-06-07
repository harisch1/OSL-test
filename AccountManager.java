import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class AccountManager {
    private ArrayList<BankAccount> Accounts;
    private BankAccount userAccount;
    List<String> allowedCurrencies = new ArrayList<>(){{add("USD");add("SGD");add("HKD");}};
    public AccountManager(){
        this.Accounts = new ArrayList<>();
    }

    public void createAccount(String name, String Currency){
        if (allowedCurrencies.contains(Currency)){
            BankAccount account = new BankAccount(name, Currency);
            this.Accounts.add(account);
            System.out.println("Account Created for user: "+account.getUser().getUserName()+" .Your Account Number is "+account.getAccountNumber());
        }
        else {
            System.out.println("Invalid Currency!");
        }

    }
    private void useAccount(int AccountNo){
        for (BankAccount acc: Accounts){
            if (acc.getAccountNumber() == AccountNo){
                this.userAccount = acc;
                break;
            }
        }
    }

    public void deposit(int source, double Amount, String Currency){
        useAccount(source);
        if (userAccount!=null){
            userAccount.deposit(Amount, Currency, "Deposit");
            System.out.printf("Deposited %s %s into %s's Account!\n", Currency,Amount,userAccount.getUser().getUserName());
        }
        else {
            System.out.println("Account Does not Exist");
        }


    }
    public void withdrawal(int source, double Amount, String Currency){
        useAccount(source);
        Date now = new Date();
        userAccount.updateWithdraws(now);
        if (userAccount!=null){
            if (userAccount.getBalance() >= Amount){
                if (userAccount.getWithdraws() < 5){
                    userAccount.withdraw(Amount, Currency, "Withdrawal");
                    userAccount.withdraw((Amount * 0.01), Currency, "Withdrawal FEE");
                    System.out.printf("Withdrew %s %s from %s's Account!\n", Currency,Amount,userAccount.getUser().getUserName());
                    userAccount.incrementWithdraws(now);
                }
                else{
                    System.out.println("Withdrawal limit reached. Please try later!");
                }
            }
            else{
                System.out.println("Not enough funds!");
                return;
            }

        }
        else {
            System.out.println("Account Does not Exist");
        }

    }
    public void transfer(int source, int targetNo, double Amount, String Currency){
        useAccount(source);
        BankAccount target;
        if (userAccount!=null) {
            for (BankAccount acc : Accounts) {
                if (acc.getAccountNumber() == targetNo) {
                    target = acc;
                    target.deposit(Amount, Currency, "Transfer In");
                    userAccount.withdraw(Amount, Currency, "Transfer out");
                    System.out.printf("Transferred %s %s from %s's Account to %s's Account!\n", Currency,Amount,userAccount.getUser().getUserName(), target.getUser().getUserName());
                    break;
                }
            }

        }
        else {
            System.out.println("Account Does not Exist");
        }

    }

    public void listBalance(String user){
        System.out.printf("%-10s|%-10s\n","Currency","Balance");
        int count = 0;
        for (BankAccount acc: Accounts){
            if (acc.getUser().getUserName() == user){
                this.userAccount = acc;
                System.out.printf("%-10s|%-10s\n", acc.getCurrency(),acc.getBalance());
                count++;
            }
        }
        if(count == 0) {
            System.out.println("User Does not have any accounts yet!");
        }
    }

    public void displayStatement(String user){

        System.out.printf("%-20s|%-10s|%-20s|%-10s\n","Client Name", user,"", "");
        System.out.printf("%-20s|%-10s|%-20s|%-10s\n","Date", "Currency", "Operation", "Amount");
        int count = 0;
        for (BankAccount acc: Accounts){
            if (acc.getUser().getUserName() == user){
                acc.printTranscations();
                count++;
            }
            
        }
        if(count == 0) {
            System.out.println("Account Does not Exist");
        }

    }
}
