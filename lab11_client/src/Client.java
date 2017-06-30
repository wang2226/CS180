import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.Scanner;

public class Client {
    private String serverIP;
    private int serverPort;
    private Socket socket;

    public Client() {
        try {
            serverIP = "localhost";
            serverPort = 9090;
            //Connect to server
            this.socket = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String callserver(String message) throws Exception {
        // Create stream writer/reader
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send message to server
        out.println(message);

        String output = in.readLine();
        try {
            if (output.equals("FileNotFoundException")) {
                System.out.println("File does not exist");
            } else if (output.equals("InfoNotFoundException")) {
                throw new InfoNotFoundException("InfoNotFoundException: Your Information is not in our file.");
            }else writeFile(output);
        } catch (InfoNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Return server response
        return output;
    }

    public void writeFile(String s) throws IOException {
        BufferedWriter out = null;
        try {
            // File writer
            out = new BufferedWriter(new FileWriter(new File("info.txt")));
            // Write to info.txt
            out.write(s + "\n");
        } finally {
            // Finalize
            if (out != null) {
                out.close();
            }
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String message;

        System.out.println("Pls input Filename");
        String s = input.next();
        message = s + ";";
        System.out.println("Pls input Username");
        s = input.next();
        message = message + s;

        Client client = new Client();

        try {
            client.callserver(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
