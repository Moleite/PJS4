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
				out.println("Bonjour et bienvenue sur Roomster, l'application de réservation de salle pour les Hipsters. ## Êtes-vous deja inscrit (o/n):");
				out.flush();
				char c = in.readLine().charAt(0);
				String pseudo;
				String mdp;
				switch (c){
				case 'o' : out.println("Entrer votre pseudo");
							pseudo = in.readLine();
							out.println("Veuillez saisir votre mot de passe: ");
							out.flush();
							mdp = in.readLine();
							if (PersonneBD.verifCompte(pseudo,mdp)){
								out.println("Vous etes bien connecté ! appuyer sur entrer pour continuer");
								out.flush();
								String tmp = in.readLine();
								System.err.println(pseudo + " est connecté sur le serveur");
								new ServiceRéservation(client).lancer();
								connecte = true;
								} else {
									out.println("Erreur pour cette combinaison pseudo/mot de passe");
									String tmp = in.readLine();
								}
							break;
				case 'n': out.println("Nom:");
							String nom = in.readLine();
							out.println("Prenom: ");
							out.flush();
							String prenom = in.readLine();
							out.println("Pseudo: ");
							out.flush();
							pseudo = in.readLine();
							out.println("Mot de passe: ");
							out.flush();
							mdp = in.readLine();
							out.println("Departement: ");
							out.flush();
							String departement = in.readLine();
							PersonneBD.sauverEnBasePersonne(nom, prenom, pseudo, mdp, departement);
							out.println("Vous êtes bien enregistré, veuillez vous connecter!");
							String tmp = in.readLine();
							
				// fonction qui ajoute les bails a la base de données
							break;
				default : out.println("Veuillez répondre par 'o' ou 'n'");
				}
			}
			
			
		} catch (IOException e){
			
		}
		
	} 
	
	public void lancer(){
		(new Thread(this)).start();	
	}
	
	

}
