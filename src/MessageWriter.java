import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kandarp.jo on 14/07/17.
 */
public class MessageWriter {
    DataOutputStream out[];

    public void println(String s) throws IOException {
        out[0].writeUTF(s + "\n");
        out[1].writeUTF(s + "\n");
    }
}
