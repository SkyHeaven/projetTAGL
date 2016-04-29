package TAGLClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.Socket;

import java.net.UnknownHostException;

public class EchoClient {

    BufferedReader in;
    PrintWriter out;
    
    public EchoClient() {
        in= null;
        out=null;

    }
   
    private void run() throws IOException {
    	InetAddress serverAddress=InetAddress.getLocalHost();
        try(
        Socket socket = new Socket(serverAddress, 2009);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String repServer=in.readLine();
                if(repServer!=null){
                	System.out.println(repServer);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                serverAddress);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        EchoClient client = new EchoClient();
        client.run();
    }
}