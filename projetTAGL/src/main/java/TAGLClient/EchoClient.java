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



//public class EchoClient {
//
//    
//
//    public static void main(String[] zero) throws IOException {
//
//        
//
//        Socket socket;
//
//        BufferedReader in;
//
//        PrintWriter out;
//        /*
//        BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader("properties/projetTAGL/src/main/java/TAGLClient/input.txt"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//            String everything = sb.toString();
//        } finally {
//            br.close();
//        }
//        */
//        String commande = "lPush lecture 4  \n incr 4 \n ei \n";
//
//        try {
//        		socket = new Socket(InetAddress.getLocalHost(),2009);
//        		out = new PrintWriter(socket.getOutputStream(), true);
//
//        		//socket = new Socket(InetAddress.getLocalHost(),2009);
//                System.out.println("Demande de connexion");
//
//
//                in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
//        		String commande= in.readLine();
//                String message_distant = in.readLine();
//
//                System.out.println(message_distant);
//                out.println(commande);
//                
//
//                socket.close();
//
//               
//
//        }catch (UnknownHostException e) {
//
//            
//
//            e.printStackTrace();
//
//        }catch (IOException e) {
//
//            
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//
//}

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
                System.out.println(in.readLine());
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