package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppliClient {
	private final static int PORT = 1234;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket s = null;
		
		try {
			s = new Socket(HOST, PORT);
			
			BufferedReader s_in = new BufferedReader(new InputStreamReader(s.getInputStream ( )));
			PrintWriter s_out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
			// Informe l'utilisateur de la connection
			System.out.println("Connecté au serveur " + s.getInetAddress() + ":"+ s.getPort());
			
			String line;
			
			System.out.println(s_in.readLine().replace("##", "\n"));
			while(true) {
								
				System.out.print("> "); System.out.flush();
				line = clavier.readLine();
				if (line == null) break;
				// envoie au serveur
				s_out.println(line);
				// lit la réponse provenant du serveur
				line = s_in.readLine(); 
				
				// Verifie si la connection est fermee.
				// Si oui on sort de la boucle
				if (line == null) { 
					System.out.println("Connection fermee par le serveur."); 
					break;
				}
				// Ecrit la ligne envoyee par le serveur
				System.out.println(line.replace("##","\n"));
			}
		}
		catch (IOException e) { System.err.println(e); }
		// Refermer dans tous les cas la socket
		try { if (s != null) s.close(); } 
		catch (IOException e2) { ; }
		
		
	}
		
		
		
		


}
