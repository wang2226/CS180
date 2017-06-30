import java.io.*;
import java.net.*;

public class Server {
    // Listen on port 9090
    private ServerSocket listener;
    private Socket socket;
    private InputStreamReader isr;
    private BufferedReader in;
    PrintWriter out;

    public Server() {
        try {
            listener = new ServerSocket(9090);
            System.out.println("Server Waiting for Connection");

            // Wait for next client connection
            socket = listener.accept();
            System.out.println("Connection is successful and waiting for commands");

            // Create data reader
            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);

            // Create data writer
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void serverSerives() throws IOException {
        try {
            // Read client request
            String clientMessage = in.readLine();
            String[] stringArray = clientMessage.split(";");
            System.out.println("Filename: <" + stringArray[0] + ">\n" +
                    "Username: <" + stringArray[1] + ">\n");
            String readResult = readFile(stringArray[0], stringArray[1]);

            if (readResult == "") {
                out.println("InfoNotFoundException");
                return;
            }
            stringArray = readResult.split(";");
            // Send reply to client
            String sentMessage = "LastName: " + stringArray[2] + ";" +
                            "FirstName: " + stringArray[3] + ";" +
                            "Marks: " + stringArray[4];
            System.out.println(sentMessage);
            out.println(sentMessage);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                out.println("FileNotFoundException");
            }
            System.out.println(e.getMessage());

        } finally {
            // Close server socket eventually
            listener.close();
        }
    }


    public String readFile(String filePath, String userName) throws IOException {
        // File reader
        File textFile = new File(filePath);
        FileReader fr = new FileReader(textFile);
        BufferedReader in = new BufferedReader(fr);

        try {
            for (int i = 0; i < 7; i++) {
                String line = in.readLine();
                String[] StringArray = line.split(";");
                if (userName.equals(StringArray[1]))
                    return line;
            }

        } finally {
            // Finalize
            if (in != null) {
                in.close();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.serverSerives();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}