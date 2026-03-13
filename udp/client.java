package udp;
import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) throws IOException
    {
        String hostName = "10.103.29.26";
        int portNumber = 4600;
        Socket socket = new Socket(hostName, portNumber);
        System.out.println("Connected to server " + hostName + ":" + portNumber);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        Thread sender = new Thread(() ->
        {
            String userInput;
            try
            {
                while ((userInput = stdIn.readLine()) != null)
                {
                    out.println(userInput);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        sender.start();
        Thread receiver = new Thread(() ->
        {
            String inputLine;
            try
            {
                while ((inputLine = in.readLine()) != null)
                {
                    System.out.println("Server: " + inputLine);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        receiver.start();
    }	
}
