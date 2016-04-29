package TAGLServer;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.ServerSocket;

import java.net.Socket;

import java.net.UnknownHostException;

import java.io.PrintWriter;

import TAGLClient.TableHachage;

public class EchoServer {

    

    public static void main(String[] zero) {

        

        ServerSocket socketserver  ;

        Socket socketduserveur ;

        BufferedReader in;

        PrintWriter out;

        try {

            socketserver = new ServerSocket(2009);

            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());

            socketduserveur = socketserver.accept(); 

                System.out.println("Un utilisateur s'est connecté");

            out = new PrintWriter(socketduserveur.getOutputStream());

                out.println("Vous êtes connecté");

                out.flush();
                /***/
                // creation table hachage
                TableHachage tableHachage = new TableHachage();
                //buffer permettant de lire les messages du/des client(s)
                BufferedReader inCl = new BufferedReader( 
                        new InputStreamReader( socketduserveur.getInputStream())); 

                String inputLine; 
                while ((inputLine = inCl.readLine()) != null) 
                    { 
                	if(inputLine.length() > 0){
                		// enleve un eventuel espace au debut de la commande
	                	if(inputLine.charAt(0)==' '){
	                		StringBuilder strB = new StringBuilder(inputLine);
	                	    strB.deleteCharAt(0);
	                	    inputLine = strB.toString();
	                	}
                	}
                     System.out.println ("Server: " + inputLine);                      
                     out.println(inputLine);
                     
                     if( inputLine.contains("lPush" ) ) {
                    	 // split afin de recuperer les arguments de la fonction
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.lPush(parts[1],parts[2]);
                     }
                     else if (inputLine.contains("rPush" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.rPush(parts[1],parts[2]);
                     }
                     else if (inputLine.contains("lRange" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.lRange(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
                     }
                     else if (inputLine.contains("lLen" )){
                    	 String[] parts = inputLine.split(" ");
                    	 System.out.println("La taille de la table est de : " +tableHachage.lLen(parts[1]));
                     }
                     
                     else if (inputLine.contains("set" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.set(parts[1],parts[2]);
                     }
                     else if (inputLine.contains("get" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.get(parts[1]);
                     }
                     else if (inputLine.contains("del" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.del(parts[1]);
                     }
                     else if (inputLine.contains("incr" )){
                    	 String[] parts = inputLine.split(" ");
                    	 tableHachage.incr(parts[1]);
                     }
                     else{
                    	 System.out.println("Mauvaise commande");         
                     }

                     if (inputLine.equals("Bye.")) 
                         break; 
                    } 
                /****/
                socketduserveur.close();

                socketserver.close();

                

        }catch (IOException e) {

            

            e.printStackTrace();

        }

    }


}