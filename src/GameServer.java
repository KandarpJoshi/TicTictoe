/**
 * Created by kandarp.jo on 14/07/17.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameServer {

        public static void main(String[] args) throws IOException , Exception {
            ExecutorService cachedpool = Executors.newCachedThreadPool();

            int i=0;
            ServerSocket serverSocket = new ServerSocket(8080);

            while(i<100) {
                System.out.println("Wating");
                Socket playerOneSocket = serverSocket.accept();
                Socket playerTwoSocket = serverSocket.accept();
                cachedpool.submit(new Playable(playerOneSocket,playerTwoSocket));
                i++;
            }

        }


}
class Playable implements Runnable{
    Socket playerOneSocket ;
    Socket playerTwoSocket ;
    Playable(Socket s1,Socket s2){
        playerOneSocket =s1;
        playerTwoSocket = s2;

    }
    @Override
    public void run() {
        try {
            OutputStream outToServer = playerOneSocket.getOutputStream();
            DataOutputStream playerOneOut = new DataOutputStream(outToServer);
            OutputStream playerTwoSocketOutputStream = playerTwoSocket.getOutputStream();
            DataOutputStream playerTwoOut = new DataOutputStream(playerTwoSocketOutputStream);

            DataOutputStream out[] = new DataOutputStream[2];
            DataInputStream in[] = new DataInputStream[2];
            out[0] = (playerOneOut);
            out[1] = (playerTwoOut);
            in[0] = new DataInputStream(playerOneSocket.getInputStream());
            in[1] = new DataInputStream(playerTwoSocket.getInputStream());
            Arena arena = new Arena(out, in);
            arena.startPlay();


            playerOneOut.close();
            playerTwoOut.close();

            playerOneSocket.close();
            playerTwoSocket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
