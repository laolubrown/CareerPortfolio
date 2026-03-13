package udp;
import java.io.*;
import java.net.*;
public class serv {
	public static void main(String[] args) throws IOException
    {
        ServerSocket sSocket = new ServerSocket(4600);
        InetAddress sAddress = InetAddress.getLocalHost();
        int sPort = sSocket.getLocalPort();
        System.out.println("Server was started at " + sAddress + ":" + sPort);
        Socket clientSocket = sSocket.accept();
        System.out.println("Client connected");  
        BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in));
       
        Thread send = new Thread(() -> {
            String userInput;
            try {
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread receive = new Thread(() -> {
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Client: " + inputLine);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        send.start();
        receive.start();
    }
}
