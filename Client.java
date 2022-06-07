import java.util.ArrayList;

public class Client {
    private static int id = 1000;
    final private String UserName;
    final private int Userid;

    public Client(String userName){
        this.UserName = userName;
        this.Userid = id;
        id++;
    }

    public String getUserName() {
        return UserName;
    }

}
