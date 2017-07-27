import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by kandarp.jo on 14/07/17.
 */
public class Arena {
    private Player player[];
    private DataOutputStream writers[];
    private DataInputStream in[];
    private DashBoard dashBoard;
    private MessageWriter out;

    public Arena(DataOutputStream out[] ,DataInputStream in[]) throws IOException {

        player = new Player[2];
        this.writers = out;
        this.in = in;
        this.out = new MessageWriter();
        this.out.out = out;
        player[0] =new Player('O');
        player[1] =new Player('X');
        player[0].setClient(in[0]);
        player[1].setClient(in[1]);

        dashBoard =new DashBoard();

    }

    private boolean takeMove(int i) throws Exception{
        int j =0;
        while(!player[i].makeMove(dashBoard)){
            writers[i].writeUTF("Invalid Move");
            writers[i].writeUTF("Please Enter your move player"+ i);
            j++;
            if(j>3){
               out.println("Player "+player[i].getSign() +" is disqualified");
               return false;
            }
        }
        return true;
    }
    public void startPlay() throws Exception{
        int i=0;
        while(true){

            dashBoard.printDashboard(out);
            writers[i].writeUTF("Please Enter your move player"+ i);

            if(!takeMove(i)){
                out.println("Player" + player[(i+1)%2].getSign() + " is winner");
                break;
            }
            char winner = dashBoard.checkWinner();
            if(winner ==player[i].getSign()){
                out.println("Winner is "+player[i].getSign());
                dashBoard.printDashboard(out);
                break;
            }
            i++;
            i%=2;
        }
    }

    public Player[] getPlayer() {
        return player;
    }


    public static void main(String cd[]) throws  Exception{


    }
}
