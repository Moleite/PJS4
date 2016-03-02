package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur implements Runnable{
	private ServerSocket listen_socket;
	
	Serveur(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	
	public void lancer() {
		(new Thread(this)).start();		
	}

	public void run() {
		
		try {
			while(true)
				new ServiceConnexion(listen_socket.accept()).lancer();
		}catch (IOException e) { 
			
		}
	}
	
}
