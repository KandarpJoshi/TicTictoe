/**
 * Created by kandarp.jo on 14/07/17.
 */
import org.omg.CORBA.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 8080);
        System.out.println("Just connected to " + server.getRemoteSocketAddress());
        DataInputStream in1 = new DataInputStream(server.getInputStream());
        DataOutputStream out =new DataOutputStream(server.getOutputStream());

        Scanner input = new Scanner(System.in);
        while(true){

            String s = in1.readUTF();
            System.out.println(s);
            if(s.startsWith("winner") || s.startsWith("draw")){
                break;
            }
            if(s.startsWith("Please Enter your move player")) {
                {
                    String ss = input.nextLine();
                    out.writeUTF(ss);


                }
            }

        }

    }
}