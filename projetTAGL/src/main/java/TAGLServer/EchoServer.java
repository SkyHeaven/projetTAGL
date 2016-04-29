package TAGLServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//public class EchoServer {
//
//    
//
//    public static void main(String[] zero) {
//
//        
//
//        ServerSocket socketserver  ;
//
//        Socket socketduserveur ;
//
//        BufferedReader in;
//
//        PrintWriter out;
//
//        try {
//
//            socketserver = new ServerSocket(2009);
//
//            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
//
//            socketduserveur = socketserver.accept(); 
//
//                System.out.println("Un utilisateur s'est connecté");
//
//            out = new PrintWriter(socketduserveur.getOutputStream());
//
//                out.println("Vous êtes connecté");
//
//                out.flush();
//                /***/
//                // creation table hachage
//                TableHachage tableHachage = new TableHachage();
//                //buffer permettant de lire les messages du/des client(s)
//                BufferedReader inCl = new BufferedReader( 
//                        new InputStreamReader( socketduserveur.getInputStream())); 
//
//                String inputLine; 
//                while ((inputLine = inCl.readLine()) != null) 
//                    { 
//                	if(inputLine.length() > 0){
//                		// enleve un eventuel espace au debut de la commande
//	                	if(inputLine.charAt(0)==' '){
//	                		StringBuilder strB = new StringBuilder(inputLine);
//	                	    strB.deleteCharAt(0);
//	                	    inputLine = strB.toString();
//	                	}
//                	}
//                     System.out.println ("Server: " + inputLine);                      
//                     out.println(inputLine);
//                     
//                     if( inputLine.contains("lPush" ) ) {
//                    	 // split afin de recuperer les arguments de la fonction
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.lPush(parts[1],parts[2]);
//                     }
//                     else if (inputLine.contains("rPush" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.rPush(parts[1],parts[2]);
//                     }
//                     else if (inputLine.contains("lRange" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.lRange(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
//                     }
//                     else if (inputLine.contains("lLen" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 System.out.println("La taille de la table est de : " +tableHachage.lLen(parts[1]));
//                     }
//                     
//                     else if (inputLine.contains("set" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.set(parts[1],parts[2]);
//                     }
//                     else if (inputLine.contains("get" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.get(parts[1]);
//                     }
//                     else if (inputLine.contains("del" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.del(parts[1]);
//                     }
//                     else if (inputLine.contains("incr" )){
//                    	 String[] parts = inputLine.split(" ");
//                    	 tableHachage.incr(parts[1]);
//                     }
//                     else{
//                    	 System.out.println("Mauvaise commande");         
//                     }
//
//                     if (inputLine.equals("Bye.")) 
//                         break; 
//                    } 
//                /****/
//                socketduserveur.close();
//
//                socketserver.close();
//
//                
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

public class EchoServer {

    
    private static final int PORT = 2009;
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
    
    public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

        private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private TableHachage tableHachage;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                tableHachage = new TableHachage();

                synchronized (names) {
                    name = ""+names.size()+"";
                    names.add(name);
                }
                
                writers.add(out);

                while (true && !writers.isEmpty()) {
                	String inputLine=in.readLine();
                	if(inputLine==null){
                        return;
                    }
                	if(inputLine.length() > 0){
               		// enleve un eventuel espace au debut de la commande
	                	if(inputLine.charAt(0)==' '){
	                		StringBuilder strB = new StringBuilder(inputLine);
	                	    strB.deleteCharAt(0);
	                	    inputLine = strB.toString();
	                	}
                	}
                     System.out.println (name+": " + inputLine);
                     
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
                    	 writers.get(Integer.parseInt(name)).println("La taille de la table est de : " +tableHachage.lLen(parts[1]));
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
                    	 writers.get(Integer.parseInt(name)).println("Mauvaise commande");         
                     }

                     if (inputLine.equals("Bye.")) 
                         break; 
                    }
//                    String input = in.readLine();
//                    if(input==null){
//                        return;
//                    }
//                    System.out.println(name + ": " + input);
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}