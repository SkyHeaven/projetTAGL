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

    

    public static void main(String[] zero) throws IOException {

        

        Socket socket;

        BufferedReader in;

        PrintWriter out;
        /*
        BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("properties/projetTAGL/src/main/java/TAGLClient/input.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        } finally {
            br.close();
        }
        */
        String commande = "lPush lecture 4  \n incr 4 \n ei \n";

        try {
        		socket = new Socket(InetAddress.getLocalHost(),2009);
        		out = new PrintWriter(socket.getOutputStream(), true);

        		//socket = new Socket(InetAddress.getLocalHost(),2009);   

                System.out.println("Demande de connexion");


                in = new BufferedReader (new InputStreamReader (socket.getInputStream()));

                String message_distant = in.readLine();

                System.out.println(message_distant);
                out.println(commande);
                

                socket.close();

               

        }catch (UnknownHostException e) {

            

            e.printStackTrace();

        }catch (IOException e) {

            

            e.printStackTrace();

        }

    }


}