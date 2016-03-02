package serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import personne.Personne;

public class ServiceConnexion implements Runnable{
	
	private Socket client;
	private List<Personne> lesInscrits;
	private boolean connecte = false;
	
	
	public ServiceConnexion(Socket c){
		lesInscrits = new ArrayList<Personne>();
		client = c;
		init();
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (!connecte) { 
				out.println("Bonjour et bienvenue sur Roomster, l'application de réservation de salle pour les Hipsters. ## Êtes-vous deja inscrit (o/n):");
				out.flush();
				char c = in.readLine().charAt(0);
				if (c == 'o'){
					out.println("Entrer votre pseudo");
					String pseudo = in.readLine();
					out.println("Veuillez saisir votre mot de passe: ");
					out.flush();
					String mdp = in.readLine();
					/*if (rechercheInscrits(pseudo,mdp)){
						out.println("Vous etes bien connecté ! appuyer sur entrer pour continuer");
						out.flush();
						String tmp = in.readLine();
						System.err.println(pseudo + " est connecté sur le serveur");
						new ServiceRéservation(client).lancer();
						connecte = true;
						*/
						
					} else {
						out.println("Erreur pour cette combinaison pseudo/mot de passe");
						String tmp = in.readLine();
					}
				} else {
					out.println("Entrer votre pseudo");
					String pseudo = in.readLine();
					out.println("Veuillez saisir votre mot de passe: ");
					out.flush();
					String mdp = in.readLine();
					// fonction qui ajoute les bails a la base de données
				}
			}	
		} catch (IOException e){
			
		}
		
	} 
	
	public void init(){
		File f = new File("log.txt");
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()){				
				String line = sc.nextLine();
				Scanner s = new Scanner(line);
				s.useDelimiter(";");
				while(s.hasNext()){
					String pseudo = s.next();
					String mdp = s.next();
					Personne p = new Personne(pseudo, mdp);
					lesInscrits.add(p);
				}
				
			}
			
		} catch (FileNotFoundException e){
			System.out.println("nom de fichier incorrect");
		}
	}
	
	public boolean rechercheInscrits(String pseudo, String mdp){
		Personne p = new Personne(pseudo, mdp);
		for (int i = 0; i<lesInscrits.size(); ++i){
			if (lesInscrits.get(i).getPseudo().equals(p.getPseudo()) && lesInscrits.get(i).getMdp().equals(p.getMdp())){
				return true;
			}
		}
		return false;
	}
	
	
	public void lancer(){
		(new Thread(this)).start();	
	}
	
	

}
