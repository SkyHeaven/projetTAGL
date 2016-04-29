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

                	 String[] parts = inputLine.split(" ");
                     if( inputLine.contains("lPush" ) ) {
                    	 // split afin de recuperer les arguments de la fonction
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.lPush(parts[1],parts[2]));
                     }
                     else if (inputLine.contains("rPush" )){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.rPush(parts[1],parts[2]));
                     }
                     else if (inputLine.contains("lRange" )){
                    	 String string=tableHachage.lRange(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
                    	 String[] t=string.split("\n");
                    	 for(String s : t){
                    		 writers.get(Integer.parseInt(name)).println(s);
                    	 }
                     }
                     else if (inputLine.contains("lLen" )){
                    	 writers.get(Integer.parseInt(name)).println("La taille de la table est de : " +tableHachage.lLen(parts[1]));
                     }
                     
                     else if (inputLine.contains("set" )){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.set(parts[1],parts[2]));
                     }
                     else if (inputLine.contains("get" )){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.get(parts[1]));
                     }
                     else if (inputLine.contains("del" )){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.del(parts[1]));
                     }
                     else if (inputLine.contains("incr" )){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.incr(parts[1]));
                     }
                     else if (inputLine.contains("lPop")){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.lPop(parts[1]));
                     }
                     else if (inputLine.contains("rPop")){
                    	 writers.get(Integer.parseInt(name)).println(tableHachage.rPop(parts[1]));
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