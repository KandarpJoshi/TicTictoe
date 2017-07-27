import java.io.DataInputStream;
import java.util.Scanner;

/**
 * Created by kandarp.jo on 14/07/17.
 */
public class Player  {

    private char sign;
    private DataInputStream in ;
    public Player(char sign){
        this.sign =sign;

    }

    public char getSign() {
        return sign;
    }
    public boolean makeMove(DashBoard dashBoard) throws Exception{
        System.out.println("start reading");
        String s[] =in.readUTF().split(" ");
        System.out.println("read");
        System.out.println("String received" + s[0] + s[1]);
       return dashBoard.enterMove(this,Integer.parseInt(s[0]),Integer.parseInt(s[1]));
    }

    public DataInputStream getClient() {
        return in;
    }

    public void setClient(DataInputStream in) {
        this.in= in;
    }
}
