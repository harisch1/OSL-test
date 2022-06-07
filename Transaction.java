import java.util.Currency;
import java.util.Date;

public class Transaction {
    private double amount;
    private String date;
    private String op;
    private String currency;

    public Transaction(double amount, String date, String op, String currency){
        this.amount= amount;
        this.currency=currency;
        this.op=op;
        this.date=date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }

    public String getOp() {
        return op;
    }
}
