package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import modele.PersonneBD;

public class ServiceConnexion implements Runnable{
	
	private Socket client;
	private boolean connecte = false;
	
	
	public ServiceConnexion(Socket c){
		client = c;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (!connecte) { 
				out.println("Bonjour et bienvenue sur Roomster, l'application de réservation de salle pour les Hipsters. ## Veuillez entrer votre pseudo :");
				out.flush();
				String pseudo = in.readLine();
				out.println("Veuillez saisir votre mot de passe: ");
				out.flush();
				String mdp = in.readLine();
				if (PersonneBD.verifCompte(pseudo, mdp)){
					out.println("Vous etes bien connecté ! appuyer sur entrer pour continuer");
					out.flush();
					String tmp = in.readLine();
					System.err.println(pseudo + " est connecté sur le serveur");
					new ServiceRéservation(client).lancer();
					connecte = true;
					
				} else {
					
				}
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	} 
	
	public void lancer(){
		(new Thread(this)).start();	
	}
	
	

}
