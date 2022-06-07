import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class BankAccount {
    private static int account = 1000;
    final private Client user;
    private int AccountNumber;
    private double Balance;
    private ArrayList<Transaction> transactions;
    public final String Currency;
    public int withdraws = 0;
    private Date lastWithdraw;

    public BankAccount(String UserName, String Currency){
        this.user = new Client(UserName);
        this.AccountNumber = account;
        this.Balance =  0;
        this.transactions = new ArrayList<>();
        this.Currency = Currency;
        account++;
    }

    public Client getUser() {
        return user;
    }

    public String getCurrency() {
        return Currency;
    }

    public int getAccountNumber(){
        return this.AccountNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public void deposit(double amount, String Currency, String op){
        //Record Transaction
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.transactions.add(new Transaction(amount, dtf.format(now), op, Currency));
        this.Balance += amount;
    }
    public void updateWithdraws(Date now){
        if (lastWithdraw != null){
            long difference = now.getTime() - this.lastWithdraw.getTime();
            if ((difference / (60 * 1000)%60) >= 5){
                withdraws =0;
            }
        }

    }

    public int getWithdraws() {
        return withdraws;
    }

    public void incrementWithdraws(Date now){
        this.withdraws++;
        lastWithdraw = now;
    }

    public void withdraw(double amount, String Currency, String op){
        //Record Transaction
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        this.transactions.add(new Transaction(-amount, formatter.format(now), op, Currency));
        this.Balance -= amount;
        

    }

    public void printTranscations(){

        for(Transaction transaction: transactions){
            System.out.printf("%-20s|%-10s|%-20s|%-10s\n", transaction.getDate().toString(), transaction.getCurrency(), transaction.getOp(), String.valueOf(transaction.getAmount()));
        }
    }


}
